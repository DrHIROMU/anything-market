package com.tommykhlin.marketservice.order.controller;

import com.tommykhlin.marketservice.order.model.Commodity;
import com.tommykhlin.marketservice.order.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/commodities")
public class CommodityController {
    @Autowired
    CommodityRepository commodityRepository;

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
}
