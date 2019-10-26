package com.cbx.controller;

import com.cbx.domain.Permission;
import com.cbx.domain.Role;
import com.cbx.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    //根据roleId查询角色信息以及可以添加的权限信息
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "roleId",required = true) Integer roleId){
        ModelAndView modelAndView=new ModelAndView();
       //根据roleId查询role
        Role role=iRoleService.findById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermissions=iRoleService.findOtherPermissions(roleId);
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",otherPermissions);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }
    //真正给角色添加权限的方法
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) Integer roleId,@RequestParam(name = "ids",required = true) Integer[] permissionIds){
            iRoleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll";
    }
    //查询角色的详情
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
            List<Permission> permissions=iRoleService.findPermissionById(id);
            modelAndView.addObject("permission",permissions);
            modelAndView.setViewName("permission-show");
        return modelAndView;
    }
}
