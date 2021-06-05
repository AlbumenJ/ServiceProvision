package com.albumen.web;

import com.albumen.domain.user.LoginDto;
import com.albumen.domain.user.UserService;
import com.albumen.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login")
    public CommonResult<LoginDto> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password) {
        LoginDto login = userService.login(username, password);
        if (login != null) {
            return new CommonResult<LoginDto>().success(login);
        } else {
            return new CommonResult<LoginDto>().fail();
        }
    }
}
