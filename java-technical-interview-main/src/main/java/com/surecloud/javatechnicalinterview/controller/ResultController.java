package com.surecloud.javatechnicalinterview.controller;

import com.surecloud.javatechnicalinterview.model.ResultRequest;
import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResultController {
    private ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/results")
    public List<ResultResponse> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/results/{id}")
    public ResultResponse getResultById(@PathVariable String id) throws Exception {
        return resultService.getResultById(id);
    }

    @PostMapping("/results")
    public ResultResponse createResult(@RequestBody ResultRequest request) {
        return resultService.createResult(request);
    }
}
