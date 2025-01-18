package com.di;

import com.google.inject.Inject;

public class LocalAuthority {
    private final DatabaseService databaseService;

    @Inject
    public LocalAuthority(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void takeAction(String location, double pollutionLevel) {
        // Зберігаємо дані в базу
        databaseService.savePollutionData(location, pollutionLevel);

        System.out.println("Actions taken based on the pollution data at: " + location);
    }
}
