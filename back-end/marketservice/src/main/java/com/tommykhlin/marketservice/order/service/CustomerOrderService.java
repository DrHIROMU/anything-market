package com.tommykhlin.marketservice.order.service;

import com.tommykhlin.marketservice.order.model.CustomerOrder;
import com.tommykhlin.marketservice.order.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.Base64;

@Service
public class CustomerOrderService {
    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    @Transactional(rollbackFor = Exception.class)
    public void makeOrder(CustomerOrder customerOrder)throws Exception{
        customerOrderRepository.save(customerOrder);
        redisTemplate.opsForHash().put("H1", "V1", toString(customerOrder));
    }

    public CustomerOrder getOrder(Long id){
        CustomerOrder order = null;
        try{
            order = (CustomerOrder) fromString(redisTemplate.opsForHash()
                            .get("H1", "V1")
                            .toString());
        }catch (Exception e){

        }
        return customerOrderRepository.findById(id).orElse(null);
    }

    /** Read the object from Base64 string. */
    private static Object fromString( String s ) throws IOException,
            ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    private static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public void confirmOrder(){
    }
}
