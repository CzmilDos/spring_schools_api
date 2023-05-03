package com.estiam.final_project.controllers;

import com.estiam.final_project.models.School;
import com.estiam.final_project.services.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping()
    public School create(@RequestBody School school){
        return schoolService.create(school);
    }
    @GetMapping()
    public List<School> read(){
        return schoolService.readAll();
    }

    @GetMapping("/{id}")
    public Optional<School> readById(@PathVariable Long id){
        return schoolService.readById(id);
    }

    @PutMapping("/{id}")
    public School update(@PathVariable Long id, @RequestBody School school){
        return schoolService.update(id, school);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return schoolService.delete(id);
    }
}
