package ru.kpfu.itis.app.model.enums;

public enum RacquetString {
    BLAST(1000),
    HURRICANE(500),
    TEAM(700);

    int price;

    RacquetString(int price) {
        this.price = price;
    }
}
