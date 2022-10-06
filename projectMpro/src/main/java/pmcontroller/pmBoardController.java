package pmcontroller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pmmodel.pmBoard;
import pmservice.pmBoardMybatisDao;

@Controller
@RequestMapping("/pmboard/")
public class pmBoardController {

	@Autowired
	pmBoardMybatisDao bd;

	HttpServletRequest request;
	Model m;
	HttpSession session;

	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		this.session = request.getSession();
	}

	public pmBoardController() throws Exception {
		System.out.println("pmboard");
	}

	@RequestMapping("welcome")
	public String welcome() throws Exception {
		System.out.println("welcome");
		request.setAttribute("welcome", "pmboard 입니다");
		return "/welcome";
	}

	@RequestMapping("pmboardForm")
	public String pmboardForm() throws Exception {

		return "pmboard/pmboardForm";
	}

	@RequestMapping("pmboardPro")
	public String pmboardPro(@RequestParam("uploadfile") MultipartFile multipartFile, pmBoard board) throws Exception {
		String path = request.getServletContext().getRealPath("/") + "view/pmboard/pmimg";

		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			board.setPmbfile1(multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);

		} else {
			board.setPmbfile1("");
		}

		String filename = null;
		String msg = "게시물 등록 실패.";
		String url = "/pmboard/pmboardForm";

		HttpSession session = request.getSession();
		String boardid = (String) session.getAttribute("boardid");
		if (boardid == null)
			boardid = "1";
		board.setPmbboardid(boardid);
		int num = bd.insertBoard(board);  
 
		if (num > 0) {
			msg = "게시물 등록 성공";
			url = "/pmboard/pmboardList";
		}

		System.out.println(board);

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmboardList")
	public String pmboardList() throws Exception {
		HttpSession session = request.getSession();

		if (request.getParameter("pmboardid") != null) {
			session.setAttribute("pmboardid", request.getParameter("pmboardid"));
			session.setAttribute("pageNum", "1");

		}

		String boardid = (String) session.getAttribute("boardid");
		if (boardid == null)
			boardid = "1";

		if (request.getParameter("pageNum") != null) {
			session.setAttribute("pageNum", request.getParameter("pageNum"));
		}

		int limit = 5; // 한 page 당 게시물 갯수

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";
		int pageInt = Integer.parseInt(pageNum);

		/*
		 * 1 page : 1,2,3 startPage : (pageInt-1)*limit+1, endPage : startPage + limit -
		 * 1 2 : 4,5,6 s: 4 , e: 6 3 : 7,8,9 s: 7 ,e: 9
		 */
//		int startPage = (pageInt -1) * limit + 1;
//		int endPage = startPage + limit - 1;

		int boardCount = bd.boardCount(boardid);
		List<pmBoard> list = bd.boardList(pageInt, limit, boardid);
		// 페이징 작업
		int bottomLine = 3;
		int start = (pageInt - 1) / bottomLine * bottomLine + 1;
		int end = start + bottomLine - 1;
		int maxPage = (boardCount / limit) + (boardCount % limit == 0 ? 0 : 1);
		if (end > maxPage)
			end = maxPage;

		String boardName = "";
		switch (boardid) {
		case "1":
			boardName = "공지사항";
			break;
		case "2":
			boardName = "자유게시판";
			break;
		case "3":
			boardName = "QnA";
			break;
		}

		int boardNum = boardCount - (pageInt - 1) * limit;

		request.setAttribute("maxPage", maxPage);
		request.setAttribute("pageInt", pageInt);
		request.setAttribute("list", list);
		request.setAttribute("boardName", boardName);
		request.setAttribute("boardCount", boardCount);
		request.setAttribute("boardNum", boardNum);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("bottomLine", bottomLine);

		return "pmboard/pmboardList";
	}

	@RequestMapping("pmboardInfo")
	public String pmboardInfo(int num) throws Exception {
		pmBoard board = bd.boardOne(num);
		bd.readCountUp(num);

		request.setAttribute("b", board);
		return "pmboard/pmboardInfo";
	}

	@RequestMapping("replyForm")
	public String replyForm(int num) throws Exception {
		pmBoard board = bd.boardOne(num);
		HttpSession session = request.getSession();
		String boardid = (String) session.getAttribute("pmboardid");
		if (boardid == null)
			boardid = "1";
		String boardName = "";
		switch (boardid) {
		case "1":
			boardName = "공지사항";
			break;
		case "2":
			boardName = "자유게시판";
			break;
		case "3":
			boardName = "QnA";
			break;
		}

		request.setAttribute("pmboardName", boardName);
		request.setAttribute("pmboard", board);
		return "pmboard/pmreplyForm";
	}

	@RequestMapping("pmreplyPro")
	public String pmreplyPro(pmBoard board) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String boardid = (String) request.getSession().getAttribute("pmboardid");
		if (boardid == null)
			boardid = "1";

		board.setPmbfile1("");
		board.setPmbboardid(boardid);

		String msg = "답글 등록 실패";
		String url = "/pmboard/pmreplyForm?num=" + board.getPmbnum();
		board.setPmbref(board.getPmbref()); // 원조 새글 ref
		board.setReflevel(board.getReflevel() + 1); // 기준글 reflevel + 1   // 2. 210~211번 행은 삭제하는게 맞겠지만 혹시 몰라 놔둡니다.
		board.setRefstep(board.getRefstep() + 1); // print 순서
		bd.refStepAdd(board.getPmbref(), board.getRefstep());
		if (bd.insertReply(board) > 0) {
			msg = "답글 등록 완료";
			url = "/pmboard/pmboardList";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmboardUpdateForm")
	public String pmboardUpdateForm(int num) {
		pmBoard board = bd.boardOne(num);
		request.setAttribute("b", board);
		return "pmboard/pmboardUpdateForm";
	}

	@RequestMapping("pmboardUpdatePro")
	public String pmboardUpdatePro(@RequestParam("uploadfile") MultipartFile multipartFile, pmBoard board)
			throws Exception {
		String path = request.getServletContext().getRealPath("/") + "view/pmboard/pmimg";
		String msg = "";
		String url = "";

		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			board.setPmbfile1(multipartFile.getOriginalFilename());
		} else {
			board.setPmbfile1("");
		}

		pmBoard dbboard = bd.boardOne(board.getPmbnum());
		msg = "비밀번호가 틀렸습니다.";
		url = "/pmboard/pmboardUpdateForm?num=" + board.getPmbnum();

		if (board.getPass().equals(dbboard.getPass())) {    // pmBoard에 없는 Pass(Password) => 삭제해야하는지.,.? 어디부터 어디까지 삭제해야하나 ㄷㄷ 
			if (bd.boardUpdate(board) > 0) {
				msg = "수정 완료";
				url = "/pmboard/pmboardInfo?num=" + board.getPmbnum();
			} else {
				msg = "수정 실패";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "alert";
	}

	@RequestMapping("pmboardDeleteForm")
	public String pmboardDeleteForm(int num) {
		request.setAttribute("num", num);
		return "pmboard/pmboardDeleteForm";
	}

	@RequestMapping("pmboardDeletePro")
	public String pmboardDeletePro(String pass, int num) {
		pmBoard b = bd.boardOne(num);
		String msg = "비밀번호가 틀렸습니다.";
		String url = "/pmboard/pmboardDeleteForm?num=" + num;

		if (pass.equals(b.getPass())) {    // pmBoard에 없는 Pass(Password)
			if (bd.boardDelete(num) > 0) {
				msg = "게시글이 삭제 되었습니다.";
				url = "/pmboard/pmboardList";
			} else {
				msg = "게시글 삭제를 실패하였습니다.";
			}
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}
}
