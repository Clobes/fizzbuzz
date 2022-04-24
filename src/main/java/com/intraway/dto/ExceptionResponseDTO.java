package com.intraway.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponseDTO extends AbstractResponseDTO {

   private int status;
   private String error;
   private String exception;
   private String message;
   private String path;

   public ExceptionResponseDTO(final int status, final String error,
         final String exception, final String message, final String path) {

      this.status = status;
      this.error = error;
      this.exception = exception;
      this.message = message;
      this.path = path;
   }
}
