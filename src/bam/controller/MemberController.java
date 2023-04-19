package bam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.dto.Member;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private int memberCount;
	private String cmd;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		this.memberCount = 0;
	}
	
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "list":
			showList();
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
			while (true) {
				System.out.print("로그인 아이디 : ");
				userId = sc.nextLine();
				boolean isMemberIdUsed = false;
				for (Member member : members) {
					if (member.memberId.equals(userId)) {
						System.out.println("이미 존재하는 아이디입니다.");
						isMemberIdUsed = true;
						break;
					}
				}
				if (isMemberIdUsed)
					continue;
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
			members.add(new Member(++memberCount, userId, userPw, userName));
			System.out.println(members.get(memberCount - 1).memberId + "회원님이 가입되었습니다.");
		
	}
	
	private void showList() {
		List<Member> foundList = new ArrayList<>();
		if (cmd.startsWith("member list ")) {
			String target = cmd.substring("member list".length()).trim();
			for (Member member : members) {
				if (member.memberId.contains(target))
					foundList.add(member);
			}
		} else foundList = members;
		
		if (foundList.size() == 0) {
			System.out.println("존재하는 유저가 없습니다.");
			return;
		}
		
		System.out.println("== 유저 리스트 ==");
		System.out.println("번호	|		가입일			|	아이디		|	비밀번호	|	이름");
		for (int i = foundList.size() - 1; i >= 0; i--) {
			Member member = foundList.get(i);
			System.out.printf("%d	|	%s	|	%s		|	%s		|	%s\n", member.id, member.regDate, member.memberId, member.memberPw, member.memberName);
		}
	}
	
	public void makeTestUserData() {
		for (int i = 1; i <= 5; i++) {
			String userId = "test" + i;
			String userPw = "test" + i;
			String userName = "유저" + i;
			Member member = new Member(++memberCount, userId, userPw, userName);
			members.add(member);
		}
		System.out.println("테스트용 유저 데이터를 5개 생성하였습니다.");
	}
}