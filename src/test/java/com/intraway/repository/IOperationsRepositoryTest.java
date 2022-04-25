package com.intraway.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.intraway.entity.Operation;
import com.intraway.util.Constants;
import com.intraway.util.EResponseCode;

@DataJpaTest
class IOperationsRepositoryTest {

   public static final String RESULT_EXPECTED = "1, 2, Fizz, 4, Buzz";

   @Autowired
   private IOperationsRepository underTest;

   @Test
   void itShouldFindOperationById() {

      //given
      Operation entity = Operation
            .builder()
            .timeStamp(new Date().getTime())
            .code(EResponseCode.of(EResponseCode.FIZZ_AND_BUZZ_CODE))
            .description(Constants.FIZZ_BUZZ_DESCRIPTION)
            .result(RESULT_EXPECTED)
            .build();

      underTest.save(entity);

      //when
      Operation expected = underTest.findOperationById(entity.getId()).get();

      //then
      assertEquals(expected, entity);

   }

   @Test
   void itShouldNotFindOperationById() {

      //given
      Long id = 1L;

      //when
      Optional<Operation> expected = underTest.findOperationById(id);

      //then
      assertFalse(expected.isPresent());
   }
}