package com.intraway.converter;

import java.util.Objects;

import com.intraway.dto.ResponseDTO;
import com.intraway.entity.Operation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OperationConverter extends AbstractConverter<Operation, ResponseDTO>{

   @Override
   public ResponseDTO fromEntity(Operation entity) {
      return null;
   }

   @Override
   public Operation fromDTO(ResponseDTO dto) {
      if(Objects.isNull(dto)) {
         return null;
      }
      return Operation
            .builder()
            .result(dto.list)
            .timeStamp(dto.getTimestamp())
            .build();
   }
}
