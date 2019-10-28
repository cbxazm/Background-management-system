package com.cbx.controller;

import com.cbx.domain.SysLog;
import com.cbx.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService iSysLogService;
     @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(value = "page",required = true,defaultValue = "1") Integer page,@RequestParam(value = "size",required = true,defaultValue = "4") Integer size){
         ModelAndView modelAndView=new ModelAndView();
         List<SysLog> sysLogs = iSysLogService.findAll(page, size);
         modelAndView.addObject("sysLogs",sysLogs);
         modelAndView.setViewName("syslog-list");
         return modelAndView;
     }
}
