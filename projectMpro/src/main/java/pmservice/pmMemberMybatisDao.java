package pmservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pmmodel.pmMember;

@Repository
public class pmMemberMybatisDao {
	
	private static final String ns = "pmmember.";
	
	@Autowired
	private SqlSessionTemplate session;
	
	public int insertMember(pmMember mem) {
		int num = session.insert(ns + "insertMember", mem);	
		
		return num;		
	}
	
	public pmMember selectOne(String id) {
		pmMember member = session.selectOne(ns + "selectOne", id);
		
		return member;
	}
	
		
	public int updateMember(pmMember mem) {
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

	public List<pmMember> memberList() {
		List<pmMember> list = session.selectList(ns + "memberList");	
		
		return list;				
	}
	
} //end memberDao
