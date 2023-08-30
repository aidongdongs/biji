package com.show.service.impl;

import com.show.controller.ResultJSON;
import com.show.ex.*;
import com.show.mapper.UserMapper;
import com.show.pojo.User;
import com.show.service.UserService;
import com.show.util.GetDateUtil;
import com.show.util.JWTUtil;
import com.show.util.ValidationObjectUtil;
import com.show.vo.LoginVo;
import com.show.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 验证用户唯一
     * @param username 传递过来的用户名
     * @return
     */
    @Override
    public Boolean getByUsername(String username) {
        User byUsername = userMapper.getByUsername(username);
        if (byUsername == null) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param registerVo s
     * @param request 需要用到session中验证码
     * @return
     */
    @Override
    public Integer addUser(RegisterVo registerVo , HttpServletRequest request) {
        //验证传递过来的对象中，是否有属性存在空值，如果有一个属性存在空值，那么会抛出参数异常，会到全局异常处理
        //返回ResultJson给到前端
        ValidationObjectUtil.ValidationObjectIsNull(registerVo);
        //数据为空验证完成

//        //先验证code码，如果code码错误，就直接返回前端
//        Object sessionCode = request.getSession().getAttribute(ValidateCodeUtil.sessionKey);
//        if (!registerVo.getVerificationCode().equals(sessionCode)){
//            //抛出验证码异常错误
//            throw new VerificationCodeException("验证码错误", ResultJSON.VERIFICATION_CODE_MISTAKE_ERR,null);
//        }

        //验证码校验完成
        //开始校验数据 -- 手机号码校验
        //如果校验失败，工具类会自动将抛出异常
        ValidationObjectUtil.ValidationPhone(registerVo.getPhone());
        //手机号码验证完成
        //开始验证用户名是否唯一
        Boolean byUsername = this.getByUsername(registerVo.getUsername());
        if (byUsername){
            //用户名已存在，抛出异常
            throw new UserNameUniqueException("用户名已经已经存在",ResultJSON.VerifyThatTheUserIsUnique_ERR,null);
        }
        //验证身份
        if (!(registerVo.getRold().equals("0")||registerVo.getRold().equals("1"))){
            //传递过来的参数不是数据库设计的参数则抛出异常
            throw new RoldException("身份错误",ResultJSON.REGISTER_ROLD_ERR,null);
        }
        //开始进行数据补全
        User user = new User();
        user.setName(registerVo.getUsername());
        user.setPhone(registerVo.getPhone());
        user.setRole(Integer.valueOf(registerVo.getRold()));
        //密码已经加密无法验证，密码再次进行加密
        //获取盐值
       String stale  = UUID.randomUUID().toString();
       user.setStole(stale);
        //将密码再次加密
       String md5Password = DigestUtils.md5DigestAsHex((stale+registerVo.getMd5Password()+stale).getBytes() );
       user.setPassword(md5Password);
       user.setCreatorTime(GetDateUtil.getDate(new Date()));
       user.setChangeTime(GetDateUtil.getDate(new Date()));
       user.setCreator(registerVo.getUsername());
       user.setChangeCreator(registerVo.getUsername());
       user.setForbidden(0);
       user.setIsDelete(1);
       //获取id
        user.setId(UUID.randomUUID().toString().toUpperCase());

        //数据补全完成，进行数据库操作
        Integer integer = userMapper.addUser(user);
        if (integer != 1) {
            //插入数据失败
            throw new InsertDateException("用户注册时，出现错误",ResultJSON.INSERT_DATA_ERR,null);
        }
        return integer;
    }

    /**
     * 登录功能
     * @param loginVo 前端传递过来的数据
     * @param request 使用session中的验证码
     * @return 数据库是否查找到用户的
     */
    @Override
    public String login(LoginVo loginVo, HttpServletRequest request) {
        //1：验证数据是否是不完整
        ValidationObjectUtil.ValidationObjectIsNull(loginVo);
//        //2：验证验证码是否正确
//        Object sessionCode = request.getSession().getAttribute(ValidateCodeUtil.sessionKey);
//        if(!loginVo.getVerificationCode().equals(sessionCode)){
//            throw new VerificationCodeException("验证错误",ResultJSON.VERIFICATION_CODE_MISTAKE_ERR,null);
//        }
        //3:验证用户名和密码
        //验证根据用户名查找数据库
        User byUsername = userMapper.getByUsername(loginVo.getUser());
        if (byUsername==null) {
            throw new NotFindUserNameException("用户名或密码错误",ResultJSON.USER_PASSWORD_NOT_FIND,null);
        }
        //获取验证加用户输入的密码进行md5加密对比
        String userInputPassword = loginVo.getPassword();
        String stole = byUsername.getStole();
        userInputPassword = DigestUtils.md5DigestAsHex((stole+userInputPassword+stole).getBytes());

        if(!userInputPassword.equals(byUsername.getPassword())){
            //对比失败
            throw new NotFindUserNameException("用户名或者密码错误",ResultJSON.USER_PASSWORD_NOT_FIND,null);
        }
        //4：验证登录身份
        if (!(loginVo.getRold().equals("0")||loginVo.getRold().equals("1"))){
            //传递过来的参数不是数据库设计的参数则抛出异常
            throw new RoldException("身份错误",ResultJSON.REGISTER_ROLD_ERR,null);
        }
        System.out.println(11111);
        //5:验证账号是否被禁用
        if (byUsername.getForbidden()==0){
            throw new UserWasDisabledException("您的账号未启用，请联系管理员",ResultJSON.USER_WAS_DISABLED,null);
        }
        //验证全部完成
        //登录成功，生成登录令牌
        Map map = new HashMap();
        map.put("username",loginVo.getUser());
//        map.put("rold",loginVo.getRold());
         return    JWTUtil.getToken(map).toString().trim();
    }
}
