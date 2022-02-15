package reckoning.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import reckoning.OperationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity for operation journal.
 */
@Entity
@Table(name = "journals")
@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation")
    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;

    @Column(name = "initial_amount")
    private BigDecimal initialAmount;

    @Column(name = "final_amount")
    private BigDecimal finalAmount;

    @Column(name = "operation_time")
    private LocalDateTime operationTime;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
