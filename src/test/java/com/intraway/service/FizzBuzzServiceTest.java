package com.intraway.service;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.intraway.converter.OperationConverter;
import com.intraway.entity.Operation;
import com.intraway.exceptions.BadRequest;
import com.intraway.exceptions.NotFound;
import com.intraway.handler.FizzBuzzHandler;
import com.intraway.repository.IOperationsRepository;
import com.intraway.util.Constants;
import com.intraway.util.ConstantsTest;
import com.intraway.util.EResponseCode;
import com.intraway.util.FizzBuzzServiceDataTestUtils;

@ExtendWith(MockitoExtension.class)
class FizzBuzzServiceTest {

   @Mock
   private IOperationsRepository operationsRepository;

   @Mock
   private FizzBuzzHandler fizzBuzzHandler;

   @Mock
   private OperationConverter operationConverter;

   private FizzBuzzService underTest;

   @BeforeEach
   void setUp() {
      underTest = new FizzBuzzService(operationConverter, fizzBuzzHandler, operationsRepository);
   }


   @Test
   @DisplayName("Can get all Operations.")
   void canGetOperations() {
      //when
      underTest.getOperations();

      //then
      Mockito.verify(operationsRepository).findAll();
      Mockito.verify(operationConverter).fromEntity(new ArrayList<>());
   }

   @Test
   @DisplayName("Can Get Fizz Buzz operation.")
   void canDoFizzBuzz() {
      //given
      final int min = 1;
      final int max = 15;

      //when
      underTest.doFizzBuzz(min, max);

      //then
      ArgumentCaptor<Operation> operationArgumentCaptor = ArgumentCaptor.forClass(Operation.class);

      Mockito.verify(fizzBuzzHandler).getFizzBuzz(min, max);
      Mockito.verify(operationsRepository).save(operationArgumentCaptor.capture());
   }

   @Test
   @DisplayName("Can Operation By Id.")
   void CanGetOperationById() {
      //when
      Operation operation = FizzBuzzServiceDataTestUtils.getOperation(EResponseCode.FIZZ_BUZZ_CODE, Constants.FIZZ_BUZZ_DESCRIPTION,
            ConstantsTest.EXPECTED_FIZZ_BUZZ_LIST);

      Mockito.when(operationsRepository.findOperationById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(operation));

      underTest.getOperationById(ArgumentMatchers.anyLong());

      //then
      ArgumentCaptor<Operation> operationArgumentCaptor = ArgumentCaptor.forClass(Operation.class);

      Mockito.verify(operationsRepository).findOperationById(ArgumentMatchers.anyLong());
      Mockito.verify(operationConverter).fromEntity(operationArgumentCaptor.capture());
      Assertions.assertEquals(operation, operationArgumentCaptor.getValue());
   }

   @Test
   @DisplayName("Should Throw Bad Request Exception When min is greater than max.")
   public void shouldThrowBadRequestExceptionWhenMinIsGreaterThanMax() {
      //given
      final int min = 10;
      final int max = 5;

      //when
      BadRequest badRequestException = Assertions.assertThrows(BadRequest.class,
            ()->underTest.doFizzBuzz(min, max));

      //then
      Assertions.assertEquals(Constants.ERROR_MIN_GE_MAX, badRequestException.getMessage());
   }

   @Test
   @DisplayName("Should Throw Bad Request Exception When min is equal than max.")
   public void shouldThrowBadRequestExceptionWhenMinIsEqualThanMax() {
      //given
      final int min = 5;
      final int max = 5;

      //when
      BadRequest badRequestException = Assertions.assertThrows(BadRequest.class,
            ()-> underTest.doFizzBuzz(min, max));

      //then
      Assertions.assertEquals(Constants.ERROR_MIN_GE_MAX, badRequestException.getMessage());
   }

   @Test
   @DisplayName("Should Throw Not Found Exception When Operation Id does not exist.")
   public void shouldThrowNotFoundExceptionWhenOperationIdDoesNotExists() {
      //given
      final Long id = 1L;
      final String expectedMessage = String.format(Constants.OPERATION_NOT_FOUND, id);

      //when
      NotFound notFoundException = Assertions.assertThrows(NotFound.class,
            () -> underTest.getOperationById(id));

      //then
      Assertions.assertEquals(expectedMessage, notFoundException.getMessage());
      Mockito.verify(operationsRepository).findOperationById(id);
   }
   /*
   @Test
   @DisplayName("Should Get ResponsdeDto with multiplo the Five and three.")
   public void shouldGetResponseDTOWhitMultiploTheFiveAndThree() {


      //then
      Mockito.verify(fizzBuzzHandler).getFizzBuzz(min, max);
   }

    */
}