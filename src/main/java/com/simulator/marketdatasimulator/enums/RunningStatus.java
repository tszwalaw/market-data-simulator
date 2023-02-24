package com.simulator.marketdatasimulator.enums;
public enum RunningStatus {
    RUNNING("Running"),
    STOPPED("Stopped");

    private final String status;

    RunningStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}