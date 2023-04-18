package bam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.controller.ArticleController;
import bam.controller.MemberController;
import bam.dto.Article;
import bam.dto.Member;

public class App {
	private static List<Member> members;
	private static List<Article> articles;

	public App() {
		members = new ArrayList<Member>();
		articles = new ArrayList<Article>();
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		String cmd = "";
	
		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);
	
		memberController.makeTestUserData();
		articleController.makeTestData();

		while(true) {
			System.out.print("\n명령어) ");
			cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit"))
				break;
			
			if (cmd.equals("member join")) {
				memberController.doJoin();
			} else if (cmd.startsWith("member list")) {
				memberController.memberList(cmd);
			} else if (cmd.equals("article write")) {
				articleController.doWrite();
		} else if (cmd.startsWith("article modify ")) {
			articleController.doModify(cmd);
		} else if (cmd.startsWith("article delete ")) {
			articleController.doDelete(cmd);
		} else if (cmd.startsWith("article list")) {
			articleController.showList(cmd);
		} else if (cmd.startsWith("article detail ")) {
			articleController.showDetail(cmd);
		} else
			System.out.println("'" + cmd + "'는 존재하지 않는 명령어 입니다.");
	}sc.close();

	System.out.println("== 프로그램 끝 ==");
	}
}