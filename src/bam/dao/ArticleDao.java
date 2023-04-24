package bam.dao;

import java.util.ArrayList;
import java.util.List;

import bam.dto.Article;

public class ArticleDao extends Dao {
	private List<Article> articles;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
	}
	
	public void add(Article article) {
		articles.add(article);
		lastId++;
	}
	
	public void remove(Article article) {
		articles.remove(article);
	}
	
	public Article foundArticleById(int id) {
		if(foundIndexById(id) == -1) return null;
		return articles.get(foundIndexById(id));
	}
	
	public int foundIndexById(int id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == id)
				return i;
		}
		return -1;
	}

	public int getTarget(String cmd) {
		String targetStr = cmd.split(" ")[2];
		if (isNumber(targetStr) == false)
			return -1;
		return Integer.parseInt(targetStr);
	}

	public boolean isNumber(String strValue) {
		return strValue.matches("[-+]?\\d*\\.?\\d+");
	}
	
	public void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			Article article = new Article(setLastId(), i, title, body);
			add(article);
		}
	}

	public List<Article> getArticleList(String cmd) {
		List<Article> foundList = new ArrayList<>();
		List<Article> articleList = new ArrayList<>();
		articleList.addAll(articles);
		if (cmd.startsWith("article list ")) {
			String target = cmd.substring("article list".length()).trim();
			for (Article article : articleList) {
				if (article.title.contains(target))
					foundList.add(article);
			}
		} else foundList = articleList;
		return foundList;
	}
}
