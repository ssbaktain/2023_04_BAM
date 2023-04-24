package bam.service;

import java.util.List;

import bam.container.Container;
import bam.dao.MemberDao;
import bam.dto.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}
	
	public int setLastId() {
		return memberDao.setLastId();
	}
	
	public boolean isLoginIdDup(String loginId) {
		return memberDao.isLoginIdDup(loginId);
	}
	
	public void makeTestData() {
		memberDao.makeTestData();
		System.out.println("테스트용 유저 데이터를 5개 생성하였습니다.");
	}

	public Member getMemberByLoginId(String userId) {
		return memberDao.getMemberByLoginId(userId);
	}
	
	public Member getWriterById(int userId) {
		return memberDao.getWriterById(userId);
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public List<Member> getMemberList(String cmd) {
		return memberDao.getMemberList(cmd);
	}
}
