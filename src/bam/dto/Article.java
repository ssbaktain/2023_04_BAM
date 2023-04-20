package bam.dto;

import bam.util.Util;

public class Article {
	public int id;
	public int memberId;
	public String title;
	public String body;
	public String regDate;
	
	public Article(int id, int memberId, String title, String body) {
		this.id = id;
		this.memberId = memberId;
		this.regDate = Util.getDateStr();
		this.title = title;
		this.body = body;
	}
}