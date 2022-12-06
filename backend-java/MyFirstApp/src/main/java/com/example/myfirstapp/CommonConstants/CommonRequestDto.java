package com.example.myfirstapp.CommonConstants;

import java.io.Serializable;
import java.util.List;

import com.example.myfirstapp.dto.AnswerDto;

public class CommonRequestDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String password;
	private List<AnswerDto> answerDto;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AnswerDto> getAnswerDto() {
		return answerDto;
	}
	public void setAnswerDto(List<AnswerDto> answerDto) {
		this.answerDto = answerDto;
	}
	
}
