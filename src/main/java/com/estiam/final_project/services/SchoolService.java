package com.estiam.final_project.services;

import com.estiam.final_project.models.Classroom;
import com.estiam.final_project.models.School;
import com.estiam.final_project.repositories.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public School create(School school){
        for (Classroom classroom : school.getClassrooms()) {
            classroom.setSchool(school);
        }
        return schoolRepository.save(school);
    }

    public List<School> readAll(){
        return schoolRepository.findAll();
    }

    public Optional<School> readById(Long id){
        return schoolRepository.findById(id);
    }

    public School update(Long school_id, School school){
        //Recupérer l'Ecole par l'Id
        School oldSchool = schoolRepository.findById(school_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "L'école n'existe pas"));
        //supprimer les anciennes salles
        oldSchool.getClassrooms().clear();

        // Attribuer l'Ecole pour chaque salle de la nouvelle liste
        for (Classroom classroom : school.getClassrooms()) {
            classroom.setSchool(oldSchool);
        }
        // Mettre à jour l'ecole avec les nouvelles classes
        oldSchool.setName(school.getName());
        oldSchool.setClassrooms(school.getClassrooms());
        return schoolRepository.save(oldSchool);
    }

    public String delete(Long id){
        schoolRepository.deleteById(id);
        return "Ecole supprimée avec succès";
    }
}
