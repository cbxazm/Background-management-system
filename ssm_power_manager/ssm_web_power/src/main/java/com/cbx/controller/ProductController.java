package com.cbx.controller;

import com.cbx.domain.Product;
import com.cbx.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
