package com.intraway.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intraway.converter.OperationConverter;
import com.intraway.dto.ResponseDTO;
import com.intraway.entity.Operation;
import com.intraway.exceptions.BadRequest;
import com.intraway.repository.IOperationsRepository;
import com.intraway.util.Constants;
import com.intraway.util.EResponseCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FizzBuzzService {

   private OperationConverter operationConverter;

   private IOperationsRepository operationsRepository;

   @Autowired
   public FizzBuzzService(OperationConverter operationConverter, IOperationsRepository iOperationsRepository) {
      this.operationConverter = operationConverter;
      this.operationsRepository = iOperationsRepository;
   }

   //Get FizzBuzz
   public ResponseEntity<ResponseDTO> doFizzBuzz(final int min, final int max) {
      if(min >= max) {
         throw new BadRequest(Constants.ERROR_MIN_GE_MAX);
      }

      final ResponseDTO response = getFizzBuzz(min, max);
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

   // private uses
   private ResponseDTO getFizzBuzz(final int min, final int max) {

      final String listFizzBuzz = IntStream.rangeClosed(min, max)
               .mapToObj(i -> i % Constants.THREE == Constants.ZERO ? (i % Constants.FIVE == Constants.ZERO ?
                     Constants.FIZZ_BUZZ : Constants.FIZZ) :
                     (i % Constants.FIVE == Constants.ZERO ?
                           Constants.BUZZ : i))
               .map(i -> ((Comparable) i).toString())
               .collect(Collectors.joining(", "));

      String code = EResponseCode.of(EResponseCode.DEFAULT);
      String description;

      if(listFizzBuzz.contains(Constants.FIZZ_BUZZ)){

         description = Constants.FIZZ_BUZZ_DESCRIPTION;
         code = EResponseCode.of(EResponseCode.FIZZ_BUZZ_CODE);

      }
      else if (listFizzBuzz.contains(Constants.BUZZ) && listFizzBuzz.contains(Constants.FIZZ)) {
         description = Constants.FIZZ_BUZZ_DESCRIPTION;
         code = EResponseCode.of(EResponseCode.FIZZ_AND_BUZZ_CODE);

      }
      else if (listFizzBuzz.contains(Constants.BUZZ)) {

         description = Constants.BUZZ_DESCRIPTION;
         code = EResponseCode.of(EResponseCode.BUZZ_CODE);

      }
      else if (listFizzBuzz.contains(Constants.FIZZ)) {

         description = Constants.FIZZ_DESCRIPTION;
         code = EResponseCode.of(EResponseCode.FIZ_CODE);

      }
      else {

         description = Constants.DEFAULT_DESCRIPTION;

      }

      return ResponseDTO
            .builder()
            .timestamp(new Date().getTime())
            .code(code)
            .description(description)
            .list(listFizzBuzz)
            .build();
   }
}
