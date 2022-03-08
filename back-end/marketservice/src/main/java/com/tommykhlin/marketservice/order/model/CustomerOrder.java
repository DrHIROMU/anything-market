package com.tommykhlin.marketservice.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tommykhlin.marketservice.common.model.BaseInfoEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
public class CustomerOrder extends BaseInfoEntity implements Serializable {
//    @Column(name="order_number")
    private String orderNumber;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnoreProperties({"customerOrder"})
    private List<OrderItem> orderItems = new ArrayList<>();
}
