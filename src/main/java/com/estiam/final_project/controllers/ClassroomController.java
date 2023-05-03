package com.estiam.final_project.controllers;

import com.estiam.final_project.models.Classroom;
import com.estiam.final_project.services.ClassroomService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    //Créer salles par école...
    @PostMapping("/schools/{school_id}/classrooms")
    public ResponseEntity<List<Classroom>> create(@PathVariable Long school_id, @RequestBody List<Classroom> classrooms){
        return classroomService.create(school_id, classrooms);
    }

    //Toutes les salles | mot clé
    @GetMapping("/classrooms")
    public List<Classroom> read(@RequestParam(value = "title", required = false) String keyword){
        return classroomService.readByKey(keyword);
    }

    //Salles par école | mot clé
    @GetMapping("/schools/{school_id}/classrooms")
    public List<Classroom> readBySchool(@PathVariable Long school_id, @RequestParam(value = "title", required = false) String keyword){
       return classroomService.readBySchool(school_id, keyword);
    }

    @GetMapping("/classrooms/pagination")
    public Page<Classroom> pagination(@RequestParam int page, @RequestParam int items){
        return classroomService.classroomsPagination(page,items);
    }

    @PutMapping("/classrooms/{id}")
    public Classroom update(@PathVariable Long id, @RequestBody Classroom classroom){
        return classroomService.update(id, classroom);
    }

    @DeleteMapping("/classrooms/{id}")
    public String delete(@PathVariable Long id){
        return classroomService.delete(id);
    }

}
