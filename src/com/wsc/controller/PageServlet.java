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
			// 调用跳转到注册页面的方法
			showRegisterPage(req, resp);
		} else if (method.equals("index")) {
			// 调用跳转到首页页面的方法
			showIndexPage(req, resp);
		} else if (method.equals("company_case")) {
			// 调用跳转到装修案例页面的方法
			showCompanyCase(req, resp);
		} else if (method.equals("designer_case")) {
			// 调用跳转到设计案例页面的方法
			showDesignerCase(req, resp);
		} else if (method.equals("apply_appointment")) {
			// 处理用户预约逻辑
			applyAppointment(req, resp);
		} else if (method.equals("company")) {
			// 调用跳转到装修公司页面的方法
			showCompany(req, resp);
		} else if (method.equals("designer")) {
			// 调用跳转到分页显示设计师页面的方法
			showDesigner(req, resp);
		} else if (method.equals("product")) {
			// 调用跳转到建材商城页面的方法
			showProduct(req, resp);
		} else if (method.equals("designer_particular")) {
			// 调用跳转到设计师详情页面的方法
			showDesignerParticular(req, resp);
		} else if (method.equals("company_case_particular")) {
			// 跳转到装修公司装修案例详情
			showCompanyCaseParticular(req, resp);
		} else if (method.equals("designer_case_particular")) {
			// 跳转到设计师设计案例详情
			showDesignerCaseParticular(req, resp);
		} else if (method.equals("exit")) {
			// 退出后台管理
			showExitPage(req, resp);
		} else if (method.equals("query_desgienr_case_page")) {
			// 设计师详情页面查看指定设计师的所有案例
			showQueryDesignerCasePage(req, resp);
		} else if (method.equals("company_particular_case")) {
			// 查看该公司的所有案例
			showCompanyParticularCasePage(req, resp);
		} else if (method.equals("product_col_index")) {
			// 首页收藏商品
			productCol(req, resp, "index");
		} else if (method.equals("product_col")) {
			// 商品页面收藏商品
			productCol(req, resp, "product");
		} else if (method.equals("designer_col_index")) {
			// 首页收藏设计师
			designerCol(req, resp, "index");
		} else if (method.equals("designer_col")) {
			// 设计师个人信息页面收藏设计师
			designerColParticular(req, resp);
		} else if (method.equals("company_col_index")) {
			// 首页的装修公司收藏
			companyCol(req, resp, "index");
		} else if (method.equals("company_col")) {
			// 装修公司列表收藏装修公司
			companyCol(req, resp, "company");
		} else if (method.equals("company_case_col")) {
			// 装修公司案例收藏
			companyCaseCol(req, resp);
		} else if (method.equals("designer_case_col")) {
			// 设计师案例收藏
			designerCaseCol(req, resp);
		}
	}

	/**
	 * 跳转到注册界面
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showRegisterPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.setDivDisplay(req, "block", "none", "none", "checked='checked'", "");
		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
	
	/**
	 * 跳转到首页界面
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
	 * 跳转到装修案例界面
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showCompanyCase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 6; // 每页数据的大小
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
	 * 跳转到设计案例界面
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showDesignerCase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 6; // 每页数据的大小
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
	 * 跳转到装修公司界面
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 4; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = commonService.queryCountByChecked("Y", "t_company"); // 获取到数据总个数
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
		Pager<Company> pager = companyService.queryByPage(pageNo, pageSize, "Y"); // 分页查询已审核的装修公司
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
	 * 跳转到设计师界面
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showDesigner(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 3; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = designerService.queryCountByDesignerChecked("Y"); // 获取到数据总个数
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
		Pager<Designer> pager = designerService.queryByPage(pageNo, pageSize, "Y"); // 分页查询已审核的设计师
		List<Designer> designers = pager.getResult();
		req.setAttribute("designers", designers);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/designer.jsp").forward(req, resp);
	}
	
	/**
	 * 跳转到建材商城界面
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize"); // 获取到指定每一页显示多少条数据
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 12; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = productService.queryCountByStatus("Y"); // 获取到数据总个数
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
	 * 跳转到设计师详情界面
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
	 * 处理客户的预约请求逻辑
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
					req.setAttribute("errorMes", "您已经提交过预约了，请不要重复提交");
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
					req.setAttribute("errorMes", "您的预约已受理，请耐心等通知");
					req.getRequestDispatcher("/company_particular.jsp").forward(req, resp);
				}
			} catch(Exception e) {
				req.setAttribute("errorMes", "您输入的手机或面积格式有误，预约失败");
				req.getRequestDispatcher("/company_particular.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("errorMes", "您输入的数据有误，预约失败");
			req.getRequestDispatcher("/company_particular.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 跳转到装修公司案例详情
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
	 * 跳转到设计师设计案例详情
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
	 * 用于把页面转发到首页，退出后台管理
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
	 * 分页显示指定设计师的所有案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryDesignerCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String designer_id = req.getParameter("id");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		Designer designer = designerService.queryDesignerById(designer_id);
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 6; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = designerCaseService.queryCountByDesignerId(designer_id); // 获取到数据总个数
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
	 * 查看指定装修公司的所有案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCompanyParticularCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String company_id = req.getParameter("id");
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize");
		Company company = companyService.queryCompanyById(company_id);
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 6; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
				
			} catch (NumberFormatException e) {
				
			}
		}
		int total = companyCaseService.queryCountByCompanyId(company_id); // 获取到数据总个数
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
	 * 收藏商品的逻辑处理
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
			req.setAttribute("errorMes", "请先登入业主账号");
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
	 * 设计师收藏逻辑处理
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
			req.setAttribute("errorMes", "请先登入业主账号");
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
	 * 装修公司收藏逻辑处理
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
			req.setAttribute("errorMes", "请先登入业主账号");
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
	 * 装修公司案例收藏逻辑处理
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
			req.setAttribute("errorMes", "请先登入业主账号");
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
	 * 设计师案例收藏逻辑处理
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
			req.setAttribute("errorMes", "请先登入业主账号");
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
	 * 在设计师详情页面收藏设计师
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
			req.setAttribute("errorMes", "请先登入业主账号");
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
