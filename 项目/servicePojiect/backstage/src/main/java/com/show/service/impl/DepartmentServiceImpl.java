package com.show.service.impl;

import com.show.mapper.DepartmentMapper;
import com.show.pojo.Department;
import com.show.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper mapper;

    @Override
    public List<Department> getAll() {
        return mapper.getAll();
    }
}
