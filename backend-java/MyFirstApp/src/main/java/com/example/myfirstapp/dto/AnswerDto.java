package com.example.myfirstapp.dto;

import java.io.Serializable;

public class AnswerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String answer;

	private Boolean isActive;

	private Long questionId;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

}
