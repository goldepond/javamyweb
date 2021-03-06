package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.FreeReplyVO;
import com.team404.command.TestVO;

@RestController // 레스트 컨트롤러 = 비동기 컨트롤러 @RequestBody + @ResponseBody
//RestController는 기본적으로 return에 담기는 값이 리졸버뷰로 가는게 아니고 요청된 주소로 반환
// Rest API에서는 (produces키워드로 보내는 데어터에 대한 내용)+(consumers 전달받는 데이터에 대한 내용) ??
public class RestBasicController {

	// @RequestMapping (value = "/getText", method=RequestMethod.GET, produces =
	// "text/plain")
	// @RequestMapping (벨류값에 온 getText를 자동적으로 안내, get방식으로 전송함, Content-Type정의)

	@GetMapping(value = "/getText", produces = "text/plain")
	// @GetMapping (벨류값에 온 getText를 자동적으로 안내, Content-Type정의
	// Content-Type으로는 (application/JSON) / (text/plain) / (application/text)등
	// 여러가지있음

	// @GetMapping = @RequestMapping(method = RequestMethod.GET)
	// @GetMapping은 줄임말
	public String getText() {
		return "hellow world";
	}

	// 자바에서 json형식으로 받고 json형식으로 보낼떄 jackson라이브러리 반드시 필요
	// json형식의 데이터라면 produces내용 생략 가능
	@GetMapping(value = "/getObject", produces = "application/json")
	public TestVO getObject() {
		return new TestVO("김이택", "24", "2021", 1998);
	}

	@GetMapping(value = "/getCollection")
	public ArrayList<TestVO> getCollection(@RequestParam("num") String num) {
//		System.out.println(num);

//		System.out.println(num + "가나다");
		// ====================↓더미데이터=============================
		ArrayList<TestVO> list = new ArrayList<TestVO>();
		for (int i = 1; i <= 10; i++) {
			TestVO vo = new TestVO("김이택", "24", "2021", 1998);
			list.add(vo);
		}
		return list;
		// ====================↑더미데이터=============================
	}

	// 값/값/값형태로 받고 Map으로 변환
	@GetMapping("/getPaht/{sort}/{rank}/{page}")
	public HashMap<String, TestVO> getPath(@PathVariable("sort") String sort, @PathVariable("rank") String rank,
			@PathVariable("page") int page) {
//		System.out.println(sort + " sort");
//		System.out.println(rank + " rank");
//		System.out.println(page + " page");

		HashMap<String, TestVO> map = new HashMap<String, TestVO>();

		TestVO vo = new TestVO("김이택", "24", "2021", 1998);
		map.put("info", vo);
		return map;

	}

	// 포스트형식의 JSON형식의 데이터를 받는다
	// consumes = 명시한 데이터형식이 아니면 consumer가 뭘 데이터를 절달하든 안받음
	// @RequestBody = 요청받은 키값을 뽑아서 vo에 자동으로 매핑하는 태그
	@PostMapping(value = "/getJson", consumes = "application/json")
	public ArrayList<TestVO> getJSON(@RequestBody TestVO vo) {
		System.out.println(vo.toString());

		ArrayList<TestVO> list = new ArrayList<TestVO>();
		TestVO t = new TestVO("김이택", "24", "2021", 1998);
		list.add(t);
		return list;

	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//(Cross-Origin Resource Sharing,CORS) 란 다른 출처의 자원을 공유할 수 있도록 설정하는 권한 체제를 말합니다.
	//서로 다른 서버에 대한 허용
	@CrossOrigin(origins = "*")
	//consumes = application.json  => json으로 보내라
	//produces = "application/json" => json으로 보낸다
	@PostMapping(value = "/getAjax", consumes = "application/json", produces = "application/json")
	public ArrayList<TestVO> getAjax(@RequestBody TestVO vo) {
		System.out.println(vo.toString());

		ArrayList<TestVO> list = new ArrayList<TestVO>();
//		TestVO t = new TestVO("김이택", "24", "2021", 1998);
		list.add(vo);
//		list.add(t);
//		System.out.println(list.toString());
		return list;

	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//xml형식으로 반환 jackson -xml라이브러리 반드시 필요
	@CrossOrigin(origins = "*")
	//json형식으로 보내라 난 xml형식으로 반환한다
	@PostMapping(value = "/getXML", consumes = "application/json", produces = "application/xml")
	public ArrayList<TestVO> getXML(@RequestBody TestVO vo) {
		System.out.println(vo.toString());

		ArrayList<TestVO> list = new ArrayList<TestVO>();
//		TestVO t = new TestVO("김이택", "24", "2021", 1998);
		list.add(vo);
//		list.add(t);
//		System.out.println(list.toString());
		return list;

	}
	
	@CrossOrigin(origins = "*")
	//json형식으로 보내라 난 xml형식으로 반환한다,
	@GetMapping(value = "/getTest/{bno}/{code}")
	public HashMap<String, Object> tESTXML(@PathVariable( "code") int code , @PathVariable("bno") int bno)
	{
		//
//		ArrayList<FreeReplyVO> list = AAA.getList(bno, code); 
		//사원코드 code, 관리자 인증키 bno를 받은뒤 받아 사원의 주소등을 합쳐 리스트로 반환할 함
		
		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("list",list);
		return map;

	}
	
	
}
