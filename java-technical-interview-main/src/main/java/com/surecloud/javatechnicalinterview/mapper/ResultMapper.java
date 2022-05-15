package com.surecloud.javatechnicalinterview.mapper;

import com.surecloud.javatechnicalinterview.model.ResultResponse;
import com.surecloud.javatechnicalinterview.repository.ResultEntity;


//TODO: Write tests! Avoid getting NPE from getId. Use of Optional?
//Or use a proper validation before?
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
