package com.cbx.controller;

import com.cbx.domain.Product;
import com.cbx.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = iProductService.findAll();
        modelAndView.addObject("productList",products);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
          //保存添加的信息
    @RequestMapping("/save")
    public String save(Product product){
        iProductService.save(product);
        return "redirect:findAll";
    }
//      编辑订单
    @RequestMapping("/update")
    public  ModelAndView update(@RequestParam(value = "id",required = true) Integer productId){
        Product product =iProductService.findById(productId);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("product",product);
        modelAndView.setViewName("product-update");
        return modelAndView;
    }
    //这里需要修改页面的<td>中加入一个输入框，并且名字改为Product中的类名，这样才能对应数据库的字段
    @RequestMapping("/saveProduct")
    public String saveProduct(@RequestParam(value = "id",required = true) Integer productId,Product product){
        System.out.println(product);
           iProductService.saveProduct(productId);
        return "redirect:findAll";
    }
}
