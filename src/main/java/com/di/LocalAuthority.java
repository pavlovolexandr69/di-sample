package com.di;

import java.util.List;

import com.google.inject.Inject;

public class LocalAuthority {
    private List<String> actionsTaken;

    @Inject
    public void setActionsTaken(List<String> actionsTaken) {
        this.actionsTaken = actionsTaken;
    }

    public void takeAction(String report) {
        actionsTaken.add(report);
        System.out.println("Actions taken: " + actionsTaken);
    }
}
