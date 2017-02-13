package com.wsc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsc.bean.Comment;
import com.wsc.bean.Company;
import com.wsc.bean.CompanyActivity;
import com.wsc.bean.CompanyCase;
import com.wsc.bean.CompanyCaseCol;
import com.wsc.bean.CompanyCol;
import com.wsc.bean.Customer;
import com.wsc.bean.DesignerCaseCol;
import com.wsc.bean.DesignerCol;
import com.wsc.bean.ProductCol;
import com.wsc.bean.SupplyCol;
import com.wsc.common.DateUtil;
import com.wsc.common.EncryptUtil;
import com.wsc.common.WebUtil;
import com.wsc.parentbean.Pager;
import com.wsc.service.CommentService;
import com.wsc.service.CommentServiceImpl;
import com.wsc.service.CommonService;
import com.wsc.service.CommonServiceImpl;
import com.wsc.service.CompanyActivityService;
import com.wsc.service.CompanyActivityServiceImpl;
import com.wsc.service.CompanyCaseColService;
import com.wsc.service.CompanyCaseColServiceImpl;
import com.wsc.service.CompanyCaseService;
import com.wsc.service.CompanyCaseServiceImpl;
import com.wsc.service.CompanyColService;
import com.wsc.service.CompanyColServiceImpl;
import com.wsc.service.CompanyService;
import com.wsc.service.CompanyServiceImpl;
import com.wsc.service.CustomerService;
import com.wsc.service.CustomerServiceImpl;
import com.wsc.service.DesignerCaseColService;
import com.wsc.service.DesignerCaseColServiceImpl;
import com.wsc.service.DesignerCaseService;
import com.wsc.service.DesignerCaseServiceImpl;
import com.wsc.service.DesignerColService;
import com.wsc.service.DesignerColServiceImpl;
import com.wsc.service.DesignerService;
import com.wsc.service.DesignerServiceImpl;
import com.wsc.service.ProductColService;
import com.wsc.service.ProductColServiceImpl;
import com.wsc.service.ProductService;
import com.wsc.service.ProductServiceImpl;
import com.wsc.service.SupplyColService;
import com.wsc.service.SupplyColServiceImpl;

