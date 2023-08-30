package cn.itcast.hotel.controller;

import cn.itcast.hotel.pojo.PageResult;
import cn.itcast.hotel.pojo.RequestParams;
import cn.itcast.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @PostMapping("/list")
    public PageResult search ( @RequestBody RequestParams requestParams){
        System.out.println(requestParams);
        return  hotelService.search(requestParams);
    }

    @PostMapping("/filters")
    public PageResult filter (@RequestBody RequestParams requestParams){

        System.out.println(requestParams);
        return null;
    }
}
