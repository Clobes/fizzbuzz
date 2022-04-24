package com.intraway.dto;

import com.intraway.util.EResponseCode;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDTO extends AbstractResponseDTO {

   public String code;
   public String description;
   public String list ;

   public ResponseDTO(final String code,
         final String description, final String list) {

      this.code = code;
      this.description = description;
      this.list = list;
   }
}
