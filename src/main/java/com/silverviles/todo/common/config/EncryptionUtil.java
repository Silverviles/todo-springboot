package com.silverviles.todo.common.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EncryptionUtil implements AttributeConverter<String, String> {
    // TODO: Implement encryption and decryption logic
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return decrypt(dbData);
    }

    private static String encrypt(String text) {
        return text;
    }

    private static String decrypt(String text) {
        return text;
    }
}
