package com.albumen.aop;

import com.albumen.result.CommonResult;
import com.albumen.result.ResultConstants;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Albumen
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandlerAdvice {


    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public CommonResult<Void> handlerIllegalStateException(IllegalStateException ex) {
        return new CommonResult<Void>().fail(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public CommonResult<Void> handlerAuthorizationException(AccessDeniedException ex){
        return new CommonResult<Void>().fail("权限不足！", ResultConstants.FORBIDDEN_CODE);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public CommonResult<Void> handlerUnknownAccountException(BadCredentialsException ex){
        return new CommonResult<Void>().fail("用户名或密码错误！", ResultConstants.AUTHENTICATION_FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult<Void> handlerException(Exception e){
        return new CommonResult<Void>().fail(e.getMessage());
    }
}

