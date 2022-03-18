package drhiromu.anythingmarketmainservice.market.service;

import drhiromu.anythingmarketmainservice.market.payload.PurchaseAction;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private static final String PRICE_SERVICE_NAME_SUFFIX = "PriceService";

    @Autowired
    private BeanFactory beanFactory;

    public String purchase(PurchaseAction purchaseAction) {
        PriceService priceService = beanFactory.getBean(getPriceServiceBeanName(purchaseAction.getRegionName()),
                PriceService.class);

        return priceService.calculatePrice(purchaseAction.getItemId(), purchaseAction.getRegionName());
    }

    private String getPriceServiceBeanName(String regionName){
        return regionName + PRICE_SERVICE_NAME_SUFFIX;
    }
}
