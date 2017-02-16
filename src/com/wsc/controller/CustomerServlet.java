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
			// �����û�ע���߼�
			register(req, resp);
		} else if (method.equals("login")) {
			// �����¼�߼�
			login(req, resp);
		} else if (method.equals("customer_index_page")) {
			// ��ʾ�û��ĺ�̨����ҳ��
			showCustomerIndexPage(req, resp);
		} else if (method.equals("exit")) {
			// �˳�����
			exit(req, resp);
		} else if (method.equals("return_index_page")) {
			// ������ҳ��
			showReturnIndexPage(req, resp);
		} else if (method.equals("query_personal_mes_page")) {
			// ��ʾ������Ϣ����
			showQueryPersonalMesPage(req, resp);
		} else if (method.equals("update_personal_mes_page")) {
			// ��ʾ�޸ĸ�����Ϣҳ��
			showUpdatePersonalMesPage(req, resp);
		} else if (method.equals("update_personal_mes")) {
			// �����޸��Լ���Ϣ���߼�
			updatePersonalMes(req, resp);
		} else if (method.equals("update_personal_pwd_page")) {
			// �޸��Լ�������
			showUpdatePersonalPwdPage(req, resp);
		} else if (method.equals("update_personal_pwd")) {
			// �����޸��Լ�������߼�
			updatePersonalPwd(req, resp);
		} else if (method.equals("query_company_col_page")) {
			// �鿴�ղص�װ�޹�˾
			showQueryCompanyColPage(req, resp);
		} else if (method.equals("delete_company_col")) {
			// ����ɾ���ղص�װ�޹�˾
			deleteCompanyCol(req, resp);
		} else if (method.equals("query_company_col_case_page")) {
			// �鿴�ղص�װ�޹�˾����
			showQueryCompanyColCasePage(req, resp);
		} else if (method.equals("delete_company_col_case")) {
			// ɾ���ղص�װ�޹�˾����
			deleteCompanyColCase(req, resp);
		} else if (method.equals("query_designer_col_page")) {
			// �鿴�ղص����ʦ
			showQueryDesignerColPage(req, resp);
		} else if (method.equals("delete_designer_col")) {
			// ɾ���ղص����ʦ
			deleteDesignerCol(req, resp);
		} else if (method.equals("query_designer_col_case_page")) {
			// �鿴�ղص����ʦ����
			showQueryDesignerColCasePage(req, resp);
		} else if (method.equals("delete_designer_col_case")) {
			// ɾ���ղ����ʦ����
			deleteDesignerColCase(req, resp);
		} else if (method.equals("query_supply_col_page")) {
			// �鿴�ղصĽ�����
			showQuerySupplyColPage(req, resp);
		} else if (method.equals("delete_supply_col")) {
			// ɾ���ղصĽ�����
			deleteSupplyCol(req, resp);
		} else if (method.equals("query_product_col_page")) {
			// �鿴�ղصĽ�����Ʒ
			showQuerySupplyProductPage(req, resp);
		} else if (method.equals("delete_product_col")) {
			// ɾ���ղصĽ�����Ʒ
			deleteSupplyProductCol(req, resp);
		} else if (method.equals("index_page")) {
			// ��ҳ��ת������ҳ
			showIndexPage(req, resp);
		} else if (method.equals("login_page")) {
			// ������ת������ҳ��ķ���
			showLoginPage(req, resp);
		} else if (method.equals("comment")) {
			// �ύ����
			comment(req, resp);
		} else if (method.equals("company_particular")) {
			// ��ת��װ�޹�˾����
			showCompanyParticular(req, resp);
		} else if (method.equals("company_col_particular")) {
			// װ�޹�˾����ҳ���ղ�װ�޹�˾
			companyColParticular(req, resp);
		} 
	}

	/**
	 * ���ڴ����û�ע����߼�
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("pwd"); // �Ȱ��������
		String phone = req.getParameter("phone");
		String name = req.getParameter("name");
		Customer customer = new Customer();
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (WebUtil.checkEmail(commonService, email, req, resp)) {
			if (phone.length() != 11) {
				req.setAttribute("errorMes", "�ֻ��������Ϊ11λ����");
				req.getRequestDispatcher("/register.jsp").forward(req, resp);
			} else {
				customer.setE_mail(email);
				customer.setPassword(EncryptUtil.encrypt(password));
				customer.setName(name);
				customer.setPhone(phone);
				customer.setPlot_name("Ĭ��С��");
				customer.setAddress("����");
				customer.setCreated_time(DateUtil.getDate());
				customer.setLast_login_time(DateUtil.getDate());
				customer.setStatus("Y"); // Ĭ���ǿ��õ�
				customerService.addCustomer(customer);
				req.setAttribute("succeed", "ע��ɹ������Խ��е�¼");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("errorMes", "�Ѵ��ڸ��û�");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * �����û���¼�߼�
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
			req.setAttribute("errorMes", "�˺Ż���������");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			if (customer.getStatus().equals("Y")) {
				customer.setLast_login_time(DateUtil.getDate());
				customer.setLogin_count(customer.getLogin_count() + 1);
				customerService.updateCustomer(customer); // �������ݿ�
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
				req.setAttribute("errorMes", "���˺Ų�����");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMes", "�����ڸ��˺�");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		}
		
	}
	
	/**
	 * ��ҳ��ת�����û��ĺ�̨����ҳ��
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
	 * �û��˳���¼
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
	 * ��ҳ��ת������ҳ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showReturnIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		if (session.getAttribute("customer") == null) {
			req.setAttribute("errorMes", "�˺���ʧЧ�������µ�¼");
			WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/customers/customer_index.jsp").forward(req, resp);
		}
		
	}

	/**
	 * ��ҳ��ת����������Ϣҳ��
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
			req.setAttribute("errorMes", "�˺���ʧЧ�������µ�¼");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("lastLoginTime", DateUtil.getDateStr(customer.getLast_login_time()));
			req.getRequestDispatcher("/customers/customer_personal_mes.jsp").forward(req, resp);
		}
	}

	/**
	 * ��ҳ��ת�����޸ĸ�����Ϣҳ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/customers/customer_update_mes.jsp").forward(req, resp);
	}
	
	/**
	 * ���������Ϣ�޸ĵ��߼�
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
	 * ��ҳ��ת�����޸ĸ�������ҳ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalPwdPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/customers/customer_update_pwd.jsp").forward(req, resp);
	}

	/**
	 * ������������޸ĵ��߼�
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
			req.setAttribute("succeed", "�����Ѿ����ģ��´ε�¼��ʹ��������");
			req.getRequestDispatcher("/customers/customer_update_pwd.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMes", "����������");
			req.getRequestDispatcher("/customers/customer_update_pwd.jsp").forward(req, resp);
		}
	}

	/**
	 * ��ҳ��ת�����ղصĹ�˾ҳ�棬��ҳ�鿴
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyColPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
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
		int total = companyColService.queryCount("t_company_col", customer.getId()); // ��ȡ�������ܸ���
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
		Pager<CompanyCol> pager = companyColService.queryByPage(pageNo, pageSize, customer.getId());
		List<CompanyCol> companyCols = pager.getResult();
		req.setAttribute("companyCols", companyCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_company_col.jsp").forward(req, resp);
	}

	/**
	 * ɾ���ղصĹ�˾�߼�����
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
	 * ��ҳ��ת�����ղع�˾������ҳ�档��ҳ�鿴
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCompanyColCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
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
		int total = companyCaseColService.queryCount("t_company_case_col", customer.getId()); // ��ȡ�������ܸ���
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
		Pager<CompanyCaseCol> pager = companyCaseColService.queryByPage(pageNo, pageSize, customer.getId());
		List<CompanyCaseCol> companyCaseCols = pager.getResult();
		req.setAttribute("companyCaseCols", companyCaseCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_company_case_col.jsp").forward(req, resp);
	}

	/**
	 * ɾ���ղصİ���
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
	 * ��ҳ�鿴���ղص����ʦ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerColPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
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
		int total = designerColService.queryCount("t_designer_col", customer.getId()); // ��ȡ�������ܸ���
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
		Pager<DesignerCol> pager = designerColService.queryByPage(pageNo, pageSize, customer.getId());
		List<DesignerCol> designerCols = pager.getResult();
		req.setAttribute("designerCols", designerCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_designer_col.jsp").forward(req, resp);
	}

	/**
	 * ɾ�����ղص����ʦ
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
	 * ��ҳ�鿴���ղص����ʦ����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerColCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
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
		int total = designerCaseColService.queryCount("t_designer_case_col", customer.getId()); // ��ȡ�������ܸ���
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
		Pager<DesignerCaseCol> pager = designerCaseColService.queryByPage(pageNo, pageSize, customer.getId());
		List<DesignerCaseCol> designerCaseCols = pager.getResult();
		req.setAttribute("designerCaseCols", designerCaseCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_designer_case_col.jsp").forward(req, resp);
	}

	/**
	 * ɾ�����ղص����ʦ����
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
	 * ��ҳ�鿴���ղصĽ�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyColPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
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
		int total = supplyColService.queryCount("t_supply_col", customer.getId()); // ��ȡ�������ܸ���
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
		Pager<SupplyCol> pager = supplyColService.queryByPage(pageNo, pageSize, customer.getId());
		List<SupplyCol> supplyCols = pager.getResult();
		req.setAttribute("supplyCols", supplyCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_supply_col.jsp").forward(req, resp);
	}

	/**
	 * ɾ�����ղصĽ�����
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
	 * ��ҳ�鿴���ղصĽ�����Ʒ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQuerySupplyProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
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
		int total = productColService.queryCount("t_product_col", customer.getId()); // ��ȡ�������ܸ���
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
		Pager<ProductCol> pager = productColService.queryByPage(pageNo, pageSize, customer.getId());
		List<ProductCol> productCols = pager.getResult();
		req.setAttribute("productCols", productCols);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/customers/customer_query_product_col.jsp").forward(req, resp);
	}

	/**
	 * ɾ�����ղصĽ�����Ʒ
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
	 * ��ҳ��ת������ҳ
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	/**
	 * ��ת���������
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	/**
	 * �����û��ύ����
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
			req.setAttribute("errorMes", "���ȵ�¼��ͨ�û��˺�");
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
	 * �鿴װ�޹�˾����Ĺ�������
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
	
	/**��ת��װ�޹�˾����
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
	 * ��װ�޹�˾����ҳ����ղ�װ�޹�˾
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
			req.setAttribute("errorMes", "���ȵ���ҵ���˺�");
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
