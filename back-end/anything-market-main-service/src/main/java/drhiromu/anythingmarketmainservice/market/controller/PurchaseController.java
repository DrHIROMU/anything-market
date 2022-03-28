package drhiromu.anythingmarketmainservice.market.controller;

import drhiromu.anythingmarketmainservice.market.model.Item;
import drhiromu.anythingmarketmainservice.market.payload.PurchaseAction;
import drhiromu.anythingmarketmainservice.market.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("purchase-item")
    public String purchaseItem(@RequestBody PurchaseAction purchaseAction){
        return purchaseService.purchase(purchaseAction);
    }

    @PostMapping("test-query")
    public Item testQuery(@RequestBody Long itemId){
        return purchaseService.testQuery(itemId);
    }

    @PostMapping("test-save")
    public String testSave(@RequestBody String str) throws Exception{
        return purchaseService.testSave(str);
    }

    @PostMapping("test-update")
    public Item testUpdate(@RequestBody Item item) throws Exception{
        return purchaseService.testUpdate(item);
    }

    @PostMapping("line-for-item")
    public String lineForItem(@RequestBody String userId){
        return purchaseService.lineForItem(userId);
    }

    @GetMapping("line-information")
    public List<String> getLineInformation(){
        return purchaseService.getLineInformation();
    }
}
