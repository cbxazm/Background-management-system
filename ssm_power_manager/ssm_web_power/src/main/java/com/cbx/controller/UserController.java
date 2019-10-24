package com.cbx.controller;

import com.cbx.domain.UserInfo;
import com.cbx.service.IUserService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 处理user操作的
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
              ModelAndView modelAndView=new ModelAndView();
        List<UserInfo> userList = iUserService.findAll();
         modelAndView.addObject("userList",userList);
           modelAndView.setViewName("user-list");
        return modelAndView;
    }
    //添加用户
    @RequestMapping("/save")
    public String save(UserInfo userInfo){
        iUserService.save(userInfo);
        return "redirect:findAll";
    }
    //用户详情(根据Id 来查询用户的信息)
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(value = "id") Integer id){
        ModelAndView  modelAndView=new ModelAndView();
        UserInfo userInfo=iUserService.findById(id);
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

}
