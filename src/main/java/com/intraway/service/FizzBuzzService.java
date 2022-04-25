package com.intraway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intraway.converter.OperationConverter;
import com.intraway.dto.ResponseDTO;
import com.intraway.entity.Operation;
import com.intraway.exceptions.BadRequest;
import com.intraway.exceptions.NotFound;
import com.intraway.handler.FizzBuzzHandler;
import com.intraway.repository.IOperationsRepository;
import com.intraway.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FizzBuzzService {

   private OperationConverter operationConverter;

   private FizzBuzzHandler fizzBuzzHandler;

   private IOperationsRepository operationsRepository;

   @Autowired
   public FizzBuzzService(OperationConverter operationConverter, FizzBuzzHandler fizzBuzzHandler, IOperationsRepository iOperationsRepository) {
      this.operationConverter = operationConverter;
      this.fizzBuzzHandler = fizzBuzzHandler;
      this.operationsRepository = iOperationsRepository;
   }

   //Get FizzBuzz
   public ResponseEntity<ResponseDTO> doFizzBuzz(final int min, final int max) {
      if(min >= max) {
         throw new BadRequest(Constants.ERROR_MIN_GE_MAX);
      }

      final ResponseDTO response = fizzBuzzHandler.getFizzBuzz(min, max);
      final Operation operation = operationConverter.fromDTO(response);
      operationsRepository.save(operation);

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   //Get all operations
   public ResponseEntity<List<ResponseDTO>> getOperations() {
      final List<Operation> operations = operationsRepository.findAll();
      final List<ResponseDTO> response = operationConverter.fromEntity(operations);

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   public ResponseEntity<ResponseDTO> getOperationById(final Long id) {
      final Operation operation = operationsRepository.findOperationById(id).orElseThrow(()-> new NotFound(String.format(Constants.OPERATION_NOT_FOUND, id)));
      final ResponseDTO response = operationConverter.fromEntity(operation);

      return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
