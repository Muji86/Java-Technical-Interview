package com.surecloud.javatechnicalinterview.service;

import com.surecloud.javatechnicalinterview.mapper.ResultMapper;
import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private ResultRepository resultRepository;

    @Autowired
    public ResultService(ResultRepository repository) {
        this.resultRepository = repository;
    }

    public List<ResultResponse> getAllResults() {
        return resultRepository //
                .findAll() //
                .stream() //
                .map(ResultMapper::mapEntityToResult) //
                .collect(Collectors.toList());
    }
}
