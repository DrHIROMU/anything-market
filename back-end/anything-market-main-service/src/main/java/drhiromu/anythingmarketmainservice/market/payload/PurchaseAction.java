package drhiromu.anythingmarketmainservice.market.payload;

import lombok.Data;

@Data
public class PurchaseAction {
    private Long itemId;
    private String regionName;
}
