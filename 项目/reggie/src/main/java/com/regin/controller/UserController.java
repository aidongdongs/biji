package com.regin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.regin.common.R;
import com.regin.entity.User;
import com.regin.service.UserService;
import com.regin.utrils.SMSUtils;
import com.regin.utrils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号码
        String phone = user.getPhone();
        if (!phone.isEmpty()) {
            //生成验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            SMSUtils.sendMessage("瑞吉外卖", "SMS_274535505", phone, code);
            System.out.println("验证码" + code);
            session.setAttribute("code", code);
            return   R.success("手机发送验证码码成功");
        } else {
            return R.error("手机发送验证码失败");
        }
    }

    @PostMapping("/login")
    public R<String> login(@RequestBody Map map, HttpSession session) {
        //获取手机号码
        String phone = (String) map.get("phone");
        //获取验证码
        String code = (String) map.get("code");
        //比对，比较验证码
        if (code==null){
            return R.success("验证码不存在");
        }
        if (session.getAttribute("code")==null){
            return R.success("未生成验证码");
        }
       if (! session.getAttribute("code").equals(code)){
        return R.success("验证码错误，验证失败");
       }
       //验证成功，开始查询是否是新用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
       queryWrapper.eq(User::getPhone,phone);
        User one = userService.getOne(queryWrapper);
        if (one==null){
            //是新用户，进行注册
            User user = new User();
            user.setPhone(phone);
            user.setStatus(1);
            userService.save(user);
            //不存在就再次查询
            LambdaQueryWrapper<User> queryWrapper1 =  new LambdaQueryWrapper<>();
            queryWrapper1.eq(User::getPhone,phone);
            User one1 = userService.getOne(queryWrapper);
            session.setAttribute("user",one1.getId());
        }else if (one!=null){
            //存在直接将id放入
            session.setAttribute("user",one.getId());
        }

        return R.success("登录成功");
    }
}
