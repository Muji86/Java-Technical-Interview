package com.surecloud.javatechnicalinterview.service;

import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.repository.ResultEntity;
import com.surecloud.javatechnicalinterview.repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ResultServiceTest {

    private static final String ENTITY_NAME = "entityname";
    private static final int SCORE = 200;
    private static final Date DATE = new Date();
    private ResultService testCandidate;
    private ResultRepository resultRepository;

    @BeforeEach
    public void setUp() {
        resultRepository = mock(ResultRepository.class);
        testCandidate = new ResultService(resultRepository);
    }

    @Test
    public void testGetAllResultsWithOneEntity() {
        //given
        ResultEntity entity = new ResultEntity(
                UUID.randomUUID(),
                ENTITY_NAME,
                SCORE,
                DATE
        );
        List<ResultEntity> entities = Arrays.asList(entity);
        given(resultRepository.findAll()).willReturn(entities);

        //when
        List<ResultResponse> result = testCandidate.getAllResults();

        //then
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo(ENTITY_NAME);
        assertThat(result.get(0).getScore()).isEqualTo(SCORE);
        assertThat(result.get(0).getDate_taken()).isEqualTo(DATE);
    }

    @Test
    public void testGetAllResultsWithNoEntity() {
        //given
        List<ResultEntity> entities = new ArrayList<>();
        given(resultRepository.findAll()).willReturn(entities);

        //when
        List<ResultResponse> result = testCandidate.getAllResults();

        //then
        assertThat(result.isEmpty()).isTrue();

    }
}