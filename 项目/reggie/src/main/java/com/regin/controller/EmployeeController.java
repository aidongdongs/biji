package com.regin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.regin.common.R;
import com.regin.entity.Employee;
import com.regin.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.LocaleData;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录方法
     * @param request 用于讲员工登录的信息存入session
     * @param employee 前端传递过来的对象数据
     * @return 返回通用结果集 R
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //    1 - 讲页面提交的密码password进行md5密码加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2:- 根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<Employee>();
        //封装查询条件
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        //查询条件放入方法
         employeeService.getOne(queryWrapper);
         //查询结果对象
         Employee  emp = employeeService.getOne(queryWrapper);

         //3 - 如果没有查询到则返回登录失败结果
         //判断用户名是否存在，如果不存在，返回结果集，
        if (emp==null){
            return R.error("登录失败");
        }
        //4 -  密码对比，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }
        //5 - 查看员工状态，如果为已禁用状态，则返回员工已禁用结果 0禁用  1可用
        if (emp.getStatus()==0){
            return  R.error("账号已禁用");
        }
        //6  - 登录成功，将员工id存入session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        //查询的结果集放入
        return R.success(emp);
    }

    /**
     * 员工退出
     * @param request 获取session 清楚session中的数据
     * @return 返回R结果集
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        // 清除session中的数据
        request.getSession().removeAttribute("employee");
        return  R.success("退出成功");
    }

    /**
     * 新增员工共
     * @param employee
     * @return R结果集
     */
    @PostMapping
    public R<String> save(@RequestBody Employee employee,HttpServletRequest request){
        //设置默认的初始密码
       employee.setPassword( DigestUtils.md5DigestAsHex("123456".getBytes()));
//       employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
        //获取登录的用户id
        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateUser(empId);
//        employee.setCreateUser(empId);
        employeeService.save(employee);
        System.out.println("新增成功");
        return  R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page ,int pageSize,String name){
        log.info(page+""+pageSize+""+name);
        //分页构造器
        Page pageinfo = new Page(page,pageSize);
        //分页构造条件
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        if (name!=null){
            queryWrapper.like(Employee::getName,name);
        }
        //添加一个排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageinfo,queryWrapper);
        return R.success(pageinfo);
    }

    /**
     * 根据员工id修改员工信息
     * @param employee 前端传递过来的前端对象数据
     * @return R结果集
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
     Long empId = (Long) request.getSession().getAttribute("employee");
     employee.setUpdateTime(LocalDateTime.now());
     employee.setUpdateUser(empId);
     employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    /**
     * 根据员工id查询员工信息
     * @param id 员工id
     * @return 员工信息
     */
    @GetMapping("/{id}")
    public R<Employee> queryByIdEmployee(@PathVariable Long id){
        Employee byId = employeeService.getById(id);
        return R.success(byId);
    }
}
