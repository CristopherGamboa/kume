package com.kume.kume.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kume.kume.infraestructure.models.Step;

public interface StepRepository extends JpaRepository<Step, Long>{

}
