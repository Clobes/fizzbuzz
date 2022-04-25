package com.intraway.exceptions;

import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
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

   @ExceptionHandler(value = NotFound.class)
   public ResponseEntity<Object> handlerNotFoundException(final NotFound ex, final WebRequest request){
      log.error(ex.getLocalizedMessage());

      HttpStatus notFound = HttpStatus.NOT_FOUND;

      return createResponse(ex, notFound, request);
   }

   @Override
   protected ResponseEntity<Object> handleNoHandlerFoundException(
         NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

      final ExceptionResponseDTO response = ExceptionResponseDTO
            .builder()
            .timestamp(new Date().getTime())
            .status(status.value())
            .error(status.getReasonPhrase())
            .exception(ex.getClass().getName())
            .message(Constants.API_PATH_NOT_FOUND)
            .path(getPath(request))
            .build();

      return handleExceptionInternal(ex, response, headers, status, request);
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
            .timestamp(new Date().getTime())
            .status(httpStatus.value())
            .error(httpStatus.getReasonPhrase())
            .exception(exception.getClass().getName())
            .message( exception.getMessage())
            .path(getPath(request))
            .build();

      return new ResponseEntity<>(response, httpStatus);
   }

}
