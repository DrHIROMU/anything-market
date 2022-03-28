package drhiromu.anythingmarketmainservice.market.repository;

import drhiromu.anythingmarketmainservice.market.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
