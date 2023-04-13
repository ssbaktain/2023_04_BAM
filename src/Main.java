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
			cmd = sc.nextLine();

			if (cmd.equals("exit"))
				break;

			if (cmd.equals("article write")) {
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();

				articles.add(new Article(++writingCount, title, body));
				System.out.println(articles.get(articles.size() - 1).id + "번 글이 생성되었습니다.");
			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				System.out.print("번호	|	제목\n");
				for (int i = articles.size() - 1; i >= 0; i--) {
					System.out.println(articles.get(i).id + "	|	" + articles.get(i).title);
				}
			} else
				System.out.println("'" + cmd + "'는 존재하지 않는 명령어입니다.");
		}
		sc.close();

		System.out.println("== 프로그램 끝 ==");
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