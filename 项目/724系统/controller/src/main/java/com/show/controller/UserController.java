package com.show.controller;

import com.mysql.cj.Session;
import com.show.pojo.User;
import com.show.vo.UserVo;
import com.show.service.IRoldService;
import com.show.service.ISupplierService;
import com.show.service.IUserService;
import com.show.utill.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/sys/user")
@Slf4j
public class UserController {
    /**
     * 登录方法
     * @param account
     * @param password
     */
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoldService roldService;

    @Autowired
    private ISupplierService supplierService;

    /**
     * 登录
     * @param account
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(String account , String password, HttpSession session){
        log.info(account,password);
        ModelAndView modelAndView = new ModelAndView();
        UserVo login = userService.login(account, password);
        if (login!=null){
            session.setAttribute("userId",login.getId());
            session.setAttribute("realName",login.getRealName());
            //登录成功
            modelAndView.setViewName("/WEB-INF/jsp/frame.jsp");
            modelAndView.addObject("realName",login.getRealName());
            return modelAndView;
        }else {
            //此处逻辑是登录失败
            modelAndView.addObject("error","账号or密码错误");
            modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
            return modelAndView;
        }

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        session.removeAttribute("userId");
        session.removeAttribute("realName");
        modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
        return modelAndView;

    }

    @GetMapping("/list")
    public ModelAndView list(Integer pageIndex ,Integer queryRoleId,String queryRealName){
        ModelAndView modelAndView = new ModelAndView();
        //添加用户角色数据
        modelAndView.addObject("roleList",roldService.queryAll());

        //用户分页
        PageUtils pages = userService.pages(pageIndex==null?0:pageIndex, 3, queryRoleId, queryRealName);
        modelAndView.addObject("userList",pages.getList());
        pages.setShowNumber(pageIndex);
        addModel(modelAndView,pages);
        modelAndView.setViewName("/WEB-INF/jsp/sysUser/list.jsp?pageIndex=1");
        return modelAndView;
    }



    /**
     * 跳转到修改用户信息
     * @return
     */
    @GetMapping("/toUpdate")
    public ModelAndView toUpdate( String uid){
        ModelAndView modelAndView  = new ModelAndView();
        User user = userService.queryById(uid);
        modelAndView.addObject("sysUser",user);
//        modelAndView.addObject("roldList",roldService.queryAll());
        modelAndView.setViewName("/WEB-INF/jsp/sysUser/update.jsp");
        return modelAndView;
    }

    /**
     * 修改
     * @param user
     * @param response
     * @throws IOException
     */
    @PostMapping("/update")
    public void update(UserVo user,HttpServletResponse response,HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        Object userId = request.getSession().getAttribute("userId");

        if ( userService.update(user,userId.toString())==1){
            response.sendRedirect("http://localhost:8080/controller_war/sys/user/list");
        }else{
            response.sendRedirect("http://localhost:8080/controller_war/sys/user/toUpdate?uid="+user.getId());
        }
    }

    /**
     * 删除用户
     * @param id
     * @param response
     * @return
     */
    @ResponseBody
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable("id") String id,HttpServletResponse response){
        Result result = new Result();
        if (userService.delete(id)==1){
            result.setDelResult("true");
         return result;
        }else {
            result.setDelResult("false");
         return  result;
        }

    }

    /**
     * 跳转增加
     * @return
     */
    @GetMapping("/toAdd")
    public ModelAndView toAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/jsp/sysUser/add.jsp");
        modelAndView.addObject("",roldService.queryAll());
        return modelAndView;
    }
    @GetMapping("/userExist")
    public Result userExit(String account){
        Result result = new Result();
        Integer integer = userService.userExit(account);
        result.setExist(integer);
        return result;
    }

    /**
     * 跳转展示页面
     * @return
     */
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        UserVo userVo = userService.showUser(id);
        Date birthday = StrToDate(userVo.getBirthday());
        modelAndView.addObject("sysUser",userVo);
        modelAndView.addObject("birthday",birthday);
        modelAndView.setViewName("/WEB-INF/jsp/sysUser/view.jsp");
        return modelAndView;
    }

    @PostMapping("/add")
    public void add(UserVo userVo,HttpSession session,HttpServletResponse response){
        Object userId = session.getAttribute("userId");
        System.out.println(userId+"-----------");
        Integer add = userService.add(userVo,userId.toString());
        if (add==1){
            //增加成 跳转视图
            try {
                response.sendRedirect("http://localhost:8080/controller_war/sys/user/list");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 跳转修改密码
     * @return
     */
    @GetMapping("/toUpdatePwd")
    public ModelAndView toUpdatePwd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/jsp/sysUser/updatePassword.jsp");
        return modelAndView;
    }

    /**
     * 验证old密码是否正确
     * @param oldPassword
     * @param session
     * @return
     */
    @PostMapping("/checkOldPwd")
    public Result checkOldPwd(String oldPassword,HttpSession session){
        String id = session.getAttribute("userId").toString();
        System.out.println(oldPassword+"----");
        Result result = new Result();
        result.setResult(userService.checkOldPwd(oldPassword,id));
        return result;
    }

    @PostMapping("/savePassword")
    public ModelAndView savePassword(String newPassword,HttpSession session ){
        ModelAndView modelAndView = new ModelAndView();
        String id = session.getAttribute("userId").toString();
        if ( userService.updatePassword(newPassword, id)){
            //修改成功
            modelAndView.addObject("error","密码修改成功，请重新登录");
            modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
        }
        return modelAndView;

    }

    /**
     * 往model中添加数据
     * @param modelAndViewName
     * @param pages
     */
    public void addModel(ModelAndView modelAndViewName , PageUtils pages){
        Integer showNumber = pages.getShowNumber();
        if(showNumber==null){
            showNumber=1;
        }else{
            if (pages.getList().size()==0){
                showNumber=0;
            }
        }
        modelAndViewName.addObject("supplierList",pages.getList());
        modelAndViewName.addObject("totalCount",pages.getTotalNum());
        modelAndViewName.addObject("currentPageNo",showNumber);
        modelAndViewName.addObject("totalPageCount",pages.getTotalPage());
        modelAndViewName.setViewName("/WEB-INF/jsp/supplier/list.jsp");

    }


    /**
     * 渲染supplier数据放入
     * @param modelAndViewName
     */
    public void addModel(ModelAndView modelAndViewName){
        PageUtils pages = supplierService.pages(0, 3,null,null);
        modelAndViewName.addObject("supplierList",pages.getList());
        modelAndViewName.addObject("totalCount",pages.getTotalNum());
        modelAndViewName.addObject("currentPageNo",pages.getCurrentPage());
        modelAndViewName.addObject("totalPageCount",pages.getTotalPage());
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
