package com.tommykhlin.marketservice.order.model;

import com.tommykhlin.marketservice.common.model.BaseInfoEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_order")
@Setter
@Getter
public class CustomerOrder extends BaseInfoEntity {
    private String orderNumber;

    @OneToMany(mappedBy = "customerOrder")
    private List<OrderItem> orderItems = new ArrayList<>();
}
