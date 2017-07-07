package com.ashan.hakka.ucenter.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/27
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
