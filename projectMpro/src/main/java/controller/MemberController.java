package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//import com.oreilly.servlet.MultipartRequest;

import model.Member;
import service.MemberMybatisDao;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	MemberMybatisDao md;

	HttpServletRequest request;
	Model m;
	HttpSession session;

	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		this.session = request.getSession();
	}

	public MemberController() {
		System.out.println("member");
	}

	@RequestMapping("index")
	public String index() throws Exception {
		request.setAttribute("index", "member 입니다");
		return "index";
	}

	@RequestMapping("joinForm")
	public String joinForm() throws Exception {
		request.setAttribute("index", "member 입니다");
		return "member/joinForm";
	}

	@RequestMapping("joinPro")
	public String joinPro(Member mem) throws Exception {

		int num = md.insertMember(mem);
		String msg = "";
		String url = "";

		if (num > 0) {
			msg = mem.getName() + "님의 가입이 완료 되었습니다.";
			url = "/member/loginForm";
		} else {
			msg = "회원가입이 실패 했습니다.";
			url = "/member/joinForm";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("loginForm")
	public String loginForm() throws Exception {
		return "member/loginForm";
	}

	@RequestMapping("loginPro")
	public String loginPro(String id, String pass) throws Exception {
		Member mem = md.selectOne(id);

		String msg = "아이디를 확인하세요";
		String url = "/member/loginForm";

		if (mem != null) {
			if (pass.equals(mem.getPass())) {
				session.setAttribute("id", id);
				msg = mem.getName() + "님이 로그인 하셨습니다.";
				url = "/member/index";
			} else {
				msg = "비밀번호를 확인하세요";
			}
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("logout")
	public String logout() throws Exception {
		String login = (String) session.getAttribute("id");
		session.invalidate();
		String msg = login + " 님 로그아웃 되었습니다.";
		String url = "/member/loginForm";
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberInfo")
	public String memberInfo() throws Exception {
		String id = (String) session.getAttribute("id");

		if (id != null && !id.equals("")) {
			Member m = md.selectOne(id);
			request.setAttribute("m", m);
			return "member/memberInfo";
		} else {
			String msg = "로그인이 필요합니다.";
			String url = "/member/loginForm";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
		}
	}

	@RequestMapping("memberUpdateForm")
	public String memberUpdateForm() throws Exception {
		String id = (String) session.getAttribute("id");
		if (id != null && !id.equals("")) {
			Member m = md.selectOne(id);
			request.setAttribute("m", m);
			return "member/memberUpdateForm";
		} else {
			String msg = "로그인이 필요합니다.";
			String url = "/member/loginForm";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
		}
	}

	@RequestMapping("memberUpdatePro")
	public String memberUpdatePro(Member mem) throws Exception {
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/member/loginForm";

		if (id != null && !id.equals("")) {
			request.setCharacterEncoding("UTF-8");

			Member dbm = md.selectOne(id);

			if (dbm != null) {
				if (dbm.getPass().equals(mem.getPass())) {
					int num = md.updateMember(mem);
					if (num > 0) {
						msg = mem.getName() + "님의 회원 정보 수정이 완료 되었습니다.";
						url = "/member/memberInfo";
					} else {
						msg = "회원 정보 수정이 실패 했습니다.";
						url = "/member/memberUpdateForm";
					}
				} else {
					msg = "잘못된 비밀번호 입니다.";
					url = "/member/memberUpdateForm";
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberDelete")
	public String memberDelete() throws Exception {
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/member/loginForm";
		if (id != null && !id.equals("")) {
			return "member/memberDelete";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberDeletePro")
	public String memberDeletePro(String pass) throws Exception {
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/member/loginForm";
		if (id != null && !id.equals("")) {

			Member dbm = md.selectOne(id);

			if (dbm != null) {
				if (dbm.getPass().equals(pass)) {
					int num = md.deleteMember(id);
					if (num > 0) {
						msg = id + "님이 탈퇴처리 되었습니다.";
						session.invalidate();
						url = "/member/loginForm";
					} else {
						msg = "회원 탈퇴가 실패 했습니다.";
						url = "/member/memberDelete";
					}
				} else {
					msg = "잘못된 비밀번호 입니다.";
					url = "/member/memberDelete";
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberPassUpdate")
	public String memberPassUpdate() throws Exception {
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/member/loginForm";
		if (id != null && !id.equals("")) {
			return "member/memberPassUpdate";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberPassPro")
	public String memberPassPro(String pass, String chgpass1) throws Exception {

		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "loginForm";

		if (id != null && !id.equals("")) {
			Member dbm = md.selectOne(id);
			if (dbm != null) {
				if (dbm.getPass().equals(pass)) {
					int num = md.changePass(id, chgpass1);
					if (num > 0) {
						msg = id + "님의 비밀번호가 수정 되었습니다.";
						url = "/member/index";
					} else {
						msg = "비밀번호 변경에 실패했습니다.";
						url = "/member/memberPassUpdate";
					}
				} else {
					msg = "비밀번호가 틀렸습니다.";
					url = "/member/memberPassUpdate";
				}
			}
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("memberList")
	public String memberList() throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/member/loginForm";
		if (id != null && id.equals("admin")) {
			List<Member> li = new ArrayList<Member>();
			li = md.memberList();
			request.setAttribute("li", li);
			return "member/memberList";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pictureimgForm")
	public String pictureimgForm() throws Exception {
		return "member/pictureimgForm";
	}

	@RequestMapping("picturePro")
	public String picturePro(@RequestParam("picture") MultipartFile multipartFile)
			throws Exception {
		String path = request.getServletContext().getRealPath("/") + "view/member/picture/";
		String filename = null;
		
		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			filename = multipartFile.getOriginalFilename();
		}

		request.setAttribute("filename", filename);

		return "member/picturePro";
	}

}
