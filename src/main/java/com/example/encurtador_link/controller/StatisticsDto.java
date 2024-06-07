package com.example.encurtador_link.controller;

public class StatisticsDto {

    private int accessTotalCount;

    private double acessDailyAverage;

    public StatisticsDto(int accessTotalCount, double acessDailyAverage) {
        this.accessTotalCount = accessTotalCount;
        this.acessDailyAverage = acessDailyAverage;
    }

    public StatisticsDto() {
    }

    public int getAccessTotalCount() {
        return accessTotalCount;
    }

    public void setAccessTotalCount(int accessTotalCount) {
        this.accessTotalCount = accessTotalCount;
    }

    public double getAcessDailyAverage() {
        return acessDailyAverage;
    }

    public void setAcessDailyAverage(double acessDailyAverage) {
        this.acessDailyAverage = acessDailyAverage;
    }
}
