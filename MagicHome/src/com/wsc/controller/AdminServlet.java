package com.wsc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsc.bean.Admin;
import com.wsc.bean.Company;
import com.wsc.bean.CompanyActivity;
import com.wsc.bean.CompanyCase;
import com.wsc.bean.Customer;
import com.wsc.bean.Designer;
import com.wsc.bean.DesignerCase;
import com.wsc.bean.Product;
import com.wsc.bean.Supply;
import com.wsc.bean.SupplyActivity;
import com.wsc.common.DateUtil;
import com.wsc.common.EncryptUtil;
import com.wsc.common.WebUtil;
import com.wsc.parentbean.Pager;
import com.wsc.service.AdminService;
import com.wsc.service.AdminServiceImpl;
import com.wsc.service.CommonService;
import com.wsc.service.CommonServiceImpl;
import com.wsc.service.CompanyActivityService;
import com.wsc.service.CompanyActivityServiceImpl;
import com.wsc.service.CompanyCaseService;
import com.wsc.service.CompanyCaseServiceImpl;
import com.wsc.service.CompanyService;
import com.wsc.service.CompanyServiceImpl;
import com.wsc.service.CustomerService;
import com.wsc.service.CustomerServiceImpl;
import com.wsc.service.DesignerCaseService;
import com.wsc.service.DesignerCaseServiceImpl;
import com.wsc.service.DesignerService;
import com.wsc.service.DesignerServiceImpl;
import com.wsc.service.ProductService;
import com.wsc.service.ProductServiceImpl;
import com.wsc.service.SupplyActivityService;
import com.wsc.service.SupplyActivityServiceImpl;
import com.wsc.service.SupplyService;
import com.wsc.service.SupplyServiceImpl;

