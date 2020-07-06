package com.project.apptruistic.logic.time;

import org.springframework.core.convert.converter.Converter;

import java.time.format.DateTimeFormatter;

public class MongoLocalTimeToStringConverter implements Converter<MongoLocalTime, String> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public String convert(MongoLocalTime source) {
        return formatter.format(source.toLocalTime());
    }
}
