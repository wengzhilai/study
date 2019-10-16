package com.files.up;

import com.files.up.property.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileProperties.class
})
public class FilesupApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesupApplication.class, args);
    }

}

