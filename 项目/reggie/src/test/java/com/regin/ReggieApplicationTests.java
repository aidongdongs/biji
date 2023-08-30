package com.regin;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.regin.entity.Employee;
import com.regin.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReggieApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private EmployeeService employeeService;
    @Test
    void employeeTest(){

    }
}
