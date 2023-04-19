package bam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.controller.ArticleController;
import bam.controller.Controller;
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

		while (true) {
			System.out.print("\n명령어) ");
			cmd = sc.nextLine().trim();

			if (cmd.equals("exit"))
				break;

			String[] cmdBits = cmd.split(" ");
			
			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}
			
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			Controller controller = null;
			
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			controller.doAction(cmd, methodName);
			
		}
		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}
}