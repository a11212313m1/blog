package com.huawei.Controller;


import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.User;
import com.huawei.enums.AppHttpCodeEnum;
import com.huawei.exception.SystemException;
import com.huawei.service.BlogLoginService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class BlogLoginController {

    private final BlogLoginService blogLoginService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }


        return blogLoginService.login(user);

    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
