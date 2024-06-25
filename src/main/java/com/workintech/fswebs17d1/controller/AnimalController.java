package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path= "/workintecth/animal")
public class AnimalController {
    private Map<Integer,Animal> animals ;



    @PostConstruct //uyg ayağa kalkarken hazır edilmesi için
    public void loadAlls(){
        System.out.println("*****postconstruct çalıştı");
        this.animals=new HashMap<>();
        this.animals.put(1,new Animal(1,"zürafa"));
        //proje ayağa kalktığında bir animal hazır olacak

    }

    //
    @GetMapping //get işlemi yapmak için
    public List<Animal> getAllAnimals(){
        System.out.println("-----animals get triggered");
        return new ArrayList<>(animals.values());
        //animals mapindeki valueları arrayList olarak
    }
    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable("id") int id) {
        if (id<0){
            System.out.println("id cannot be less than zero");
            return null;
        }
        return animals.get(id);
    }

    @PostMapping //post(ekleme) işlemi yapmak için
    public void addAnimal(@RequestBody Animal animal){
        System.out.println("-----add animal get triggered");

        this.animals.put(animal.getId(),animal);
    }

    @PutMapping("/{id}")// update yapmak için
    public Animal updateAnimal(@PathVariable("id") int id,@RequestBody Animal newAnimal){
       Animal existingAnimal = this.animals.get(id);
        this.animals.replace(id,newAnimal);
        return existingAnimal;

    }
    @DeleteMapping("{id}")
        public Animal deleteAnimal(@PathVariable("id") int id){
        if (id<0){
            System.out.println("Id cannot be less than zero");
            return null;
        } else {
            return this.animals.remove(id);
        }

    }








}


