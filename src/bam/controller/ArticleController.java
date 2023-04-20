package bam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.dto.Article;

public class ArticleController extends Controller {
	private List<Article> articles;
	private Scanner sc;
	private int writingCount;
	private String cmd;

	
	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
		this.writingCount = 0;
	}
	
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		switch (methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
		case "detail":
			showDetail();
			break;
		default:
			System.out.println("명령어를 확인해주세요.");
			break;
		}
	}
	
	private void doWrite() {
		if (loginedUser == null) {
			System.out.println("현재 로그인 상태가 아닙니다.");
			return;
		}
		
		System.out.println("== 게시물 작성 ==");
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();
		
		Article article = new Article(++writingCount, loginedUser.id, title, body);
		
		articles.add(article);
		System.out.println(article.id + "번 글이 생성되었습니다.");
	}
	
	private void doModify() {
		if (cmd.split(" ").length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		int target = getTarget();
		if (target == -1) {
			System.out.println("'article modify' 뒤에는 숫자만 올 수 있습니다.");
			return;
		}
		Article article = foundArticleById(target);

		if (article == null) {
			System.out.println(target + "번 게시물은 존재하지 않습니다.");
			return;
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
	}
	
	private void doDelete() {
		if (cmd.split(" ").length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		int target = getTarget();
		if (target == -1) {
			System.out.println("'article delete' 뒤에는 숫자만 올 수 있습니다.");
			return;
		}
		int index = foundIndexById(target);
		if (index == -1) {
			System.out.println(target + "번 게시물은 존재하지 않습니다.");
			return;
		}
		articles.remove(index);
		System.out.println(target + "번 게시물이 삭제되었습니다.");
	}
	
	private void showList() {
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
			return;
		}

		System.out.println("== 게시물 목록 ==");
		System.out.println("번호	|		작성일			|	제목");
		for (int i = foundList.size() - 1; i >= 0; i--) {
			Article article = foundList.get(i);
			System.out.printf("%d	|	%s	|	%s\n", article.id, article.regDate, article.title);
		}
	}
	
	private void showDetail() {
		if (cmd.split(" ").length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		int target = getTarget();
		if (target == -1) {
			System.out.println("'article detail' 뒤에는 숫자만 올 수 있습니다.");
			return;
		}
		Article article = foundArticleById(target);
		if (article == null) {
			System.out.println(target + "번 게시물은 존재하지 않습니다.");
			return;
		}
		System.out.println("== 게시물 상세보기 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성일 : %s\n", article.regDate);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
	}
	
	private Article foundArticleById(int id) {
		if(foundIndexById(id) == -1) return null;
		return articles.get(foundIndexById(id));
	}
	
	private int foundIndexById(int id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == id)
				return i;
		}
		return -1;
	}

	private int getTarget() {
		String targetStr = cmd.split(" ")[2];
		if (isNumber(targetStr) == false)
			return -1;
		return Integer.parseInt(targetStr);
	}

	private boolean isNumber(String strValue) {
		return strValue.matches("[-+]?\\d*\\.?\\d+");
	}
	
	public void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			Article article = new Article(++writingCount, i, title, body);
			articles.add(article);
		}
		System.out.println("테스트용 게시물 데이터를 5개 생성하였습니다.");
	}

}