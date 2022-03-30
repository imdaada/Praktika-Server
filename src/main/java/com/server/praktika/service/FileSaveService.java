package com.server.praktika.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileSaveService {
    public static void uploadFile(byte[] file,String filePath,String fileName)throws Exception{
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();

    }

    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String getFileName(String fileOriginName){
        return getUUID() + getSuffix(fileOriginName);
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static List<String> getAllowSuffix() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(".jpg");
        arrayList.add(".jpeg");
        arrayList.add(".doc");
        arrayList.add(".docx");
        arrayList.add(".ppt");
        arrayList.add(".pptx");
        arrayList.add(".png");
        arrayList.add(".zip");
        arrayList.add(".rar");
        return arrayList;
    }

}
