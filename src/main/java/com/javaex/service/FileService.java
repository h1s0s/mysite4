package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileDao;

@Service
public class FileService {

	@Autowired
	FileDao fileDao;

	public String restore(MultipartFile file) {
		System.out.println("[FileService.restore()]");
		String saveDir = "/Users/hs/JavaStudy/upload/";

		System.out.println(file.getOriginalFilename());

		// 파일을 하드디스크에 저장(운영내용)

		// 원본파일이름
		String orgName = file.getOriginalFilename();

		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		// lastIndexOf 뒤에서부터 지정한 글자를 찾아 그 index를 리턴해주는 메소드
		// substring 지정한 인덱스에서 끊는 메소드

		// 저장파일이름(겹치지 않게)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		// System.currentTimeMillis() 현재 시간을 글자로 출력
		// UUID 겹치지 않을 코드를 임의로 뽑아줌

		// 파일패스 생성
		String filePath = saveDir + saveName;

		// 파일 사이즈
		long fileSize = file.getSize();// long

		// 파일 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);// 어떤 경로에 파일을 저장할건지?
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// DB에 저장
		
		
		//과제
		
		return saveName;
	}
}
