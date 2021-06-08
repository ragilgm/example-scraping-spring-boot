package com.ragilnamasaya.scraper.controller;

import com.ragilnamasaya.scraper.entity.Product;
import com.ragilnamasaya.scraper.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;


    @GetMapping
    public List<Product> getProducts(){
        return productServices.getProducts();
    }

    @PostMapping("/scrape")
    public void scrapeProduct() throws Exception {
        productServices.scrapeProduct();
    }

}
