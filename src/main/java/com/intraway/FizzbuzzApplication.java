package com.intraway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class FizzbuzzApplication {
   public static void main(String[] args) {
      SpringApplication.run(FizzbuzzApplication.class, args);
   }

}
