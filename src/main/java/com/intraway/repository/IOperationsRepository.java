package com.intraway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intraway.entity.Operation;

public interface IOperationsRepository extends JpaRepository<Operation, Long> {

}
