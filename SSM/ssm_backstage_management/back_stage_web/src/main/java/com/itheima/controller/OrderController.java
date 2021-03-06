package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

     /*为分页*/
    /*@RequestMapping("/findAll.do")
    public String findAll(Model model){
        List<Orders> orders = orderService.findAll();
        model.addAttribute("ordersList",orders);
        return "orderList";
    }*/
    /*分页*/
    @RequestMapping("/findAllByPage.do")
    public String findAllByPage(@RequestParam(value = "page",required = true ,defaultValue = "1")Integer page, @RequestParam(value = "size",required = true,defaultValue = "5")Integer size, Model model){
        List<Orders> orders = orderService.findAll(page,size);
        model.addAttribute("ordersList",orders);

        /*PageInfo就是一个分页的bean它里面有很多其他参数比如总页数总条数等等*/
        PageInfo pageInfo = new PageInfo(orders);
        model.addAttribute("pageInfo",pageInfo);
        return "ordersPageList";
    }


    /*
    查询订单详情*/
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(value = "id",required = true) String id){
        ModelAndView modelAndView =new ModelAndView();
       Orders order= orderService.findById(id);
       modelAndView.addObject("orders",order);
       modelAndView.setViewName("orderShow");
        return modelAndView;
    }

}
