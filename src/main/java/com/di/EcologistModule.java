package com.di;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;  // Додаємо цей імпорт

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class EcologistModule extends AbstractModule {
    @Override
    protected void configure() {
        // Впровадження класів для еколога та датчиків
        bind(Sensor.class).to(AirSensor.class);  // Використовуємо AirSensor
        bind(String.class).annotatedWith(Names.named("Ecologist Name"))
            .toInstance("Dr. Jane Doe");
    }

    @Provides
    @Singleton
    public Connection provideDatabaseConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:ecology.db");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }
}
