package com.estiam.final_project.repositories;

import com.estiam.final_project.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
