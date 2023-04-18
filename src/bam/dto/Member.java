package bam.dto;

import bam.util.Util;

public class Member {
	public int id;
	public String regDate;
	public String memberId;
	public String memberPw;
	public String memberName;
	
	public Member(int id, String memberId, String memberPw, String memberName) {
		this.id = id;
		this.regDate = Util.getDateStr();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
	}
}