package com.di;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Ecologist {
    private final String name;
    private Sensor sensor;
    private LocalAuthority localAuthority;

    @Inject
    public Ecologist(@Named("Ecologist Name") String name, Sensor sensor, LocalAuthority localAuthority) {
        this.name = name;
        this.sensor = sensor;
        this.localAuthority = localAuthority;
    }

    public void analyze() {
        sensor.analyzeData();  // аналіз даних
        
        double pollutionLevel = sensor.getPollutionLevel();
        String location = "Lake 1";
        
        // Надсилаємо дані до органу влади для вжиття заходів
        localAuthority.takeAction(location, pollutionLevel);
        
        // Створення звіту
        String report = "Ecologist report generated.";
        System.out.println(report);
        
        sendReport(report);
        takeAction(report);
    }

    private void sendReport(String report) {
        System.out.println("Sending report...");
    }

    private void takeAction(String report) {
        // Оцінка рівня забруднення та вжиття дій
        double pollutionLevel = sensor.getPollutionLevel();
        System.out.println("Taking action based on report: " + report);
        
        if (pollutionLevel > 30.0) {
            System.out.println("Action taken: Cleaning process initiated.");
        }
    }
}
