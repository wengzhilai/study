package com.files.up.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取配置文件file的配置
 */
@ConfigurationProperties(prefix = "file")
@Data
public class FileProperties {
    /**
     * 读取file.upload-dir的属性
     */
    public String uploadDir;

    /**
     * 用户头像
     */
    public String userIcon;
}