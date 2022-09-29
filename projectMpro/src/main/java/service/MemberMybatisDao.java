package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Member;

@Repository
public class MemberMybatisDao {
	
	private static final String ns = "member.";
	
	@Autowired
	private SqlSessionTemplate session;
	
	public int insertMember(Member mem) {
		int num = session.insert(ns + "insertMember", mem);	
		
		return num;		
	}
	
	public Member selectOne(String id) {
		Member member = session.selectOne(ns + "selectOne", id);
		
		return member;
	}
	
		
	public int updateMember(Member mem) {
		int num = session.update(ns + "updateMember", mem);	
		
		return num;				
	}	
	
	public int deleteMember(String id) {
		int num = session.update(ns + "deleteMember", id);	
		
		return num;		
	}
	
	public int changePass(String id, String pass) {		
		Map map = new HashMap();
		map.put("id", id);
		map.put("pass", pass);
		
		int num = session.update(ns + "changePass", map);	
		
		return num;		
	}

	public List<Member> memberList() {
		List<Member> list = session.selectList(ns + "memberList");	
		
		return list;				
	}
	
} //end memberDao
