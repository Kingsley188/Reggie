package com.itheima.reggie.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@ResponseBody
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    //它接收一个SQLIntegrityConstraintViolationException类型的参数
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){

        if(ex.getMessage().contains("Duplicate entry") ){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2];
           return  R.error(msg);
        }

        return R.error("抛出异常");
    }

}
