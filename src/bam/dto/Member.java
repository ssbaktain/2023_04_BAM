package bam.dto;

import bam.util.Util;

public class Member {
	public int id;
	public String regDate;
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(int id, String memberId, String memberPw, String memberName) {
		this.id = id;
		this.regDate = Util.getDateStr();
		this.loginId = memberId;
		this.loginPw = memberPw;
		this.name = memberName;
	}
}