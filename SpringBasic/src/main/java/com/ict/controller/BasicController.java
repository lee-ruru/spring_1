package com.ict.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.controller.vo.UserVO;

import oracle.jdbc.proxy.annotation.Post;

//어노테이션에 네 종류가 있었는데(@Component, @Repository, @Controller, @Service)
//컨트롤러를 만드는 경우이니 당연히@Controller를 씁니다.
@Controller
public class BasicController {
	
	// RequestMapping의 value는 localhost:8181/어떤 주소로 접속시 해당 로직이 실행될지 결정합니다.
	// 아무것도 안 적으면 기본적으로 get방식을 허용합니다.
	// /goA
	@RequestMapping(value="/goA")
	// 아래 해당주소로 접속시 실행하고 싶은 메서드를 작성합니다.
	public String goA() {
		System.out.println("goA 접속이 감지되었습니다.");
		//return "goA" 라고 적으면 views폴더 내부의 goA.jsp파일을 보여줍니다.
		return "goA";
	}
	
	// /goB
	// /goB로 접속했을때 b.jsp 창이 열리도록 아래에 세팅해주세요.
	@RequestMapping(value="/goB")
	public String goB() {		
		return "b";
	}
	
	//여러분들의 성함 성씨기준(강사 기준 : "/chae")으로 패턴을 잡고
	//결과 페이지는 "XXX의 페이지 입니다." 라는 문장이 뜨도록 처리해서 메서드와 이노테이션을 저에게 보내주세요.
	@RequestMapping(value="/lee")
	public String ha() {		
		return "yeon";
	}
	
	// 외부에서 전송하는 데이터는 메서드 선언부에 선언된 변수로 받습니다.
	// 이름만 일치하면 알아서 받아옵니다.
	// 자료형을 신경쓸 필요가 없습니다.
	@RequestMapping(value="/getData", method=RequestMethod.POST)//localhost:8181/getData
					// getData?data1=데이터1&data2=데이터2 에 해당하는 요소를 받아옵니다.
	public String getData(String data1, int data2, Model model) {
		
		//String data1 = Integer.getParameter("data1"); //jsp때 데이터를 받아오는 방법
		//int data2 = Integer.ParseInt(strData2);//jsp에서 받아온 데이터를 다른 자료형으로 변환하는 방법
		System.out.println("data1에 든 값 : " + data1);
		System.out.println("data2에 든 값 : " + data2);
		System.out.println("data2가 정수임을 증명 : " + (data2+100));
		// data1, data2 변수를 getResult.jsp
		model.addAttribute("data1", data1);
		model.addAttribute("data2", data2);
		
		return "getResult";
	}
	
	// 외부에서 전송하는 데이터를 /getMoney 주소로 받아오겠습니다.
	// 이주소는 int won이라는 형식으로 금액을 받아서
	// 환율에 따른 환전 금액을 콘솔에 찍어줍니다. 환전 화폐는 임의로 정해주세요.
	// 결과 페이지는 exchange.jsp 로 하겠습니다.
	// 메서드명은 임의로 만들어주세요.
	@RequestMapping(value="/getMoney", method=RequestMethod.POST)//post 방식으로 받도록처리
					// 포워딩시 바인딩을 하고싶다면 Model을 선언합니다.
	public String getMoney(int won, Model model) {
		System.out.println("입력한 금액은 " + won + "원 입니다.");
		System.out.println("현재 바트화 환율은 36.16원당 1바트입니다.");
		System.out.println("입력한 금액에 따른 환전 금액은 " + (won / 36.16) + "바트입니다.");
		double result = (won / 36.16);
		// model.addAttribute("보낼이름", 보낼자료);
		// 넘어간 데이터는 .jsp파일에서 el을 이용해 출력합니다.
		// ex -> model.addAttribute("test", 자료); 로 바인딩한경우
		// ${test}로 .jsp에서 출력가능
		model.addAttribute("result", result);
		//won변수에 해당하는 변수도 추가로 보내보세요.
		model.addAttribute("won", won);
		
		// exchange.jsp를 타겟으로 하니 views폴더에 생성해주세요.
		return "exchange";
	}
	
	// form페이지와 결과페이지를 분리해야합니다.
	// 다만 목적지 주소가 .jsp기준이 아닌, @RequestMapping상의 주소기준으로 갑니다.
	// 주소 moneyForm으로 연결되도록 아래에 이노테이션 + 메서드를 구성해주세요.
	// moneyFrom.jsp에는 목적지를 #으로 하고
	// name=won 인 폼을 추가로 만들어주세요.
	
	// 1.@RequestMapping에 어떤 주소로 접속해야하는지 적는다
	@RequestMapping(value="/moneyForm")
	// 2. public String 메서드()를 만든다
	public String moneyForm() {
		//3. return구문 뒤에 연결한 .jsp파일의 이름을 적는다(확장자는 X)
		return "moneyForm";
		
	}
	
