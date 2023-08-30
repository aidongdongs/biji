package com.regin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.regin.common.CustomException;
import com.regin.entity.*;
import com.regin.mapper.OrdersMapper;
import com.regin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    OrderDetailService orderDetailService;

    /**
     * 订单处理逻辑
     * @param orders
     */
    @Transactional
    @Override
    public void submit(Orders orders) {
        //1:获取当前用户id
         Long userId = (Long) session.getAttribute("user");
        //2:查询当前用户的购物车数据
        LambdaQueryWrapper<ShoppingCart>queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(queryWrapper);
        if (shoppingCartList==null||shoppingCartList.size()==0){
            new CustomException("购物为空不能下单");
        }
        //补齐订单数据
        //查询用户数据
        User user = userService.getById(userId);

        //查询地址数据
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (addressBook==null){
            throw  new CustomException("地址信息是空的，无法下单");
        }
        //开始补全数据
        Long orderId = IdWorker.getId();

        AtomicInteger amount   = new AtomicInteger(0);
        //计算总金额
        List<OrderDetail> detailList =  shoppingCartList.stream().map((itms)->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(itms.getNumber());
            orderDetail.setDishFlavor(itms.getDishFlavor());
            orderDetail.setSetmealId(itms.getSetmealId());
            orderDetail.setName(itms.getName());
            orderDetail.setImage(itms.getImage());
            orderDetail.setAmount(itms.getAmount());
            amount.addAndGet(itms.getAmount().multiply(new BigDecimal(itms.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());


        //3:向订单表插入一条数据
        orders.setNumber(String.valueOf(orderId));
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setUserId(userId);
        orders.setId(orderId);
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress(addressBook.getProvinceName()==null?"":addressBook.getProvinceName()+
                        addressBook.getCityName()==null?"": addressBook.getCityName()+
                        addressBook.getDetail()==null?"": addressBook.getDetail()
                );
        orders.setAmount(new BigDecimal(amount.get()));
        this.save(orders);
        //4:向订单关系表插入多条数据
        orderDetailService.saveBatch(detailList);
        //5:删除购物车表数据u
        this.shoppingCartService.remove(queryWrapper);

    }
}
