package com.inventory.snackk.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.snackk.model.Snack;
import com.inventory.snackk.service.SnackService;

@RestController
@RequestMapping("/api/snack")
public class SnackController {
    private final SnackService snackService;

    public SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @PostMapping("/add")
    public ResponseEntity<Snack> addSnack(@RequestBody Snack snack) {
        Snack saveSnack = snackService.saveSnack(snack);
        return new ResponseEntity<>(saveSnack, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Snack>> getAllSnacks() {
        List<Snack> snacks = snackService.getSnacks();
        return new ResponseEntity<>(snacks, HttpStatus.OK);
    }

    @PutMapping("/{id}/consume")
    public ResponseEntity<Void> consumeSnack(@PathVariable Long id, @RequestBody Map<String, Integer> requestBody) {
        int quantity = requestBody.get("quantity");
        snackService.consumeSnack(id, quantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSnack(@PathVariable Long id) {
        snackService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
