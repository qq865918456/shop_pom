package com.qf.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString
public class SolrPage<T> {
    private Integer page = 1;   //当前页
    private Integer pageSize = 4;   //当前页显示条数
    private Integer pageCount;  //总页数
    private Integer pageSum;    //总条数
    private List<T> datas;  //数据
}
