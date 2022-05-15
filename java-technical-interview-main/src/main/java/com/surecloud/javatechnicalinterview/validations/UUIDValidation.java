package com.surecloud.javatechnicalinterview.validations;

import java.util.regex.Pattern;

/**
 * Validation using RegEx
 * @see <a href="https://www.code4copy.com/java/validate-uuid-string-java/">How to validate UUID String in Java</a>
 */
public class UUIDValidation {


    private final static Pattern UUID_REGEX_PATTERN =
            Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");
    public static boolean isUUID(String str) {
        return null != str && UUID_REGEX_PATTERN.matcher(str).matches();
    }
}