public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 5188481232513360584L;
	
	private HttpSession session;
	private CustomerService customerService;
	private CompanyColService companyColService;
	private CompanyCaseColService companyCaseColService;
	private DesignerColService designerColService;
	private DesignerCaseColService designerCaseColService;
	private SupplyColService supplyColService;
	private ProductColService productColService;
	private CommonService commonService;
	private ProductService productService;
	private CompanyService companyService;
	private DesignerService designerService;
	private CompanyCaseService companyCaseService;
	private DesignerCaseService designerCaseService;
	private CommentService commentService;
	private CompanyActivityService companyActivityService;
	
	public CustomerServlet() {
		customerService = new CustomerServiceImpl();
		companyColService = new CompanyColServiceImpl();
		companyCaseColService = new CompanyCaseColServiceImpl();
		designerColService = new DesignerColServiceImpl();
		designerCaseColService = new DesignerCaseColServiceImpl();
		supplyColService = new SupplyColServiceImpl();
		productColService = new ProductColServiceImpl();
		commonService = new CommonServiceImpl();
		productService = new ProductServiceImpl();
		companyService = new CompanyServiceImpl();
		companyCaseService = new CompanyCaseServiceImpl();
		designerCaseService = new DesignerCaseServiceImpl();
		designerService = new DesignerServiceImpl();
		commentService = new CommentServiceImpl();
		companyActivityService = new CompanyActivityServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getURIMethod(req);
		session = req.getSession();	
		if (method.equals("register")) {
			// 处理用户注册逻辑
			register(req, resp);
		} else if (method.equals("login")) {
			// 处理登录逻辑
			login(req, resp);
		} else if (method.equals("customer_index_page")) {
			// 显示用户的后台管理页面
			showCustomerIndexPage(req, resp);
		} else if (method.equals("exit")) {
			// 退出登入
			exit(req, resp);
		} else if (method.equals("return_index_page")) {
			// 返回主页面
			showReturnIndexPage(req, resp);
		} else if (method.equals("query_personal_mes_page")) {
			// 显示个人信息中心
			showQueryPersonalMesPage(req, resp);
		} else if (method.equals("update_personal_mes_page")) {
			// 显示修改个人信息页面
			showUpdatePersonalMesPage(req, resp);
		} else if (method.equals("update_personal_mes")) {
			// 处理修改自己信息的逻辑
			updatePersonalMes(req, resp);
		} else if (method.equals("update_personal_pwd_page")) {
			// 修改自己的密码
			showUpdatePersonalPwdPage(req, resp);
		} else if (method.equals("update_personal_pwd")) {
			// 处理修改自己密码的逻辑
			updatePersonalPwd(req, resp);
		} else if (method.equals("query_company_col_page")) {
			// 查看收藏的装修公司
			showQueryCompanyColPage(req, resp);
		} else if (method.equals("delete_company_col")) {
			// 处理删除收藏的装修公司
			deleteCompanyCol(req, resp);
		} else if (method.equals("query_company_col_case_page")) {
			// 查看收藏的装修公司案例
			showQueryCompanyColCasePage(req, resp);
		} else if (method.equals("delete_company_col_case")) {
			// 删除收藏的装修公司案例
			deleteCompanyColCase(req, resp);
		} else if (method.equals("query_designer_col_page")) {
			// 查看收藏的设计师
			showQueryDesignerColPage(req, resp);
		} else if (method.equals("delete_designer_col")) {
			// 删除收藏的设计师
			deleteDesignerCol(req, resp);
		} else if (method.equals("query_designer_col_case_page")) {
			// 查看收藏的设计师案例
			showQueryDesignerColCasePage(req, resp);
		} else if (method.equals("delete_designer_col_case")) {
			// 删除收藏设计师案例
			deleteDesignerColCase(req, resp);
		} else if (method.equals("query_supply_col_page")) {
			// 查看收藏的建材商
			showQuerySupplyColPage(req, resp);
		} else if (method.equals("delete_supply_col")) {
			// 删除收藏的建材商
			deleteSupplyCol(req, resp);
		} else if (method.equals("query_product_col_page")) {
			// 查看收藏的建材商品
			showQuerySupplyProductPage(req, resp);
		} else if (method.equals("delete_product_col")) {
			// 删除收藏的建材商品
			deleteSupplyProductCol(req, resp);
		} else if (method.equals("index_page")) {
			// 把页面转发到主页
			showIndexPage(req, resp);
		} else if (method.equals("login_page")) {
			// 调用跳转到登入页面的方法
			showLoginPage(req, resp);
		} else if (method.equals("comment")) {
			// 提交评论
			comment(req, resp);
		} else if (method.equals("company_particular")) {
			// 跳转到装修公司详情
			showCompanyParticular(req, resp);
		} else if (method.equals("company_col_particular")) {
			// 装修公司详情页面收藏装修公司
			companyColParticular(req, resp);
		} 
	}

	/**
	 * 用于处理用户注册的逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("pwd"); // 先把密码加密
		String phone = req.getParameter("phone");
		String name = req.getParameter("name");
		Customer customer = new Customer();
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (WebUtil.checkEmail(commonService, email, req, resp)) {
			if (phone.length() != 11) {
				req.setAttribute("errorMes", "手机号码必须为11位数字");
				req.getRequestDispatcher("/register.jsp").forward(req, resp);
			} else {
				customer.setE_mail(email);
				customer.setPassword(EncryptUtil.encrypt(password));
				customer.setName(name);
				customer.setPhone(phone);
				customer.setPlot_name("默认小区");
				customer.setAddress("赣州");
				customer.setCreated_time(DateUtil.getDate());
				customer.setLast_login_time(DateUtil.getDate());
				customer.setStatus("Y"); // 默认是可用的
				customerService.addCustomer(customer);
				req.setAttribute("succeed", "注册成功，可以进行登录");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("errorMes", "已存在该用户");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * 处理用户登录逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		String password = EncryptUtil.encrypt(pwd);
		String motion = req.getParameter("selfMotion");
		Customer customer = customerService.queryCustomer(email, password);
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (customer.getE_mail() == null || customer.getE_mail().equals("")) {
			req.setAttribute("errorMes", "账号或密码有误");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			if (customer.getStatus().equals("Y")) {
				customer.setLast_login_time(DateUtil.getDate());
				customer.setLogin_count(customer.getLogin_count() + 1);
				customerService.updateCustomer(customer); // 更新数据库
				session.setAttribute("customer", customer);
				Cookie emailCookie = new Cookie("customer_email", email);
				Cookie pwdCookie = new Cookie("customer_pwd", pwd);
				if (motion != null && motion.equals("y")) {
					emailCookie.setMaxAge(30 * 24*24);
					pwdCookie.setMaxAge(30 * 24*24);
				} else {
					emailCookie.setMaxAge(0);
					pwdCookie.setMaxAge(0);
				}
				resp.addCookie(emailCookie);
				resp.addCookie(pwdCookie);
				resp.sendRedirect("index_page");
				
			} else if (customer.getStatus().equals("N")) {
				req.setAttribute("errorMes", "该账号不可用");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMes", "不存在该账号");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		}
		
	}
	
	/**
	 * 把页面转发到用户的后台管理页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCustomerIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		req.getRequestDispatcher("/customers/customer_index.jsp").forward(req, resp);
	}
	
	/**
	 * 用户退出登录
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute("customer");
		WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, "");
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
	
	/**
	 * 将页面转发回主页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showReturnIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		if (session.getAttribute("customer") == null) {
			req.setAttribute("errorMes", "账号已失效，请重新登录");
			WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/customers/customer_index.jsp").forward(req, resp);
		}
		
	}

	/**
	 * 将页面转发到个人信息页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryPersonalMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (customer == null) {
			req.setAttribute("errorMes", "账号已失效，请重新登录");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("lastLoginTime", DateUtil.getDateStr(customer.getLast_login_time()));
			req.getRequestDispatcher("/customers/customer_personal_mes.jsp").forward(req, resp);
		}
	}

	/**
	 * 把页面转发到修改个人信息页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/customers/customer_update_mes.jsp").forward(req, resp);
	}
	
	/**
	 * 处理个人信息修改的逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updatePersonalMes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = (Customer) session.getAttribute("customer");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String plot_name = req.getParameter("plot_name");
		String address = req.getParameter("address");
		customer.setName(name);
		customer.setPhone(phone);
		customer.setPlot_name(plot_name);
		customer.setAddress(address);
		customerService.updateCustomer(customer);
		resp.sendRedirect("query_personal_mes_page");
	}

	/**
	 * 把页面转发到修改个人密码页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalPwdPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/customers/customer_update_pwd.jsp").forward(req, resp);
	}

	/**
	 * 处理个人密码修改的逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updatePersonalPwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pwd = EncryptUtil.encrypt(req.getParameter("pwd"));
		String oldPwd = EncryptUtil.encrypt(req.getParameter("oldPwd"));
		if (customer.getPassword().equals(oldPwd)) {
			customer.setPassword(pwd);
			customerService.updateCustomer(customer);
			req.setAttribute("succeed", "密码已经更改，下次登录请使用新密码");
			req.getRequestDispatcher("/customers/customer_update_pwd.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMes", "旧密码有误");
			req.getRequestDispatcher("/customers/customer_update_pwd.jsp").forward(req, resp);
		}
	}

	/**
	 * 将页面转发到收藏的公司页面，分页查看
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyColPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize"); // 获取到指定每一页显示多少条数据
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = companyColService.queryCount("t_company_col", customer.getId()); // 获取到数据总个数
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // 总共需要多少也来显示
		if (pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo >= totalPage) {
					pageNo = totalPage;
				}
			} catch (NumberFormatException e) {
				
			}
		}
		Pager<CompanyCol> pager = companyColService.queryByPage(pageNo, pageSize, customer.getId());
		List<CompanyCol> companyCols = pager.getResult();
		req.setAttribute("companyCols", companyCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_company_col.jsp").forward(req, resp);
	}

	/**
	 * 删除收藏的公司逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteCompanyCol(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		companyColService.deleteDataId(id, "t_company_col");
		resp.sendRedirect("query_company_col_page");
	}

	/**
	 * 将页面转发到收藏公司案例的页面。分页查看
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyColCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize"); // 获取到指定每一页显示多少条数据
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = companyCaseColService.queryCount("t_company_case_col", customer.getId()); // 获取到数据总个数
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // 总共需要多少也来显示
		if (pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo >= totalPage) {
					pageNo = totalPage;
				}
			} catch (NumberFormatException e) {
				
			}
		}
		Pager<CompanyCaseCol> pager = companyCaseColService.queryByPage(pageNo, pageSize, customer.getId());
		List<CompanyCaseCol> companyCaseCols = pager.getResult();
		req.setAttribute("companyCaseCols", companyCaseCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_company_case_col.jsp").forward(req, resp);
	}

	/**
	 * 删除收藏的案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteCompanyColCase(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		companyCaseColService.deleteDataId(id, "t_company_case_col");
		resp.sendRedirect("query_company_col_case_page");
	}

	/**
	 * 分页查看已收藏的设计师
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerColPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize"); // 获取到指定每一页显示多少条数据
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = designerColService.queryCount("t_designer_col", customer.getId()); // 获取到数据总个数
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // 总共需要多少也来显示
		if (pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo >= totalPage) {
					pageNo = totalPage;
				}
			} catch (NumberFormatException e) {
				
			}
		}
		Pager<DesignerCol> pager = designerColService.queryByPage(pageNo, pageSize, customer.getId());
		List<DesignerCol> designerCols = pager.getResult();
		req.setAttribute("designerCols", designerCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_designer_col.jsp").forward(req, resp);
	}

	/**
	 * 删除已收藏的设计师
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteDesignerCol(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		designerColService.deleteDataId(id, "t_designer_col");
		resp.sendRedirect("query_designer_col_page");
	}

	/**
	 * 分页查看已收藏的设计师案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerColCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize"); // 获取到指定每一页显示多少条数据
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = designerCaseColService.queryCount("t_designer_case_col", customer.getId()); // 获取到数据总个数
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // 总共需要多少也来显示
		if (pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo >= totalPage) {
					pageNo = totalPage;
				}
			} catch (NumberFormatException e) {
				
			}
		}
		Pager<DesignerCaseCol> pager = designerCaseColService.queryByPage(pageNo, pageSize, customer.getId());
		List<DesignerCaseCol> designerCaseCols = pager.getResult();
		req.setAttribute("designerCaseCols", designerCaseCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_designer_case_col.jsp").forward(req, resp);
	}

	/**
	 * 删除已收藏的设计师案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteDesignerColCase(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		designerCaseColService.deleteDataId(id, "t_designer_case_col");
		resp.sendRedirect("query_designer_col_case_page");
	}

	/**
	 * 分页查看已收藏的建材商
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyColPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize"); // 获取到指定每一页显示多少条数据
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = supplyColService.queryCount("t_supply_col", customer.getId()); // 获取到数据总个数
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // 总共需要多少也来显示
		if (pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo >= totalPage) {
					pageNo = totalPage;
				}
			} catch (NumberFormatException e) {
				
			}
		}
		Pager<SupplyCol> pager = supplyColService.queryByPage(pageNo, pageSize, customer.getId());
		List<SupplyCol> supplyCols = pager.getResult();
		req.setAttribute("supplyCols", supplyCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_supply_col.jsp").forward(req, resp);
	}

	/**
	 * 删除已收藏的建材商
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteSupplyCol(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		supplyColService.deleteDataId(id, "t_supply_col");
		resp.sendRedirect("query_supply_col_page");
	}

	/**
	 * 分页查看已收藏的建材商品
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize"); // 获取到指定每一页显示多少条数据
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = productColService.queryCount("t_product_col", customer.getId()); // 获取到数据总个数
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // 总共需要多少也来显示
		if (pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo >= totalPage) {
					pageNo = totalPage;
				}
			} catch (NumberFormatException e) {
				
			}
		}
		Pager<ProductCol> pager = productColService.queryByPage(pageNo, pageSize, customer.getId());
		List<ProductCol> productCols = pager.getResult();
		req.setAttribute("productCols", productCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_product_col.jsp").forward(req, resp);
	}

	/**
	 * 删除已收藏的建材商品
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteSupplyProductCol(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		productColService.deleteDataId(id, "t_product_col");
		resp.sendRedirect("query_product_col_page");
	}
	
	/**
	 * 把页面转发到主页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	/**
	 * 跳转到登入界面
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	/**
	 * 处理用户提交评论
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void comment(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String content = req.getParameter("content");
		String company_id = req.getParameter("company_id");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
			req.setAttribute("errorMes", "请先登录普通用户账号");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			Comment comment = new Comment();
			comment.setCustomer_id(customer.getId());
			comment.setContent(content);
			comment.setCreated_time(DateUtil.getDate());
			commentService.addComment(comment);
			queryCompanyParticular(req, resp, company_id);
		}
		
	}
	
	/**
	 * 查看装修公司详情的公共方法
	 * @param req
	 * @param resp
	 * @param company_id
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryCompanyParticular(HttpServletRequest req, HttpServletResponse resp, String company_id) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		Company company = companyService.queryCompanyById(company_id);
		List<CompanyCase> companyCases = companyCaseService.queryCompanyCaseByTop4(company_id);
		List<Comment> comments = commentService.queryComentByTop3();
		List<CompanyActivity> companyActivitys = companyActivityService.queryByTop4(company_id);
		if (customer != null) {
			List<Company> companys = new ArrayList<Company>();
			companys.add(company);
			WebUtil.checkCompanyCol(customer.getId(), companyService, companys);
		}
		req.setAttribute("comments", comments);
		req.setAttribute("companyCases", companyCases);
		req.setAttribute("companyActivitys", companyActivitys);
		req.setAttribute("company", company);
		req.getRequestDispatcher("/company_particular.jsp").forward(req, resp);
	}
	
	/**跳转到装修公司详情
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCompanyParticular(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		queryCompanyParticular(req, resp, id);
	}
	
	/**
	 * 在装修公司详情页面点收藏装修公司
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void companyColParticular(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String company_id = req.getParameter("id");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (customer == null) {
			session.setMaxInactiveInterval(-1);
			req.setAttribute("errorMes", "请先登入业主账号");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			CompanyCol companyCol = new CompanyCol();
			companyCol.setCustomer_id(customer.getId());
			companyCol.setCompany_id(company_id);
			companyCol.setCreated_time(DateUtil.getDate());
			companyColService.addCompanyCol(companyCol);
			WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, customer.getId());
			queryCompanyParticular(req, resp, company_id);
		}
	}

}
