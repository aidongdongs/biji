package cn.itcast.hotel;

import cn.itcast.hotel.mapper.HotelMapper;
import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.service.IHotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HotelDemoApplicationTests {

    @Autowired
    IHotelService iHotelService;

    @Test
    void contextLoads() {
        List<Hotel> hotels = iHotelService.list();
        System.out.println(hotels);
    }

}
