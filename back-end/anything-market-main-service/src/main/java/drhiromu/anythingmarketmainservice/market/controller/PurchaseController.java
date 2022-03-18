package drhiromu.anythingmarketmainservice.market.controller;

import drhiromu.anythingmarketmainservice.market.payload.PurchaseAction;
import drhiromu.anythingmarketmainservice.market.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("purchase-item")
    public String purchaseItem(@RequestBody PurchaseAction purchaseAction){
        return purchaseService.purchase(purchaseAction);
    }
}
