package com.tommykhlin.marketservice.order.model;

import com.tommykhlin.marketservice.common.model.BaseInfoEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Setter
@Getter
public class OrderItem extends BaseInfoEntity {
    @ManyToOne
    @JoinColumn(name = "customer_order_id", nullable = false)
    private CustomerOrder customerOrder;

    @OneToOne
    @JoinColumn(name = "commodity_id", referencedColumnName = "id", nullable = false)
    private Commodity commodity;

    private Double orderQuantity;
}
