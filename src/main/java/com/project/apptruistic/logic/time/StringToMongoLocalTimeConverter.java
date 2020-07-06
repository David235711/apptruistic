package com.project.apptruistic.logic.time;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class StringToMongoLocalTimeConverter implements Converter<String, MongoLocalTime> {
    private static final TypeDescriptor SOURCE = TypeDescriptor.valueOf(String.class);
    private static final TypeDescriptor TARGET = TypeDescriptor.valueOf(MongoLocalTime.class);

    @Override
    public MongoLocalTime convert(String source) {
        try {
            return MongoLocalTime.of(LocalTime.parse(source));
        } catch (DateTimeParseException e) {
            throw new ConversionFailedException(SOURCE, TARGET, source, e);
        }
    }
}
