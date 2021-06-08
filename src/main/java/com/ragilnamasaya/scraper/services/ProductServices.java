package com.ragilnamasaya.scraper.services;

import com.ragilnamasaya.scraper.entity.Product;
import com.ragilnamasaya.scraper.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public void scrapeProduct() throws Exception {
        // Scraping data from tokopedia
        final String url = "https://www.tokopedia.com/brewoodmerch";
        try {
            final Document document = Jsoup.connect(url).get();

            // looping element
            log.info("scraping product");
            AtomicInteger success= new AtomicInteger();
            AtomicInteger failed = new AtomicInteger();
            document.select(".css-1sn1xa2").forEach(a -> {
                if(!a.text().equals("")){
                    final String productUrl = a.select("a").attr("href");
                    final String productTitle = a.select(".css-18c4yhp").text();
                    final String productPrice = a.select(".css-rhd610").text();

                    Product product = Product.builder()
                            .imgUrl(productUrl)
                            .productTitle(productTitle)
                            .price(productPrice)
                            .build();

                    log.info("saving product {} to database",product);

                    // saving element to db
                    productRepository.save(product);

                    success.addAndGet(1);

                }else{

                    failed.addAndGet(1);

                }

                log.info("total success : "+success);
                log.info("total failed : "+failed);

            });

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
