package pmservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pmmodel.pmBoard;

@Component
public class pmBoardMybatisDao {

	@Autowired
	SqlSessionTemplate session;

	private static final String ns = "pmboard.";
	private Map map = new HashMap();

	public int boardCount(String boardid) {

		int num = session.selectOne(ns + "boardCount", boardid);

		return num;
	}

	public List<pmBoard> boardList(int pageInt, int limit, String boardid) {

		map.clear();
		map.put("boardid", boardid);
		map.put("start", (pageInt - 1) * limit + 1);
		map.put("end", (pageInt * limit));
		List<pmBoard> list = session.selectList(ns + "boardList", map);

		return list;
	}

	public int insertBoard(pmBoard board) {

		int num = session.insert(ns + "insertBoard", board);

		return num;
	}

	public pmBoard boardOne(int num) {

		pmBoard board = session.selectOne(ns + "boardOne", num);

		return board;
	}

	public void readCountUp(int num) {

		session.update(ns + "readCountUp", num);

	}

	public void refStepAdd(int ref, int refstep) {

		map.clear();
		map.put("ref", ref);
		map.put("refstep", refstep);
		session.update(ns + "refStepAdd", map);

	}

	public int insertReply(pmBoard board) {

		int num = session.insert(ns + "insertReply", board);

		return num;
	}

	public int boardUpdate(pmBoard board) {

		int num = session.update(ns + "boardUpdate", board);

		return num;
	}

	public int boardDelete(int num) {

		int n = session.delete(ns + "pmboardDelete", num);

		return n;
	}

}
