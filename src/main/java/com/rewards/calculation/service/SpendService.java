package com.rewards.calculation.service;

import com.rewards.calculation.domain.Spend;
import com.rewards.calculation.repository.SpendRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpendService {
    private SpendRepository spendRepository;


    public Spend savePurchase(Spend purchase) {
        return this.spendRepository.save(purchase);
    }

    public List<Spend> getAllPurchases() {
        return this.spendRepository.findAll();
    }

    public Optional<Spend> retrievePurchases(Integer purchaseId) {
        return this.spendRepository.findById(purchaseId);
    }

    public List<Spend>  retrievePurchasesByCustomerId(Integer customerId){
        return this.spendRepository.findAllByCustomerId(customerId);
    }

    public List<Spend>  getPurchasesOfCustomer(Integer customerId, Integer minusMonths){
        ZonedDateTime startDate = ZonedDateTime.now().minusMonths(minusMonths);
        return this.spendRepository.findAllByCustomerIdAndDateGreaterThan(customerId, Date.from(startDate.toInstant()));
    }


}
