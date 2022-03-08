package com.tommykhlin.marketservice.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tommykhlin.marketservice.common.model.BaseInfoEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_item")
@Setter
@Getter
public class OrderItem extends BaseInfoEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "customer_order_id", nullable = false)
    @JsonBackReference
    private CustomerOrder customerOrder;

    @OneToOne
    @JoinColumn(name = "commodity_id", referencedColumnName = "id", nullable = false)
    private Commodity commodity;

//    @Column(name="order_quantity")
    private Double orderQuantity;
}
