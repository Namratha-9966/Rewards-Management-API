package com.rewards.calculation.repository;

import com.rewards.calculation.domain.Spend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpendRepository extends JpaRepository<Spend,Integer> {

    List<Spend> findAllByCustomerId(Integer customerId);

    @Query("SELECT p from Spend p where p.customerId =?1  AND p.transactionDate > ?2")
    List<Spend> findAllByCustomerIdAndDateGreaterThan(Integer customerId, Date startOfDate);
}
