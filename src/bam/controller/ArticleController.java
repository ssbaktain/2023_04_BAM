package bam.controller;

import java.util.List;
import java.util.Scanner;

import bam.container.Container;
import bam.dto.Article;
import bam.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private Scanner sc;
	private String cmd;

	public ArticleController(Scanner sc) {
		articleService = Container.articleService;
		this.sc = sc;
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
		System.out.println("== 게시물 작성 ==");
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();
		
		Article article = new Article(articleService.setLastId(), loginedUser.id, title, body);
		
		articleService.add(article);
		System.out.println(article.id + "번 글이 생성되었습니다.");
	}
	
	private void doModify() {
		if (cmd.split(" ").length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		int target = articleService.getTarget(cmd);
		if (target == -1) {
			System.out.println("'article modify' 뒤에는 숫자만 올 수 있습니다.");
			return;
		}
		Article article = articleService.foundArticleById(target);

		if (article == null) {
			System.out.println(target + "번 게시물은 존재하지 않습니다.");
			return;
		}
		if (loginedUser.id != article.memberId) {
			System.out.println("회원님의 게시물이 아닙니다.");
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
		int target = articleService.getTarget(cmd);
		if (target == -1) {
			System.out.println("'article delete' 뒤에는 숫자만 올 수 있습니다.");
			return;
		}
		int index = articleService.foundIndexById(target);
		Article targetArticle = articleService.foundArticleById(index);
		if (index == -1) {
			System.out.println(target + "번 게시물은 존재하지 않습니다.");
			return;
		}
		if (loginedUser.id != targetArticle.memberId) {
			System.out.println("회원님의 게시물이 아닙니다.");
			return;
		}
		articleService.remove(targetArticle);
		System.out.println(target + "번 게시물이 삭제되었습니다.");
	}
	
	private void showList() {
		List<Article> foundList = articleService.getArticleList(cmd);
		
		if (foundList.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}

		System.out.println("== 게시물 목록 ==");
		System.out.println("번호	|		작성일			|	제목	|	작성자");
		for (int i = foundList.size() - 1; i >= 0; i--) {
			Article article = foundList.get(i);
			System.out.printf("%d	|	%s	|	%s	|	%d\n", article.id, article.regDate, article.title, article.memberId);
		}
	}
	
	private void showDetail() {
		if (cmd.split(" ").length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		int target = articleService.getTarget(cmd);
		if (target == -1) {
			System.out.println("'article detail' 뒤에는 숫자만 올 수 있습니다.");
			return;
		}
		Article article = articleService.foundArticleById(target);
		if (article == null) {
			System.out.println(target + "번 게시물은 존재하지 않습니다.");
			return;
		}
		System.out.println("== 게시물 상세보기 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성일 : %s\n", article.regDate);
		System.out.printf("작성자 : %d\n", article.memberId);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
	}


	public void makeTestData() {
		articleService.makeTestData();
	}
}