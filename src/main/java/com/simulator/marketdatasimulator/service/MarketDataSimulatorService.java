package com.simulator.marketdatasimulator.service;

public interface  MarketDataSimulatorService {
    void startMarketDataSimulation();
    void stopMarketDataSimulation();
    String getRunningStatus();
}
