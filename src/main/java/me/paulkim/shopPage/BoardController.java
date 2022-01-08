package me.paulkim.shopPage;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import me.paulkim.shopPage.model.BoardDBBean;
import me.paulkim.shopPage.service.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@RequestMapping(value="/board.do")
	public String listBoard(HttpServletRequest req, @RequestParam(required=false) String pageNum) {
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int count = boardMapper.getCount();
		if (endRow > count) endRow = count;
		Map<String, Integer> map = new Hashtable<String, Integer>();
		map.put("start", startRow);
		map.put("end", endRow);
		List<BoardDBBean> list = boardMapper.listBoard(map);
		
		if (count>0) {
			int pageCount = count / pageSize + (count%pageSize == 0 ? 0 : 1);
			int pageBlock = 3;
			int startPage = ((currentPage-1) / pageBlock) * pageBlock + 1;
			//pageNum이 1,2,3 이면 startPage = 1;
			//pageNum이 4,5,6 이면 startPage = 4;
			//pageNum이 7,8,9 이면 startPage = 7....
			int endPage = startPage + pageBlock -1;
			if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageBlock", pageBlock);
			req.setAttribute("pageCount", pageCount);
		}
		
		req.setAttribute("listBoard", list);
		req.setAttribute("getCount", count);
		
		return "board/list";
	}
	
	@RequestMapping(value="/write_board.do", method=RequestMethod.GET)
	public String writeForm() {
		return "board/writeForm";
	}
	
	@RequestMapping(value="/write_board.do", method=RequestMethod.POST)
	public ModelAndView writePro(HttpServletRequest req, 
					@ModelAttribute BoardDBBean dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setNum(0);
			dto.setRe_step(0);
			dto.setRe_level(0);
		}
		dto.setIp(req.getRemoteAddr());
		String sql = null;
		if (dto.getNum() == 0) {
			sql = "update board set re_step = re_step + 1";
		}else {
			sql = "update board set re_step = re_step + 1 where re_step > " + dto.getRe_step();
			dto.setRe_step(dto.getRe_step() + 1);
			dto.setRe_level(dto.getRe_level() + 1);
		}
		boardMapper.plusRe_step(sql);
		int res = boardMapper.insertBoard(dto);
		String msg = null, url = null;
		if (res>0) {
			msg = "게시글 등록 성공!! 게시글 목록페이지로 이동합니다.";
			url = "board.do";
		}else {
			msg = "게시글 등록 실패!! 게시글 등록페이지로 이동합니다.";
			url = "writeForm_board.do";
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("message");
		return mav;
	}
	
	@RequestMapping(value="/delete_board.do", method=RequestMethod.GET)
	public String deleteForm() {
		return "board/deleteForm";
	}
	
	@RequestMapping(value="/delete_board.do", method=RequestMethod.POST)
	public String deletePro(HttpServletRequest req, 
											@RequestParam Map<String, String> params) {
		int res = boardMapper.deleteBoard(params);
		if (res>0) {
			req.setAttribute("msg", "게시글 삭제 성공!! 게시글 목록페이지로 이동합니다.");
			req.setAttribute("url", "board.do");
		}else if (res<0){
			req.setAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해 주세요.");
			req.setAttribute("url", "delete_board.do?num=" + params.get("num"));	
		}else {
			req.setAttribute("msg", "게시글 삭제 실패!! 게시글 보기페이지로 이동합니다.");
			req.setAttribute("url", "content_board.do?num=" + params.get("num"));
		}
		return "message";
	}
	
	@RequestMapping(value="/update_board.do", method=RequestMethod.GET)
	public String updateForm(HttpServletRequest req, @RequestParam int num) {
		BoardDBBean dto = boardMapper.getBoard(num, "update");
		req.setAttribute("getBoard", dto);
		return "board/updateForm";
	}
	
	@RequestMapping(value="/update_board.do", method=RequestMethod.POST)
	public String updatePro(HttpServletRequest req, @ModelAttribute BoardDBBean dto) {
		int res = boardMapper.updateBoard(dto);
		if (res>0) {
			req.setAttribute("msg", "게시글 수정 성공!! 게시글 목록페이지로 이동합니다.");
			req.setAttribute("url", "board.do");
		}else if (res<0){
			req.setAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해 주세요.");
			req.setAttribute("url", "update_board.do?num=" + dto.getNum());	
		}else {
			req.setAttribute("msg", "게시글 수정 실패!! 게시글 보기페이지로 이동합니다.");
			req.setAttribute("url", "content_board.do?num=" + dto.getNum());
		}
		return "message";
	}
	
	@RequestMapping("/content_board.do")
	public String contentBoard(HttpServletRequest req, @RequestParam int num) {
		BoardDBBean dto = boardMapper.getBoard(num, "content");
		req.setAttribute("getBoard", dto);
		return "board/content";
	}
}