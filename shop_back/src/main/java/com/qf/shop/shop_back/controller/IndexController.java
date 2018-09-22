package com.qf.shop.shop_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/topage/{pagename}")
    public String toPage(@PathVariable("pagename")String pagename){

        return pagename;
    }
}
