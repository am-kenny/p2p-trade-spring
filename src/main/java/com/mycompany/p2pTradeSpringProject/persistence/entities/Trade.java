package com.mycompany.p2pTradeSpringProject.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade", indexes = {
        @Index(name = "fk_trade_initiator_idx", columnList = "initiator_user_id"),
        @Index(name = "fk_trade_responder_idx", columnList = "responder_user_id"),
        @Index(name = "fk_trade_currency_idx", columnList = "trade_currency_id"),
        @Index(name = "fk_trade_exchange_currency_idx", columnList = "exchange_currency_id")
})
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private User initiatorUser;

    @ManyToOne(fetch = FetchType.EAGER)
    private User responderUser;

    @Column(name = "is_seller", nullable = false)
    private Boolean isSeller;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Currency tradeCurrency;

    @Column(name = "trade_currency_amount", nullable = false)
    private Float tradeCurrencyAmount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "exchange_currency_id", nullable = false)
    private Currency exchangeCurrency;

    @Column(name = "exchange_rate", nullable = false)
    private Float exchangeRate;

    @ColumnDefault("'open'")
    @Lob
    @Column(name = "status", insertable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ColumnDefault("0")
    @Column(name = "is_confirmed_by_initiator", insertable = false, nullable = false)
    private Boolean isConfirmedByInitiator;

    @ColumnDefault("0")
    @Column(name = "is_confirmed_by_responder", insertable = false, nullable = false)
    private Boolean isConfirmedByResponder;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", insertable = false, nullable = false)
    private Instant createdAt;

    @Column(name = "replied_at")
    private Instant repliedAt;

    @Column(name = "closed_at")
    private Instant closedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trade")
    private Set<TradeFeedback> tradeFeedbacks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trade")
    private Set<TradeMessage> tradeMessages;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Trade trade = (Trade) o;
        return getId() != null && Objects.equals(getId(), trade.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}