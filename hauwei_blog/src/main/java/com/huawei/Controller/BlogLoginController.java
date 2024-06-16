package com.huawei.Controller;


import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.User;
import com.huawei.enums.AppHttpCodeEnum;
import com.huawei.service.BlogLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
       //return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_EXIST);
        ResponseResult login = blogLoginService.login(user);
        return login;
    }
}
