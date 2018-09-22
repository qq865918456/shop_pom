package com.qf.shop.shop_back.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import utils.HttpClientUtil;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    //文件上传的对象
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${image.path}")
    private String path;

    @Reference
    private IGoodsService goodsService;

    @RequestMapping("/queryAll")
    public String queryAll(Model model){
        List<Goods> goods = goodsService.queryAll();
        System.out.println(goods);
        model.addAttribute("goods",goods);
        model.addAttribute("path",path);
        return "goodlist";
    }

    @RequestMapping("/goodsadd")
    public String goodsadd(Goods goods, @RequestParam("file") MultipartFile file,Model model) throws IOException {
        //进行文件上传-fastfds 获取fastfds回写url
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), "JPG", null);
        String path = storePath.getFullPath();

        //将路径存入对象中
        goods.setGimage(path);

        //将添加的goods添加到数据库中
        goods = goodsService.goodsadd(goods);
        HttpClientUtil.sendJsonPost("http://localhost:8082/search/add",new Gson().toJson(goods));
        return "redirect:/goods/queryAll";
    }

    @RequestMapping("/querynew")
    @CrossOrigin
    @ResponseBody
    public List<Goods> querynews(){
        List<Goods> querynew = goodsService.querynew();
        return querynew;
    }
}
