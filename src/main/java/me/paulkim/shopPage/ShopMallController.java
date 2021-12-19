package me.paulkim.shopPage;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import me.paulkim.shopPage.model.CategoryDTO;
import me.paulkim.shopPage.model.ProductDTO;
import me.paulkim.shopPage.service.CategoryMapper;
import me.paulkim.shopPage.service.ProductMapper;

@Controller
public class ShopMallController {

	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
		//return "testFileUpload";
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
	
	@RequestMapping(value = "/prod_input.do", method = RequestMethod.GET)
	public String prod_input(HttpServletRequest req) {
		List<CategoryDTO> list = categoryMapper.listCategory();
		if (list == null && list.size() == 0) {
			req.setAttribute("msg", "카테고리를 먼저 입력해 주세요!!");
			req.setAttribute("url", "cate_input.do");
			return "message";
		}
		req.setAttribute("listCategory", list);
		return "admin/shop/prod_input";
	}
	
	@RequestMapping(value = "/prod_input.do", method = RequestMethod.POST)
	public String prod_input(HttpServletRequest req, @ModelAttribute ProductDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setPimage("");
		}
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile file = mr.getFile("pimage");
		File target = new File(uploadPath, file.getOriginalFilename());
		if (file.getSize()>0) {
			try {
				file.transferTo(target);
			}catch(IOException e) {}
		dto.setPimage(file.getOriginalFilename());
		}
		
		dto.setPcode(req.getParameter("category_fk") + dto.getPcode());
		
		int res = productMapper.insertProduct(dto);
		if (res>0) {
			req.setAttribute("msg", "상품등록 성공!! 상품고리목록페이지로 이동합니다.");
			req.setAttribute("url", "prod_list.do");
		}else {
			req.setAttribute("msg", "상품 실패!! 다시 입력해 주세요.");
			req.setAttribute("url", "prod_input.do");
		}
		return "message";
	}
	
	@RequestMapping(value = "/prod_list.do")
	public String prod_list(HttpServletRequest req) {
		List<ProductDTO> list = productMapper.listProduct();
		req.setAttribute("listProduct", list);
		return "admin/shop/prod_list";
	}
	
	@RequestMapping(value = "/prod_view.do", method = RequestMethod.GET)
	public String prod_view(HttpServletRequest req, @RequestParam int pnum) {
		ProductDTO dto = productMapper.getProduct(pnum);
		req.setAttribute("getProduct", dto);
		return "admin/shop/prod_view";
	}
	
	@RequestMapping(value = "/prod_delete.do", method = RequestMethod.GET)
	public String prod_delete(HttpServletRequest req, 
										@RequestParam Map<String, String> params) {
		int res = productMapper.deleteProduct(params.get("pnum"));
		if (res>0) {
			File file = new File(uploadPath, params.get("pimage"));
			file.delete();
			req.setAttribute("msg", "파일삭제 성공!! 파일목록페이지로 이동합니다.");
			req.setAttribute("url", "prod_list.do");
		}else {
			req.setAttribute("msg", "파일삭제 실패!! 파일목록페이지로 이동합니다.");
			req.setAttribute("url", "prod_list.do");
		}
		return "message";
				
	}
	
	@RequestMapping(value = "/prod_update.do", method = RequestMethod.GET)
	public String prod_update(HttpServletRequest req, @RequestParam int pnum) {
		ProductDTO dto = productMapper.getProduct(pnum);
		req.setAttribute("getProduct", dto);
		return "admin/shop/prod_update";
	}
	
	@RequestMapping(value = "/prod_update.do", method = RequestMethod.POST)
	public String prod_updateOk(HttpServletRequest req, @ModelAttribute ProductDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setPimage("");
		}
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile file = mr.getFile("pimage");
		File target = new File(uploadPath, file.getOriginalFilename());
		if (file.getSize()>0) {
			try {
				file.transferTo(target);
				dto.setPimage(file.getOriginalFilename());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			dto.setPimage(req.getParameter("pimage2"));
		}
		System.out.println(dto.getPimage());	
		int res = productMapper.updateProduct(dto);
		if (res>0) {
			req.setAttribute("msg", "상품수정 성공!! 상품고리목록페이지로 이동합니다.");
			req.setAttribute("url", "prod_list.do");
		}else {
			req.setAttribute("msg", "상품 수정!! 다시 입력해 주세요.");
			req.setAttribute("url", "prod_update.do?pnum=" + dto.getPnum());
		}
		return "message";
	}
	
	@RequestMapping(value = "/prod_find.do")
	public String prod_find(HttpServletRequest req, @RequestParam Map<String, String> params) {
		List<ProductDTO> list = null;
		if (params.get("search").equals("all")) {
			list = productMapper.listProduct();
		}else {
			if (params.get("search").equals("cate")) {
				params.put("search", "pcode");
				list = productMapper.findCateProduct(params);
			}else {
				params.put("search", "pname");
				list = productMapper.findProdProduct(params);
			}
		}
		req.setAttribute("listProduct", list);
		return "admin/shop/prod_list";
	}
}














