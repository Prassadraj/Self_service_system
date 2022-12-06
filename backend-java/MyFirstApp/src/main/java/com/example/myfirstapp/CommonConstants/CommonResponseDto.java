package com.example.myfirstapp.CommonConstants;

import java.io.Serializable;
import java.util.List;

public class CommonResponseDto<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T responseData;
	private String message;
	private String status;
	private String statusCode;
	private String error;
	private String errorCode;
	private List<String> errorDescriptions;
	private List<String> remarks;
	private String responseStatus;
	private String responseMessage;
	private String description;

	public CommonResponseDto() {
	}

	public CommonResponseDto(T responseData, String message, String status, String statusCode, String error,
			String errorCode) {
		super();
		this.responseData = responseData;
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
		this.error = error;
		this.errorCode = errorCode;
	}

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrorDescriptions() {
		return errorDescriptions;
	}

	public void setErrorDescriptions(List<String> errorDescriptions) {
		this.errorDescriptions = errorDescriptions;
	}

	public List<String> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

