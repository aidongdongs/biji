package com.regin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.regin.common.R;
import com.regin.entity.ShoppingCart;
import com.regin.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 将选中的菜品添加到购物车
     * @param session 需要从session获取用户的id
     * @param shoppingCart 前端传递过来的菜品数据，放入购物车
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(HttpSession session, @RequestBody ShoppingCart shoppingCart){
        //获取用户id
        Long userId = (Long) session.getAttribute("user");
        //设置用户id
      shoppingCart.setUserId(userId);
      //查询添加的菜品是否已经存在购物车

        //创建构造条件
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        //将id 作为天骄
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        //判断是否是菜品
        if (shoppingCart.getDishId()!=null){
            //添加到购物车的是菜品
            queryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getDishId());
        }
        //判断是否是套餐
        if (shoppingCart.getSetmealId()!=null){
            //添加到购物车的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        //查询出结果集
        ShoppingCart one = shoppingCartService.getOne(queryWrapper);
        //判断是否是第一次添加
        if (one!=null){
            //已经存在 是第一添加
            Integer number = one.getNumber();
            //如果已经存在将存在的数据的number++ 就可以了
            number=number+=1;
            one.setNumber(number);
            //将数据修改
            shoppingCartService.updateById(one);
        }else {
            //不是第一次添加
            //如果不存在就添加到数据库  默认number=1
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            one = shoppingCart;
        }
        return R.success(one);
    }

    /**
     * 查询用户当前的购物车数据
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>>list(HttpSession session){
        Long userId  = (Long) session.getAttribute("user");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        queryWrapper.orderByDesc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = this.shoppingCartService.list(queryWrapper);
        return R.success(list);
    }

    @DeleteMapping("/clean")
    public R<String> clear(HttpSession session){
       Long userId = (Long) session.getAttribute("user");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        shoppingCartService.remove(queryWrapper);
        return R.success("删除成功");
    }
    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart,HttpSession session){
        Long dishId = shoppingCart.getDishId();
        Long setmealId = shoppingCart.getSetmealId();
        Long userId = (Long) session.getAttribute("user");
        //将id封装存入对象
        shoppingCart.setUserId(userId);
        //不知道菜品的具体数量，需要根据菜品id查询出来
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        if (dishId!=null){
            queryWrapper.eq(ShoppingCart::getDishId,dishId).eq(ShoppingCart::getUserId,userId);
        }else if (setmealId!=null){
            queryWrapper.eq(ShoppingCart::getSetmealId,setmealId).eq(ShoppingCart::getUserId,userId);
        }
        ShoppingCart one = shoppingCartService.getOne(queryWrapper);
        //判断是否存在菜品
        if (one==null){
            return R.error("还没有加购购物车");
        }

        if (one.getNumber()<=1){
            this.shoppingCartService.removeById(one);
        }
        Integer number = one.getNumber();
        number=number-1;
        one.setNumber(number);
        shoppingCartService.updateById(one);
        return R.success(one);
    }
}
