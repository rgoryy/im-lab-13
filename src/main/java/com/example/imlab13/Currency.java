package com.example.imlab13;

import java.util.Random;

public class Currency {

    private final Random random = new Random();

    private Double price;

    private Integer day;

    public Currency(double price) {
        this.price = price;
        day = 0;
    }

    public double getPrice() {
        return price;
    }

    public Integer getDay() {
        return day;
    }

    private double drift = 0.2;
    private double volatility = 0.3;
    private double dt = 0.1;

    public void updatePriceForNextDay() {
        day++;
        if (price != null) {
            double tempGenNorm = generateNormalRandom();
            price = price * Math.exp((drift - Math.pow(volatility, 2.0) / 2.0) * dt + Math.sqrt(volatility) * Math.sqrt(dt) * tempGenNorm);
        }
    }

    private double generateNormalRandom() {
        double u1 = random.nextDouble();
        double u2 = random.nextDouble();
        return Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);
    }
}
