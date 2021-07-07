package com.team404.controller;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
	private SnsBoardService snsBoardService;

	@RequestMapping("/upload")
	public String snsupload() {
		return "freeBoard/freeRegist";
	}

	@RequestMapping("/snsList")
	public String snsList() {
		return "snsBoard/snsList";
	}

	// @RequestParam("writer") String writer,
	@ResponseBody
	@RequestMapping(value = "/snsUpload", method = RequestMethod.POST)
	public String snsupload(@RequestParam("content") String content, @RequestParam("file") MultipartFile file,
			HttpSession session) {

		UsersVO usersVO = (UsersVO) session.getAttribute("userVO");

		try {
			String writer = "test";// usersVO.getUserID();
			System.out.println(file);
			System.out.println(content);

			Date data = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String fileLoca = sdf.format(data);
			File folder = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileLoca);
			if (!folder.exists()) {
				folder.mkdir();
			}
			String fileRealName = file.getOriginalFilename();
			Long size = file.getSize();
			// 저장된 전체 경로
			String uploadPath = folder.getPath();

			String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());

			UUID uuid = UUID.randomUUID();
			String uuids = uuid.toString().replaceAll("-", "");
			String fileName = uuids + fileExtention;

			System.out.println("폴더위치 " + fileLoca);
			System.out.println("원본파일명" + fileRealName);
			System.out.println("사이즈" + size);
			System.out.println("업로드경로" + uploadPath);
			System.out.println("업로드파일명" + fileName + fileExtention);

			File saveFile = new File(uploadPath + "\\" + fileName);
			System.out.println("1");
			file.transferTo(saveFile);
			; // 파일 쓰기
			System.out.println("2");

			SnsBoardVo vo = new SnsBoardVo(0, writer, content, uploadPath, fileLoca, fileName, fileRealName, null);
			System.out.println("3");
			int result = snsBoardService.insert(vo);
			System.out.println(result);
			if (result == 1) {
				return "success";

			} else {
				return "fail";
			}

		} catch (NullPointerException e) {
			return "idFail";
		} catch (Exception e) {
			return "fail";
		}
	}
	
	
	
	@ResponseBody
	@RequestMapping( value = "/getList",method = RequestMethod.GET)
	public ArrayList<SnsBoardVo> getList(){
		ArrayList<SnsBoardVo> list =  snsBoardService.getList();
		return list;
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/view/{fileLoca}/{fileName.+}")
//	public byte[] view (@PathVariable("fileLoca") String fileLoca,
//						@PathVariable("fileName") String fileName) {
//		System.out.println(fileLoca);
//		System.out.println(fileName);
//		System.out.println("12312");
//		//파일데이터 바이트 데이터로 변환해서 반환
//		byte[] result = null;
//		try {
//			File file = new File(APP_CONSTANT.UPLOAD_PATH+"\\"+fileLoca +"\\"+fileName);
//			result = FileCopyUtils.copyToByteArray(file);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//	
	
	@ResponseBody
	@RequestMapping(value = "/view/{fileLoca}/{fileName:.+}")
	public ResponseEntity<byte[]> view (@PathVariable("fileLoca") String fileLoca,
						@PathVariable("fileName") String fileName) {
		//파일데이터 바이트 데이터로 변환해서 반환
		
		ResponseEntity<byte[]> result = null;
		try {
			File file = new File(APP_CONSTANT.UPLOAD_PATH+"\\"+fileLoca +"\\"+fileName);;
			HttpHeaders header  = new HttpHeaders();
			header.add("Content-type",Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/download/{fileLoca}/{fileName:.+}")
	public ResponseEntity<byte[]> download (@PathVariable("fileLoca") String fileLoca,
						@PathVariable("fileName") String fileName) {
		//파일데이터 바이트 데이터로 변환해서 반환
		
		ResponseEntity<byte[]> result = null;
		try {
			File file = new File(APP_CONSTANT.UPLOAD_PATH+"\\"+fileLoca +"\\"+fileName);;
			HttpHeaders header  = new HttpHeaders();
			header.add("Content-Disposition","attachment; filename="+fileName);
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
}
