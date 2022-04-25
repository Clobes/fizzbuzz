package com.intraway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.intraway.entity.Operation;

public interface IOperationsRepository extends JpaRepository<Operation, Long> {

   @Query("SELECT e FROM Operation e WHERE e.id = :id")
   Optional<Operation> findOperationById(@Param("id") final Long id);

}
