package com.mojafirma.cinema.model;

public enum TicketOption {

    NORMAL (1.0),
    STUDENT (0.5),
    KID (0.5),
    BIG_GROUP (0.7);

    private final double factor;

    TicketOption(double factor) {
        this.factor = factor;
    }
}
