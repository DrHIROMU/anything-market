package com.tommykhlin.marketservice.order.controller;

import com.tommykhlin.marketservice.order.model.Commodity;
import com.tommykhlin.marketservice.order.repository.CommodityRepository;
import com.tommykhlin.marketservice.order.service.ThreadTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/commodities")
public class CommodityController {
    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    ThreadPoolTaskExecutor executor;

    @Autowired
    ApplicationContext applicationContext;

    @PostMapping(value = "commodity")
    public Commodity addCommodity(@RequestBody Commodity commodity){
        commodity = commodityRepository.save(commodity);

        return commodity;
    }

    @GetMapping(value = "commodity/{id}")
    public Commodity getCommodity(@PathVariable Long id){
        Commodity commodity = commodityRepository.findById(id).orElse(null);
        return  commodity;
    }

    @PostMapping(value = "thread-test")
    public void threadTest(@RequestBody String msg){
        for(int i=0; i<10; i++){
            executor.execute(applicationContext.getBean(ThreadTask.class, msg+i));
        }
    }
}
