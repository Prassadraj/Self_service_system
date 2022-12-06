package com.example.myfirstapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myfirstapp.CommonConstants.Questions;

@Repository
public interface QuestionsRepo extends JpaRepository<Questions, Long> {

	List<Questions> findByIsActiveTrueOrderByIdAsc();


}
