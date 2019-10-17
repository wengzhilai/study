package com.files.up.service;

import com.files.up.exception.FileException;
import com.files.up.property.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final Path fileStorageLocation;
    private final FileProperties fileProperties;

    @Autowired
    public FileService(FileProperties fileProperties) {

        this.fileProperties=fileProperties;
        /**
         * 获取文件的绝对路径
         */
        this.fileStorageLocation = Paths.get(fileProperties.uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
            Files.createDirectories(Paths.get(fileProperties.userIcon).toAbsolutePath().normalize());
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public String storeFile(MultipartFile file,String pathType) {
        if(StringUtils.isEmpty(pathType)){
            throw new FileException("路径类型[pathType]不可为空" );
        }
        String rePath;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String relativePath;
        switch (pathType) {
            case "userIcon":
                relativePath=this.fileProperties.userIcon;
                break;
            default:
                throw new FileException("路径类型不可用" + pathType);
        }
        rePath=relativePath+fileName;
        try {
            // Copy file to the target location (Replacing existing file with the same name)
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Path targetLocation = Paths.get(relativePath).toAbsolutePath().normalize().resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return rePath;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * 加载文件
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadFileAsResource(String fileName,String relativePath) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();

            if(!StringUtils.isEmpty(relativePath)){
                filePath =  Paths.get(relativePath).toAbsolutePath().normalize().resolve(fileName);
            }
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }

}
