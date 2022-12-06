package com.example.myfirstapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.myfirstapp.CommonConstants.CommonRequestDto;
import com.example.myfirstapp.CommonConstants.CommonResponseDto;
import com.example.myfirstapp.dto.QuestionsDto;
import com.example.myfirstapp.dto.UserDto;

public interface MainService {

	CommonResponseDto<UserDto> saveUsers(UserDto userDto, HttpServletRequest request);

	CommonResponseDto<List<QuestionsDto>> fetchAllQuestions(HttpServletRequest request);

	CommonResponseDto<UserDto> fetchUserDetails(CommonRequestDto commonRequestDto, HttpServletRequest request);

	CommonResponseDto<UserDto> getAllAnswers(CommonRequestDto commonRequestDto, HttpServletRequest request);

}
