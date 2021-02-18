package com.xuetang9.qingying.service.impl;

import com.xuetang9.qingying.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/16 11:26
 * @copyright 老九学堂
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Value("${upload.image.path}")
    private String brandImagePath;

//    @Value("${upload.image.server}")
//    private String server;

    @Value("${upload.image.server.path}")
    private String imagePath;

    @Override
    public String uploadBrandImage(MultipartFile uploadFile) {
        String imagePath = null;
        // 创建一个文件对象
        File file = new File(brandImagePath,uploadFile.getOriginalFilename());
        try {
            uploadFile.transferTo(file);
//            imagePath = server + this.imagePath + file.getName();
            imagePath = this.imagePath + file.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imagePath;
    }
}
