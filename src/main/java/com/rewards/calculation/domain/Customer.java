package com.rewards.calculation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="Customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Integer id;

    private String name;

    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    Set<Spend> spend;

}
