package com.hulkhiretech.payments.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;


    /**
     * Converts JSON String to Java Object.
     *
     * @param jsonString JSON String
     * @param clazz      Target Java Class
     * @param <T>        Generic Type
     * @return Converted Object or null if conversion fails
     */
    public <T> T convertJsonToObject(String jsonString, Class<T> clazz) {

        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            log.error("Failed to convert JSON to Object. Target Class: {}", clazz.getName(), e);
            return null;
        }
    }

    /**
     * Converts Java Object to JSON String.
     *
     * @param object Java Object
     * @return JSON String or null if conversion fails
     */
    public String convertObjectToJson(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert Object to JSON. Object Type: {}",
                    object != null ? object.getClass().getName() : "null", e);
            return null;
        }
    }
}