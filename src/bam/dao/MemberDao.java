package bam.dao;

import java.util.ArrayList;
import java.util.List;

import bam.dto.Member;

public class MemberDao extends Dao {
	private List<Member> members;
	
	public MemberDao() {
		this.members = new ArrayList<>();
	}

	public boolean isLoginIdDup(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}
		return false;
	}

	public void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			String userId = "test" + i;
			String userPw = "test" + i;
			String userName = "유저" + i;
			Member member = new Member(setLastId(), userId, userPw, userName);
			members.add(member);
		}
	}

	public Member getMemberByLoginId(String userId) {
		for (Member member : members) {
			if (member.loginId.equals(userId)) {
				return member;
			}
		}
		return null;
	}

	public void add(Member member) {
		members.add(member);
		lastId++;
	}

	public List<Member> getMemberList(String cmd) {
		List<Member> foundList = new ArrayList<>();
		List<Member> memberList = new ArrayList<>();
		memberList.addAll(members);
		if (cmd.startsWith("member list ")) {
			String target = cmd.substring("member list".length()).trim();
			for (Member member : members) {
				if (member.loginId.contains(target))
					foundList.add(member);
			}
		} else foundList = memberList;
		return foundList;
	}
}
