package com.show.feign;


import com.show.pojo.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "stock-service", path = "/stock")
public interface StockServiceFeign {

    @RequestMapping("/insert")
    public String insert (Stock stock);
}
