package com.tommykhlin.marketservice.order.controller;

import com.tommykhlin.marketservice.order.model.CustomerOrder;
import com.tommykhlin.marketservice.order.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@RequestMapping(value="/api/customer-order")
public class CustomerOrderController {
    @Autowired
    CustomerOrderService customerOrderService;

    private Queue<Long> requestQueue = new ConcurrentLinkedQueue();

    @PostMapping(value = "order")
    public void makeOrder(@RequestBody CustomerOrder customerOrder){
        try{
            customerOrderService.makeOrder(customerOrder);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping(value = "order/{id}")
    public CustomerOrder getOrder(@PathVariable Long id){
        return customerOrderService.getOrder(id);
    }

    @GetMapping(value = "set-queue/{id}")
    public void setQueue(@PathVariable Long id){
        requestQueue.add(id);
    }

    @GetMapping(value = "show")
    public void getOrder(){
        System.out.println(this.requestQueue.size());
//        requestQueue.stream()
//                .forEach(id -> System.out.println(id.toString()));
    }
}
