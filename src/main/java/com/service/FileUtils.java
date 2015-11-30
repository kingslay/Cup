package com.service;

import com.config.WebConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FileUtils {

    public static String saveFile(MultipartFile file, String filePath) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                File localFile = new File(WebConfig.Resource + filePath);
                if (!localFile.exists()) {
                    localFile.getParentFile().mkdirs();
                    localFile.createNewFile();
                }
                // 转存文件
                file.transferTo(localFile);
                return filePath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String updateProfile(MultipartFile file, String name) {
        return FileUtils.saveFile(file, "/images/uploads/" + name);
    }
}
