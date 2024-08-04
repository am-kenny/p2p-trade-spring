package com.mycompany.p2pTradeSpringProject.persistence.repositories.specifications;

import com.mycompany.p2pTradeSpringProject.persistence.entities.Status;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade_;
import org.springframework.data.jpa.domain.Specification;

public class TradeSpecifications {

    public static Specification<Trade> isSeller(Boolean isSeller) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Trade_.IS_SELLER), isSeller); //
    }

    public static Specification<Trade> tradeCurrencyId(Integer tradeCurrencyId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Trade_.TRADE_CURRENCY).get("id"), tradeCurrencyId);
    }

    public static Specification<Trade> exchangeCurrencyId(Integer exchangeCurrencyId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Trade_.EXCHANGE_CURRENCY).get("id"), exchangeCurrencyId);
    }

    public static Specification<Trade> isOpen() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Trade_.STATUS), Status.open);
    }

    public static Specification<Trade> belongsToUser(Integer userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Trade_.INITIATOR_USER).get("id"), userId);
    }

    public static Specification<Trade> repliedByUser(Integer userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Trade_.RESPONDER_USER).get("id"), userId);
    }

}
