package com.example.FinalAssessmentSpringBoot.services;

import com.example.FinalAssessmentSpringBoot.beans.ResponseHandler;
import com.example.FinalAssessmentSpringBoot.exceptions.EntityAlreadyExistsException;
import com.example.FinalAssessmentSpringBoot.exceptions.EntityNotFoundException;
import com.example.FinalAssessmentSpringBoot.models.Dish;
import com.example.FinalAssessmentSpringBoot.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    //To fetch all dishes
    public List<Dish> getAll()
    {
        return dishRepository.findAll();
    }

    // To fetch a dish based on id
    public Dish get(Long id)
    {
        Optional<Dish> dish = dishRepository.findById(id);

        if(dish.isPresent()){
            return dish.get();
        }
        throw new EntityNotFoundException("Dish with given ID is not present");
    }

    //To create a new dish entry
    public Dish create(Dish dish)
    {
        if(dishRepository.findById(dish.getId()).isEmpty())
        {
            Dish newDish = dishRepository.save(dish);
            return newDish;
        }
        throw new EntityAlreadyExistsException("Dish with this id already exists.");

    }

    public Boolean delete(Long id){

        Optional<Dish> dish = dishRepository.findById(id);
        if(dish.isPresent()){
            dishRepository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException("Dish with given ID is not present");
    }

    //To update a Dish entry

    public Dish update(Dish dish){

        Optional<Dish> optionalDish = dishRepository.findById(dish.getId());
        if(optionalDish.isPresent()){
            Dish updatedDish = dishRepository.save(dish);
            return updatedDish;
        }
        throw new EntityNotFoundException("Dish with given ID is not present");
    }

}
