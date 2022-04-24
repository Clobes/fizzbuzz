package com.intraway.converter;

import java.util.Objects;

import com.intraway.dto.ResponseDTO;
import com.intraway.entity.Operation;
import com.intraway.util.Constants;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class OperationConverter extends AbstractConverter<Operation, ResponseDTO>{

   @Override
   public ResponseDTO fromEntity(Operation entity) {
      if(Objects.isNull(entity)) {
         log.error(Constants.CONVERTER_ERROR);
         throw new IllegalArgumentException(Constants.CONVERTER_ERROR);
      }

      return ResponseDTO
            .builder()
            .timestamp(entity.getTimeStamp())
            .code(entity.getCode())
            .description(entity.getDescription())
            .list(entity.getResult())
            .build();
   }

   @Override
   public Operation fromDTO(ResponseDTO dto) {
      if(Objects.isNull(dto)) {
         log.error(Constants.CONVERTER_ERROR);
         throw new IllegalArgumentException(Constants.CONVERTER_ERROR);
      }
      return Operation
            .builder()
            .result(dto.getList())
            .description(dto.getDescription())
            .timeStamp(dto.getTimestamp())
            .code(dto.getCode())
            .build();
   }
}
