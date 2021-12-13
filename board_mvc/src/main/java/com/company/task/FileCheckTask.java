package com.company.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.company.domain.AttachFileDTO;
import com.company.mapper.BoardAttachMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FileCheckTask {
	
	@Autowired
	private BoardAttachMapper attach;
	
	@Scheduled(cron="0 0 3 * * *")
	public void checkFile() {
		log.info("file Check 실행 .....");
		
		// 데이터 베이스 어제 날짜 파일 리스트 가져오기
		List<AttachFileDTO> yesterList =  attach.getYesterDayFiles();
		
		//AttachFileDTO 값들을 경로명으로 변경하는 작업
		List<Path> fileListPaths = yesterList.stream()
				.map(dto -> Paths.get("c:\\upload",dto.getUploadPath(),dto.getUuid()+"_"+dto.getFileName()))
				.collect(Collectors.toList());
		// 썸네일 이미지로 경로 추출 
		yesterList.stream().filter(dto -> dto.isFileType() == true)
		.map(dto -> Paths.get("c:\\upload",dto.getUploadPath(),"s_"+dto.getUuid()+"_"+dto.getFileName()))
		.forEach(p -> fileListPaths.add(p));
		
		// 폴더 목록과 데이터베이스 파일 목록 비교 
		// 어제날짜 폴더 구하기
		File targetDir = Paths.get("c:\\upload", getYesterDay()).toFile();
		
		// targetDri에 접근 후 파일 목록을 추출하여 데이터 베이스 파일 목록과 일치하지 않은 
		// 파일 정보 담기
		File[] removeFiles= targetDir.listFiles(f -> fileListPaths.contains(f.toPath()) == false); 
		
		// 파일 삭제
		for(File file : removeFiles) {
			log.info(file.getAbsolutePath());
			file.delete();
		}
	}
	
	// 어제 날짜 구하기
	public String getYesterDay() {
		LocalDate yesterDay = LocalDate.now().minusDays(1); // 하루 전 날짜를 가져오는 코드
		
		String str = yesterDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return str.replace("-", File.separator);// 2021\12\09
	}
}
