package com.intraway.handler;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.intraway.dto.ResponseDTO;
import com.intraway.util.Constants;
import com.intraway.util.ConstantsTest;
import com.intraway.util.EResponseCode;
import com.intraway.util.FizzBuzzServiceDataTestUtils;

@ExtendWith(SpringExtension.class)
class FizzBuzzHandlerTest {

   private FizzBuzzHandler underTest;


   @BeforeEach
   void setUp() {
      underTest = new FizzBuzzHandler();
   }

   @Test
   @DisplayName("Should Get ResponsdeDto with multiple of Five and three and both.")
   void shouldGetMultiploThe5And3() {
      //given
      final int min = 1;
      final int max = 15;

      final ResponseDTO expected = FizzBuzzServiceDataTestUtils.getMultipleOfXXX(
            EResponseCode.FIZZ_BUZZ_CODE,
            Constants.FIZZ_BUZZ_DESCRIPTION,
            ConstantsTest.EXPECTED_FIZZ_BUZZ_LIST);

      //when
      final ResponseDTO actual = underTest.getFizzBuzz(min, max);

      //then
      Assertions.assertEquals(expected.getList(), actual.getList());
      Assertions.assertEquals(expected.getCode(), actual.getCode());
      Assertions.assertEquals(expected.getDescription(), actual.getDescription());
   }

   @Test
   @DisplayName("Should Get ResponsdeDto with multiple of Five.")
   void shouldGetMultiploThe5() {
      //given
      final int min = 4;
      final int max = 5;

      final ResponseDTO expected = FizzBuzzServiceDataTestUtils.getMultipleOfXXX(
            EResponseCode.BUZZ_CODE,
            Constants.BUZZ_DESCRIPTION,
            ConstantsTest.EXPECTED_BUZZ_LIST);

      //when
      final ResponseDTO actual = underTest.getFizzBuzz(min, max);

      //then
      Assertions.assertEquals(expected.getList(), actual.getList());
      Assertions.assertEquals(expected.getCode(), actual.getCode());
      Assertions.assertEquals(expected.getDescription(), actual.getDescription());
   }

   @Test
   @DisplayName("Should Get ResponsdeDto with multiple of Five and Three.")
   void shouldGetMultipleOf5And3() {
      //given
      final int min = 1;
      final int max = 9;

      final ResponseDTO expected = FizzBuzzServiceDataTestUtils.getMultipleOfXXX(
            EResponseCode.FIZZ_AND_BUZZ_CODE,
            Constants.FIZZ_BUZZ_DESCRIPTION,
            ConstantsTest.EXPECTED_FIZZ_AND_BUZZ_LIST);

      //when
      final ResponseDTO actual = underTest.getFizzBuzz(min, max);

      //then
      Assertions.assertEquals(expected.getList(), actual.getList());
      Assertions.assertEquals(expected.getCode(), actual.getCode());
      Assertions.assertEquals(expected.getDescription(), actual.getDescription());
   }

   @Test
   @DisplayName("Should Get ResponsdeDto with multiple of Three.")
   void shouldGetMultipleOf3() {
      //given
      final int min = 1;
      final int max = 3;

      final ResponseDTO expected = FizzBuzzServiceDataTestUtils.getMultipleOfXXX(
            EResponseCode.FIZZ_CODE,
            Constants.FIZZ_DESCRIPTION,
            ConstantsTest.EXPECTED_FIZZ_LIST);

      //when
      final ResponseDTO actual = underTest.getFizzBuzz(min, max);

      //then
      Assertions.assertEquals(expected.getList(), actual.getList());
      Assertions.assertEquals(expected.getCode(), actual.getCode());
      Assertions.assertEquals(expected.getDescription(), actual.getDescription());
   }

   @Test
   @DisplayName("Should Get ResponsdeDto without multiple of Five and three and both")
   void shouldGetListWithoutMultiple() {
      //given
      final int min = 1;
      final int max = 2;

      final ResponseDTO expected = FizzBuzzServiceDataTestUtils.getMultipleOfXXX(
            EResponseCode.DEFAULT,
            Constants.DEFAULT_DESCRIPTION, ConstantsTest.EXPECTED_WITHOUT_MULTIPLE_LIST);

      //when
      final ResponseDTO actual = underTest.getFizzBuzz(min, max);

      //then
      Assertions.assertEquals(expected.getList(), actual.getList());
      Assertions.assertEquals(expected.getCode(), actual.getCode());
      Assertions.assertEquals(expected.getDescription(), actual.getDescription());
   }
}