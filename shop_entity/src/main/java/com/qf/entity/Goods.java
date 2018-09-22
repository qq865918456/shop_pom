package com.qf.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Goods implements Serializable {
    private Integer id;
    private String title;
    private String ginfo;
    private double gcount;
    private Integer tid;
    private double allprice;
    private double price;
    private String gimage;
}
