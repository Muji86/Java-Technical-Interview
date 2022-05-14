package com.surecloud.javatechnicalinterview.service;

import com.surecloud.javatechnicalinterview.mapper.ResultMapper;
import com.surecloud.javatechnicalinterview.model.ResultRequest;
import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.repository.ResultEntity;
import com.surecloud.javatechnicalinterview.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.surecloud.javatechnicalinterview.mapper.ResultMapper.mapEntityToResult;

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

    //TODO: Find alternative instead of throwing exception
    public ResultResponse getResultById(String id) throws Exception {
        return resultRepository
                .findById(UUID.fromString(id))
                .map(ResultMapper::mapEntityToResult)
                .orElseThrow(() -> new Exception("Result not found!"));
    }

    //TODO: How to ensure that UUID is unique? Theoretically, it can already occur in the table...
    public ResultResponse createResult(ResultRequest request) {
        ResultEntity entity = new ResultEntity(
                UUID.randomUUID(),
                request.getName(),
                request.getScore(),
                LocalDate.now()
        );
        ResultEntity savedEntity = resultRepository.save(entity);
        return mapEntityToResult(savedEntity);
    }
}
