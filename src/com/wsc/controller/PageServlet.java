package com.wsc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsc.bean.Appointment;
import com.wsc.bean.Company;
import com.wsc.bean.CompanyCase;
import com.wsc.bean.CompanyCaseCol;
import com.wsc.bean.CompanyCol;
import com.wsc.bean.Customer;
import com.wsc.bean.Designer;
import com.wsc.bean.DesignerCase;
import com.wsc.bean.DesignerCaseCol;
import com.wsc.bean.DesignerCol;
import com.wsc.bean.Product;
import com.wsc.bean.ProductCol;
import com.wsc.common.DateUtil;
import com.wsc.common.WebUtil;
import com.wsc.parentbean.Pager;
import com.wsc.service.AppointmentService;
import com.wsc.service.AppointmentServiceImpl;
import com.wsc.service.CommonService;
import com.wsc.service.CommonServiceImpl;
import com.wsc.service.CompanyCaseColService;
import com.wsc.service.CompanyCaseColServiceImpl;
import com.wsc.service.CompanyCaseService;
import com.wsc.service.CompanyCaseServiceImpl;
import com.wsc.service.CompanyColService;
import com.wsc.service.CompanyColServiceImpl;
import com.wsc.service.CompanyService;
import com.wsc.service.CompanyServiceImpl;
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

public class PageServlet extends HttpServlet {

	private static final long serialVersionUID = -262660434278836584L;
	
	private ProductService productService;
	private CompanyService companyService;
	private DesignerService designerService;
	private CompanyCaseService companyCaseService;
	private DesignerCaseService designerCaseService;
	private AppointmentService appointmentService;
	private CommonService commonService;
	private ProductColService productColService;
	private DesignerColService designerColService;
	private CompanyColService companyColService;
	private CompanyCaseColService companyCaseColService;
	private DesignerCaseColService designerCaseColService;
	private HttpSession session;
	
	public PageServlet() {
		productService = new ProductServiceImpl();
		companyService = new CompanyServiceImpl();
		companyCaseService = new CompanyCaseServiceImpl();
		designerCaseService = new DesignerCaseServiceImpl();
		designerService = new DesignerServiceImpl();
		appointmentService = new AppointmentServiceImpl();
		commonService = new CommonServiceImpl();
		productColService = new ProductColServiceImpl();
		designerColService = new DesignerColServiceImpl();
		companyColService = new CompanyColServiceImpl();
		companyCaseColService = new CompanyCaseColServiceImpl();
		designerCaseColService = new DesignerCaseColServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getURIMethod(req);
		if (method.equals("register_page")) {
			// ������ת��ע��ҳ��ķ���
			showRegisterPage(req, resp);
		} else if (method.equals("index")) {
			// ������ת����ҳҳ��ķ���
			showIndexPage(req, resp);
		} else if (method.equals("company_case")) {
			// ������ת��װ�ް���ҳ��ķ���
			showCompanyCase(req, resp);
		} else if (method.equals("designer_case")) {
			// ������ת����ư���ҳ��ķ���
			showDesignerCase(req, resp);
		} else if (method.equals("apply_appointment")) {
			// �����û�ԤԼ�߼�
			applyAppointment(req, resp);
		} else if (method.equals("company")) {
			// ������ת��װ�޹�˾ҳ��ķ���
			showCompany(req, resp);
		} else if (method.equals("designer")) {
			// ������ת����ҳ��ʾ���ʦҳ��ķ���
			showDesigner(req, resp);
		} else if (method.equals("product")) {
			// ������ת�������̳�ҳ��ķ���
			showProduct(req, resp);
		} else if (method.equals("designer_particular")) {
			// ������ת�����ʦ����ҳ��ķ���
			showDesignerParticular(req, resp);
		} else if (method.equals("company_case_particular")) {
			// ��ת��װ�޹�˾װ�ް�������
			showCompanyCaseParticular(req, resp);
		} else if (method.equals("designer_case_particular")) {
			// ��ת�����ʦ��ư�������
			showDesignerCaseParticular(req, resp);
		} else if (method.equals("exit")) {
			// �˳���̨����
			showExitPage(req, resp);
		} else if (method.equals("query_desgienr_case_page")) {
			// ���ʦ����ҳ��鿴ָ�����ʦ�����а���
			showQueryDesignerCasePage(req, resp);
		} else if (method.equals("company_particular_case")) {
			// �鿴�ù�˾�����а���
			showCompanyParticularCasePage(req, resp);
		} else if (method.equals("product_col_index")) {
			// ��ҳ�ղ���Ʒ
			productCol(req, resp, "index");
		} else if (method.equals("product_col")) {
			// ��Ʒҳ���ղ���Ʒ
			productCol(req, resp, "product");
		} else if (method.equals("designer_col_index")) {
			// ��ҳ�ղ����ʦ
			designerCol(req, resp, "index");
		} else if (method.equals("designer_col")) {
			// ���ʦ������Ϣҳ���ղ����ʦ
			designerColParticular(req, resp);
		} else if (method.equals("company_col_index")) {
			// ��ҳ��װ�޹�˾�ղ�
			companyCol(req, resp, "index");
		} else if (method.equals("company_col")) {
			// װ�޹�˾�б��ղ�װ�޹�˾
			companyCol(req, resp, "company");
		} else if (method.equals("company_case_col")) {
			// װ�޹�˾�����ղ�
			companyCaseCol(req, resp);
		} else if (method.equals("designer_case_col")) {
			// ���ʦ�����ղ�
			designerCaseCol(req, resp);
		}
	}

