package drhiromu.anythingmarketmainservice.market.service.impl;

import drhiromu.anythingmarketmainservice.market.service.PriceService;
import org.springframework.stereotype.Service;

@Service("EUPriceService")
public class EUPriceService implements PriceService {
    @Override
    public String calculatePrice(Long itemId, String regionName){
        return "Purchasing item: "+itemId+" with 80% off in region "+regionName+".";
    }
}
