package com.surecloud.javatechnicalinterview.service;

import com.surecloud.javatechnicalinterview.mapper.ResultMapper;
import com.surecloud.javatechnicalinterview.model.ResultRequest;
import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.repository.ResultEntity;
import com.surecloud.javatechnicalinterview.repository.ResultRepository;
import com.surecloud.javatechnicalinterview.validations.UUIDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //TODO: Not clear if there is for example only a typo in the UUID
    //or if it's just not present in the database.
    // Alternative????
    public ResponseEntity<ResultResponse> getResultById(String id) {
        return UUIDValidation.isUUID(id)
                ? resultRepository
                    .findById(UUID.fromString(id))
                    .map(ResultMapper::mapEntityToResult)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build())
                : ResponseEntity.notFound().build();
    }


    //TODO: How to ensure that UUID is unique?
    // Theoretically, it can already occur in the table...
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