	/**
	 * ��ת��ע�����
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showRegisterPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
	
	/**
	 * ��ת����ҳ����
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, "");
		} else {
			WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, customer.getId());
		}
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
	
	/**
	 * ��ת��װ�ް�������
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showCompanyCase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 6; // ÿҳ���ݵĴ�С
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
		if (customer != null) {
			WebUtil.checkCompanyCaseCaseCol(customer.getId(), companyCaseService, companyCases);
		}
		req.setAttribute("companyCases",companyCases);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/company_case.jsp").forward(req, resp);
		
	}
	
	/**
	 * ��ת����ư�������
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showDesignerCase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 6; // ÿҳ���ݵĴ�С
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
		if (customer != null) {
			WebUtil.checkDesignerCaseCol(customer.getId(), designerCaseService, designerCases);
		}
		req.setAttribute("designerCases",designerCases);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/designer_case.jsp").forward(req, resp);
	}
	
	/**
	 * ��ת��װ�޹�˾����
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 4; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCountByChecked("Y", "t_company"); // ��ȡ�������ܸ���
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
		Pager<Company> pager = companyService.queryByPage(pageNo, pageSize, "Y"); // ��ҳ��ѯ����˵�װ�޹�˾
		List<Company> companys = pager.getResult();
		if (customer != null) {
			WebUtil.checkCompanyCol(customer.getId(), companyService, companys);
		}
		req.setAttribute("companys", companys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/company.jsp").forward(req, resp);
	}
	
	/**
	 * ��ת�����ʦ����
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showDesigner(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 3; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = designerService.queryCountByDesignerChecked("Y"); // ��ȡ�������ܸ���
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
		Pager<Designer> pager = designerService.queryByPage(pageNo, pageSize, "Y"); // ��ҳ��ѯ����˵����ʦ
		List<Designer> designers = pager.getResult();
		req.setAttribute("designers", designers);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/designer.jsp").forward(req, resp);
	}
	
	/**
	 * ��ת�������̳ǽ���
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize"); // ��ȡ��ָ��ÿһҳ��ʾ����������
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 12; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = productService.queryCountByStatus("Y"); // ��ȡ�������ܸ���
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
		Pager<Product> pager = productService.queryByStatus(pageNo, pageSize, "Y");
		List<Product> products = pager.getResult();
		if (customer != null) {
			WebUtil.checkProductCol(customer.getId(), productService, products);
		}
		req.setAttribute("products", products);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/product.jsp").forward(req, resp);
	}
	
	/**
	 * ��ת�����ʦ�������
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showDesignerParticular(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		Designer designer = designerService.queryDesignerById(id);
		List<DesignerCase> designerCases = designerCaseService.queryDesignerCaseByTop4(id);
		if (customer != null) {
			List<Designer> designers = new ArrayList<Designer>();
			designers.add(designer);
			WebUtil.checkDesignerCol(customer.getId(), designerService, designers);
		}
		req.setAttribute("designerCases", designerCases);
		req.setAttribute("designer", designer);
		req.getRequestDispatcher("/designer_particular.jsp").forward(req, resp);
	}
	
	/**
	 * ����ͻ���ԤԼ�����߼�
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void applyAppointment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String plot_name = req.getParameter("plot_name");
		String phone = req.getParameter("phone");
		String areaStr = req.getParameter("area");
		String budget = req.getParameter("budget");
		String id = req.getParameter("id");
		String way = req.getParameter("way");
		Company company = companyService.queryCompanyById(id);
		List<CompanyCase> companyCases = companyCaseService.queryCompanyCaseByTop4(id);
		req.setAttribute("companyCases", companyCases);
		req.setAttribute("company", company);
		float area = 0f;
		if (name != null && !name.equals("") && plot_name != null && !plot_name.equals("") && phone != null && !phone.equals("") && phone.length() == 11) {
			try {
				area = Float.valueOf(areaStr);
				if (appointmentService.queryAppointmentByNameAndPhone(name, phone) != null) {
					req.setAttribute("errorMes", "���Ѿ��ύ��ԤԼ�ˣ��벻Ҫ�ظ��ύ");
					req.getRequestDispatcher("/company_particular.jsp").forward(req, resp);
				} else {
					Appointment appointment = new Appointment();
					appointment.setCompany_id(id);
					appointment.setName(name);
					appointment.setPlot_name(plot_name);
					appointment.setPhone(phone);
					appointment.setArea(area);
					appointment.setBudget(budget);
					appointment.setWay(way);
					appointment.setCreated_time(DateUtil.getDate());
					appointmentService.addAppointment(appointment);
					req.setAttribute("errorMes", "����ԤԼ�����������ĵ�֪ͨ");
					req.getRequestDispatcher("/company_particular.jsp").forward(req, resp);
				}
			} catch(Exception e) {
				req.setAttribute("errorMes", "��������ֻ��������ʽ����ԤԼʧ��");
				req.getRequestDispatcher("/company_particular.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("errorMes", "���������������ԤԼʧ��");
			req.getRequestDispatcher("/company_particular.jsp").forward(req, resp);
		}
	}
	
	/**
	 * ��ת��װ�޹�˾��������
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCompanyCaseParticular(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		CompanyCase companyCase = companyCaseService.queryCompanyCaseById(id);
		req.setAttribute("companyCase", companyCase);
		req.getRequestDispatcher("/company_case_particular.jsp").forward(req, resp);
	}

	/**
	 * ��ת�����ʦ��ư�������
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDesignerCaseParticular(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		DesignerCase designerCase = designerCaseService.queryDesignerCaseById(id);
		req.setAttribute("designerCase", designerCase);
		req.getRequestDispatcher("/designer_case_particular.jsp").forward(req, resp);
	}

	/**
	 * ���ڰ�ҳ��ת������ҳ���˳���̨����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showExitPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, "");
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}

	/**
	 * ��ҳ��ʾָ�����ʦ�����а���
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String designer_id = req.getParameter("id");
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		Designer designer = designerService.queryDesignerById(designer_id);
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 6; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = designerCaseService.queryCountByDesignerId(designer_id); // ��ȡ�������ܸ���
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
		
		Pager<DesignerCase> pager = designerCaseService.queryDesignerCaseByDesignerId(pageNo, pageSize, designer_id);
		List<DesignerCase> designerCases = pager.getResult();
		req.setAttribute("designerCases",designerCases);
		req.setAttribute("designer", designer);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/designer_particular_case.jsp").forward(req, resp);
	}
	
	/**
	 * �鿴ָ��װ�޹�˾�����а���
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCompanyParticularCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String company_id = req.getParameter("id");
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize");
		Company company = companyService.queryCompanyById(company_id);
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 6; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = companyCaseService.queryCountByCompanyId(company_id); // ��ȡ�������ܸ���
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
		
		Pager<CompanyCase> pager = companyCaseService.queryByCompanyId(pageNo, pageSize, company_id);
		List<CompanyCase> companyCases = pager.getResult();
		req.setAttribute("companyCases",companyCases);
		req.setAttribute("company", company);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/company_particular_case.jsp").forward(req, resp);
	}
	
	/**
	 * �ղ���Ʒ���߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void productCol(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
		String product_id = req.getParameter("id");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (customer == null) {
			session.setMaxInactiveInterval(-1);
			req.setAttribute("errorMes", "���ȵ���ҵ���˺�");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			ProductCol productCol = new ProductCol();
			productCol.setCustomer_id(customer.getId());
			productCol.setProduct_id(product_id);
			productCol.setCreated_time(DateUtil.getDate());
			productColService.addProductCol(productCol);
			WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, customer.getId());
			resp.sendRedirect(url);
		}
	}

	/**
	 * ���ʦ�ղ��߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void designerCol(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
		String designer_id = req.getParameter("id");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (customer == null) {
			session.setMaxInactiveInterval(-1);
			req.setAttribute("errorMes", "���ȵ���ҵ���˺�");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			DesignerCol designerCol = new DesignerCol();
			designerCol.setCustomer_id(customer.getId());
			designerCol.setDesigner_id(designer_id);
			designerCol.setCreated_time(DateUtil.getDate());
			designerColService.addDesignerCol(designerCol);
			WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, customer.getId());
			resp.sendRedirect(url);
		}
	}

	/**
	 * װ�޹�˾�ղ��߼�����
	 * @param req
	 * @param resp
	 * @param string
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void companyCol(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
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
			resp.sendRedirect(url);
		}
	}
	
	/**
	 * װ�޹�˾�����ղ��߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void companyCaseCol(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String company_case_id = req.getParameter("id");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (customer == null) {
			session.setMaxInactiveInterval(-1);
			req.setAttribute("errorMes", "���ȵ���ҵ���˺�");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			CompanyCaseCol companyCaseCol = new CompanyCaseCol();
			companyCaseCol.setCustomer_id(customer.getId());
			companyCaseCol.setCase_id(company_case_id);
			companyCaseCol.setCreated_time(DateUtil.getDate());
			companyCaseColService.addCompanyCaseCol(companyCaseCol);
			resp.sendRedirect("company_case");
		}
	}

	/**
	 * ���ʦ�����ղ��߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void designerCaseCol(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String designer_case_id = req.getParameter("id");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (customer == null) {
			session.setMaxInactiveInterval(-1);
			req.setAttribute("errorMes", "���ȵ���ҵ���˺�");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			DesignerCaseCol designerCaseCol = new DesignerCaseCol();
			designerCaseCol.setCustomer_id(customer.getId());
			designerCaseCol.setCase_id(designer_case_id);
			designerCaseCol.setCreated_time(DateUtil.getDate());
			designerCaseColService.addDesignerCaseCol(designerCaseCol);
			resp.sendRedirect("designer_case");
		}
	}
	
	/**
	 * �����ʦ����ҳ���ղ����ʦ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void designerColParticular(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String designer_id = req.getParameter("id");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		if (customer == null) {
			session.setMaxInactiveInterval(-1);
			req.setAttribute("errorMes", "���ȵ���ҵ���˺�");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			DesignerCol designerCol = new DesignerCol();
			designerCol.setCustomer_id(customer.getId());
			designerCol.setDesigner_id(designer_id);
			designerCol.setCreated_time(DateUtil.getDate());
			designerColService.addDesignerCol(designerCol);
			Designer designer = designerService.queryDesignerById(designer_id);
			List<DesignerCase> designerCases = designerCaseService.queryDesignerCaseByTop4(designer_id);
			if (customer != null) {
				List<Designer> designers = new ArrayList<Designer>();
				designers.add(designer);
				WebUtil.checkDesignerCol(customer.getId(), designerService, designers);
			}
			req.setAttribute("designerCases", designerCases);
			req.setAttribute("designer", designer);
			req.getRequestDispatcher("/designer_particular.jsp").forward(req, resp);
		}
	}
	
}
