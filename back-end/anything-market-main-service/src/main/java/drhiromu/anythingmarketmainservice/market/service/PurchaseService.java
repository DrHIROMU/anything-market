package drhiromu.anythingmarketmainservice.market.service;

import drhiromu.anythingmarketmainservice.market.model.Item;
import drhiromu.anythingmarketmainservice.market.payload.PurchaseAction;
import drhiromu.anythingmarketmainservice.market.repository.ItemRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;
import java.util.*;

@Service
public class PurchaseService {
    private static final String PRICE_SERVICE_NAME_SUFFIX = "PriceService";

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private List<String> userIds = new ArrayList<>();

    public String purchase(PurchaseAction purchaseAction) {
        PriceService priceService = beanFactory.getBean(getPriceServiceBeanName(purchaseAction.getRegionName()),
                PriceService.class);

        return priceService.calculatePrice(purchaseAction.getItemId(), purchaseAction.getRegionName());
    }

    private String getPriceServiceBeanName(String regionName){
        return regionName + PRICE_SERVICE_NAME_SUFFIX;
    }

    public Item testQuery(Long itemId){
        Item item = itemRepository.findById(itemId).get();

        return item;
    }

    @Transactional(rollbackFor = Exception.class)
    public String testSave(String itemName) throws Exception{
        Item item = new Item();
        item.setName(itemName);
        item.setDescription("Description of "+itemName);
        itemRepository.save(item);
        if(true){
            throw new Exception();
        }

        return itemName;
    }

    @Transactional(rollbackFor = Exception.class)
    public Item testUpdate(Item updateItem) throws Exception{
        Optional<Item> itemOptional = itemRepository.findById(updateItem.getId());
        Item item = null;
        if(itemOptional.isPresent()){
            item = itemOptional.get();
        }

        item.setName(updateItem.getName());
        item.setDescription(updateItem.getDescription());
        itemRepository.save(item);
        if(true){
            throw new Exception();
        }

        return item;
    }

    public String lineForItem(String userId){
        userIds.add(userId);
        return "User: "+userId+" is waiting for event starting.";
    }

    public List<String> getLineInformation(){
        Random random = new Random();

        Set<Integer> indexs = new LinkedHashSet<>();
        List<String> randomUserIds = new ArrayList<>();

        while(indexs.size() < this.userIds.size()){
            Integer randomIndex = random.nextInt(this.userIds.size());
            System.out.println(randomIndex);
            if (!indexs.contains(randomIndex)) {
                randomUserIds.add(this.userIds.get(randomIndex));
                indexs.add(randomIndex);
            }
        }
        return randomUserIds;
    }
}
