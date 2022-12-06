package com.example.myfirstapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myfirstapp.CommonConstants.CommonConstants;
import com.example.myfirstapp.CommonConstants.CommonRequestDto;
import com.example.myfirstapp.CommonConstants.CommonResponseDto;
import com.example.myfirstapp.CommonConstants.Questions;
import com.example.myfirstapp.dto.AnswerDto;
import com.example.myfirstapp.dto.QuestionsDto;
import com.example.myfirstapp.dto.UserDto;
import com.example.myfirstapp.entity.AnswersEntity;
import com.example.myfirstapp.entity.UserEntity;
import com.example.myfirstapp.repository.AnswerRepo;
import com.example.myfirstapp.repository.QuestionsRepo;
import com.example.myfirstapp.repository.UserRepo;
import com.example.myfirstapp.service.MainService;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private QuestionsRepo questionsRepo;

	@Autowired
	private AnswerRepo answerRepo;

	@Override
	public CommonResponseDto<UserDto> saveUsers(UserDto userDto, HttpServletRequest request) {
		CommonResponseDto<UserDto> commonResponseDto = new CommonResponseDto<>();
		try {
			UserEntity userEntity = null;
			if (!(Objects.nonNull(userDto) && userDto.getUserId() != null
					&& userDto.getUserId() != "" & userDto.getUserId() != " ")) {
				commonResponseDto.setStatus(CommonConstants.ERROR);
				commonResponseDto.setMessage(CommonConstants.INVALIDREQUEST);
				return commonResponseDto;
			}
			userEntity = userRepo.findByUserIdAndIsActiveTrue(userDto.getUserId());
			// new save
			if (Objects.isNull(userEntity)) {
				userEntity = new UserEntity();
				BeanUtils.copyProperties(userDto, userEntity);
				userEntity.setIsActive(Boolean.TRUE);
				if (!(userDto.getAnswerDto() != null && !userDto.getAnswerDto().isEmpty())) {
					commonResponseDto.setStatus(CommonConstants.ERROR);
					commonResponseDto.setMessage("Answer Entity Null");
					return commonResponseDto;
				}
				List<AnswerDto> answerDtos = new ArrayList<>();
				List<AnswersEntity> answersEntities = new ArrayList<>();
				for (AnswerDto answerDto : userDto.getAnswerDto()) {
					AnswersEntity ansEntity = new AnswersEntity();
					BeanUtils.copyProperties(answerDto, ansEntity);
					ansEntity.setUserEntity(userEntity);
					ansEntity.setQuestions(answerDto.getQuestionId());
					ansEntity.setIsActive(Boolean.TRUE);
					answersEntities.add(ansEntity);
					AnswerDto ansDto = new AnswerDto();
					BeanUtils.copyProperties(ansEntity, ansDto);
					ansDto.setQuestionId(ansEntity.getQuestions());
					answerDtos.add(ansDto);
				}
				userEntity.setAnswerEntity(answersEntities);
				userRepo.save(userEntity);
				if (null != userEntity.getId()) {
					UserDto dto = new UserDto();
					BeanUtils.copyProperties(userEntity, dto);
					dto.setAnswerDto(answerDtos);
					commonResponseDto.setResponseData(dto);
					commonResponseDto.setStatus(CommonConstants.SUCCESS);
					commonResponseDto.setMessage("New User Created Successfully..");
				} else {
					commonResponseDto.setStatus(CommonConstants.ERROR);
					commonResponseDto.setMessage("User Not Saved");
					return commonResponseDto;
				}
			}
			// update
			else {
				Long id = userEntity.getId();
				BeanUtils.copyProperties(userDto, userEntity);
				userEntity.setId(id);
				if (userDto.getAnswerDto() != null && !userDto.getAnswerDto().isEmpty()) {
					commonResponseDto.setStatus(CommonConstants.ERROR);
					commonResponseDto.setMessage("Answer Entity Null");
					return commonResponseDto;
				}
				List<AnswerDto> answerDtos = new ArrayList<>();
				List<AnswersEntity> answersEntities = new ArrayList<>();
				for (AnswerDto answerDto : userDto.getAnswerDto()) {
					AnswersEntity ansEntity = answerRepo.findByIdAndIsActiveTrue(answerDto.getId());
					if (Objects.isNull(ansEntity)) {
						commonResponseDto.setStatus(CommonConstants.ERROR);
						commonResponseDto.setMessage("No Record Found In Answer Entity");
					}
					BeanUtils.copyProperties(answerDto, ansEntity);
					ansEntity.setUserEntity(userEntity);
					ansEntity.setQuestions(answerDto.getQuestionId());
					answersEntities.add(ansEntity);
					AnswerDto ansDto = new AnswerDto();
					BeanUtils.copyProperties(ansEntity, ansDto);
					ansDto.setQuestionId(ansEntity.getQuestions());
					answerDtos.add(ansDto);
				}
				userEntity.setAnswerEntity(answersEntities);
				userRepo.save(userEntity);
				if (null != userEntity.getId()) {
					UserDto dto = new UserDto();
					BeanUtils.copyProperties(userEntity, dto);
					dto.setAnswerDto(answerDtos);
					commonResponseDto.setResponseData(dto);
					commonResponseDto.setStatus(CommonConstants.SUCCESS);
					commonResponseDto.setMessage("User Saved Successfully..");
				} else {
					commonResponseDto.setStatus(CommonConstants.ERROR);
					commonResponseDto.setMessage("User Not Saved");
					return commonResponseDto;
				}
			}
		} catch (Exception e) {
			commonResponseDto.setStatus(CommonConstants.ERROR);
			commonResponseDto.setMessage(CommonConstants.INTERNALERROR);
			e.printStackTrace();
		}
		return commonResponseDto;
	}

	@Override
	public CommonResponseDto<List<QuestionsDto>> fetchAllQuestions(HttpServletRequest request) {
		CommonResponseDto<List<QuestionsDto>> commonResponseDto = new CommonResponseDto<>();
		try {
			List<QuestionsDto> questionsDtos = null;
			List<Questions> questions = questionsRepo.findByIsActiveTrueOrderByIdAsc();
			if (!questions.isEmpty()) {
				questionsDtos = new ArrayList<>();
				for (Questions quest : questions) {
					QuestionsDto questionsDto = new QuestionsDto();
					BeanUtils.copyProperties(quest, questionsDto);
					questionsDtos.add(questionsDto);
				}
				commonResponseDto.setResponseData(questionsDtos);
				commonResponseDto.setStatus(CommonConstants.SUCCESS);
				commonResponseDto.setMessage("Record Fetched Successfully..");
			} else {
				commonResponseDto.setMessage("No Record Found");
				commonResponseDto.setStatus(CommonConstants.ERROR);
				return commonResponseDto;
			}
		} catch (Exception e) {
			commonResponseDto.setStatus("ERROR");
			commonResponseDto.setMessage("Some Internal Error");
			e.printStackTrace();
		}
		return commonResponseDto;
	}

	@Override
	public CommonResponseDto<UserDto> fetchUserDetails(CommonRequestDto commonRequestDto, HttpServletRequest request) {
		CommonResponseDto<UserDto> commonResponseDto = new CommonResponseDto<>();
		try {
			if (!(Objects.nonNull(commonRequestDto) && commonRequestDto.getUserId() != null
					&& commonRequestDto.getUserId() != "" && commonRequestDto.getUserId() != " ")) {
				commonResponseDto.setStatus(CommonConstants.ERROR);
				commonResponseDto.setMessage(CommonConstants.INVALIDREQUEST);
				return commonResponseDto;
			}
			UserEntity userEntity = userRepo.findByUserIdAndIsActiveTrue(commonRequestDto.getUserId());
			if (Objects.isNull(userEntity)) {
				commonResponseDto.setStatus(CommonConstants.ERROR);
				commonResponseDto.setMessage("Invalid UserId");
				return commonResponseDto;
			}
			if (!(commonRequestDto.getPassword() != null && commonRequestDto.getPassword() != ""
					&& commonRequestDto.getPassword() != " "
					&& userEntity.getPassword().equalsIgnoreCase(commonRequestDto.getPassword()))) {
				commonResponseDto.setStatus(CommonConstants.ERROR);
				commonResponseDto.setMessage("Invalid Password");
				return commonResponseDto;
			}
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(userEntity, dto);
			commonResponseDto.setResponseData(dto);
			commonResponseDto.setStatus(CommonConstants.SUCCESS);
			commonResponseDto.setMessage(
					"Hi " + dto.getFirstName() + " " + dto.getLastName() + " " + " Welcome To Self Service System... ");
		} catch (Exception e) {
			commonResponseDto.setStatus("ERROR");
			commonResponseDto.setMessage("Some Internal Error");
			e.printStackTrace();
		}
		return commonResponseDto;
	}

	@Override
	public CommonResponseDto<UserDto> getAllAnswers(CommonRequestDto commonRequestDto, HttpServletRequest request) {
		CommonResponseDto<UserDto> commonResponseDto = new CommonResponseDto<>();
		try {
			if (Objects.isNull(commonRequestDto)) {
				commonResponseDto.setStatus(CommonConstants.ERROR);
				commonResponseDto.setMessage(CommonConstants.INVALIDREQUEST);
				return commonResponseDto;
			}

			if (!(commonRequestDto.getUserId() != null || commonRequestDto.getUserId() != ""
					|| commonRequestDto.getUserId() != " ")) {
				commonResponseDto.setStatus(CommonConstants.ERROR);
				commonResponseDto.setMessage(CommonConstants.INVALIDREQUEST);
				return commonResponseDto;
			}
			UserEntity userEntity = userRepo.findByUserIdAndIsActiveTrue(commonRequestDto.getUserId());
			if (Objects.isNull(userEntity)) {
				commonResponseDto.setStatus(CommonConstants.ERROR);
				commonResponseDto.setMessage(
						"No Record Found In User Table Against This User Id.. " + commonRequestDto.getUserId());
				return commonResponseDto;
			}

			List<AnswersEntity> answerEntity = answerRepo
					.findByUserEntityUserIdAndIsActiveTrue(commonRequestDto.getUserId());
			if (Objects.isNull(answerEntity)) {
				commonResponseDto.setStatus(CommonConstants.ERROR);
				commonResponseDto.setMessage(
						"No Record Found In Answer Table Against This User Id.. " + commonRequestDto.getUserId());
				return commonResponseDto;
			}

			boolean allMatch = false;
			for (AnswersEntity ans : answerEntity) {
				allMatch = commonRequestDto.getAnswerDto().stream()
						.anyMatch(x -> x.getAnswer().equalsIgnoreCase(ans.getAnswer()));
				if (!allMatch) {
					commonResponseDto.setStatus(CommonConstants.ERROR);
					commonResponseDto.setMessage("Answers Not Match.. " + commonRequestDto.getUserId());
					return commonResponseDto;
				}
			}

			if (allMatch) {
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(userEntity, userDto);
				commonResponseDto.setResponseData(userDto);
				commonResponseDto.setStatus(CommonConstants.SUCCESS);
			}

		} catch (Exception e) {
			commonResponseDto.setStatus(CommonConstants.ERROR);
			commonResponseDto.setMessage(CommonConstants.INTERNALERROR);
			e.printStackTrace();
		}
		return commonResponseDto;
	}

}