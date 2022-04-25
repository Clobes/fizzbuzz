package com.intraway.util;

import java.util.Date;
import java.util.Optional;

import com.intraway.dto.ResponseDTO;
import com.intraway.entity.Operation;

public class FizzBuzzServiceDataTestUtils {

   public static ResponseDTO getMultiploThe5And3(final EResponseCode code, final String description, final String list){
      return ResponseDTO
            .builder()
            .timestamp(new Date().getTime())
            .description(description)
            .code(EResponseCode.of(code))
            .list(list)
            .build();
   }

   public static Operation getOperation(final EResponseCode code, final String description, final String list){
      return Operation
            .builder()
            .id(10L)
            .timeStamp(new Date().getTime())
            .description(description)
            .code(EResponseCode.of(code))
            .result(list)
            .build();
   }

}
