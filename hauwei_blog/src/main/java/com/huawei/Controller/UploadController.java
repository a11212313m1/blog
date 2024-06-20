package com.huawei.Controller;


import com.huawei.domain.ResponseResult;
import com.huawei.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;
    @PostMapping("/upload")
    public ResponseResult uploadImage(MultipartFile img){
        return uploadService.uploadImg(img);
    }












}
