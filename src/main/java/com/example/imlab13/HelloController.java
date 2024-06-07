package com.example.imlab13;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    @FXML
    private TextField numberOfCurrencies;

    @FXML
    private LineChart<Integer, Double> lineChart;

    private Timer timer;

    private final ArrayList<Currency> currencies = new ArrayList<>();

    public HelloController() {
    }

    @FXML
    protected void onStartStopButtonClick() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
            return;
        }

        lineChart.getData().clear();

        timer = new Timer();

        currencies.clear();

        int size = Integer.parseInt(numberOfCurrencies.getText());

        ArrayList<XYChart.Series<Integer, Double>> currencySeries = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            currencies.add(new Currency(new Random().nextDouble(1, 2)));
            currencySeries.add(new XYChart.Series<>());
            currencySeries.get(i).getData().add(new XYChart.Data<>(
                    currencies.get(i).getDay(),
                    currencies.get(i).getPrice()
            ));
        }


        lineChart.getData().addAll(currencySeries);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> drawNextDay());
            }
        };

        timer.schedule(task, 0, 100);

    }

    public void drawNextDay() {
        for (int i = 0; i < currencies.size(); i++) {
            currencies.get(i).updatePriceForNextDay();
            lineChart.getData().get(i).getData().add(
                    new XYChart.Data<>(currencies.get(i).getDay(), currencies.get(i).getPrice())
            );
        }
    }
}