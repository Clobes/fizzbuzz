package com.intraway.exceptions;

import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.intraway.dto.ExceptionResponseDTO;
import com.intraway.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(value = Exception.class)
   public ResponseEntity<Object> handlerAll(final Exception ex, final WebRequest request){
      log.error(ex.getLocalizedMessage());

      HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

      return createResponse(ex, internalServerError, request);
   }

   @ExceptionHandler(value = BadRequest.class)
   public ResponseEntity<Object> handlerBadRequestException(final BadRequest ex, final WebRequest request){
      log.error(ex.getLocalizedMessage());

      HttpStatus badRequest = HttpStatus.BAD_REQUEST;

      return createResponse(ex, badRequest, request);
   }

   private String getPath(final WebRequest request) {
      final String uri = request.getDescription(false);

      if(Objects.isNull(uri)) {
         return request.getContextPath();
      }
      String[] path = uri.split(Constants.EQUAL_STRING);

      final String s = path.length > 1 ? path[1] : path[0];

      return s;
   }

   private ResponseEntity<Object> createResponse(final Exception exception, final HttpStatus httpStatus, final WebRequest request) {

      final ExceptionResponseDTO response = ExceptionResponseDTO
            .builder()
            .status(httpStatus.value())
            .error(httpStatus.getReasonPhrase())
            .exception(exception.getClass().getName())
            .message( exception.getMessage())
            .path(getPath(request))
            .build();

      return new ResponseEntity<>(response, httpStatus);
   }
}
