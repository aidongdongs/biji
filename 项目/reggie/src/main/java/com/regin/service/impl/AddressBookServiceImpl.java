package com.regin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.regin.entity.AddressBook;
import com.regin.mapper.AddressBookMapper;
import com.regin.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
