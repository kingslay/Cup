package com.lhl.usersystem.action;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;import java.util.ArrayList;
import java.util.List;import java.util.Map;
import java.util.UUID;
public class FileUtils {
    private  static  String filePath;
    private  static ArrayList<String> filePaths=new ArrayList<>();
    public static  boolean saveFile(HttpServletRequest request,MultipartFile file){
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String localFilePath=null;filePath= "/uploads/"+UUID.randomUUID()+"_"+System.currentTimeMillis()+".png";
                // 文件保存路径
                localFilePath =request.getSession().getServletContext().getRealPath("/")+filePath; System.out.println("filepath="+filePath);
                // 转存文件
                file.transferTo(new File(localFilePath));
                setFilePath(filePath); return true;
            } catch (Exception e) {  e.printStackTrace(); }
        }
        return false;
    }
    public static boolean saveFiles(HttpServletRequest request,Map<String,MultipartFile> files){
        for (int i=0;i<files.size();i++){
            //有一张图片没上传成功，则return false
            MultipartFile multipartFile=files.get("file"+i);
            if (saveFile(request,multipartFile)){
                filePaths.add(getFilePath());
            }else{
                return false;
            }
        }
        return  true;
    }

    public  static String getFilePath(){
        return  filePath ;
    }
    public static ArrayList<String> getFilePaths(){
        return  filePaths;
    }
    public static void setFilePath(String path){
        filePath=path;
    }
}
