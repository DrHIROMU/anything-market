package com.tommykhlin.marketservice.common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseInfoEntity extends BaseEntity {
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_by")
    private String modifyBy;

    @Column(name = "modify_date")
    private Date modifyDate;

    public BaseInfoEntity(){
        Date date = new Date();
        this.createDate = date;
        this.modifyDate = date;
    }
}
