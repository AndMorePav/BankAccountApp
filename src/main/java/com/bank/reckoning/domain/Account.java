package com.bank.reckoning.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Entity of account.
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account",  cascade = CascadeType.ALL)
    @BatchSize(size=100)
    @EqualsAndHashCode.Exclude
    private List<Journal> accountJournals;
}
