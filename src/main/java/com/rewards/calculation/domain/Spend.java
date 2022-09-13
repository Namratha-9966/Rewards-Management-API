package com.rewards.calculation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@Table(name = "purchases")
@NoArgsConstructor
@AllArgsConstructor
public class Spend {

    @Id
    @GeneratedValue
    private Integer id;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    private Double amount;

    @Column(name = "customer_id")
    private Integer customerId;


    @PrePersist
    public void setTransactionDate() {
        if (this.transactionDate == null)
            this.transactionDate = Calendar.getInstance().getTime();
    }


}
