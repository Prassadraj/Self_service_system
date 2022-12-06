package com.example.myfirstapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myfirstapp.entity.AnswersEntity;

public interface AnswerRepo extends JpaRepository<AnswersEntity, Long>{

	AnswersEntity findByIdAndIsActiveTrue(Long id);

	List<AnswersEntity> findByUserEntityUserIdAndIsActiveTrue(String userId);

}
