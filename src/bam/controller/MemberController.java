package bam.controller;

import java.util.List;
import java.util.Scanner;

import bam.container.Container;
import bam.dto.Member;
import bam.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;
	private Scanner sc;
	
	public MemberController(Scanner sc) {
		this.memberService = Container.memberService;
		this.sc = sc;
	}
	
	public void doAction(String cmd, String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "list":
			showList(cmd);
			break;
		default:
			System.out.println("명령어를 확인해주세요.");
			break;
		}
	}

	private void doJoin() {
		String userId;
		String userPw;
		String userName;
		System.out.println("== 회원가입 ==");
		int id = memberService.setLastId();
		while (true) {
			System.out.print("로그인 아이디 : ");
			userId = sc.nextLine();
			Member member = memberService.getMemberByLoginId(userId);
			if (member != null) {
				System.out.println("이미 존재하는 아이디입니다.");
				continue;
			}
			System.out.println("사용 가능한 아이디입니다.");
			
			while (true) {
				System.out.print("로그인 비밀번호 : ");
				userPw = sc.nextLine();
				if (userPw.contains(userId) || userId.contains(userPw)) {
					System.out.println("비밀번호는 아이디를 포함할 수 없습니다.");
					continue;
				}
				System.out.print("로그인 비밀번호 확인 : ");
				if (userPw.equals(sc.nextLine()) == false) {
					System.out.println("비밀번호가 일치하지 않습니다.");
					continue;
				}
				break;
			}
			break;
		}
		System.out.print("이름 : ");
		userName = sc.nextLine();
		
		Member member = new Member(id, userId, userPw, userName);
		memberService.add(member);
		System.out.println(member.name + " 회원님이 가입되었습니다.");
	}
	
	private void doLogin() {
		System.out.print("로그인 아이디 : ");
		String loginId = sc.nextLine();
		
		Member member = memberService.getMemberByLoginId(loginId);
		if (member == null) {
			System.out.println("일치하는 아이디가 없습니다.");
			return;
		}
		System.out.print("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();
		if (member.loginPw.equals(loginPw)) {
			loginedUser = member;
			System.out.println(loginedUser.name + "님 환영합니다.");
			return;
		}
		System.out.println("비밀번호가 일치하지 않습니다.");
	}
	
	private void doLogout() {
		loginedUser = null;
		System.out.println("로그아웃 되었습니다.");
	}

	private void showList(String cmd) {
		List<Member> foundList = memberService.getMemberList(cmd);
		
		if (foundList.size() == 0) {
			System.out.println("존재하는 유저가 없습니다.");
			return;
		}
		
		System.out.println("== 유저 리스트 ==");
		System.out.println("번호	|		가입일			|	아이디		|	비밀번호	|	이름");
		for (int i = foundList.size() - 1; i >= 0; i--) {
			Member member = foundList.get(i);
			System.out.printf("%d	|	%s	|	%s		|	%s		|	%s\n", member.id, member.regDate, member.loginId, member.loginPw, member.name);
		}
	}
	
	public void makeTestData() {
		memberService.makeTestData();
	}
}
