package com.jurisdiction.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jurisdiction.ssm.domain.Product;
import com.jurisdiction.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    //查询所有
    @RequestMapping("findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(productList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    /* public  ModelAndView findAll() throws Exception {
         ModelAndView mv=new ModelAndView();
         List<Product> all = productService.findAll();
         mv.addObject("productList",all);
         mv.setViewName("product-list");
         return mv;
     }*/
    //产品添加
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

    //根据ID查询详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String productId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(productId);
        mv.addObject("product", product);
        mv.setViewName("product-show");
        return mv;
    }

    //产品修改
    @RequestMapping("/updateById.do")
    public ModelAndView updateById(@RequestParam(name = "id") String Id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(Id);
        mv.addObject("product", product);
        mv.setViewName("product-update");
        return mv;
    }

    /**
     * 功能未完成
     */
    @RequestMapping("/update.do")
    public String updateId(@RequestParam(name = "id" , required = true)String product) throws Exception {
        productService.updateById(product);
        return "redirect:findAll.do";
    }

    //产品的删除
    @RequestMapping("/deleteProduct.do")
    public String deleteProduct(@RequestParam(name = "id", required = true) String product) throws Exception {
        productService.deleteProduct(product);
        return "redirect:findAll.do";
    }
}
