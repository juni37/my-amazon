package me.paulkim.shopPage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import me.paulkim.shopPage.model.MemberDTO;
import me.paulkim.shopPage.service.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	private MemberMapper memberMapper;
	
	@RequestMapping(value="/memberSsn.do")
	public String memberSsn() {
			return "member/memberSsn";
	}
	
	@RequestMapping(value="/memberCheck.do")
	public String memberCheck(HttpServletRequest req, @RequestParam Map<String, String> params) {
		boolean isMember = memberMapper.checkMember(params);
		String msg = null, url = null;
		if (isMember) {
			msg = "ȸ���̽ʴϴ�. �α����� �� �ּ���";
			url = "index.do";
		}else {
			msg = "ȸ�������������� �̵��մϴ�.";
			url = "member_input.do";
			HttpSession session = req.getSession();
			session.setAttribute("check", params);
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping(value="/member_input.do", method=RequestMethod.GET)
	public String memberInput() {
			return "member/member_input";
	}
	
	@RequestMapping(value="/member_input.do", method=RequestMethod.POST)
	public String memberInputOk(HttpServletRequest req, MemberDTO dto) {
		int res = memberMapper.insertMember(dto);
		String msg = null, url = null;
		if (res > 0) {
			msg = "ȸ������ �����ϼ̽��ϴ�. �α����� �� �ּ���";
			url = "index.do";
		}else {
			msg = "ȸ������ �����ϼ̽��ϴ�. �ٽ� �Է��� �ּ���";
			url = "memberSsn.do";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
}







