package com.files.up.controller;



import com.files.up.dto.UploadFileResponse;
import com.files.up.exception.FileException;
import com.files.up.service.FileService;
import com.wzl.commons.model.ResultObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Expression;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("file")
@CrossOrigin
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    public UploadFileResponse uploadSingleFile(MultipartFile file, HttpServletRequest request){
        /**
         * 获取上传文件路径类型
         */
        String pathType= request.getHeader("pathType");
        /**
         * 保存文件
         */
        String fileDownloadUri = fileService.storeFile(file,pathType);
        Enumeration<String> helist=request.getHeaderNames();
        UploadFileResponse reObj=new UploadFileResponse();
        reObj.fileDownloadUri=fileDownloadUri;
        reObj.fileType= file.getContentType();
        reObj.size=file.getSize();
        reObj.fileName=reObj.fileDownloadUri.substring(reObj.fileDownloadUri.lastIndexOf("/")+1);
        reObj.filePath=reObj.fileDownloadUri.substring(0,reObj.fileDownloadUri.lastIndexOf("/")+1);
        return reObj;
    }

    @PostMapping("/uploadFile")
    public ResultObj<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        ResultObj<UploadFileResponse> reObj = new ResultObj<>(true);
        try {
            reObj.data = uploadSingleFile(file, request);
        } catch (FileException e) {
            reObj.success = false;
            reObj.msg = e.getMessage();
        } catch (Exception e) {
            reObj.success = false;
            reObj.msg = e.getMessage();
        }
        return reObj;
    }




    @PostMapping("/uploadMultipleFiles")
    public ResultObj<UploadFileResponse> uploadMultipleFiles(@RequestParam(value="files",required=false) MultipartFile[] files,HttpServletRequest request) {
        ResultObj<UploadFileResponse> reObj= new ResultObj<>(true);

        try {
            reObj.dataList= Arrays.stream(files)
                    .map(x->uploadSingleFile(x,request))
                    .collect(Collectors.toList());
        } catch (FileException e) {
            reObj.success = false;
            reObj.msg = e.getMessage();
        } catch (Exception e) {
            reObj.success = false;
            reObj.msg = e.getMessage();
        }
        return reObj;
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,String path, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName, path);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}