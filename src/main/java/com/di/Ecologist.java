package com.di;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Ecologist {
    private String name;
    private Sensor sensor;

    // Закоментуємо конструктор
    // @Inject
    // public Ecologist(@Named("Ecologist Name") String name, Sensor sensor) {
    //     this.name = name;
    //     this.sensor = sensor;
    // }

    // Додаємо методи для впровадження залежностей
    @Inject
    public void setName(@Named("Ecologist Name") String name) {
        this.name = name;
    }

    @Inject
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public void analyze() {
        sensor.analyzeData();  // аналіз даних
        
        // Створення звіту
        String report = "Ecologist report generated.";
        System.out.println(report);
        
        // Надсилання звіту
        sendReport(report);
        
        // Вжиття дій
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
