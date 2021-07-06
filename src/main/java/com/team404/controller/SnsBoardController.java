package com.team404.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team404.command.SnsBoardVo;
import com.team404.command.UsersVO;
import com.team404.snsboard.service.SnsBoardService;

@Controller
@RequestMapping("/snsBoard")
public class SnsBoardController {
	
	
	@Autowired
	@Qualifier("SnsBoardService")
	private SnsBoardService  snsBoardService;
	
	@RequestMapping("/upload")
	public String snsupload() {
		return "freeBoard/freeRegist";
	}
	
	@RequestMapping("/snsList")
	public String snsList() {
		return "snsBoard/snsList";
	}
	
	//@RequestParam("writer") String writer,
	@ResponseBody
	@RequestMapping(value = "/snsUpload",method =RequestMethod.POST)
	public String snsupload(@RequestParam("content") String content, @RequestParam("file") MultipartFile file, HttpSession session) {

		UsersVO usersVO  = (UsersVO)session.getAttribute("userVO");
		

		
		
	try {
		String writer = "test";//usersVO.getUserID();
		System.out.println(file);	
		System.out.println(content);
		
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fileLoca = sdf.format(data);
		File folder = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileLoca);
		if(!folder.exists()) {
			folder.mkdir();
		}
		String fileRealName = file.getOriginalFilename();
		Long size = file.getSize();
		//저장된 전체 경로
		String uploadPath = folder.getPath();
		
		String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
		
		
		UUID uuid = UUID.randomUUID();
		String uuids  = uuid.toString().replaceAll("-","");
		String fileName = uuids + fileExtention;
		
		System.out.println("폴더위치 " + fileLoca);
		System.out.println("원본파일명" + fileRealName);
		System.out.println("사이즈" + size);
		System.out.println("업로드경로" + uploadPath);
		System.out.println("업로드파일명" + fileName+fileExtention);
		
		File saveFile = new File(uploadPath + "\\" + fileName);
		System.out.println("1");
		file.transferTo(saveFile);; //파일 쓰기
		System.out.println("2");
		
		SnsBoardVo vo = new SnsBoardVo(0, writer, content, uploadPath, fileLoca, fileName, fileRealName, null);
		System.out.println("3");
		int result = snsBoardService.insert(vo);
		System.out.println(result);
		if(result ==1) {
			return "success";
			
		}else {
			return "fail";
		}
		
		
		
		
		
	}catch (NullPointerException e) {
		return "idFail";
	}catch (Exception e) {
		return "fail";
	}
	
	
	
	
//		return "success";
		
	}
}
