package com.surecloud.javatechnicalinterview.controller;

import com.surecloud.javatechnicalinterview.model.ResultRequest;
import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResultResponse> getResultById(@PathVariable String id)  {
        return resultService.getResultById(id);
    }

    @PostMapping("/results")
    public ResponseEntity<ResultResponse> createResult(@RequestBody ResultRequest request) {
        return resultService.createResult(request);
    }
}
