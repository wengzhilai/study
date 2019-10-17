package com.files.up.dto;

import lombok.Data;

@Data
public class UploadFileResponse {
    /**
     * 文件名
     */
    public String fileName;
    /**
     * 保存路径
     */
    public String filePath;
    /**
     * 下载路径
     */
    public String fileDownloadUri;
    /**
     * 文件类型
     */
    public String fileType;
    /**
     * 文件大小
     */
    public long size;
}