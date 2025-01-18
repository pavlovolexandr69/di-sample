package com.di;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class EcologistModule extends AbstractModule {
    @Override
    protected void configure() {
        // Впровадження Sensor
        bind(Sensor.class).to(AirSensor.class);  // Використовуємо AirSensor
        
        // Впровадження String для ім'я еколога
        bind(String.class).annotatedWith(Names.named("Ecologist Name"))
            .toInstance("Dr. Jane Doe");
    }
}
