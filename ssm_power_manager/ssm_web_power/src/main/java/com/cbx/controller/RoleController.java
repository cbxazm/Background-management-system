package com.cbx.controller;

import com.cbx.domain.Role;
import com.cbx.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
  private IRoleService iRoleService;
    @RequestMapping("/findAll")
 public ModelAndView findAll(){
       ModelAndView modelAndView =new ModelAndView();
        List<Role> roles = iRoleService.findAll();
        modelAndView.addObject("roleList",roles);
        modelAndView.setViewName("role-list");
        return modelAndView;
 }
// 添加角色
    @RequestMapping("/save")
    public String save(Role role){
        iRoleService.save(role);
        return "redirect:findAll";
    }
}
