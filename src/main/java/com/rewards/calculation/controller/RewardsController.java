package com.rewards.calculation.controller;

import com.rewards.calculation.domain.Reward;
import com.rewards.calculation.service.RewardsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewards")
@AllArgsConstructor
public class RewardsController {
    private RewardsService rewardsService;

    @GetMapping("/{customerId}")
    public Reward getCustomerRewards(@PathVariable("customerId") Integer customerId) throws Exception {
        return  this.rewardsService.calculateRewards(customerId);
    }
}

