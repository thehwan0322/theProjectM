package pmcontroller;

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

import pmmodel.pmMember;
import pmservice.pmMemberMybatisDao;

@Controller
@RequestMapping("/pmmember/")
public class pmMemberController {

	@Autowired
	pmMemberMybatisDao md;

	HttpServletRequest request;
	Model m;
	HttpSession session;

	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		this.session = request.getSession();
	}

	public pmMemberController() {
		System.out.println("pmmember");
	}

	@RequestMapping("welcome")
	public String welcome() throws Exception {
		request.setAttribute("welcome", "pmmember 입니다");
		return "welcome";
	}

	@RequestMapping("pmjoinForm")
	public String pmjoinForm() throws Exception {
		request.setAttribute("welcome", "pmmember 입니다");
		return "pmmember/pmjoinForm";
	}

	@RequestMapping("pmjoinPro")
	public String pmjoinPro(pmMember mem) throws Exception {

		int num = md.insertMember(mem);
		String msg = "";
		String url = "";

		if (num > 0) {
			msg = mem.getPmname() + "님의 가입이 완료 되었습니다.";
			url = "/pmmember/pmloginForm";
		} else {
			msg = "회원가입이 실패 했습니다.";
			url = "/pmmember/pmjoinForm";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmloginForm")
	public String pmloginForm() throws Exception {
		return "pmmember/pmloginForm";
	}

	@RequestMapping("pmloginPro")
	public String pmloginPro(String id, String pass) throws Exception {
		pmMember mem = md.selectOne(id);

		String msg = "아이디를 확인하세요";
		String url = "/pmmember/pmloginForm";

		if (mem != null) {
			if (pass.equals(mem.getPmpass())) {
				session.setAttribute("pmid", id);
				msg = mem.getPmname() + "님이 로그인 하셨습니다.";
				url = "/pmmember/welcome";
			} else {
				msg = "비밀번호를 확인하세요";
			}
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmlogout")
	public String pmlogout() throws Exception {
		String login = (String) session.getAttribute("id");
		session.invalidate();
		String msg = login + " 님 로그아웃 되었습니다.";
		String url = "/pmmember/pmloginForm";
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmmemberInfo")
	public String pmmemberInfo() throws Exception {
		String pmid = (String) session.getAttribute("pmid");

		if (pmid != null && !pmid.equals("")) {
			pmMember m = md.selectOne(pmid);
			request.setAttribute("m", m);
			return "pmmember/pmmemberInfo";
		} else {
			String msg = "로그인이 필요합니다.";
			String url = "/pmmember/pmloginForm";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
		}
	}

	@RequestMapping("pmmemberUpdateForm")
	public String pmmemberUpdateForm() throws Exception {
		String id = (String) session.getAttribute("id");
		if (id != null && !id.equals("")) {
			pmMember m = md.selectOne(id);
			request.setAttribute("m", m);
			return "pmmember/pmmemberUpdateForm";
		} else {
			String msg = "로그인이 필요합니다.";
			String url = "/pmmember/pmloginForm";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "alert";
		}
	}

	@RequestMapping("pmmemberUpdatePro")
	public String pmmemberUpdatePro(pmMember mem) throws Exception {
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/pmmember/pmloginForm";

		if (id != null && !id.equals("")) {
			request.setCharacterEncoding("UTF-8");

			pmMember dbm = md.selectOne(id);

			if (dbm != null) {
				if (dbm.getPmpass().equals(mem.getPmpass())) {
					int num = md.updateMember(mem);
					if (num > 0) {
						msg = mem.getPmname() + "님의 회원 정보 수정이 완료 되었습니다.";
						url = "/pmmember/pmmemberInfo";
					} else {
						msg = "회원 정보 수정이 실패 했습니다.";
						url = "/pmmember/pmmemberUpdateForm";
					}
				} else {
					msg = "잘못된 비밀번호 입니다.";
					url = "/pmmember/pmmemberUpdateForm";
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmmemberDelete")
	public String pmmemberDelete() throws Exception {
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/pmmember/pmloginForm";
		if (id != null && !id.equals("")) {
			return "pmmember/pmmemberDelete";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmmemberDeletePro")
	public String pmmemberDeletePro(String pass) throws Exception {
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/pmmember/pmloginForm";
		if (id != null && !id.equals("")) {

			pmMember dbm = md.selectOne(id);

			if (dbm != null) {
				if (dbm.getPmpass().equals(pass)) {
					int num = md.deleteMember(id);
					if (num > 0) {
						msg = id + "님이 탈퇴처리 되었습니다.";
						session.invalidate();
						url = "/pmmember/pmloginForm";
					} else {
						msg = "회원 탈퇴가 실패 했습니다.";
						url = "/pmmember/pmmemberDelete";
					}
				} else {
					msg = "잘못된 비밀번호 입니다.";
					url = "/pmmember/pmmemberDelete";
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmmemberPassUpdate")
	public String pmmemberPassUpdate() throws Exception {
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/pmmember/pmloginForm";
		if (id != null && !id.equals("")) {
			return "pmmember/pmmemberPassUpdate";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmmemberPassPro")
	public String pmmemberPassPro(String pass, String chgpass1) throws Exception {

		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "pmloginForm";

		if (id != null && !id.equals("")) {
			pmMember dbm = md.selectOne(id);
			if (dbm != null) {
				if (dbm.getPmpass().equals(pass)) {
					int num = md.changePass(id, chgpass1);
					if (num > 0) {
						msg = id + "님의 비밀번호가 수정 되었습니다.";
						url = "/pmmember/welcome";
					} else {
						msg = "비밀번호 변경에 실패했습니다.";
						url = "/pmmember/pmmemberPassUpdate";
					}
				} else {
					msg = "비밀번호가 틀렸습니다.";
					url = "/pmmember/pmmemberPassUpdate";
				}
			}
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmmemberList")
	public String pmmemberList() throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String msg = "로그인이 필요합니다.";
		String url = "/pmmember/pmloginForm";
		if (id != null && id.equals("admin")) {
			List<pmMember> li = new ArrayList<pmMember>();
			li = md.memberList();
			request.setAttribute("li", li);
			return "pmmember/pmmemberList";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "alert";
	}

	@RequestMapping("pmpictureimgForm")
	public String pmpictureimgForm() throws Exception {
		return "pmmember/pmpictureimgForm";
	}

	@RequestMapping("pmpicturePro")
	public String pmpicturePro(@RequestParam("picture") MultipartFile multipartFile) throws Exception {
		String path = request.getServletContext().getRealPath("/") + "view/pmmember/pmpicture/";
		String filename = null;

		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			filename = multipartFile.getOriginalFilename();
		}

		request.setAttribute("filename", filename);

		return "pmmember/pmpicturePro";
	}

}
