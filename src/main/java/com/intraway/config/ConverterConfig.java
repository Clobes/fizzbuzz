package com.intraway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intraway.converter.OperationConverter;

@Configuration
public class ConverterConfig {

   @Bean
   public OperationConverter getOperationConverter() {
      return new OperationConverter();
   }

}
