package me.paulkim.shopPage;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import me.paulkim.shopPage.model.CategoryDTO;
import me.paulkim.shopPage.service.CategoryMapper;

@Controller
public class ShopMallController {

	@Autowired
	CategoryMapper categoryMapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/shop.do", method = RequestMethod.GET)
	public String shopHome() {
		return "admin/shop/shopIndex";
	}
	
	@RequestMapping(value = "/cate_input.do", method = RequestMethod.GET)
	public String cateInput() {
		return "admin/shop/cate_input";
	}
	
	@RequestMapping(value = "/cate_input.do", method = RequestMethod.POST)
	public String cateInputOk(HttpServletRequest req, @ModelAttribute CategoryDTO dto) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("column", "code");
		map.put("value", dto.getCode());
		CategoryDTO cdto = categoryMapper.getCategory(map);
		int res = 0;
		if (cdto == null) {
			res = categoryMapper.insertCategory(dto);
		}else {
			res = -1;
		}
		if (res>0) {
			req.setAttribute("msg", "카테고리등록 성공!! 카테고리목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}else if (res<0){
			req.setAttribute("msg", "카테고리 코드가 이미 등록되어 있습니다. 다시 입력해 주세요.");
			req.setAttribute("url", "cate_input.do");
		}else {
			req.setAttribute("msg", "카테고리등록 실패!! 다시 입력해 주세요.");
			req.setAttribute("url", "cate_input.do");
		}
		return "message";
	}
	
	@RequestMapping(value = "/cate_list.do", method = RequestMethod.GET)
	public ModelAndView cateList() {
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		list = categoryMapper.listCategory();
		ModelAndView mav = new ModelAndView("admin/shop/cate_list", "listCategory", list);
		return mav;
	}
	
	@RequestMapping(value = "/cate_delete.do", method = RequestMethod.GET)
	public String cateDelete(HttpServletRequest req, @RequestParam int cnum) {
		int res = categoryMapper.deleteCategory(cnum);
		if (res>0){
			req.setAttribute("msg", "카테고리삭제 성공!! 카테고리 목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "카테고리삭제 실패!! 카테고리 목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}
		return "message";
	}
	
	@RequestMapping(value = "/cate_edit.do", method = RequestMethod.GET)
	public String cate_edit(HttpServletRequest req, @RequestParam int cnum) {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("column", "cnum");
		map.put("value", String.valueOf(cnum));
		CategoryDTO dto = categoryMapper.getCategory(map);
		req.setAttribute("getCategory", dto);
		return "admin/shop/cate_edit";
	}
	
	@RequestMapping(value = "/cate_edit.do", method = RequestMethod.POST)
	public String cateEditOk(HttpServletRequest req, @ModelAttribute CategoryDTO dto) {
		int res = categoryMapper.updateCategory(dto);
		if (res>0) {
			req.setAttribute("msg", "카테고리수정 성공!! 카테고리목록페이지로 이동합니다.");
			req.setAttribute("url", "cate_list.do");
		}else {
			req.setAttribute("msg", "카테고리수정 실패!! 다시 입력해 주세요.");
			req.setAttribute("url", "cate_edit.do?cnum=" + dto.getCnum());
		}
		return "message";
	}
}




