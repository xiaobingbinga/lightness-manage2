package com.xuetang9.qingying.web;

import com.xuetang9.qingying.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/16 11:42
 * @copyright 老九学堂
 */
@RestController
@RequestMapping("/api/upload/images")
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/brand")
    public String uploadBrandImage(@RequestParam("image") MultipartFile multipartFile){
        return imageUploadService.uploadBrandImage(multipartFile);
    }
}