public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = -4240511411790122746L;
	
	private AdminService adminService;
	private HttpSession session;
	private CustomerService customerService;
	private CompanyService companyService;
	private CompanyCaseService companyCaseService;
	private CompanyActivityService companyActivityService;
	private SupplyService supplyService;
	private SupplyActivityService supplyActivityService;
	private ProductService productService;
	private DesignerService designerService;
	private DesignerCaseService designerCaseService;
	private CommonService commonService;
	
	public AdminServlet() {
		adminService = new AdminServiceImpl();
		customerService = new CustomerServiceImpl();
		companyService = new CompanyServiceImpl();
		companyCaseService = new CompanyCaseServiceImpl();
		companyActivityService = new CompanyActivityServiceImpl();
		supplyService = new SupplyServiceImpl();
		supplyActivityService = new SupplyActivityServiceImpl();
		productService = new ProductServiceImpl();
		designerService = new DesignerServiceImpl();
		designerCaseService = new DesignerCaseServiceImpl();
		commonService = new CommonServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getURIMethod(req);
		session = req.getSession();
		if (method.equals("login_page")) {
			// 调用显示管理员登入页面的方法
			showLoginPage(req, resp);
		} else if (method.equals("login")) {
			// 调用处理登入逻辑的方法
			loginPage(req, resp);
		} else if (method.equals("query_personal_mes_page")) {
			// 调用显示管理员信息的页面
			showPersonalMesPage(req, resp);
		} else if (method.equals("return_index_page")) {
			// 调用返回主页面的方法
			showReturnIndexPage(req, resp);
		} else if (method.equals("update_personal_mes_page")) {
			// 调用显示修改管理员信息页面
			showUpdatePersonalMesPage(req, resp);
		} else if (method.equals("update_mes")) {
			// 调用处理修改管理员信息的逻辑方法
			updateMes(req, resp);
		} else if (method.equals("update_personal_pwd_page")) {
			// 调用显示修改密码页面
			showUpdatePersonalPwdPage(req, resp);
		} else if (method.equals("update_pwd")) {
			// 调用更新用户密码的方法
			updatePwd(req, resp);
		} else if (method.equals("exit")) {
			// 调用退出系统的方法
			exit(req, resp);
		} else if (method.equals("add_admin_page")) {
			// 调用显示添加管理员页面
			showAddAdminPage(req, resp);
		} else if (method.equals("add_admin")) {
			// 调用添加管理员逻辑
			addAdmin(req, resp);
		} else if (method.equals("query_admin_all_page")) {
			// 查看所有管理员页面
			showQueryAdminAllPage(req, resp);
		} else if (method.equals("forbid_admin")) {
			// 禁用管理员
			forbidAdmin(req, resp);
		} else if (method.equals("start_admin")) {
			// 启用管理员
			startAdmin(req, resp);
		} else if (method.equals("update_admin_mes_page")) {
			// 把页面转发到修改管理员信息页面
			showUpdateAdminMesPage(req, resp);
		} else if (method.equals("update_admin_mes")) {
			// 处理修改管理员信息逻辑
			updateAdminMes(req, resp);
		} else if (method.equals("reset_admin_pwd")) {
			// 重置管理员密码
			resetAdminPwd(req, resp);
		} else if (method.equals("delete_admin_mes_page")) {
			// 如果是普通管理员进行删除，则提示没有权限
			showDeleteAdminMesPage(req, resp);
		} else if (method.equals("delete_admin_mes")) {
			// 超级管理员想对管理员进行删除
			deleteAdminMes(req, resp);
		} else if (method.equals("condition_query_admin")) {
			// 根据条件查询管理员
			conditionQueryAdmin(req, resp);
		} else if (method.equals("query_customer_all_page")) {
			// 调用把页面转发到显示所有用户的页面
			showQueryCustomerAllPage(req, resp);
		} else if (method.equals("forbid_customer")) {
			// 禁用用户账号
			forbidCustomer(req, resp);
		} else if (method.equals("start_customer")) {
			// 启用用户账号
			startCustomer(req, resp);
		} else if (method.equals("update_customer_mes_page")) {
			// 转发到修改用户的一些信息页面
			showUpdateCustomerMesPage(req, resp);
		} else if (method.equals("update_customer_mes")) {
			// 处理修改用户信息的逻辑
			updateCustomerMes(req, resp);
		} else if (method.equals("reset_customer_pwd")) {
			// 重置用户的密码
			resetCustomerPwd(req, resp);
		} else if (method.equals("condition_query_customer")) {
			// 根据条件查询用户
			conditionQueryCustomer(req, resp);
		} else if (method.equals("query_not_audit_company_page")) {
			// 查询未审核的公司，分页显示
			showQueryNotAuditCompanyPage(req, resp);
		} else if(method.equals("condition_query_not_audit_company")) {
			// 按条件查询未审核的公司
			conditionQueryNotAuditCompany(req,resp);
		} else if (method.equals("pass_audit_company")) {
			// 公司通过审核
			passAuditCompany(req, resp);
		} else if (method.equals("delete_audit_company")) {
			// 删除审核公司
			deleteAuditCompany(req, resp);
		} else if (method.equals("query_company_page")) {
			// 分页显示已审核的公司
			showQueryCompanyPage(req, resp);
		} else if (method.equals("condition_query_company")) {
			// 按条件查询已审核的公司
			conditionQueryCompany(req, resp);
		} else if (method.equals("forbid_company")) {
			// 禁用公司
			forbidCompany(req, resp);
		} else if (method.equals("start_company")) {
			// 启用公司
			startCompany(req, resp);
		} else if (method.equals("query_company_case_page")) {
			// 分页显示查看装修公司案例
			showQueryCompanyCasePage(req, resp);
		} else if (method.equals("condition_query_company_case")) {
			// 按条件查找装修公司案例
			conditionQueryCompanyCase(req, resp);
		} else if (method.equals("query_company_activity_page")) {
			// 分页显示所有装修公司的活动
			showQueryCompanyActivityPage(req, resp);
		} else if (method.equals("condition_query_company_activity")) {
			// 按条件查询装修公司活动
			conditionQueryCompanyActivity(req, resp);
		} else if (method.equals("delete_company_activity")) {
			// 删除装修公司活动
			deleteCompanyActivity(req, resp);
		} else if (method.equals("query_not_audit_supply_page")) {
			// 查询未审核的建材商，分页显示
			showQueryNotAuditSupplyPage(req, resp);
		} else if(method.equals("condition_query_not_audit_supply")) {
			// 按条件查询未审核的建材商
			conditionQueryNotAuditSupply(req,resp);
		} else if (method.equals("pass_audit_supply")) {
			// 建材商通过审核
			passAuditSupply(req, resp);
		} else if (method.equals("query_supply_page")) {
			// 分页显示已审核的建材商
			showQuerySupplyPage(req, resp);
		} else if (method.equals("condition_query_supply")) {
			// 按条件查询已审核的建材商
			conditionQuerySupply(req, resp);
		} else if (method.equals("forbid_supply")) {
			// 禁用建材商
			forbidSupply(req, resp);
		} else if (method.equals("start_supply")) {
			// 启用建材商
			startSupply(req, resp);
		} else if (method.equals("query_supply_activity_page")) {
			// 分页显示所有建材商活动
			showQuerySupplyActivityPage(req, resp);
		} else if (method.endsWith("delete_supply_activity")) {
			// 删除建材商活动
			deleteSupplyActivity(req, resp);
		} else if (method.equals("query_supply_product_page")) {
			// 分页显示所有建材商品
			showQuerySupplyProductPage(req, resp);
		} else if (method.equals("condition_query_supply_product")) {
			// 按条件查找建材商品
			conditionQuerySupplyProduct(req, resp);
		} else if (method.equals("query_not_audit_designer_page")) {
			// 查询未审核的设计师，分页显示
			showQueryNotAuditDesignerPage(req, resp);
		} else if(method.equals("condition_query_not_audit_designer")) {
			// 按条件查询未审核的设计师
			conditionQueryNotAuditDesigner(req,resp);
		} else if (method.equals("pass_audit_designer")) {
			// 未审核的设计师通过审核
			passAuditDesigner(req, resp);
		} else if (method.equals("delete_not_audit_designer")) {
			// 删除未审核设计师
			deleteNotAuditDesigner(req, resp);
		} else if (method.equals("query_designer_page")) {
			// 分页显示已审核的设计师
			showQueryDesignerPage(req, resp);
		} else if (method.equals("condition_query_designer")) {
			// 按条件查询已审核的设计师
			conditionQueryDesigner(req, resp);
		} else if (method.equals("forbid_designer")) {
			// 禁用设计师
			forbidDesigner(req, resp);
		} else if (method.equals("start_designer")) {
			// 启用设计师
			startDesigner(req, resp);
		} else if (method.equals("query_designer_case_page")) {
			// 分页显示查看设计师案例
			showQueryDesignerCasePage(req, resp);
		} else if (method.equals("condition_query_designer_case")) {
			// 按条件查找设计师案例
			conditionQueryDesignerCase(req, resp);
		} 
		
	}

	/**
	 * 将页面转发到管理员登入界面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (session.getAttribute("admin") != null) {
			req.getRequestDispatcher("/admins/admin_index.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 用于处理管理员登入逻辑，用来判断邮箱密码是否正确
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void loginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pwd = req.getParameter("password"); 
		if (email != null && !email.equals("") && pwd != null && !pwd.equals("")) {
			String password = EncryptUtil.encrypt(pwd); // 把获取到的密码先进行加密处理
			Admin admin = adminService.queryCurr(email, password);
			if (email.equals(admin.getE_mail()) && password.equals(admin.getPassword())) {
				if (admin.getStatus().equals("Y")) {
					admin.setLast_login_time(DateUtil.getDate());
					admin.setLogin_count(admin.getLogin_count() + 1);
					adminService.updateAdmin(admin); // 更新数据库
					session.setAttribute("admin", admin);
					req.getRequestDispatcher("/admins/admin_index.jsp").forward(req, resp);
				} else {
					req.setAttribute("errorMes", "该账号不可用");
					req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("errorMes", "账号或密码错误");
				req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("errorMes", "账号或密码错误");
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		}
		
		
	}
	
	/**
	 * 将页面转发到管理员信息页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showPersonalMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		} else {
			req.setAttribute("lastLoginTime", DateUtil.getDateStr(admin.getLast_login_time()));
			req.getRequestDispatcher("/admins/admin_personal_mes.jsp").forward(req, resp);
		}
	}

	/**
	 * 将页面转发回主页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showReturnIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (session.getAttribute("admin") != null) {
			req.getRequestDispatcher("/admins/admin_index.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		}
		
	}

	/**
	 * 把页面转发到修改管理员信息的页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		if (session.getAttribute("admin") == null) {
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/admins/admin_update_mes.jsp").forward(req, resp);
		}
	}

	/**
	 * 用来处理修改管理员逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateMes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		} else {
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			admin.setName(name);
			admin.setPhone(phone);
			adminService.updateAdmin(admin);
			req.getRequestDispatcher("/admins/admin_personal_mes.jsp").forward(req, resp);
		}
		
		
	}

	/**
	 * 用于把页面转发到修改密码的页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalPwdPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		if (session.getAttribute("admin") == null) {
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/admins/admin_update_pwd.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * 用于处理修改密码的逻辑
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null){
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		} else {
			String pwd = EncryptUtil.encrypt(req.getParameter("pwd"));
			String oldPwd = EncryptUtil.encrypt(req.getParameter("oldPwd"));
			if (admin.getPassword().equals(oldPwd)) {
				admin.setPassword(pwd);
				adminService.updateAdmin(admin);
				req.setAttribute("succeed", "密码已经更改，下次请使用新密码登入");
				req.getRequestDispatcher("/admins/admin_update_pwd.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMes", "旧密码有误");
				req.getRequestDispatcher("/admins/admin_update_pwd.jsp").forward(req, resp);
			}
		}
		
	}

	/**
	 * 退出系统，把session移除掉
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute("admin");
		req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
	}
	
	/**
	 * 将页面转发到添加管理员页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showAddAdminPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		if (session.getAttribute("admin") == null) {
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/admins/admin_add_admin.jsp").forward(req, resp);
		}
		
	}

	/**
	 * 处理添加管理员逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		if (session.getAttribute("admin") == null) {
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		} else {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String status = req.getParameter("status");
			if (WebUtil.checkEmail(commonService, email, req, resp)) {
				Date date = DateUtil.getDate();
				Admin admin = new Admin();
				admin.setE_mail(email);
				admin.setPassword(EncryptUtil.encrypt(password));
				admin.setName(name);
				admin.setPhone(phone);
				admin.setRole("normal"); // 默认是添加普通管理员
				admin.setCreated_time(date);
				admin.setLast_login_time(date);
				admin.setLogin_count(0);
				admin.setStatus(status);
				adminService.addAdmin(admin);
				resp.sendRedirect("query_admin_all_page");
			} else {
				req.setAttribute("errorMes", "已经存在该用户，请重新输入");
				req.getRequestDispatcher("/admins/admin_add_admin.jsp").forward(req, resp);
			}
		}
		
	}

	/**
	 * 把页面转发到查看所有管理员页面,分页显示所有管理员信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryAdminAllPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		int total = commonService.queryCount("t_admin"); // 获取到数据总个数
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
		Pager<Admin> pager = adminService.queryByPage(pageNo, pageSize);
		List<Admin> admins = pager.getResult();
		req.setAttribute("admins", admins);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
	}

	/**
	 * 启用和禁用管理员的公共方法
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void setAdminStatus(HttpServletRequest req, HttpServletResponse resp, String status) throws IOException, ServletException {
		session = req.getSession();
		String email = req.getParameter("email");
		Admin admin_self = (Admin) session.getAttribute("admin");
		if (admin_self.getE_mail().equals("Admin@qq.com")) {
			if (email.equals("Admin@qq.com")) {
				req.setAttribute("errorMes", "不能禁用超级管理员");
				req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
			} else {
				Admin admin = adminService.queryAdmin(email);
				admin.setStatus(status);
				adminService.updateAdmin(admin);
				resp.sendRedirect("query_admin_all_page");
			}
		} else {
			req.setAttribute("errorMes", "抱歉，您没有权限操作");
			req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
		}
		
		
	}
	
	/**
	 * 禁用管理员逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void forbidAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		setAdminStatus(req,resp, "N");
	}

	/**
	 * 启用管理员逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void startAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		setAdminStatus(req, resp, "Y");
	}
	
	/**
	 * 把页面转发到修改管理信息页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdateAdminMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		if (email.equals("Admin@qq.com")) {
			req.setAttribute("errorMes", "不能修改超级管理员信息");
			req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
		} else {
			Admin admin = adminService.queryAdmin(email);
			req.setAttribute("admin", admin);
			req.getRequestDispatcher("/admins/admin_update_admin.jsp").forward(req, resp);
		}
	}

	/**
	 * 用于处理修改管理员信息逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateAdminMes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String status = req.getParameter("status");
		Admin admin = adminService.queryAdmin(email);
		admin.setName(name);
		admin.setPhone(phone);
		admin.setStatus(status);
		adminService.updateAdmin(admin);
		req.setAttribute("succeed", "信息修改成功");
		req.getRequestDispatcher("/admins/admin_update_mes.jsp").forward(req, resp);
	}

	/**
	 * 用于处理重置管理员密码，以防管理员忘记密码，只有超级管理员才有此权限
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void resetAdminPwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String newPwd = EncryptUtil.encrypt("123456");
		Admin admin = adminService.queryAdmin(email);
		admin.setPassword(newPwd);
		adminService.updateAdmin(admin);
		req.setAttribute("succeed", "密码重置成功，默认为“123456”");
		req.getRequestDispatcher("/admins/admin_update_pwd.jsp").forward(req, resp);
	}

	/**
	 * 表示是普通管理员想进行删除管理员操作，则提示用户，无法删除
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDeleteAdminMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("errorMes", "您没有权限此操作");
		req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
	}

	/**
	 * 表示是超级管理员想对管理员进行删除，则处理删除逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void deleteAdminMes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		if (email.equals("Admin@qq.com")) {
			req.setAttribute("errorMes", "不能删除超级管理员");
			req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
		} else {
			commonService.deleteData(email, "t_admin");
			resp.sendRedirect("query_admin_all_page");
		}
	}

	/**
	 * 根据条件来查找管理员
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void conditionQueryAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		if (email.equals("") && name.equals("")) {
			resp.sendRedirect("query_admin_all_page");
		} else {
			List<Admin> admins = adminService.conditionQueryAdmin(email, name);
			req.setAttribute("admins", admins);
			req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 将页面转发到查看所有用户,分页显示出所有的用户信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCustomerAllPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCount("t_customer"); // 获取到数据总个数
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
		
		Pager<Customer> pager = customerService.queryByPage(pageNo, pageSize);
		List<Customer> customers = pager.getResult();
		req.setAttribute("customers", customers);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/admins/admin_query_customer.jsp").forward(req, resp);
	}

	/**
	 * 禁用和启用用户账号的公共方法
	 * @throws IOException 
	 */
	private void setCustomerStatus(HttpServletRequest req, HttpServletResponse resp, String status) throws IOException {
		String email = req.getParameter("email");
		Customer customer = customerService.queryCustomer(email);
		customer.setStatus(status);
		customerService.updateCustomer(customer);
		resp.sendRedirect("query_customer_all_page");
	}
	
	/**
	 * 冻结用户账号
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void forbidCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		setCustomerStatus(req, resp, "N");
	}

	/**
	 * 解冻用户账号
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void startCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		setCustomerStatus(req, resp, "Y");
	}
	
	/**
	 * 用于把页面转发到修改用户信息的页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdateCustomerMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		Customer customer = customerService.queryCustomer(email);
		req.setAttribute("customer", customer);
		req.getRequestDispatcher("/admins/admin_update_customer.jsp").forward(req, resp);
	}

	/**
	 * 用于处理修改用户信息的逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateCustomerMes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String plot_name = req.getParameter("plot_name");
		String address = req.getParameter("address");
		Customer customer = customerService.queryCustomer(email);
		customer.setName(name);
		customer.setPhone(phone);
		customer.setPlot_name(plot_name);
		customer.setAddress(address);
		customerService.updateCustomer(customer);
		req.setAttribute("succeed", "信息修改成功");
		req.getRequestDispatcher("/admins/admin_update_customer.jsp").forward(req, resp);
	}

	/**
	 * 用户重置用户的密码
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void resetCustomerPwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String newPwd = EncryptUtil.encrypt("123456");
		Customer customer = customerService.queryCustomer(email);
		customer.setPassword(newPwd);
		customerService.updateCustomer(customer);
		req.setAttribute("succeed", "密码重置成功,默认密码为‘123456’");
		req.getRequestDispatcher("/admins/admin_update_customer.jsp").forward(req, resp);
	}

	/**
	 * 根据条件来查询用户
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void conditionQueryCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		if (email.equals("") && name.equals("")) {
			resp.sendRedirect("query_customer_all_page");
		} else {
			List<Customer> customers = customerService.conditionQueryCustomer(email, name);
			req.setAttribute("customers", customers);
			req.getRequestDispatcher("/admins/admin_query_customer.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * 公司管理分页显示的公共部分（通过审核和未通过审核）
	 * @param req
	 * @param resp
	 * @param checked
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	private void companyCommon(HttpServletRequest req, HttpServletResponse resp, String checked, String url) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCountByChecked(checked, "t_company"); // 获取到数据总个数
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
		Pager<Company> pager = companyService.queryByPage(pageNo, pageSize, checked); // 分页查询未审核的装修公司
		List<Company> companys = pager.getResult();
		req.setAttribute("companys", companys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher(url).forward(req, resp);
	}

	/**
	 * 分页显示未审核的装修公司
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryNotAuditCompanyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		companyCommon(req, resp, "N", "/admins/admin_not_audit_company.jsp");
	}

	/**
	 * 按条件查询公共的部分
	 * @param req
	 * @param resp
	 * @param checked
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	public void conditionQueryCommon(HttpServletRequest req, HttpServletResponse resp, String checked, String url, String url1) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String principal = req.getParameter("principal");
		if (email.equals("") && name.equals("") && principal.equals("")) {
			resp.sendRedirect(url1);
		} else {
			List<Company> companys = companyService.conditionQueryNotAuditCompany(checked, email, name, principal);
			req.setAttribute("companys", companys);
			req.getRequestDispatcher(url).forward(req, resp);
		}
		
	}
	
	/**
	 * 按条件查询未审核的公司
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void conditionQueryNotAuditCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		conditionQueryCommon(req, resp, "N", "/admins/admin_not_audit_company.jsp", "query_not_audit_company_page");
	}

	/**
	 * 公司操作部分的共同部分，用来做公司通过审核，启用和禁用公司
	 * @param req
	 * @param resp
	 * @param status
	 * @param url
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void operationCompanyCommon(HttpServletRequest req, HttpServletResponse resp, String status, String url) throws ServletException, IOException {
		String email = req.getParameter("email");
		Company company = companyService.queryCompanyByEmail(email);
		company.setChecked("Y");
		company.setChecked_time(DateUtil.getDate());
		company.setStatus(status);
		companyService.updateCompany(company);
		resp.sendRedirect(url);
	}
	
	/**
	 * 公司通过审核
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void passAuditCompany(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		operationCompanyCommon(req, resp, "Y", "query_not_audit_company_page");
	}

	/**
	 * 删除公司通用的方法
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void deleteCompanyCommon(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException {
		String email = req.getParameter("email");
		commonService.deleteData(email, "t_company");
		resp.sendRedirect(url);
	}
	
	/**
	 * 删除未审核公司
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteAuditCompany(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		deleteCompanyCommon(req, resp, "query_not_audit_company_page");
	}
	
	/**
	 * 把页面转发到分页显示已经通过审核的公司
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		companyCommon(req, resp, "Y", "/admins/admin_query_company.jsp");
	}

	/**
	 * 按条件查询已审核的公司
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void conditionQueryCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		conditionQueryCommon(req, resp, "Y", "/admins/admin_query_company.jsp", "query_company_page");
	}
	
	/**
	 * 禁用公司
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void forbidCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationCompanyCommon(req, resp, "N", "query_company_page");
	}

	/**
	 * 启用公司
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void startCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationCompanyCommon(req, resp, "Y", "query_company_page");
	}

	/**
	 * 把页面转发到查看装修公司案例页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		int total = companyCaseService.queryCount("t_company_case"); // 获取到数据总个数
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
		Pager<CompanyCase> pager = companyCaseService.queryByPage(pageNo, pageSize);
		List<CompanyCase> companyCases = pager.getResult();
		req.setAttribute("companyCases", companyCases);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/admins/admin_query_company_case.jsp").forward(req, resp);
	}
	
	/**
	 * 按条件查找装修公司案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void conditionQueryCompanyCase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String companyName = req.getParameter("companyName");
		String caseName = req.getParameter("caseName");
		String plotName = req.getParameter("plotName");
		if (companyName.equals("") && caseName.equals("") && plotName.equals("")) {
			resp.sendRedirect("query_company_case_page");
		} else {
			List<CompanyCase> companyCases = companyCaseService.queryCompanyCaseByCondition(companyName, caseName, plotName);
			req.setAttribute("companyCases", companyCases);
			req.getRequestDispatcher("/admins/admin_query_company_case.jsp").forward(req, resp);
		}
	}

	/**
	 * 分页显示所有装修公司的活动
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		int total = companyActivityService.queryCount("t_company_activity"); // 获取到数据总个数
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
		Pager<CompanyActivity> pager = companyActivityService.queryByPage(pageNo, pageSize);
		List<CompanyActivity> companyActivitys = pager.getResult();
		req.setAttribute("companyActivitys", companyActivitys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/admins/admin_query_company_activity.jsp").forward(req, resp);
	}
	
	/**
	 * 按条件查找装修公司活动
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void conditionQueryCompanyActivity(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String company_name = req.getParameter("companyName");
		String activity_name = req.getParameter("activityName");
		if (company_name.equals("") && activity_name.equals("")) {
			resp.sendRedirect("query_company_activity_page");
		} else {
			List<CompanyActivity> companyActivitys = companyActivityService.queryCompanyActivityByCondition(company_name, activity_name);
			req.setAttribute("companyActivitys", companyActivitys);
			req.getRequestDispatcher("/admins/admin_query_company_activity.jsp").forward(req, resp);
		}
	}

	/**
	 * 删除装修公司活动
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteCompanyActivity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		companyCaseService.deleteDataId(id, "t_company_activity");
		resp.sendRedirect("query_company_activity_page");
	}
	
	/**
	 * 建材商分页显示的公共部分（通过审核和未通过审核）
	 * @param req
	 * @param resp
	 * @param checked
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supplyCommon(HttpServletRequest req, HttpServletResponse resp, String checked, String url) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCountByChecked(checked, "t_supply"); // 获取到数据总个数
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
		Pager<Supply> pager = supplyService.queryByPage(pageNo, pageSize, checked); // 分页查询指定是否审核的建材商
		List<Supply> supplys = pager.getResult();
		req.setAttribute("supplys", supplys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher(url).forward(req, resp);
	}
	
	/**
	 * 分页显示未审核的建材商信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryNotAuditSupplyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		supplyCommon(req, resp, "N", "/admins/admin_not_audit_supply.jsp");
	}

	/**
	 * 按条件查询未审核的建材商信息
	 * @param req
	 * @param resp
	 */
	private void conditionQueryNotAuditSupply(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 以后再实现
		
	}

	/**
	 * 建材商操作部分的共同部分，用来做建材商通过审核，启用和禁用建材商
	 * @param req
	 * @param resp
	 * @param status
	 * @param url
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void operationSupplyCommon(HttpServletRequest req, HttpServletResponse resp, String status, String url) throws ServletException, IOException {
		String email = req.getParameter("email");
		Supply supply = supplyService.querySupplyByEmail(email);
		supply.setChecked("Y");
		supply.setChecked_time(DateUtil.getDate());
		supply.setStatus(status);
		supplyService.updateSupply(supply);
		resp.sendRedirect(url);
	}
	
	/**
	 * 建材商通过审核逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void passAuditSupply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationSupplyCommon(req, resp, "Y", "query_not_audit_supply_page");
	}

	/**
	 * 分页查询通过审核的建材商信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		supplyCommon(req, resp, "Y", "/admins/admin_query_supply.jsp");
	}

	/**
	 * 按条件查询通过审核的建材商信息
	 * @param req
	 * @param resp
	 */
	private void conditionQuerySupply(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 以后再实现
		
	}

	/**
	 * 禁用建材商
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void forbidSupply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationSupplyCommon(req, resp, "N", "query_supply_page");
	}

	/**
	 * 启用建材商
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void startSupply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationSupplyCommon(req, resp, "Y", "query_supply_page");
	}

	/**
	 * 分页显示所有建材商的活动信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		int total = commonService.queryCount("t_supply_activity"); // 获取到数据总个数
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
		Pager<SupplyActivity> pager = supplyActivityService.queryByPage(pageNo, pageSize);
		List<SupplyActivity> supplyActivitys = pager.getResult();
		req.setAttribute("supplyActivitys", supplyActivitys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/admins/admin_query_supply_activity.jsp").forward(req, resp);
	}
	
	/**
	 * 删除建材商活动
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteSupplyActivity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		companyCaseService.deleteDataId(id, "t_supply_activity");
		resp.sendRedirect("query_supply_activity_page");
	}
	
	/**
	 * 分页查看建材商品
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		int total = productService.queryCount("t_product"); // 获取到数据总个数
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
		Pager<Product> pager = productService.queryByPage(pageNo, pageSize);
		List<Product> products = pager.getResult();
		req.setAttribute("products", products);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/admins/admin_query_product.jsp").forward(req, resp);
	}

	/**
	 * 按条件查询建材商品
	 * @param req
	 * @param resp
	 */
	private void conditionQuerySupplyProduct(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 以后再实现
		
	}

	/**
	 * 公司管理分页显示的公共部分（通过审核和未通过审核）
	 * @param req
	 * @param resp
	 * @param checked
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	private void designerDesigner(HttpServletRequest req, HttpServletResponse resp, String checked, String url) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCountByChecked(checked, "t_designer"); // 获取到数据总个数
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
		Pager<Designer> pager = designerService.queryByPage(pageNo, pageSize, checked); // 分页查询未审核的装修公司
		List<Designer> designers = pager.getResult();
		req.setAttribute("designers", designers);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher(url).forward(req, resp);
	}

	/**
	 * 分页查询未审核的设计师
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryNotAuditDesignerPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		designerDesigner(req, resp, "N", "/admins/admin_not_audit_designer.jsp");
	}

	/**
	 * 按条件查询未审核的设计师
	 * @param req
	 * @param resp
	 */
	private void conditionQueryNotAuditDesigner(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 以后再实现
		
	}

	/**
	 * 设计师操作部分的共同部分，用来做公司通过审核，启用和禁用公司
	 * @param req
	 * @param resp
	 * @param status
	 * @param url
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void operationDesignerCommon(HttpServletRequest req, HttpServletResponse resp, String status, String url) throws ServletException, IOException {
		String email = req.getParameter("email");
		Designer designer = designerService.queryDesignerByEmail(email);
		designer.setChecked("Y");
		designer.setChecked_time(DateUtil.getDate());
		designer.setStatus(status);
		designerService.updateDesigner(designer);
		resp.sendRedirect(url);
	}
	
	/**
	 * 未审核的设计师通过审核
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void passAuditDesigner(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationDesignerCommon(req, resp, "Y", "query_not_audit_designer_page");
	}

	/**
	 * 删除设计师通用的方法
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void deleteDesignerCommon(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException {
		String email = req.getParameter("email");
		commonService.deleteData(email, "t_designer");
		resp.sendRedirect(url);
	}
	
	/**
	 * 删除未审核的设计师
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteNotAuditDesigner(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		deleteDesignerCommon(req, resp, "query_not_audit_designer_page");
	}

	/**
	 * 查询已审核的设计师
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		designerDesigner(req, resp, "Y", "/admins/admin_query_designer.jsp");
	}

	/**
	 * 按条件查询已审核的设计师
	 * @param req
	 * @param resp
	 */
	private void conditionQueryDesigner(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 以后再实现
		
	}

	/**
	 * 禁用设计师
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void forbidDesigner(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationDesignerCommon(req, resp, "N", "query_designer_page");
	}

	/**
	 * 取消禁用设计师
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void startDesigner(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationDesignerCommon(req, resp, "Y", "query_designer_page");
	}

	/**
	 * 分页显示设计师案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		int total = designerCaseService.queryCount("t_designer_case"); // 获取到数据总个数
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
		Pager<DesignerCase> pager = designerCaseService.queryByPage(pageNo, pageSize);
		List<DesignerCase> designerCases = pager.getResult();
		req.setAttribute("designerCases", designerCases);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/admins/admin_query_designer_case.jsp").forward(req, resp);
	}

	/**
	 * 按条件查询设计师案例
	 * @param req
	 * @param resp
	 */
	private void conditionQueryDesignerCase(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 以后再实现
		
	}

	
}