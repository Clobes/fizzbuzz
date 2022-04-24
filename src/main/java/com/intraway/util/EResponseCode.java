package com.intraway.util;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum EResponseCode {
   
   FIZZ_BUZZ_CODE("001"),
   FIZ_CODE("002"),
   BUZZ_CODE("003"),
   FIZZ_AND_BUZZ_CODE("004"),
   DEFAULT("1000");

   private String value;

   public static String of(final EResponseCode code) {
      return Stream.of(values())
            .filter(r -> r.equals(code))
            .findFirst()
            .map(EResponseCode::getValue)
            .orElseThrow(IllegalArgumentException::new);
   }

}
