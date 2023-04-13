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
		while(true) {
			System.out.print("\n명령어) ");
			cmd = sc.nextLine();
			
			if(cmd.equals("exit")) break;
			
			if(cmd.equals("article write")) {
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				
				articles.add(new Article(++writingCount, title, body));
				System.out.println(articles.get(articles.size() - 1).id + "번 글이 생성되었습니다.");
			}
			
			
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