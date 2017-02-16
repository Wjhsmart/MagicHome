package com.wsc.common;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsc.bean.Company;
import com.wsc.bean.CompanyCase;
import com.wsc.bean.CompanyCaseCol;
import com.wsc.bean.CompanyCol;
import com.wsc.bean.Designer;
import com.wsc.bean.DesignerCase;
import com.wsc.bean.DesignerCaseCol;
import com.wsc.bean.DesignerCol;
import com.wsc.bean.Product;
import com.wsc.bean.ProductCol;
import com.wsc.service.CommonService;
import com.wsc.service.CompanyCaseService;
import com.wsc.service.CompanyService;
import com.wsc.service.DesignerCaseService;
import com.wsc.service.DesignerService;
import com.wsc.service.ProductService;

public class WebUtil {

	public static String DEFAULT_UPLOAD = "upload"; // 用来保存上传图片的文件目录
	public static String SON_FILE = ""; // 用来保存图片的真实路径
	
	/**
	 * 用来获取到url地址栏的最后一个字段
	 * @param req
	 * @return
	 */
	public static String getURIMethod(HttpServletRequest req) {
		String uri = req.getRequestURI();
		return uri.substring(uri.lastIndexOf("/") + 1);
	}
	
	/**
	 * 用来指定获取url地址栏的字段，index=1表示获取倒数第一个字段，index=2表示获取倒数第二个字段，用于分页技术
	 * @param req
	 * @param index
	 * @return
	 */
	public static String getURIMethod(HttpServletRequest req, int index) {
		String uri = req.getRequestURI();
		if (index == 1) {
			return uri.substring(uri.lastIndexOf("/") + 1);
		} else if (index == 2) {
			String newUri = uri.substring(0, uri.lastIndexOf("/"));
			return newUri = newUri.substring(newUri.lastIndexOf("/") + 1);
		} else {
			return uri;
		}
	}
	
	/**
	 * 用于获取网站在服务器端的真实路径
	 * @param req
	 * @return
	 */
	public static String mkUpload(HttpServletRequest req, String fileName) {
		SON_FILE = DEFAULT_UPLOAD + "/" + fileName;
		String path = req.getServletContext().getRealPath("/");
		File file = new File(path, SON_FILE);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}
	
	/**
	 * 设施登录注册的div可见
	 * @param req
	 * @param userDiv
	 * @param enterpriseDiv
	 * @param designerDiv
	 * @param company
	 * @param supply
	 */
	public static void setDivDisplay(HttpServletRequest req, String userDiv, String enterpriseDiv, String designerDiv, String company, String supply) {
		req.setAttribute("user", userDiv);
		req.setAttribute("enterprise", enterpriseDiv);
		req.setAttribute("designer", designerDiv);
		req.setAttribute("company", company);
		req.setAttribute("supply", supply);
	}
	
	/**
	 * 设置主页面的数据
	 * @param req
	 * @param productService
	 * @param companyService
	 * @param designerService
	 * @param designerCaseService
	 * @param companyCaseService
	 */
	public static void setIndexData(HttpServletRequest req, ProductService productService, CompanyService companyService,
		DesignerService designerService, DesignerCaseService designerCaseService, CompanyCaseService companyCaseService, String customer_id) {
		List<Product> products = productService.queryProductByTop4();
		List<Company> companys = companyService.queryCompanyByTop4();
		List<Designer> designers = designerService.queryDesignerByTop4();
		DesignerCase designerCase = designerCaseService.queryDesignerCase();
		CompanyCase companyCase = companyCaseService.queryCompanyCase();
		checkProductCol(customer_id, productService, products);
		checkDesignerCol(customer_id, designerService,designers);
		checkCompanyCol(customer_id, companyService,companys);
		req.setAttribute("products", products);
		req.setAttribute("companys", companys);
		req.setAttribute("designers", designers);
		req.setAttribute("designerCase", designerCase);
		req.setAttribute("companyCase", companyCase);
	}
	
