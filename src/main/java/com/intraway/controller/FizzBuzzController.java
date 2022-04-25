package com.intraway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intraway.dto.ResponseDTO;
import com.intraway.service.FizzBuzzService;

import lombok.NonNull;

@RestController
@RequestMapping("/fizzbuzz")
public class FizzBuzzController {

   private FizzBuzzService fizzBuzzService;

   @Autowired
   public FizzBuzzController(FizzBuzzService fizzBuzzService) {
      this.fizzBuzzService = fizzBuzzService;
   }


   @GetMapping("/{min}/{max}")
   public ResponseEntity<ResponseDTO> doFizzBuzz(@NonNull @PathVariable final Integer min, @NonNull @PathVariable final Integer max){
     return fizzBuzzService.doFizzBuzz(min, max);
   }

   @GetMapping("/list")
   public ResponseEntity<List<ResponseDTO>> getOperations(){
      return fizzBuzzService.getOperations();
   }

   @GetMapping("/{id}")
   public ResponseEntity<ResponseDTO> getOperationById(@NonNull @PathVariable final Long id){
      return fizzBuzzService.getOperationById(id);
   }

}
