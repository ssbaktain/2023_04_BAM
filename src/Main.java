import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String cmd = "";
		
		System.out.println("== 프로그램 시작 ==");
		while(true) {
			System.out.print("\n명령어) ");
			cmd = sc.nextLine();
			
			if(cmd.equals("exit")) break;
			
			
		}
		sc.close();
		
		System.out.println("== 프로그램 끝 ==");
	}
}