package com.surecloud.javatechnicalinterview.validations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UUIDValidationTest {

    @Test
    void testThatIsUUIDWithValidUUIDReturnTrue() {
        //given
        String id = UUID.randomUUID().toString();

        //when
        boolean result = UUIDValidation.isUUID(id);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void testThatIsUUIDWithInvalidUUIDReturnFalse() {
        //given
        String id = "1-1-1-1-1";

        //when
        boolean result = UUIDValidation.isUUID(id);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void testThatIsUUIDWithNullReturnFalse() {
        //given
        String id = null;

        //when
        boolean result = UUIDValidation.isUUID(id);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void testThatIsUUIDWithEmptyStringReturnFalse() {
        //given
        String id = "";

        //when
        boolean result = UUIDValidation.isUUID(id);

        //then
        assertThat(result).isFalse();
    }
}