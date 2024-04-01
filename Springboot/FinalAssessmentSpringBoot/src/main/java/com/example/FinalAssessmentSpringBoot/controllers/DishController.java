package com.example.FinalAssessmentSpringBoot.controllers;

import com.example.FinalAssessmentSpringBoot.beans.ResponseHandler;
import com.example.FinalAssessmentSpringBoot.models.Dish;
import com.example.FinalAssessmentSpringBoot.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll(){
        return ResponseHandler.createResponse("Found dishes", HttpStatus.OK, dishService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getDish(@PathVariable Long id){
        return ResponseHandler.createResponse("Found the dish", HttpStatus.OK, dishService.get(id));
    }

    @PostMapping("/")

    public ResponseEntity<Object> create(@RequestBody Dish dish) {
        Dish newDish = dishService.create(dish);
        return ResponseHandler.createResponse("Created a dish successfully", HttpStatus.OK, newDish);
    }

    @PostMapping("/")

    public ResponseEntity<Object> delete(@RequestBody Long id) {
        Boolean didDelete = dishService.delete(id);
        return ResponseHandler.createResponse("Deleted a dish successfully", HttpStatus.OK, didDelete);
    }

    @PostMapping("/")

    public ResponseEntity<Object> update(@RequestBody Dish dish) {
        Dish updatedDish = dishService.update(dish);
        return ResponseHandler.createResponse("Updated a dish successfully", HttpStatus.OK, updatedDish);
    }
}
