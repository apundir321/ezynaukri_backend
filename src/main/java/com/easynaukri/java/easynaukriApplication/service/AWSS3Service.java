package com.easynaukri.java.easynaukriApplication.service;

import java.io.ByteArrayOutputStream;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

	void uploadFile(MultipartFile multipartFile,String userId)  throws Exception;
	
	void uploadGenericFile(MultipartFile multipartFile)  throws Exception;
	
	public ByteArrayOutputStream downloadFile(String keyName);
	
	
}
