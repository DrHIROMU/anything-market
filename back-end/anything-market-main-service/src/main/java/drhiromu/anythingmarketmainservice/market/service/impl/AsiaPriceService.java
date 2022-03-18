package drhiromu.anythingmarketmainservice.market.service.impl;

import drhiromu.anythingmarketmainservice.market.service.PriceService;
import org.springframework.stereotype.Service;

@Service("AsiaPriceService")
public class AsiaPriceService implements PriceService {
    @Override
    public String calculatePrice(Long itemId, String regionName){
        return "Purchasing item: "+itemId+" with 70% off in the region "+regionName+".";
    }
}
