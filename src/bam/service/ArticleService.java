package bam.service;

import java.util.List;

import bam.container.Container;
import bam.dao.ArticleDao;
import bam.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	
	public int setLastId() {
		return articleDao.setLastId();
	}

	public void add(Article article) {
		articleDao.add(article);
	}
	
	public void remove(Article article) {
		articleDao.remove(article);
	}

	public int getTarget(String cmd) {
		return articleDao.getTarget(cmd);
	}

	public Article foundArticleById(int target) {
		return articleDao.foundArticleById(target);
	}

	public int foundIndexById(int target) {
		return articleDao.foundIndexById(target);
	}

	public List<Article> getArticleList(String cmd) {
		return articleDao.getArticleList(cmd);
	}

	public void makeTestData() {
		articleDao.makeTestData();
		System.out.println("테스트용 게시물 데이터를 5개 생성하였습니다.");
	}
}
