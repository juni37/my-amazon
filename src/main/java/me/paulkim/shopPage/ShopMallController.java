package me.paulkim.shopPage;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import me.paulkim.shopPage.service.MallMapper;
import me.paulkim.shopPage.service.ProductMapper;

@Controller
public class ShopMallController {

	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	MallMapper mallMapper;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value={ "/", "/main.do" }, method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/mall_index.do")
	public String mallIndex(HttpServletRequest req) {
		List<CategoryDTO> clist = categoryMapper.listCategory();
		if (clist == null && clist.size() == 0) {
			req.setAttribute("msg", "아직 쇼핑몰이 준비되지 못했습니다. main페이지로 이동합니다");
			req.setAttribute("url", "main.do");
			return "message";
		}
		HttpSession session = req.getSession();
		session.setAttribute("listCategory", clist);
		Hashtable<String, List<ProductDTO>> ht = (Hashtable)session.getAttribute("viewProd");
		if (ht == null) ht = new Hashtable<String, List<ProductDTO>>();
		String[] str = new String[] {"HIT", "NEW", "SALE"};
		for(String s : str) {
			List<ProductDTO> list = mallMapper.listPspec(s);
			ht.put(s, list);
		}
		session.setAttribute("viewProd", ht);
		/*
		List<ProductDTO> phit = mallMapper.listPspec("HIT");
		List<ProductDTO> pnew = mallMapper.listPspec("NEW");
		List<ProductDTO> psale = mallMapper.listPspec("SALE");
		
		ht.put("HIT", phit);
		ht.put("NEW", pnew);
		ht.put("SALE", psale);
		*/
		
		return "display/mall_index";
	}
	
	@RequestMapping(value = "/mall_cgProdList.do")
	public String mallCgProdList(HttpServletRequest req, @RequestParam Map<String, String> params) {
		List<ProductDTO> list = mallMapper.listPcode(params.get("code"));
		HttpSession session = req.getSession();
		Hashtable<String, List<ProductDTO>> ht = (Hashtable)session.getAttribute("viewProd");
		ht.put(params.get("code"), list);
		session.setAttribute("viewProd", ht);
		req.setAttribute("view", list);
		return "display/mall_cgProdList";
	}
	
	@RequestMapping(value = "/mall_view.do")
	public String mallView(HttpServletRequest req, @RequestParam Map<String, String> params) {
		HttpSession session = req.getSession();
		Hashtable<String, List<ProductDTO>> ht = (Hashtable)session.getAttribute("viewProd");
		List<ProductDTO> list = ht.get(params.get("select"));
		for(ProductDTO dto : list) {
			if (Integer.parseInt(params.get("pnum")) == dto.getPnum()) {
				req.setAttribute("getProduct", dto);
				break;
			}
		}
		return "display/mall_prodView";
	}
	
	@RequestMapping(value = "/prod_inQty.do", method = RequestMethod.GET)
	public String prodInQty(HttpServletRequest req, @RequestParam int pnum) {
		ProductDTO dto = productMapper.getProduct(pnum);
		req.setAttribute("getProduct", dto);
		return "admin/shop/prod_inQty";
	}
	
	@RequestMapping(value = "/prod_inQty.do", method = RequestMethod.POST)
	public String prodInQtyOk(HttpServletRequest req, @ModelAttribute ProductDTO dto) {
		dto.setPqty(dto.getPqty() + Integer.parseInt(req.getParameter("inqty")));
		int res = productMapper.inQty(dto);
		return "redirect:prod_list.do";
	}
	
	@RequestMapping(value = "/prod_outQty.do", method = RequestMethod.GET)
	public String prodOutQty(HttpServletRequest req, @RequestParam int pnum) {
		ProductDTO dto = productMapper.getProduct(pnum);
		req.setAttribute("getProduct", dto);
		return "admin/shop/prod_outQty";
	}
	
	@RequestMapping(value = "/prod_outQty.do", method = RequestMethod.POST)
	public String prodOutQtyOk(HttpServletRequest req, @ModelAttribute ProductDTO dto) {
		dto.setPqty(dto.getPqty() - Integer.parseInt(req.getParameter("outqty")));
		if (dto.getPqty() < 0) {
			req.setAttribute("msg", "현재 재고보다 많은 수가 출고되었습니다. 다시 확인해 주세요!!");
			req.setAttribute("url", "prod_outQty.do?pnum=" + dto.getPnum());
			return "message";
		}
		productMapper.inQty(dto);
		return "redirect:prod_list.do";
	}
	
	@RequestMapping(value="/mall_cartAdd.do")
	public String mallCartAdd(HttpServletRequest req, @RequestParam Map<String, String> params) {
		HttpSession session = req.getSession();
		List<ProductDTO> cart = (List)session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<ProductDTO>();
		}
		Hashtable<String, List<ProductDTO>> ht = (Hashtable)session.getAttribute("viewProd");
		List<ProductDTO> list = ht.get(params.get("select"));
		for(ProductDTO dto : list) {
			if (Integer.parseInt(params.get("pnum")) == dto.getPnum()) {
				dto.setPqty(Integer.parseInt(params.get("qty")));
				cart.add(dto);
				break;
			}
		}
		session.setAttribute("cart", cart);
		return "display/mall_cartList";
	}
	
	@RequestMapping(value="/mall_cartEdit.do")
	public String mallCartEdit(HttpServletRequest req, @RequestParam Map<String, String> params) {
		HttpSession session = req.getSession();
		List<ProductDTO> cart = (List)session.getAttribute("cart");
		ProductDTO dto = cart.get(Integer.parseInt(params.get("index")));
		dto.setPqty(Integer.parseInt(params.get("pqty")));
		//cart.remove(index);
		if (dto.getPqty()<=0) {
			cart.remove(Integer.parseInt(params.get("index")));
		}
		return "display/mall_cartList";
	}
	
	@RequestMapping(value="/mall_cartDel.do")
	public String mallCartDel(HttpServletRequest req, @RequestParam int index) {
		HttpSession session = req.getSession();
		List<ProductDTO> cart = (List)session.getAttribute("cart");
		cart.remove(index);
		return "display/mall_cartList";
	}
}
	














