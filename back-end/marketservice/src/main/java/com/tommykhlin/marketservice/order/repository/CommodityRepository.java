package com.tommykhlin.marketservice.order.repository;

import com.tommykhlin.marketservice.order.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {
}
