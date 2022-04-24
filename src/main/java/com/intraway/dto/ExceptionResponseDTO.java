package com.intraway.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponseDTO {

   private long timestamp;
   private int status;
   private String error;
   private String exception;
   private String message;
   private String path;

}
