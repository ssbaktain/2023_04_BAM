package bam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.dto.Article;

public class App {
	private static List<Article> articles;
	private static int writingCount;

	public App() {
		articles = new ArrayList<Article>();
		writingCount = 0;
	}

	public void run() {
		
	Scanner sc = new Scanner(System.in);

	String cmd = "";

	System.out.println("== 프로그램 시작 ==");

	makeTestData();

	while(true) {
		System.out.print("\n명령어) ");
		cmd = sc.nextLine().trim();

		if (cmd.equals("exit"))
			break;

		if (cmd.equals("article write")) {
			System.out.println("== 게시물 작성 ==");
			System.out.print("제목 : ");
			String title = sc.nextLine();
			System.out.print("내용 : ");
			String body = sc.nextLine();

			Article article = new Article(++writingCount, title, body);

			articles.add(article);
			System.out.println(article.id + "번 글이 생성되었습니다.");
		} else if (cmd.startsWith("article modify ")) {
			int target = getTarget(cmd);
			if (target == -1) {
				System.out.println("'article modify' 뒤에는 숫자만 올 수 있습니다.");
				continue;
			}
			Article article = foundArticleById(target);

			if (article == null) {
				System.out.println(target + "번 게시물은 존재하지 않습니다.");
				continue;
			}
			System.out.println("수정할 항목을 선택하세요. (제목 / 내용 / 전체)");
			while (true) {
				System.out.print(">> ");
				String modifyTarget = sc.nextLine();
				if (modifyTarget.equals("제목")) {
					System.out.print("수정할 제목 : ");
					article.title = sc.nextLine();
				} else if (modifyTarget.equals("내용")) {
					System.out.print("수정할 내용 : ");
					article.body = sc.nextLine();
				} else if (modifyTarget.equals("전체")) {
					System.out.print("수정할 제목 : ");
					article.title = sc.nextLine();
					System.out.print("수정할 내용 : ");
					article.body = sc.nextLine();
				} else {
					System.out.println("(제목 / 내용 / 전체) 중에 선택해주세요.");
					continue;
				}
				break;
			}
			System.out.println(article.id + "번 게시물이 수정되었습니다.");
		} else if (cmd.startsWith("article delete ")) {
			int target = getTarget(cmd);
			if (target == -1) {
				System.out.println("'article delete' 뒤에는 숫자만 올 수 있습니다.");
				continue;
			}
			int index = foundIndexById(target);
			if (index == -1) {
				System.out.println(target + "번 게시물은 존재하지 않습니다.");
				continue;
			}
			articles.remove(index);
			System.out.println(target + "번 게시물이 삭제되었습니다.");
		} else if (cmd.startsWith("article list")) {
			List<Article> foundList = new ArrayList<>();
			if (cmd.startsWith("article list ")) {
				String target = cmd.substring("article list".length()).trim();
				for (Article article : articles) {
					if (article.title.contains(target))
						foundList.add(article);
				}
			} else foundList = articles;
			
			if (foundList.size() == 0) {
				System.out.println("존재하는 게시물이 없습니다.");
				continue;
			}

			System.out.println("== 게시물 목록 ==");
			System.out.println("번호	|		작성일			|	제목");
			for (int i = foundList.size() - 1; i >= 0; i--) {
				Article article = foundList.get(i);
				System.out.printf("%d	|	%s	|	%s\n", article.id, article.regDate, article.title);
			}
		} else if (cmd.startsWith("article detail ")) {
			int target = getTarget(cmd);
			if (target == -1) {
				System.out.println("'article detail' 뒤에는 숫자만 올 수 있습니다.");
				continue;
			}
			Article article = foundArticleById(target);
			if (article == null) {
				System.out.println(target + "번 게시물은 존재하지 않습니다.");
				continue;
			}
			System.out.println("== 게시물 상세보기 ==");
			System.out.printf("번호 : %d\n", article.id);
			System.out.printf("작성일 : %s\n", article.regDate);
			System.out.printf("제목 : %s\n", article.title);
			System.out.printf("내용 : %s\n", article.body);
		} else
			System.out.println("'" + cmd + "'는 존재하지 않는 명령어 입니다.");
	}sc.close();

	System.out.println("== 프로그램 끝 ==");
	}

	static Article foundArticleById(int id) {
		if(foundIndexById(id) == -1) return null;
		return articles.get(foundIndexById(id));
	}
	
	static int foundIndexById(int id) {
		for (int i = 0; i < App.articles.size(); i++) {
			if (articles.get(i).id == id)
				return i;
		}
		return -1;
	}

	static void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			Article article = new Article(++writingCount, title, body);
			articles.add(article);
		}
		System.out.println("테스트용 게시물 데이터를 5개 생성하였습니다.");
	}
	
	static int getTarget(String cmd) {
		String targetStr = cmd.split(" ")[2];
		if (isNumber(targetStr) == false)
			return -1;
		return Integer.parseInt(targetStr);
	}

	static boolean isNumber(String strValue) {
		return strValue.matches("[-+]?\\d*\\.?\\d+");
	}
}