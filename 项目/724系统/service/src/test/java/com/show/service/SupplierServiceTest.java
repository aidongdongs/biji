package com.show.service;

import com.show.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class SupplierServiceTest {
    @Autowired
    private ISupplierService supplierService;

    @Test
    public void page(){
        supplierService.pages(0,10,null,null);
    }



}
