package com.surecloud.javatechnicalinterview.validations;

import java.util.UUID;

public class ResultValidation {

    //TODO: Poor implementation with try/catch. Find a better way!
    public static boolean isUUID(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
