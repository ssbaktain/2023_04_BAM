package bam.container;

import bam.dao.ArticleDao;
import bam.dao.MemberDao;
import bam.service.ArticleService;
import bam.service.MemberService;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberService memberService;
	public static MemberDao memberDao;
	
	static {
		articleService = new ArticleService();
		articleDao = new ArticleDao();
		memberService = new MemberService();
		memberDao = new MemberDao();
	}
}
