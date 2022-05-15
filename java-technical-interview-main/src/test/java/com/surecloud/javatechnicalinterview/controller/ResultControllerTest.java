package com.surecloud.javatechnicalinterview.controller;

import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.service.ResultService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ResultController.class)
class ResultControllerTest {

    private static final String ENTITY_NAME = "entityname";
    private static final int SCORE = 200;
    private static final LocalDate DATE = LocalDate.now();
    private static final String ID = "1";

    @MockBean
    ResultService resultService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testThatGetReturnAllExamResults() throws Exception {

        //given
        List<ResultResponse> resultResponseList = List.of(new ResultResponse(
                ID,
                ENTITY_NAME,
                SCORE,
                DATE
        ));

        given(resultService.getAllResults()).willReturn(resultResponseList);

        //when and then
        mockMvc.perform(get("/results"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(ID))
                .andExpect(jsonPath("$[0].name").value(ENTITY_NAME))
                .andExpect(jsonPath("$[0].score").value(SCORE))
                .andExpect(jsonPath("$[0].date_taken").value(DATE.toString()));

        verify(resultService).getAllResults();
    }

    @Test
    public void testThatGetByIdWithValidIdReturnExamResult() throws Exception {
        //given
        ResultResponse resultResponse = new ResultResponse(
                ID,
                ENTITY_NAME,
                SCORE,
                DATE
        );

        ResponseEntity<ResultResponse> responseEntity = ResponseEntity.ok(resultResponse);

        given(resultService.getResultById(ID)).willReturn(responseEntity);

        //when and then
        mockMvc.perform(get("/results/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(ENTITY_NAME))
                .andExpect(jsonPath("$.score").value(SCORE))
                .andExpect(jsonPath("$.date_taken").value(DATE.toString()));

        verify(resultService).getResultById(ID);

    }

    @Test
    public void testThatGetByIdWithNonExistingIdReturnStatusCode404() throws Exception {

        //given
        ResponseEntity<ResultResponse> responseEntityWithStatusCode404 = ResponseEntity.notFound().build();
        //Assuming the ID is not found in the database or not even a valid ID
        given(resultService.getResultById(ID)).willReturn(responseEntityWithStatusCode404);

        //when and then
        mockMvc.perform(get("/results/" + ID))
                .andExpect(status().isNotFound());

        verify(resultService).getResultById(ID);
    }

}