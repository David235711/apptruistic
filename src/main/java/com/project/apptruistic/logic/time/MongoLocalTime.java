package com.project.apptruistic.logic.time;

import java.time.LocalTime;

public class MongoLocalTime implements Comparable<MongoLocalTime> {
    private LocalTime localTime;

    private MongoLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public static MongoLocalTime of(LocalTime localTime) {
        return new MongoLocalTime(localTime);
    }

    public static MongoLocalTime of(int hour, int minute) {
        return new MongoLocalTime(LocalTime.of(hour, minute));
    }

    public MongoLocalTime(String time) {
        this.localTime = LocalTime.parse(time);
    }

    public int getHour() {
        return this.localTime.getHour();
    }

    public int getMinute() {
        return this.localTime.getMinute();
    }

    public LocalTime toLocalTime() {
        return this.localTime;
    }

    @Override
    public int compareTo(MongoLocalTime other) {
        return this.localTime.compareTo(other.localTime);
    }

    @Override
    public String toString() {
        return this.localTime.toString();
    }
}