	/**
	 * 建材商品是否被收藏了
	 * @param customer_id
	 * @param productService
	 * @param products
	 */
	public static void checkProductCol(String customer_id, ProductService productService, List<Product> products) {
		if (customer_id != null && !customer_id.equals("")) {
			List<ProductCol> productCols = productService.queryProductColByProductId(customer_id);
			for (Product p : products) {
				for (ProductCol pc : productCols) {
					if (p.getId().equals(pc.getProduct_id())) {
						p.setCollected(true);
					}
				}
			}
		}
	}
	
	/**
	 * 检查设计师是否被收藏了
	 * @param customer_id
	 * @param designerService
	 * @param designers
	 */
	public static void checkDesignerCol(String customer_id, DesignerService designerService, List<Designer> designers) {
		if (customer_id != null && !customer_id.equals("")) {
			List<DesignerCol> designerCols = designerService.queryDesignerColByCustomerId(customer_id);
			for (Designer d : designers) {
				for (DesignerCol dc : designerCols) {
					if (d.getId().equals(dc.getDesigner_id())) {
						d.setCollected(true);
					}
				}
			}
		}
	}
	
	/**
	 * 检查装修公司是否被收藏了
	 * @param customer_id
	 * @param designerService
	 * @param designers
	 */
	public static void checkCompanyCol(String customer_id, CompanyService companyService, List<Company> companys) {
		if (customer_id != null && !customer_id.equals("")) {
			List<CompanyCol> companyCols = companyService.queryCompanyColByProductId(customer_id);
			for (Company c : companys) {
				for (CompanyCol cc : companyCols) {
					if (c.getId().equals(cc.getCompany_id())) {
						c.setCollected(true);
					}
				}
			}
		}
	}
	
	/**
	 * 检查装修公司案例是否被收藏了
	 * @param customer_id
	 * @param designerService
	 * @param designers
	 */
	public static void checkCompanyCaseCaseCol(String customer_id, CompanyCaseService companyCaseService, List<CompanyCase> companyCases) {
		if (customer_id != null && !customer_id.equals("")) {
			List<CompanyCaseCol> companyCaseCols = companyCaseService.queryCompanyCaseColByProductId(customer_id);
			for (CompanyCase cc : companyCases) {
				for (CompanyCaseCol ccc : companyCaseCols) {
					if (cc.getId().equals(ccc.getCase_id())) {
						cc.setCollected(true);
					}
				}
			}
		}
	}
	
	/**
	 * 检查设计师案例是否被收藏了
	 * @param customer_id
	 * @param designerService
	 * @param designers
	 */
	public static void checkDesignerCaseCol(String customer_id, DesignerCaseService designerCaseService, List<DesignerCase> designerCases) {
		if (customer_id != null && !customer_id.equals("")) {
			List<DesignerCaseCol> designerCaseCols = designerCaseService.queryDesignerCaseColByProductId(customer_id);
			for (DesignerCase dc : designerCases) {
				for (DesignerCaseCol dcc : designerCaseCols) {
					if (dc.getId().equals(dcc.getCase_id())) {
						dc.setCollected(true);
					}
				}
			}
		}
	}
	/**
	 * 检查是否存在账号
	 * @param commonService
	 * @param email
	 * @param req
	 * @param resp
	 * @param url
	 * @return
	 */
	public static boolean checkEmail(CommonService commonService, String email, HttpServletRequest req, HttpServletResponse resp) {
		boolean adminEmail = commonService.queryEmail(email, "t_admin");
		boolean customerEmail = commonService.queryEmail(email, "t_customer");
		boolean designerEmail = commonService.queryEmail(email, "t_designer");
		boolean supplyEmail = commonService.queryEmail(email, "t_supply");
		boolean companyEmail = commonService.queryEmail(email, "t_company");
		if (adminEmail) {
			return false;
		} else if (customerEmail) {
			return false;
		} else if (designerEmail) {
			return false;
		} else if (supplyEmail) {
			return false;
		} else if (companyEmail) {
			return false;
		} else {
			return true;
		}
	}
}
