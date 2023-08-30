package com.show.controller;


import com.show.mapper.StockMapper;
import com.show.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockMapper stockMapper;

    @RequestMapping("/dome")
    public List<Stock> dome(){
        return stockMapper.list();
    }
    @RequestMapping("/insert")
    public String insert (@RequestBody  Stock stock){
        Integer insert = stockMapper.insert(stock);
        return insert.toString();
    }
}
