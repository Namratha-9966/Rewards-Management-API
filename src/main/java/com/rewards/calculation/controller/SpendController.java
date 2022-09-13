package com.rewards.calculation.controller;

import com.rewards.calculation.domain.Spend;
import com.rewards.calculation.service.SpendService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spends")
@AllArgsConstructor
public class SpendController {
    private SpendService purchaseService;

    @PostMapping
    public ResponseEntity<Spend> recordPurchase(@RequestBody Spend spend) {
        return new ResponseEntity<>(this.purchaseService.savePurchase(spend), HttpStatus.CREATED);
    }


}
