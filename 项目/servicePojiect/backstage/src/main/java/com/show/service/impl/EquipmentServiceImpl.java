package com.show.service.impl;

import com.show.mapper.EquipmentMapper;
import com.show.pojo.Equipment;
import com.show.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentMapper mapper;
    @Override
    public List<Equipment> getAll() {
        return mapper.getAll();
    }


}
