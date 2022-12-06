package com.example.myfirstapp.dto;

import java.io.Serializable;
import java.util.List;

import com.example.myfirstapp.entity.AnswersEntity;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String userId;
	
	private String password;

	private String email;

	private String firstName;

	private String lastName;

	private Boolean isActive;

	private Long mobileNo;

	private List<AnswerDto> answerDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public List<AnswerDto> getAnswerDto() {
		return answerDto;
	}

	public void setAnswerDto(List<AnswerDto> answerDto) {
		this.answerDto = answerDto;
	}

}
