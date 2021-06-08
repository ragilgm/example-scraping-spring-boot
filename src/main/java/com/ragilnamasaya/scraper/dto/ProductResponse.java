package com.ragilnamasaya.scraper.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductResponse {

    private int id;
    private String imgUrl;
    private String productTitle;
    private String price;


}
