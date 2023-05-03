package com.estiam.final_project.repositories;

import com.estiam.final_project.models.Classroom;
import com.estiam.final_project.models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findBySchool(School school);
    List<Classroom> findBySchoolAndTitleContainingIgnoreCase(School school, String title);
    List<Classroom> findByTitleContainingIgnoreCase(String title);
}
