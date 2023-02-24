package com.simulator.marketdatasimulator.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
public class LevelOneMarketData {
    private String symbol;
    private Double bid;
    private Double ask;
    private Integer bidSize;
    private Integer askSize;
    private Double lastPrice;
    private Integer volume;
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "LevelOneMarketData{" +
                "symbol='" + symbol + '\'' +
                ", timestamp=" + timestamp +
                ", bidSize=" + bidSize +
                ", bidPrice=" + bid +
                ", askPrice=" + ask +
                ", askSize=" + askSize +
                '}';
    }
}

