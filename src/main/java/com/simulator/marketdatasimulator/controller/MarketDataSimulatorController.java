package com.simulator.marketdatasimulator.controller;

import com.simulator.marketdatasimulator.service.MarketDataSimulatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketDataSimulatorController {
    private final MarketDataSimulatorService marketDataService;

    public MarketDataSimulatorController(MarketDataSimulatorService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @PostMapping("/startMarketData")
    public void startMarketData() {
        marketDataService.startMarketDataSimulation();
    }

    @PostMapping("/stopMarketData")
    public void stopMarketData() {
        marketDataService.stopMarketDataSimulation();
    }

    @GetMapping("/marketDataRunningStatus")
    public String marketDataRunningStatus() {
        return marketDataService.getRunningStatus();
    }
}
