package com.show.service;

import com.show.pojo.Department;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DepartmentService {
    @Transactional
    List<Department> getAll();
}
