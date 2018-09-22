package com.qf.shop.shop_search.controller;

import com.qf.entity.Goods;
import com.qf.entity.SolrPage;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SolrController {

    //操作solr主对象
    @Autowired
    private SolrClient solrClient;

    @RequestMapping("/add")
    @ResponseBody
    public Boolean solrAdd(@RequestBody Goods goods){
        //新建索引库引入对象
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        //存储字段
        solrInputDocument.addField("id",goods.getId());
        solrInputDocument.addField("gtitle",goods.getTitle());
        solrInputDocument.addField("gimages",goods.getGimage());
        solrInputDocument.addField("ginfo",goods.getGinfo());
        solrInputDocument.addField("gprice",goods.getPrice());

        try {
            //将准备好的字段添加到索引库中
            solrClient.add(solrInputDocument);
            //提交
            solrClient.commit();
            return true;

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String solrQuery(String keyword, SolrPage<Goods> solrPage, Model model){
        System.out.println("关键字"+keyword);

        SolrQuery solrQuery = new SolrQuery();

        //设置搜索的关键词
        //如果搜索的关键词为空,既搜索为全部内容
        if(keyword==null||keyword.trim().equals("")){
            solrQuery.setQuery("*:*");
        }else {
            solrQuery.setQuery("goods_info:"+keyword);
        }

        //设置搜索的高亮
        solrQuery.setHighlight(true);
        //设置关键词的前部分和后部分
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");

        //需要设置高亮的字段
        solrQuery.addHighlightField("gtitle");

        //设置分页
        solrQuery.setStart((solrPage.getPage()-1)*solrPage.getPageSize());
        solrQuery.setRows(solrPage.getPageSize());

        List<Goods> list = new ArrayList<>();

        QueryResponse queryResponse = null;
        try {
             queryResponse = solrClient.query(solrQuery);

            eldname:[高亮的内容,.....]}}    //获得高亮的结果
            //返回值Map<String, Map<String, List<String>>>
            //{id:{fi
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            //将结果循环出来

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
