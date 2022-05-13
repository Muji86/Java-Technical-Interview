package com.surecloud.javatechnicalinterview.controller;

import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ResultController {
    private ResultRepository resultRepository;

    @Autowired
    public ResultController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @GetMapping("/results")
    public List<ResultResponse> getAllResults() {
        return resultRepository //
                .findAll() //
                .stream() //
                .map(entity -> new ResultResponse(
                        entity.getId().toString(),
                        entity.getName(),
                        entity.getScore(),
                        entity.getDate_taken()
                )).collect(Collectors.toList());
    }
}
