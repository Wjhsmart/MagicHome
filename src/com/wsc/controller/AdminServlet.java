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
			// ������ʾ����Ա����ҳ��ķ���
			showLoginPage(req, resp);
		} else if (method.equals("login")) {
			// ���ô�������߼��ķ���
			loginPage(req, resp);
		} else if (method.equals("query_personal_mes_page")) {
			// ������ʾ����Ա��Ϣ��ҳ��
			showPersonalMesPage(req, resp);
		} else if (method.equals("return_index_page")) {
			// ���÷�����ҳ��ķ���
			showReturnIndexPage(req, resp);
		} else if (method.equals("update_personal_mes_page")) {
			// ������ʾ�޸Ĺ���Ա��Ϣҳ��
			showUpdatePersonalMesPage(req, resp);
		} else if (method.equals("update_mes")) {
			// ���ô����޸Ĺ���Ա��Ϣ���߼�����
			updateMes(req, resp);
		} else if (method.equals("update_personal_pwd_page")) {
			// ������ʾ�޸�����ҳ��
			showUpdatePersonalPwdPage(req, resp);
		} else if (method.equals("update_pwd")) {
			// ���ø����û�����ķ���
			updatePwd(req, resp);
		} else if (method.equals("exit")) {
			// �����˳�ϵͳ�ķ���
			exit(req, resp);
		} else if (method.equals("add_admin_page")) {
			// ������ʾ��ӹ���Աҳ��
			showAddAdminPage(req, resp);
		} else if (method.equals("add_admin")) {
			// ������ӹ���Ա�߼�
			addAdmin(req, resp);
		} else if (method.equals("query_admin_all_page")) {
			// �鿴���й���Աҳ��
			showQueryAdminAllPage(req, resp);
		} else if (method.equals("forbid_admin")) {
			// ���ù���Ա
			forbidAdmin(req, resp);
		} else if (method.equals("start_admin")) {
			// ���ù���Ա
			startAdmin(req, resp);
		} else if (method.equals("update_admin_mes_page")) {
			// ��ҳ��ת�����޸Ĺ���Ա��Ϣҳ��
			showUpdateAdminMesPage(req, resp);
		} else if (method.equals("update_admin_mes")) {
			// �����޸Ĺ���Ա��Ϣ�߼�
			updateAdminMes(req, resp);
		} else if (method.equals("reset_admin_pwd")) {
			// ���ù���Ա����
			resetAdminPwd(req, resp);
		} else if (method.equals("delete_admin_mes_page")) {
			// �������ͨ����Ա����ɾ��������ʾû��Ȩ��
			showDeleteAdminMesPage(req, resp);
		} else if (method.equals("delete_admin_mes")) {
			// ��������Ա��Թ���Ա����ɾ��
			deleteAdminMes(req, resp);
		} else if (method.equals("condition_query_admin")) {
			// ����������ѯ����Ա
			conditionQueryAdmin(req, resp);
		} else if (method.equals("query_customer_all_page")) {
			// ���ð�ҳ��ת������ʾ�����û���ҳ��
			showQueryCustomerAllPage(req, resp);
		} else if (method.equals("forbid_customer")) {
			// �����û��˺�
			forbidCustomer(req, resp);
		} else if (method.equals("start_customer")) {
			// �����û��˺�
			startCustomer(req, resp);
		} else if (method.equals("update_customer_mes_page")) {
			// ת�����޸��û���һЩ��Ϣҳ��
			showUpdateCustomerMesPage(req, resp);
		} else if (method.equals("update_customer_mes")) {
			// �����޸��û���Ϣ���߼�
			updateCustomerMes(req, resp);
		} else if (method.equals("reset_customer_pwd")) {
			// �����û�������
			resetCustomerPwd(req, resp);
		} else if (method.equals("condition_query_customer")) {
			// ����������ѯ�û�
			conditionQueryCustomer(req, resp);
		} else if (method.equals("query_not_audit_company_page")) {
			// ��ѯδ��˵Ĺ�˾����ҳ��ʾ
			showQueryNotAuditCompanyPage(req, resp);
		} else if(method.equals("condition_query_not_audit_company")) {
			// ��������ѯδ��˵Ĺ�˾
			conditionQueryNotAuditCompany(req,resp);
		} else if (method.equals("pass_audit_company")) {
			// ��˾ͨ�����
			passAuditCompany(req, resp);
		} else if (method.equals("delete_audit_company")) {
			// ɾ����˹�˾
			deleteAuditCompany(req, resp);
		} else if (method.equals("query_company_page")) {
			// ��ҳ��ʾ����˵Ĺ�˾
			showQueryCompanyPage(req, resp);
		} else if (method.equals("condition_query_company")) {
			// ��������ѯ����˵Ĺ�˾
			conditionQueryCompany(req, resp);
		} else if (method.equals("forbid_company")) {
			// ���ù�˾
			forbidCompany(req, resp);
		} else if (method.equals("start_company")) {
			// ���ù�˾
			startCompany(req, resp);
		} else if (method.equals("query_company_case_page")) {
			// ��ҳ��ʾ�鿴װ�޹�˾����
			showQueryCompanyCasePage(req, resp);
		} else if (method.equals("condition_query_company_case")) {
			// ����������װ�޹�˾����
			conditionQueryCompanyCase(req, resp);
		} else if (method.equals("query_company_activity_page")) {
			// ��ҳ��ʾ����װ�޹�˾�Ļ
			showQueryCompanyActivityPage(req, resp);
		} else if (method.equals("condition_query_company_activity")) {
			// ��������ѯװ�޹�˾�
			conditionQueryCompanyActivity(req, resp);
		} else if (method.equals("delete_company_activity")) {
			// ɾ��װ�޹�˾�
			deleteCompanyActivity(req, resp);
		} else if (method.equals("query_not_audit_supply_page")) {
			// ��ѯδ��˵Ľ����̣���ҳ��ʾ
			showQueryNotAuditSupplyPage(req, resp);
		} else if(method.equals("condition_query_not_audit_supply")) {
			// ��������ѯδ��˵Ľ�����
			conditionQueryNotAuditSupply(req,resp);
		} else if (method.equals("pass_audit_supply")) {
			// ������ͨ�����
			passAuditSupply(req, resp);
		} else if (method.equals("query_supply_page")) {
			// ��ҳ��ʾ����˵Ľ�����
			showQuerySupplyPage(req, resp);
		} else if (method.equals("condition_query_supply")) {
			// ��������ѯ����˵Ľ�����
			conditionQuerySupply(req, resp);
		} else if (method.equals("forbid_supply")) {
			// ���ý�����
			forbidSupply(req, resp);
		} else if (method.equals("start_supply")) {
			// ���ý�����
			startSupply(req, resp);
		} else if (method.equals("query_supply_activity_page")) {
			// ��ҳ��ʾ���н����̻
			showQuerySupplyActivityPage(req, resp);
		} else if (method.endsWith("delete_supply_activity")) {
			// ɾ�������̻
			deleteSupplyActivity(req, resp);
		} else if (method.equals("query_supply_product_page")) {
			// ��ҳ��ʾ���н�����Ʒ
			showQuerySupplyProductPage(req, resp);
		} else if (method.equals("condition_query_supply_product")) {
			// ���������ҽ�����Ʒ
			conditionQuerySupplyProduct(req, resp);
		} else if (method.equals("query_not_audit_designer_page")) {
			// ��ѯδ��˵����ʦ����ҳ��ʾ
			showQueryNotAuditDesignerPage(req, resp);
		} else if(method.equals("condition_query_not_audit_designer")) {
			// ��������ѯδ��˵����ʦ
			conditionQueryNotAuditDesigner(req,resp);
		} else if (method.equals("pass_audit_designer")) {
			// δ��˵����ʦͨ�����
			passAuditDesigner(req, resp);
		} else if (method.equals("delete_not_audit_designer")) {
			// ɾ��δ������ʦ
			deleteNotAuditDesigner(req, resp);
		} else if (method.equals("query_designer_page")) {
			// ��ҳ��ʾ����˵����ʦ
			showQueryDesignerPage(req, resp);
		} else if (method.equals("condition_query_designer")) {
			// ��������ѯ����˵����ʦ
			conditionQueryDesigner(req, resp);
		} else if (method.equals("forbid_designer")) {
			// �������ʦ
			forbidDesigner(req, resp);
		} else if (method.equals("start_designer")) {
			// �������ʦ
			startDesigner(req, resp);
		} else if (method.equals("query_designer_case_page")) {
			// ��ҳ��ʾ�鿴���ʦ����
			showQueryDesignerCasePage(req, resp);
		} else if (method.equals("condition_query_designer_case")) {
			// �������������ʦ����
			conditionQueryDesignerCase(req, resp);
		} 
		
	}

	/**
	 * ��ҳ��ת��������Ա�������
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
	 * ���ڴ������Ա�����߼��������ж����������Ƿ���ȷ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void loginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pwd = req.getParameter("password"); 
		if (email != null && !email.equals("") && pwd != null && !pwd.equals("")) {
			String password = EncryptUtil.encrypt(pwd); // �ѻ�ȡ���������Ƚ��м��ܴ���
			Admin admin = adminService.queryCurr(email, password);
			if (email.equals(admin.getE_mail()) && password.equals(admin.getPassword())) {
				if (admin.getStatus().equals("Y")) {
					admin.setLast_login_time(DateUtil.getDate());
					admin.setLogin_count(admin.getLogin_count() + 1);
					adminService.updateAdmin(admin); // �������ݿ�
					session.setAttribute("admin", admin);
					req.getRequestDispatcher("/admins/admin_index.jsp").forward(req, resp);
				} else {
					req.setAttribute("errorMes", "���˺Ų�����");
					req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("errorMes", "�˺Ż��������");
				req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("errorMes", "�˺Ż��������");
			req.getRequestDispatcher("/admins/admin_login.jsp").forward(req, resp);
		}
		
		
	}
	
	/**
	 * ��ҳ��ת��������Ա��Ϣҳ��
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
	 * ��ҳ��ת������ҳ��
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
	 * ��ҳ��ת�����޸Ĺ���Ա��Ϣ��ҳ��
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
	 * ���������޸Ĺ���Ա�߼�
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
	 * ���ڰ�ҳ��ת�����޸������ҳ��
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
	 * ���ڴ����޸�������߼�
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
				req.setAttribute("succeed", "�����Ѿ����ģ��´���ʹ�����������");
				req.getRequestDispatcher("/admins/admin_update_pwd.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMes", "����������");
				req.getRequestDispatcher("/admins/admin_update_pwd.jsp").forward(req, resp);
			}
		}
		
	}

	/**
	 * �˳�ϵͳ����session�Ƴ���
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
	 * ��ҳ��ת������ӹ���Աҳ��
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
	 * ������ӹ���Ա�߼�
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
				admin.setRole("normal"); // Ĭ���������ͨ����Ա
				admin.setCreated_time(date);
				admin.setLast_login_time(date);
				admin.setLogin_count(0);
				admin.setStatus(status);
				adminService.addAdmin(admin);
				resp.sendRedirect("query_admin_all_page");
			} else {
				req.setAttribute("errorMes", "�Ѿ����ڸ��û�������������");
				req.getRequestDispatcher("/admins/admin_add_admin.jsp").forward(req, resp);
			}
		}
		
	}

	/**
	 * ��ҳ��ת�����鿴���й���Աҳ��,��ҳ��ʾ���й���Ա��Ϣ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryAdminAllPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize"); // ��ȡ��ָ��ÿһҳ��ʾ����������
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCount("t_admin"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
	 * ���úͽ��ù���Ա�Ĺ�������
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void setAdminStatus(HttpServletRequest req, HttpServletResponse resp, String status) throws IOException, ServletException {
		session = req.getSession();
		String email = req.getParameter("email");
		Admin admin_self = (Admin) session.getAttribute("admin");
		if (admin_self.getE_mail().equals("Admin@qq.com")) {
			if (email.equals("Admin@qq.com")) {
				req.setAttribute("errorMes", "���ܽ��ó�������Ա");
				req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
			} else {
				Admin admin = adminService.queryAdmin(email);
				admin.setStatus(status);
				adminService.updateAdmin(admin);
				resp.sendRedirect("query_admin_all_page");
			}
		} else {
			req.setAttribute("errorMes", "��Ǹ����û��Ȩ�޲���");
			req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
		}
		
		
	}
	
	/**
	 * ���ù���Ա�߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void forbidAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		setAdminStatus(req,resp, "N");
	}

	/**
	 * ���ù���Ա�߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void startAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		setAdminStatus(req, resp, "Y");
	}
	
	/**
	 * ��ҳ��ת�����޸Ĺ�����Ϣҳ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdateAdminMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		if (email.equals("Admin@qq.com")) {
			req.setAttribute("errorMes", "�����޸ĳ�������Ա��Ϣ");
			req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
		} else {
			Admin admin = adminService.queryAdmin(email);
			req.setAttribute("admin", admin);
			req.getRequestDispatcher("/admins/admin_update_admin.jsp").forward(req, resp);
		}
	}

	/**
	 * ���ڴ����޸Ĺ���Ա��Ϣ�߼�
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
		req.setAttribute("succeed", "��Ϣ�޸ĳɹ�");
		req.getRequestDispatcher("/admins/admin_update_mes.jsp").forward(req, resp);
	}

	/**
	 * ���ڴ������ù���Ա���룬�Է�����Ա�������룬ֻ�г�������Ա���д�Ȩ��
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
		req.setAttribute("succeed", "�������óɹ���Ĭ��Ϊ��123456��");
		req.getRequestDispatcher("/admins/admin_update_pwd.jsp").forward(req, resp);
	}

	/**
	 * ��ʾ����ͨ����Ա�����ɾ������Ա����������ʾ�û����޷�ɾ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDeleteAdminMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("errorMes", "��û��Ȩ�޴˲���");
		req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
	}

	/**
	 * ��ʾ�ǳ�������Ա��Թ���Ա����ɾ��������ɾ���߼�
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void deleteAdminMes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		if (email.equals("Admin@qq.com")) {
			req.setAttribute("errorMes", "����ɾ����������Ա");
			req.getRequestDispatcher("/admins/admin_query_admin.jsp").forward(req, resp);
		} else {
			commonService.deleteData(email, "t_admin");
			resp.sendRedirect("query_admin_all_page");
		}
	}

	/**
	 * �������������ҹ���Ա
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
	 * ��ҳ��ת�����鿴�����û�,��ҳ��ʾ�����е��û���Ϣ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCustomerAllPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCount("t_customer"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
	 * ���ú������û��˺ŵĹ�������
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
	 * �����û��˺�
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void forbidCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		setCustomerStatus(req, resp, "N");
	}

	/**
	 * �ⶳ�û��˺�
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void startCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		setCustomerStatus(req, resp, "Y");
	}
	
	/**
	 * ���ڰ�ҳ��ת�����޸��û���Ϣ��ҳ��
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
	 * ���ڴ����޸��û���Ϣ���߼�
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
		req.setAttribute("succeed", "��Ϣ�޸ĳɹ�");
		req.getRequestDispatcher("/admins/admin_update_customer.jsp").forward(req, resp);
	}

	/**
	 * �û������û�������
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
		req.setAttribute("succeed", "�������óɹ�,Ĭ������Ϊ��123456��");
		req.getRequestDispatcher("/admins/admin_update_customer.jsp").forward(req, resp);
	}

	/**
	 * ������������ѯ�û�
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
	 * ��˾�����ҳ��ʾ�Ĺ������֣�ͨ����˺�δͨ����ˣ�
	 * @param req
	 * @param resp
	 * @param checked
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	private void companyCommon(HttpServletRequest req, HttpServletResponse resp, String checked, String url) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCountByChecked(checked, "t_company"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
		Pager<Company> pager = companyService.queryByPage(pageNo, pageSize, checked); // ��ҳ��ѯδ��˵�װ�޹�˾
		List<Company> companys = pager.getResult();
		req.setAttribute("companys", companys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher(url).forward(req, resp);
	}

	/**
	 * ��ҳ��ʾδ��˵�װ�޹�˾
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryNotAuditCompanyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		companyCommon(req, resp, "N", "/admins/admin_not_audit_company.jsp");
	}

	/**
	 * ��������ѯ�����Ĳ���
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
	 * ��������ѯδ��˵Ĺ�˾
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void conditionQueryNotAuditCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		conditionQueryCommon(req, resp, "N", "/admins/admin_not_audit_company.jsp", "query_not_audit_company_page");
	}

	/**
	 * ��˾�������ֵĹ�ͬ���֣���������˾ͨ����ˣ����úͽ��ù�˾
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
	 * ��˾ͨ�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void passAuditCompany(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		operationCompanyCommon(req, resp, "Y", "query_not_audit_company_page");
	}

	/**
	 * ɾ����˾ͨ�õķ���
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
	 * ɾ��δ��˹�˾
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteAuditCompany(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		deleteCompanyCommon(req, resp, "query_not_audit_company_page");
	}
	
	/**
	 * ��ҳ��ת������ҳ��ʾ�Ѿ�ͨ����˵Ĺ�˾
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		companyCommon(req, resp, "Y", "/admins/admin_query_company.jsp");
	}

	/**
	 * ��������ѯ����˵Ĺ�˾
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void conditionQueryCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		conditionQueryCommon(req, resp, "Y", "/admins/admin_query_company.jsp", "query_company_page");
	}
	
	/**
	 * ���ù�˾
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void forbidCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationCompanyCommon(req, resp, "N", "query_company_page");
	}

	/**
	 * ���ù�˾
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void startCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationCompanyCommon(req, resp, "Y", "query_company_page");
	}

	/**
	 * ��ҳ��ת�����鿴װ�޹�˾����ҳ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize"); // ��ȡ��ָ��ÿһҳ��ʾ����������
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = companyCaseService.queryCount("t_company_case"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
	 * ����������װ�޹�˾����
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
	 * ��ҳ��ʾ����װ�޹�˾�Ļ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize"); // ��ȡ��ָ��ÿһҳ��ʾ����������
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = companyActivityService.queryCount("t_company_activity"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
	 * ����������װ�޹�˾�
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
	 * ɾ��װ�޹�˾�
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
	 * �����̷�ҳ��ʾ�Ĺ������֣�ͨ����˺�δͨ����ˣ�
	 * @param req
	 * @param resp
	 * @param checked
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supplyCommon(HttpServletRequest req, HttpServletResponse resp, String checked, String url) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCountByChecked(checked, "t_supply"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
		Pager<Supply> pager = supplyService.queryByPage(pageNo, pageSize, checked); // ��ҳ��ѯָ���Ƿ���˵Ľ�����
		List<Supply> supplys = pager.getResult();
		req.setAttribute("supplys", supplys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher(url).forward(req, resp);
	}
	
	/**
	 * ��ҳ��ʾδ��˵Ľ�������Ϣ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryNotAuditSupplyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		supplyCommon(req, resp, "N", "/admins/admin_not_audit_supply.jsp");
	}

	/**
	 * ��������ѯδ��˵Ľ�������Ϣ
	 * @param req
	 * @param resp
	 */
	private void conditionQueryNotAuditSupply(HttpServletRequest req, HttpServletResponse resp) {
		// TODO �Ժ���ʵ��
		
	}

	/**
	 * �����̲������ֵĹ�ͬ���֣�������������ͨ����ˣ����úͽ��ý�����
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
	 * ������ͨ������߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void passAuditSupply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationSupplyCommon(req, resp, "Y", "query_not_audit_supply_page");
	}

	/**
	 * ��ҳ��ѯͨ����˵Ľ�������Ϣ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		supplyCommon(req, resp, "Y", "/admins/admin_query_supply.jsp");
	}

	/**
	 * ��������ѯͨ����˵Ľ�������Ϣ
	 * @param req
	 * @param resp
	 */
	private void conditionQuerySupply(HttpServletRequest req, HttpServletResponse resp) {
		// TODO �Ժ���ʵ��
		
	}

	/**
	 * ���ý�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void forbidSupply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationSupplyCommon(req, resp, "N", "query_supply_page");
	}

	/**
	 * ���ý�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void startSupply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationSupplyCommon(req, resp, "Y", "query_supply_page");
	}

	/**
	 * ��ҳ��ʾ���н����̵Ļ��Ϣ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize"); // ��ȡ��ָ��ÿһҳ��ʾ����������
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCount("t_supply_activity"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
	 * ɾ�������̻
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
	 * ��ҳ�鿴������Ʒ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize"); // ��ȡ��ָ��ÿһҳ��ʾ����������
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = productService.queryCount("t_product"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
	 * ��������ѯ������Ʒ
	 * @param req
	 * @param resp
	 */
	private void conditionQuerySupplyProduct(HttpServletRequest req, HttpServletResponse resp) {
		// TODO �Ժ���ʵ��
		
	}

	/**
	 * ��˾�����ҳ��ʾ�Ĺ������֣�ͨ����˺�δͨ����ˣ�
	 * @param req
	 * @param resp
	 * @param checked
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	private void designerDesigner(HttpServletRequest req, HttpServletResponse resp, String checked, String url) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCountByChecked(checked, "t_designer"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
		Pager<Designer> pager = designerService.queryByPage(pageNo, pageSize, checked); // ��ҳ��ѯδ��˵�װ�޹�˾
		List<Designer> designers = pager.getResult();
		req.setAttribute("designers", designers);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher(url).forward(req, resp);
	}

	/**
	 * ��ҳ��ѯδ��˵����ʦ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryNotAuditDesignerPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		designerDesigner(req, resp, "N", "/admins/admin_not_audit_designer.jsp");
	}

	/**
	 * ��������ѯδ��˵����ʦ
	 * @param req
	 * @param resp
	 */
	private void conditionQueryNotAuditDesigner(HttpServletRequest req, HttpServletResponse resp) {
		// TODO �Ժ���ʵ��
		
	}

	/**
	 * ���ʦ�������ֵĹ�ͬ���֣���������˾ͨ����ˣ����úͽ��ù�˾
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
	 * δ��˵����ʦͨ�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void passAuditDesigner(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationDesignerCommon(req, resp, "Y", "query_not_audit_designer_page");
	}

	/**
	 * ɾ�����ʦͨ�õķ���
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
	 * ɾ��δ��˵����ʦ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteNotAuditDesigner(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		deleteDesignerCommon(req, resp, "query_not_audit_designer_page");
	}

	/**
	 * ��ѯ����˵����ʦ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		designerDesigner(req, resp, "Y", "/admins/admin_query_designer.jsp");
	}

	/**
	 * ��������ѯ����˵����ʦ
	 * @param req
	 * @param resp
	 */
	private void conditionQueryDesigner(HttpServletRequest req, HttpServletResponse resp) {
		// TODO �Ժ���ʵ��
		
	}

	/**
	 * �������ʦ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void forbidDesigner(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationDesignerCommon(req, resp, "N", "query_designer_page");
	}

	/**
	 * ȡ���������ʦ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void startDesigner(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		operationDesignerCommon(req, resp, "Y", "query_designer_page");
	}

	/**
	 * ��ҳ��ʾ���ʦ����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize"); // ��ȡ��ָ��ÿһҳ��ʾ����������
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = designerCaseService.queryCount("t_designer_case"); // ��ȡ�������ܸ���
		int totalPage = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1); // �ܹ���Ҫ����Ҳ����ʾ
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
	 * ��������ѯ���ʦ����
	 * @param req
	 * @param resp
	 */
	private void conditionQueryDesignerCase(HttpServletRequest req, HttpServletResponse resp) {
		// TODO �Ժ���ʵ��
		
	}

	
}