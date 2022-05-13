package com.surecloud.javatechnicalinterview.mapper;

import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.repository.ResultEntity;

public class ResultMapper {

    public static ResultResponse mapEntityToResult(ResultEntity entity) {
        return new ResultResponse(
                entity.getId().toString(),
                entity.getName(),
                entity.getScore(),
                entity.getDate_taken()
        );
    }
}
