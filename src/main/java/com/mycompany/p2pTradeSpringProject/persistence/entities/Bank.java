package com.mycompany.p2pTradeSpringProject.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank", uniqueConstraints = {
        @UniqueConstraint(name = "name_UNIQUE", columnNames = {"name"})
})
@NamedQueries({
        @NamedQuery(name = "Bank.findAll", query = "select b from Bank b", lockMode = LockModeType.READ),
        @NamedQuery(name = "Bank.countBy", query = "select count(b) from Bank b"),
        @NamedQuery(name = "Bank.existsBy", query = "select (count(b) > 0) from Bank b"),
        @NamedQuery(name = "Bank.deleteBy", query = "delete from Bank b"),
        @NamedQuery(name = "Bank.updateNameBy", query = "update Bank b set b.name = :name")
})
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Bank bank = (Bank) o;
        return getId() != null && Objects.equals(getId(), bank.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}