package com.surecloud.javatechnicalinterview.service;

import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.repository.ResultEntity;
import com.surecloud.javatechnicalinterview.repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ResultServiceTest {

    private static final String ENTITY_NAME = "entityname";
    private static final int SCORE = 200;
    private static final LocalDate DATE = LocalDate.now();
    private static final UUID ID = UUID.randomUUID();
    private static final String INVALID_ID = "1";
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
                ID,
                ENTITY_NAME,
                SCORE,
                DATE
        );
        List<ResultEntity> entities = List.of(entity);
        given(resultRepository.findAll()).willReturn(entities);

        //when
        List<ResultResponse> result = testCandidate.getAllResults();

        //then
        verify(resultRepository).findAll();
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
        verify(resultRepository).findAll();
        assertThat(result.isEmpty()).isTrue();

    }

    @Test
    public void testThatGetResultByIdWithValidIdReturnExamResult() {
        //given
        Optional<ResultEntity> entity = Optional.of(new ResultEntity(
                ID,
                ENTITY_NAME,
                SCORE,
                DATE
        ));
        HttpStatus statusCodeOk = HttpStatus.valueOf(200);

        given(resultRepository.findById(ID)).willReturn(entity);

        //when
        ResponseEntity<ResultResponse> result = testCandidate.getResultById(ID.toString());

        //then
        verify(resultRepository).findById(ID);
        assertThat(result.getStatusCode()).isEqualTo(statusCodeOk);
        ResultResponse resultBody = result.getBody();
        assertThat(resultBody).isNotNull();
        assertThat(resultBody.getName()).isEqualTo(ENTITY_NAME);
        assertThat(resultBody.getScore()).isEqualTo(SCORE);
        assertThat(resultBody.getDate_taken()).isEqualTo(DATE);
    }

    @Test
    public void testThatGetResultByIdWithInvalidIdReturnStatusCode404() {

        //given

        //Assuming ID is NOT Valid ID
        HttpStatus statusCodeNotFound = HttpStatus.valueOf(404);

        //when
        ResponseEntity<ResultResponse> result = testCandidate.getResultById(INVALID_ID);

        //then
        assertThat(result.getStatusCode()).isEqualTo(statusCodeNotFound);

    }

    @Test
    public void testThatGetResultByIdWithNotExistingIdReturnStatusCode404() {

        //given

        //Assuming ID is NOT Valid ID
        HttpStatus statusCodeNotFound = HttpStatus.valueOf(404);
        Optional<ResultEntity> entity = Optional.empty();
        given(resultRepository.findById(ID)).willReturn(entity);


        //when
        ResponseEntity<ResultResponse> result = testCandidate.getResultById(ID.toString());

        //then
        verify(resultRepository).findById(ID);
        assertThat(result.getStatusCode()).isEqualTo(statusCodeNotFound);

    }

}