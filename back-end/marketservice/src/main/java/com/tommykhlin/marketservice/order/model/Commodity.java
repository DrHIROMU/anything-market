package com.tommykhlin.marketservice.order.model;

import com.tommykhlin.marketservice.common.model.BaseInfoEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Setter
@Getter
public class Commodity extends BaseInfoEntity implements Serializable {
    private String name;

    private String description;

    private Double price;

    private Double quantity;
}
