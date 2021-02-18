package com.xuetang9.qingying.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/16 11:21
 * @copyright 老九学堂
 */
public interface ImageUploadService {

    String uploadBrandImage(MultipartFile uploadFile);
}
