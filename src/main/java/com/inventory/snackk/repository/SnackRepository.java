package com.inventory.snackk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.snackk.model.Snack;

public interface SnackRepository extends JpaRepository<Snack, Long> {
    Optional<Snack> findByName(String name);
    
}
