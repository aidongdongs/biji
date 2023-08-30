package com.show.mapper;

import com.show.config.SpringConfig;
import com.show.pojo.Supplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class SupplierMapperTest {

    @Autowired
    private SupplierMapper mapper;

    @Test
    public void  testCount(){
        System.out.println(mapper.count(null,null));
    }

    @Test
    public void testPages(){
        System.out.println(mapper.pages( 0, 10,null,null));
    }

    @Test
    public void testDelete(){
        Integer delete = mapper.delete("15");
        System.out.println(delete);
    }

    @Test
    public void testList(){
        List<Supplier> list = mapper.list();
        System.out.println(list);
    }

    @Test
    public void testQueryById(){
        Supplier supplier = mapper.queryById("1");
        System.out.println(supplier);
    }

    @Test
    public void testUpdateById(){
       Supplier supplier = new Supplier();
        supplier.setId(1l).setSupCode("BJ_GYS0010");
        Integer integer = mapper.updateById(supplier);
        System.out.println(integer);
    }

    @Test
    public void testAdd(){
        Supplier supplier = new Supplier();
        supplier.setSupCode("bgcs-a").setSupName("asdasd").setSupDesc("asdasdasd")
                .setSupContact("asdasd").setSupPhone("123123123").setSupAddress("撒打算大厦阿大撒撒")
                .setSupFax("1230-321-123").setCreatedTime(new Date()).setCreatedUserId(1l)
                .setUpdatedTime(new Date()).setUpdatedUserId(1l);
        System.out.println(mapper.add(supplier));
    }
}