	// 상단 /dataForm을 감지해 dataForm.jsp로 보내주는 메서드를 만들어주세요.
	// data1, data2를 자료형에 맞게 폼으로 입력받아 전송버튼을 누르면
	// 해당 데이터가 결과 페이지에 나올 수 있도록 .jsp파일부터 시작해서
	// form태그나 세부 로직까지 완성시켜주세요.
	//1. 주소 및 연결 메서드 완성 후 보내주시고
	//2. form태그 완성후 보내주세요.
	@RequestMapping(value="/dataForm")
	public String dataForm() {
		return "dataForm";
	}
	
	
	// 스프링 5버전부터 허용
	// @요청 메서드Mapping은 해당 메서드만 허용하는 어노테이션입니다.
	@GetMapping(value="/onlyGet")
	public String onlyGet() {
		return "onlyGet";
	}
	
	//성적 입력 폼 접근 로직
	@GetMapping(value="/score")
	public String scoreForm() {
		return "scoreForm";
		
	}
		
	@PostMapping(value="/score")
	public String scoreResult(
			int math, 
			int english, 
			int language, 
			int social, 
			// computer -> com으로 입력하는 로직
			@RequestParam("computer") int com, 
			Model model) {
		int total = math + english + language + social + com;
		double avg = total / 5.0;
		//바인딩
		model.addAttribute("math", math);
		model.addAttribute("english", english);
		model.addAttribute("language", language);
		model.addAttribute("social", social);
		model.addAttribute("computer", com);
		
		model.addAttribute("total", total);
		model.addAttribute("avg", avg);
					
		return "scoreResult";
		
	}
	
	// 주소는 /page로 하겠습니다.
	// get방식 접속만 허용합니다.
	// 메서드명은 임의로 만들어 주세요.
	// page.jsp로 연결됩니다.
	@GetMapping(value="/page/{bookNum}/{pageNum}")
	public String getPage(@PathVariable int pageNum,
						@PathVariable int bookNum,
							Model model) {
		// page.jsp를 views폴더에 만들어주세요.
		// 해당 페이지는 int pageNum을 받아서 바인딩 합니다.
		model.addAttribute("page", pageNum);
		model.addAttribute("book", bookNum);
		// page.jsp 본문에 현재 ${page}페이지를 보고 계십니다.
		// 와 함께 입숨 더미데이터를 이용해 본문글을 채워주세요.

		return "page"; 
		
	}
	
	// 환율 계산기를 만들어 보겠습니다.
	// 단, 원화금액은 @PathVariable를 이용해 입력 받습니다.
	// 주소는 /rate입니다.
	// get방식으로 처리해주세요
	// 원화를 입력받으면 rate.jsp에서 결과로 한전 금액을 보여줍니다.
	@GetMapping(value="/rate/{won}")
	public String getRate(@PathVariable int won, Model model) {
		
		final double NTD_RATE = 42.29;
		double result = won / NTD_RATE;	
		model.addAttribute("won", won);
		model.addAttribute("result", result);

		return "rate";
	}
	
	// 리스트를 받아서 처리하기
	@GetMapping("/getList")
	public String getList(
			//배열자료 받을시 @RequestParam 사용이 강제됩니다.
			@RequestParam("array") ArrayList<String> array, 
			Model model) {
		System.out.println(array);
		// 리스트 자료형의 경우 같은 이름으로 여러 데이터를 연달아 보내면 처리가능합니다.
		model.addAttribute("array", array);
		return "getList";
	}
	
	//만약 주소와 메칭된 메서드의 리턴자료형의 String이 아닌 void로 처리하는경우
	// 지정주소 .jsp로 바로 연결됩니다.(view파일(.jsp파일) 이름 지정 불가)
	// 주소와 파일명이 일치한다면 써주셔도 되지만
	// 기본적으로는 String을 쓰는걸 추천드립니다.
	@GetMapping("/test")//test.jsp로 바로연결됨
	public void goTest() {
		//내부 실행문 없음
	}
	
	// VO를 활용해 회원 데이터를 받는 컨트롤러를 만들어보겠습니다.
	// /userInfo가 주소입니다.
	@PostMapping("/userInfo")
	public String getUserInfo(UserVO userVO, Model model) {
		// 변수명은 userVO로 저장했으나, 실제로는 내부 멤버 변수의 이름으로 데이터를 받습니다.
		
		// 바인딩 문법을 작성해주세요.
		model.addAttribute("userVO", userVO);
		return "user";//user.jsp에서 볼 수 있도록 el문법을 사용해주세요.
	}
	
	// userInfo 페이지를 만들어서 폼을 만들어
	// 상단의 userInfo로 보내게 해주세요.
	// userInfo로직은 post방식만 허용하게 해주시고
	// 폼페이지는 get방식만 허용하도록 수정합니다.
	@GetMapping(value="/userInfo")
	public String getUserForm() {
		return "userForm";
		
		
	}		 
	
}
