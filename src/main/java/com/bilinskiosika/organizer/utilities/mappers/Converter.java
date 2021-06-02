package com.bilinskiosika.organizer.utilities.mappers;

@FunctionalInterface
public interface Converter<T, F> {
    T convert(F from);
}
