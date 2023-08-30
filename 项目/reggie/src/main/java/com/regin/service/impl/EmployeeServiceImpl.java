package com.regin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.regin.entity.Employee;
import com.regin.mapper.EmployeeMapper;
import com.regin.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
