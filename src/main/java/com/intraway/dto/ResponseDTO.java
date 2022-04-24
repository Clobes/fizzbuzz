package com.intraway.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDTO {

   private long timestamp;
   public String code;
   public String description;
   public String list ;


}
