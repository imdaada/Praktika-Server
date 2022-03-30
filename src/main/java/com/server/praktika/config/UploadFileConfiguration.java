package com.server.praktika.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadFileConfiguration {
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // Устанавливаем лимит размера файла. Превышение страницы настройки вызовет сообщение об исключении.
        factory.setMaxFileSize(DataSize.parse("20MB"));
        // Установить общий размер загружаемого файла
        factory.setMaxRequestSize(DataSize.parse("30MB"));
        return factory.createMultipartConfig();
    }
}
