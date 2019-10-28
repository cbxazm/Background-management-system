package com.cbx.controller;

import com.cbx.domain.Orders;
import com.cbx.service.IOrderService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrderService iOrderService;
   @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
     ModelAndView modelAndView=new ModelAndView();
       List<Orders> orders = iOrderService.findAll(page, size);
//       System.out.println(orders.size());
       //PageInfo就是一个分页的Bean
       PageInfo pageInfo=new PageInfo(orders);
       modelAndView.addObject("pageInfo",pageInfo);
       modelAndView.setViewName("orders-page-list");
       return modelAndView;
   }
     @RequestMapping("/findById")
      public ModelAndView findById(@RequestParam(name = "id",required = true) Integer ordersId) throws Exception {
               ModelAndView modelAndView=new ModelAndView();
               Orders orders=iOrderService.findById(ordersId);
         System.out.println(orders);
         modelAndView.addObject("orders",orders);
         modelAndView.setViewName("orders-show");
               return modelAndView;
     }
}
