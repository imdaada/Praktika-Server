package com.server.praktika.controller;

import com.server.praktika.model.CurrentTask;
import com.server.praktika.model.Task;
import com.server.praktika.model.TaskFile;
import com.server.praktika.model.TaskFileDTO;
import com.server.praktika.repository.TaskFileRepository;
import com.server.praktika.repository.UserRepository;
import com.server.praktika.service.FileSaveService;
import com.server.praktika.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@RestController
@RequestMapping("/file")
public class TaskFileController {
    @Autowired
    TaskFileRepository taskFileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> upload(@RequestParam("name") String name,
                                        @RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String jwt){
        String fileName = FileSaveService.getFileName (file.getOriginalFilename());
        String filePath = "C:\\uploaded\\";
        try {
            if (FileSaveService.getAllowSuffix().contains(FileSaveService.getSuffix(fileName))) {
                FileSaveService.uploadFile(file.getBytes(), filePath, fileName);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TaskFile taskFile = new TaskFile();
        taskFile.setLink(filePath+fileName);
        taskFile.setName(name);
        taskFile.setOwner(userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)));
        return ResponseEntity.ok(taskFileRepository.save(taskFile));
        //return filePath;
    }

    @PostMapping("/setTask")
    public ResponseEntity<?> setTask (@RequestBody TaskFileDTO taskFileDTO, @RequestHeader("Authorization") String jwt) {
        Collection<TaskFile> tasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getFiles();
        for (TaskFile nextTaskFile : tasks) {
            if (taskFileDTO.getId().equals(nextTaskFile.getId())) {
                nextTaskFile.setTaskId(taskFileDTO.getTaskId());
                nextTaskFile.setCurrentTaskId(taskFileDTO.getCurrentTaskId());
                return ResponseEntity.ok(taskFileRepository.save(nextTaskFile));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) throws IOException {
        File file = new File(taskFileRepository.getById(id).getLink());
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);

            /**
             * In a regular HTTP response, the Content-Disposition response header is a
             * header indicating if the content is expected to be displayed inline in the
             * browser, that is, as a Web page or as part of a Web page, or as an
             * attachment, that is downloaded and saved locally.
             *
             */

            /**
             * Here we have mentioned it to show inline
             */
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

            //Here we have mentioned it to show as attachment
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFile (@RequestBody TaskFile taskFile, @RequestHeader("Authorization") String jwt) {
        Collection<TaskFile> tasks = userRepository.findByLogin(jwtUserDetailsService.getLogin(jwt)).getFiles();
        for (TaskFile nextTaskFile : tasks) {
            if (taskFile.getId().equals(nextTaskFile.getId())) {
                File file = new File(nextTaskFile.getLink());
                if (file.delete()) {
                    taskFileRepository.delete(nextTaskFile);
                    return ResponseEntity.ok(new TaskFile());
                }
                else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
