package com.inventory.snackk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inventory.snackk.model.Snack;
import com.inventory.snackk.repository.SnackRepository;

@Service
public class SnackService {
    public final SnackRepository snackRepository;

    public SnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    };

    public Snack saveSnack(Snack snack) {
        Optional<Snack> existingSnack = snackRepository.findByName(snack.getName());

        if (existingSnack.isPresent()) {
            throw new SnackAlreadyExistsException("Snack already exists");
        }

        return snackRepository.save(snack);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public class SnackAlreadyExistsException extends RuntimeException {
        public SnackAlreadyExistsException(String message) {
            super(message);
        }
    }
    

    public List<Snack> getSnacks() {
        return snackRepository.findAll();
    }

    public void consumeSnack(Long id, int quantity) {
        Snack snack = snackRepository.findById(id).orElseThrow(() -> new RuntimeException("Snack not found"));
        snack.setQuantity(snack.getQuantity() - quantity);
        if (snack.getQuantity() < 0) {
            snack.setQuantity(0);
        }
        snackRepository.save(snack);
    }

    public void deleteById(Long id) {
        snackRepository.deleteById(id);
    }

}
