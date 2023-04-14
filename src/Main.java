import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static int writingCount = 0;

	public static List<Article> articles;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		articles = new ArrayList<Article>();
		String cmd = "";

		System.out.println("== 프로그램 시작 ==");
		while (true) {
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
			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				System.out.println("== 게시물 목록 ==");
				System.out.println("번호	|	제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s\n", article.id, article.title);
				}
			} else if (cmd.startsWith("article detail ")) {
				int target = Integer.parseInt(cmd.split(" ")[2]);
				
				Article article = foundArticleById(target);
				
				if (article == null) {
					System.out.println(target + "번 게시물은 존재하지 않습니다.");
					continue;
				}
				System.out.println("== 게시물 상세보기 ==");
				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
			} else
				System.out.println("'" + cmd + "'는 존재하지 않는 명령어 입니다.");
		}
		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}
	
	static Article foundArticleById(int id) {
		for(Article article : Main.articles) {
			if (article.id == id)
				return article;
		}
		return null;
	}
}

class Article {
	int id;
	String title;
	String body;

	Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}