package com.surecloud.javatechnicalinterview.controller;

import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.service.ResultService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

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
    public void testGetById() throws Exception {
        //given
        ResultResponse response = new ResultResponse(
                ID,
                ENTITY_NAME,
                SCORE,
                DATE
        );

        given(resultService.getResultById(ID)).willReturn(response);


        mockMvc.perform(get("/results/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(ENTITY_NAME))
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.score").value(SCORE))
                .andExpect(jsonPath("$.date_taken").value(DATE.toString()));

        verify(resultService).getResultById(ID);

    }

}