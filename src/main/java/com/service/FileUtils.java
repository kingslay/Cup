package com.service;

import com.config.WebMvcConfig;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileUtils {

    public static String saveFile(HttpServletRequest request, MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {

                String name = UUID.randomUUID() + "_" + System.currentTimeMillis() + ".jpg";
                File localFile = new File(WebMvcConfig.getResourcePath()+"/image/uploads/"+name);
                if (!localFile.exists()) {
                    localFile.getParentFile().mkdirs();
                    localFile.createNewFile();
                }
                // 转存文件
                file.transferTo(localFile);
                return "image/uploads/"+name;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<String> saveFiles(HttpServletRequest request, Map<String, MultipartFile> files) {
        List<String> filePaths = new ArrayList<String>();
        for (int i = 0; i < files.size(); i++) {
            //有一张图片没上传成功，则return false
            MultipartFile multipartFile = files.get("file" + i);
            String filePath = saveFile(request, multipartFile);
            if (filePath != null) {
                filePaths.add(filePath);
            } else {
                return null;
            }
        }
        return filePaths;
    }
}
