package com.estiam.final_project.services;

import com.estiam.final_project.models.Classroom;
import com.estiam.final_project.models.School;
import com.estiam.final_project.repositories.ClassroomRepository;
import com.estiam.final_project.repositories.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final SchoolRepository schoolRepository;

    public ResponseEntity<List<Classroom>> create(Long school_id, List<Classroom> classrooms){
        Optional<School> optionalSchool = schoolRepository.findById(school_id);
        if (optionalSchool.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        School school = optionalSchool.get();
        for (Classroom classroom : classrooms) {
            classroom.setSchool(school);
        }

        List<Classroom> oldClassrooms = school.getClassrooms();
        oldClassrooms.addAll(classrooms);
        classroomRepository.saveAll(classrooms);

        return ResponseEntity.created(URI.create("/schools/" + school.getId() + "/classrooms/"))
                .body(oldClassrooms);
    }

    public List<Classroom> read(){
        return classroomRepository.findAll();
    }

    //Sans paramètre pour le mot clé, renvoyer toutes les salles...
    public List<Classroom> readByKey(String keyword){
        if (keyword != null) {
            return classroomRepository.findByTitleContainingIgnoreCase(keyword);
        } else {
            return read();
        }
    }

    public List<Classroom> readBySchool(Long school_id, String keyword){
        Optional<School> optionalSchool = schoolRepository.findById(school_id);
        if (optionalSchool.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'école n'existe pas");
        }
        School school = optionalSchool.get();
        if (keyword != null) {
            return classroomRepository.findBySchoolAndTitleContainingIgnoreCase(school, keyword);
        } else {
            return classroomRepository.findBySchool(school);
        }
    }

    public Classroom update(Long id, Classroom classroom){
        Classroom oldClassroom = classroomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La salle n'existe pas"));

        oldClassroom.setTitle(classroom.getTitle());
        return classroomRepository.save(oldClassroom);
    }

    public String delete(Long id){
        classroomRepository.deleteById(id);
        return "Salle supprimée avec succès";
    }

    public Page<Classroom> classroomsPagination(int page, int items){
        return classroomRepository.findAll(PageRequest.of(page,items));
    }
}
