package com.intraway.handler;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.intraway.dto.ResponseDTO;
import com.intraway.util.Constants;
import com.intraway.util.EResponseCode;

@Component
public class FizzBuzzHandler {

   public ResponseDTO getFizzBuzz(final int min, final int max) {

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
         code = EResponseCode.of(EResponseCode.FIZZ_CODE);

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
