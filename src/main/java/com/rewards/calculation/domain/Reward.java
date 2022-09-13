package com.rewards.calculation.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class Reward {
    private Integer customerId;
    private String customerName;
    private List<Spend> spends;
    Map<Integer,Integer> monthWiseRewards;
    Integer totalRewards;
}
