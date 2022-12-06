package com.example.myfirstapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myfirstapp.CommonConstants.CommonConstants;
import com.example.myfirstapp.CommonConstants.CommonRequestDto;
import com.example.myfirstapp.CommonConstants.CommonResponseDto;
import com.example.myfirstapp.dto.QuestionsDto;
import com.example.myfirstapp.dto.UserDto;
import com.example.myfirstapp.service.MainService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({ "/self/service/api/" })
public class MainController {

	@Autowired
	private MainService mainService;

	@PostMapping("saveUser")	
	public ResponseEntity<?> saveUsers(@RequestBody UserDto userDto, HttpServletRequest request) {
		CommonResponseDto<UserDto> commonResponseDto = new CommonResponseDto<>();
		try {
			commonResponseDto = mainService.saveUsers(userDto, request);
		} catch (Exception e) {
			commonResponseDto.setStatus(CommonConstants.ERROR);
			commonResponseDto.setMessage(CommonConstants.INTERNALERROR);
			e.printStackTrace();
		}
		return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
	}

	@GetMapping("fetchAllQuestions")
	public ResponseEntity<?> fetchAllQuestions(HttpServletRequest request) {
		CommonResponseDto<List<QuestionsDto>> commonResponseDto = new CommonResponseDto<>();
		try {
			commonResponseDto = mainService.fetchAllQuestions(request);
		} catch (Exception e) {
			commonResponseDto.setStatus("ERROR");
			commonResponseDto.setMessage("Some Internal Error");
			e.printStackTrace();
		}
		return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
	}

	@PostMapping("fetchUserDetails")
	public ResponseEntity<?> fetchUserDetails(@RequestBody CommonRequestDto commonRequestDto, HttpServletRequest request) {
		CommonResponseDto<UserDto> commonResponseDto = new CommonResponseDto<>();
		try {
			commonResponseDto = mainService.fetchUserDetails(commonRequestDto, request);
		} catch (Exception e) {
			commonResponseDto.setStatus("ERROR");
			commonResponseDto.setMessage("Some Internal Error");
			e.printStackTrace();
		}
		return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
	}
	
	@PostMapping("getAllAnswers")
	public ResponseEntity<?> getAllAnswers(@RequestBody CommonRequestDto commonRequestDto, HttpServletRequest request) {
		CommonResponseDto<UserDto> commonResponseDto = new CommonResponseDto<>();
		try {
			commonResponseDto = mainService.getAllAnswers(commonRequestDto, request);
		} catch (Exception e) {
			commonResponseDto.setStatus("ERROR");
			commonResponseDto.setMessage("Some Internal Error");
			e.printStackTrace();
		}
		return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
	}
}
