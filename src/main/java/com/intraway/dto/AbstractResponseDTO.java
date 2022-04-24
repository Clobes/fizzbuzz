package com.intraway.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public abstract class AbstractResponseDTO {

   private long timestamp = new Date().getTime();

}
