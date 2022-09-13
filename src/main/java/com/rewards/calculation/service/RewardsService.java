package com.rewards.calculation.service;

import com.rewards.calculation.domain.Customer;
import com.rewards.calculation.domain.Reward;
import com.rewards.calculation.domain.Spend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class RewardsService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SpendService spendService;

    public Reward calculateRewards(Integer customerId) throws Exception {
        Optional<Customer> optionalCustomer = this.customerService.getCustomer(customerId);
        if (optionalCustomer.isPresent() == false) {
            throw new Exception("Customer Not Found for the customer id:::" + customerId);
        }
        List<Spend> purchaseList = this.spendService.getPurchasesOfCustomer(customerId, 3);
        Map<Integer, Integer> transMap = new HashMap<>();
        return Reward.builder()
                .customerId(optionalCustomer.get().getId())
                .customerName(optionalCustomer.get().getName())
                .spends(purchaseList)
                .monthWiseRewards(transMap)
                .totalRewards(calculateTotalRewards(purchaseList, transMap))
                .build();

    }

    public Integer calculateTotalRewards(List<Spend> purchases, Map<Integer, Integer> transMap) {

        Integer totalRewards = 0;
        for (Spend purchase : purchases) {

            Integer rewardPoints = this.calculateRewards(purchase.getAmount());

            Integer monthsBetween = Math.toIntExact(ChronoUnit.MONTHS.between(
                    LocalDate.parse(purchase.getTransactionDate().toString()),
                    LocalDate.now()));

            Integer existingRewards = transMap.getOrDefault(monthsBetween, 0);
            transMap.put(monthsBetween, existingRewards + rewardPoints);

            totalRewards += rewardPoints;
        }
        return totalRewards;
    }

    private Integer calculateRewards(Double transAMount) {

        Integer rewardPoints = 0;

        if (transAMount > 100) {
            double amountOver100 = transAMount - 100;
            rewardPoints = rewardPoints + (int) Math.round((amountOver100) * 2);
            transAMount = transAMount - amountOver100;
        }

        if (transAMount > 50) {
            rewardPoints = rewardPoints + (int) Math.round((transAMount - 50) * 1);
        }

        return rewardPoints;
    }


}
