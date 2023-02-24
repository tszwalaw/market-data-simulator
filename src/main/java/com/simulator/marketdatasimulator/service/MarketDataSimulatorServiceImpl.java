package com.simulator.marketdatasimulator.service;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.simulator.marketdatasimulator.enums.RunningStatus;
import com.simulator.marketdatasimulator.model.LevelOneMarketData;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MarketDataSimulatorServiceImpl implements MarketDataSimulatorService{
    private final AtomicBoolean running = new AtomicBoolean(false);

    public void startMarketDataSimulation() {
        if (running.compareAndSet(false, true)) {
            new Thread(() -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());
                    InputStream inputStream = getClass().getResourceAsStream("/market_data.txt"); //TODO: Need to change - get it from S3
                    try (JsonParser parser = objectMapper.getFactory().createParser(inputStream)) {
                        while (parser.nextToken() != null && running.get()) {
                            if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
                                LevelOneMarketData marketData = parser.readValueAs(LevelOneMarketData.class);
                                System.out.println(marketData.toString());
                                //TODO: Timer + Push to Kafka
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    running.set(false);
                } finally {
                    running.set(false);
                }
            }).start();
        }
    }

    public void stopMarketDataSimulation() {
        running.set(false);
    }

    public String getRunningStatus(){
        return running.get() ? RunningStatus.RUNNING.getStatus() : RunningStatus.STOPPED.getStatus();
    }

}
