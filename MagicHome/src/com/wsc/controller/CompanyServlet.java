package com.wsc.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.wsc.bean.Appointment;
import com.wsc.bean.AppointmentView;
import com.wsc.bean.Company;
import com.wsc.bean.CompanyActivity;
import com.wsc.bean.CompanyCase;
import com.wsc.common.Constants;
import com.wsc.common.DateUtil;
import com.wsc.common.EncryptUtil;
import com.wsc.common.WebUtil;
import com.wsc.parentbean.Pager;
import com.wsc.service.AppointmentService;
import com.wsc.service.AppointmentServiceImpl;
import com.wsc.service.AppointmentViewService;
import com.wsc.service.AppointmentViewServiceImpl;
import com.wsc.service.CompanyActivityService;
import com.wsc.service.CompanyActivityServiceImpl;
import com.wsc.service.CompanyCaseService;
import com.wsc.service.CompanyCaseServiceImpl;
import com.wsc.service.CompanyService;
import com.wsc.service.CompanyServiceImpl;
import com.wsc.service.DesignerCaseService;
import com.wsc.service.DesignerCaseServiceImpl;
import com.wsc.service.DesignerService;
import com.wsc.service.DesignerServiceImpl;
import com.wsc.service.ProductService;
import com.wsc.service.ProductServiceImpl;

public class CompanyServlet extends HttpServlet {

	private static final long serialVersionUID = 1144474244299349474L;
	
	private HttpSession session;
	private CompanyService companyService;
	private CompanyCaseService companyCaseService;
	private CompanyActivityService companyActivityService;
	private AppointmentService appointmentService;
	private AppointmentViewService appointmentViewService;
	private ProductService productService;
	private DesignerService designerService;
	private DesignerCaseService designerCaseService;
	
	public CompanyServlet() {
		companyService = new CompanyServiceImpl();
		companyCaseService = new CompanyCaseServiceImpl();
		companyActivityService = new CompanyActivityServiceImpl();
		appointmentService = new AppointmentServiceImpl();
		appointmentViewService = new AppointmentViewServiceImpl();
		productService = new ProductServiceImpl();
		designerService = new DesignerServiceImpl();
		designerCaseService = new DesignerCaseServiceImpl();
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getURIMethod(req);
		session = req.getSession();
		if (method.equals("query_personal_mes_page")) {
			// 显示装修公司的基本信息
			showPersonalMesPage(req, resp);
		} else if (method.equals("return_index_page")) {
			// 调用返回主页面的方法
			showReturnIndexPage(req, resp);
		} else if (method.equals("update_personal_mes_page")) {
			// 调用显示修改装修公司信息页面
			showUpdatePersonalMesPage(req, resp);
		} else if (method.equals("update_mes")) {
			// 调用处理修改装修公司信息的逻辑方法
			updateMes(req, resp);
		} else if (method.equals("update_personal_pwd_page")) {
			// 调用显示修改密码页面
			showUpdatePersonalPwdPage(req, resp);
		} else if (method.equals("update_pwd")) {
			// 调用更新装修公司登录密码的方法
			updatePwd(req, resp);
		} else if (method.equals("exit")) {
			// 退出登录
			exit(req, resp);
		} else if (method.equals("company_add_case_page")) {
			// 调用显示添加装修公司案例页面
			showAddCasePage(req, resp);
		} else if (method.equals("company_add_case")) {
			// 调用添加装修公司案例逻辑
			addCase(req, resp);
		} else if (method.equals("query_case_all_page")) {
			// 查看所有装修公司案例页面
			showQueryCaseAllPage(req, resp);
		} else if (method.equals("company_update_case_page")) {
			// 把页面转发到修改装修公司案例信息页面
			showUpdateCaseMesPage(req, resp);
		} else if (method.equals("company_update_case")) {
			// 处理修改装修公司案例信息逻辑
			updateCaseMes(req, resp);
		} else if (method.equals("company_activity_page")) {
			//跳转到分页查看活动页
			showCompanyActivityPage(req, resp);
		} else if(method.equals("company_add_activity_page")) {
			//跳转到添加活动页
			showCompanyAddActivityPage(req, resp);
		} else if(method.equals("company_add_activity")) {
			//添加活动处理逻辑
			companyAddActivity(req, resp);
		} else if(method.equals("company_delete_activity")) {
			//删除活动逻辑处理
			companyDeleteActivity(req, resp);
		} else if (method.equals("company_update_activity_page")) {
			//跳转到修改活动页
			showCompanyUpdateActivityPage(req, resp);
		} else if(method.equals("company_update_activity")) {
			//修改活动逻辑处理
			companyUpdateActivity(req, resp);
		} else if(method.equals("query_not_audit_appointment")) {
			// 查看未接受的预约，分页显示所有用户的预约信息，手机号为不可见
			showQueryNotAuditAppointment(req, resp);
		} else if (method.equals("query_appointment")) {
			// 查看预约，可以查看某个用户预约的详细信息，手机号为可见，向预约记录表插入一条数据
			queryAppointment(req, resp);
		} else if (method.equals("query_audit_appointment")) {
			// 查看已接受的预约
			showQueryAuditAppointment(req, resp);
		} else if (method.equals("delete_appointment")) {
			// 删除已经处理过的预约
			deleteAppointment(req, resp);
		}
	}

	/**
	 * 显示装修公司信息页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showPersonalMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
		WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
		if (company == null) {
			req.setAttribute("errorMes", "账号已失效，请重新登录");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("lastLoginTime", DateUtil.getDateStr(company.getLast_login_time()));
			req.getRequestDispatcher("/companys/company_personal_mes.jsp").forward(req, resp);
		}
		
	}

	/**
	 * 把页面转发回首页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showReturnIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (session.getAttribute("company") != null) {
			req.getRequestDispatcher("/companys/company_index.jsp").forward(req, resp);
		} else {
			WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
			req.setAttribute("enterpriseErrorMes", "账号已失效，请重新登录");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

	/**
	 * 把页面转发到修改装修公司信息页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/companys/company_update_mes.jsp").forward(req, resp);
	}

	/**
	 * 修改装修公司信息逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateMes(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		session = req.getSession();
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		Company company = (Company) session.getAttribute("company");
		if (isMultipart) { // 表示JSP页面的post数据包含有文件内容
			@SuppressWarnings("deprecation")
			FileItemFactory fileItemFactory = new DiskFileItemFactory(); // 用户创建与input表单对应的FileItem对象
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			String lastLogo = company.getLogo(); // 获取到原来的logo
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) { // 判断是否为普通表单字段
						String fileName = fileItem.getFieldName(); // 获取表单的name值
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING); // 获取到表单的value值，并设置编码
						if (fileName.equals("name")) { // 获取到的是name的input
							company.setName(fileValue);
						} else if (fileName.equals("principal")) {
							company.setPrincipal(fileValue);
						} else if (fileName.equals("phone")) {
							company.setPhone(fileValue);
						} else if (fileName.equals("tel")) {
							company.setTel(fileValue);
						} else if (fileName.equals("address")) {
							company.setAddress(fileValue);
						} else if (fileName.equals("longitude")) {
							try {
								company.setLongitude(Float.valueOf(fileValue));
							} catch (NumberFormatException e) {
								
							}
						} else if (fileName.equals("latitude")) {
							try {
								company.setLatitude(Float.valueOf(fileValue));
							} catch (NumberFormatException e) {
								
							}
						} else if (fileName.equals("des")) {
							company.setDes(fileValue);
						}
						
					} else { // 表示是文件表单字段
						String fileName = fileItem.getName(); // 获取到文件的名称
						String newFileName = UUID.randomUUID().toString() + ".jpg";
						if (fileName == null || fileName.equals("")) {
							company.setLogo(lastLogo);
						} else {
							InputStream is = fileItem.getInputStream(); // 获取到文件输入流
							String upload = WebUtil.mkUpload(req, "company");
							FileUtils.copyInputStreamToFile(is, new File(upload + "/" + newFileName));
							company.setLogo(WebUtil.SON_FILE + "/" + newFileName);
						}

					}
				}
				companyService.updateCompany(company); // 更新Company对象
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
				
		} else {
			// 表示没有文件内容，不需要处理文件
		}
		resp.sendRedirect("query_personal_mes_page");
	}

	/**
	 * 把页面妆发到修改装修公司密码的页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalPwdPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/companys/company_update_pwd.jsp").forward(req, resp);
	}

	/**
	 * 处理装修公司修改密码的逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
		String pwd = EncryptUtil.encrypt(req.getParameter("pwd"));
		String oldPwd = EncryptUtil.encrypt(req.getParameter("oldPwd"));
		if (company.getPassword().equals(oldPwd)) {
			company.setPassword(pwd);
			companyService.updateCompany(company);
			req.setAttribute("succeed", "密码已经更改，下次登录请使用新密码");
			WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
			req.getRequestDispatcher("/companys/company_update_pwd.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMes", "旧密码有误");
			req.getRequestDispatcher("/companys/company_update_pwd.jsp").forward(req, resp);
		}
	}

	/**
	 * 退出登录
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute("company");
		WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, "");
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
	
	/**
	 * 显示添加装修公司案例页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showAddCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/companys/company_add_case.jsp").forward(req, resp);
	}

	/**
	 * 添加案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addCase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			@SuppressWarnings("deprecation")
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			CompanyCase companyCase = new CompanyCase();
			
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for(FileItem fileItem : fileItems) {
					if(fileItem.isFormField()) {
						String fileName = fileItem.getFieldName();
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING);
						if (fileName.equals("company_id")) {
							companyCase.setCompany_id(fileValue);
						} else if (fileName.equals("name")) {
							companyCase.setName(fileValue);
						} else if (fileName.equals("plot_name")) {
							companyCase.setPlot_name(fileValue);
						} else if (fileName.equals("style")) {
							companyCase.setStyle(fileValue);
						} else if (fileName.equals("des")) {
							companyCase.setDes(fileValue);
						}
						companyCase.setCreated_time(DateUtil.getDate());
					}else {
						String fileName = fileItem.getName();
						String name = fileItem.getFieldName();
						if (name.equals("image1")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage1(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage1(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image2")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage2(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage2(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image3")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage3(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage3(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image4")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage4(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage4(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image5")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage5(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage5(WebUtil.SON_FILE + "/" + newFileName);
							}
						}
					}
				}
				companyCaseService.addCompanyCase(companyCase);
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
		}
		req.setAttribute("succeed", "添加案例成功");
		req.getRequestDispatcher("/companys/company_add_case.jsp").forward(req, resp);
		
	}

	/**
	 * 查询所有装修公司案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCaseAllPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
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
		int total = companyCaseService.queryCountByCompanyId(company.getId()); // 获取到数据总个数
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
		Pager<CompanyCase> pager = companyCaseService.queryByPageAndCompanyId(pageNo, pageSize, company.getId());
		List<CompanyCase> companyCases = pager.getResult();
		req.setAttribute("companyCases", companyCases);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/companys/company_query_case.jsp").forward(req, resp);
	}

	/**
	 * 显示修改案例信息页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdateCaseMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		CompanyCase companyCase = companyCaseService.queryCompanyCaseById(id);
		req.setAttribute("companyCase", companyCase);
		req.getRequestDispatcher("/companys/company_update_case.jsp").forward(req, resp);
	}

	/**
	 * 更新装修公司案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	private void updateCaseMes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			CompanyCase companyCase = companyCaseService.queryCompanyCaseById(req.getParameter("id"));
			String lastImg1 = companyCase.getImage1();
			String lastImg2 = companyCase.getImage2();
			String lastImg3 = companyCase.getImage3();
			String lastImg4 = companyCase.getImage4();
			String lastImg5 = companyCase.getImage5();
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for(FileItem fileItem : fileItems) {
					if(fileItem.isFormField()) {
						String fileName = fileItem.getFieldName();
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING);
						if (fileName.equals("name")) {
							companyCase.setName(fileValue);
						} else if (fileName.equals("plot_name")) {
							companyCase.setPlot_name(fileValue);
						} else if (fileName.equals("style")) {
							companyCase.setStyle(fileValue);
						} else if (fileName.equals("des")) {
							companyCase.setDes(fileValue);
						} else if (fileName.equals("created_time")) {
							companyCase.setCreated_time(Date.valueOf(fileValue));
						}
					}else {
						String fileName = fileItem.getName();
						String name = fileItem.getFieldName();
						if (name.equals("image1")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage1(lastImg1);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage1(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image2")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage2(lastImg2);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage2(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image3")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage3(lastImg3);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage3(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image4")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage4(lastImg4);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage4(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image5")) {
							if (fileName == null || fileName.equals("")) {
								companyCase.setImage5(lastImg5);
							} else {
								String upload = WebUtil.mkUpload(req, "companyCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								companyCase.setImage5(WebUtil.SON_FILE + "/" + newFileName);
							}
						}
						
					}
				}
				
				companyCaseService.updateCase(companyCase);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
		}
		resp.sendRedirect("query_case_all_page");
		
	}
	
	/**
	 * 跳转到分页查看活动页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCompanyActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize"); // 获取到指定每一页显示多少条数据
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = companyActivityService.queryCountCompanyId(company.getId()); // 获取到数据总个数
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
		Pager<CompanyActivity> pager = companyActivityService.queryByCompanyId(pageNo, pageSize, company.getId());
		List<CompanyActivity> activitys = pager.getResult();
		req.setAttribute("activitys", activitys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/companys/company_query_activity.jsp").forward(req, resp);		
	}
	
	/**
	 * 跳转到添加活动页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCompanyAddActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/companys/company_add_activity.jsp").forward(req, resp);
	}
	/**
	 * 添加活动逻辑处理 
	 * @param req
	 * @param resp
	 * @throws 

 
	 * @throws ServletException 
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	private void companyAddActivity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			CompanyActivity activity = new CompanyActivity();
			
			try {
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for(FileItem fileItem : fileItems) {
					if(fileItem.isFormField()) {
						String fileName = fileItem.getFieldName();
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING);
						if (fileName.equals("company_id")) {
							activity.setCompany_id(fileValue);
						} else if (fileName.equals("name")) {
							activity.setName(fileValue);
						} else if (fileName.equals("des")) {
							activity.setDes(fileValue);
						}
						activity.setCreated_time(DateUtil.getDate());
					}else {
						String fileName = fileItem.getName();
						if (fileName == null || fileName.equals("")) {
							activity.setImage(Constants.DEFAULT_IMAGE);
						} else {
							String upload = WebUtil.mkUpload(req, "companyActivity");
							String newFileName = UUID.randomUUID().toString() + ".jpg";
							FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
							activity.setImage(WebUtil.SON_FILE + "/" + newFileName);
						}
					}
				}
				companyActivityService.addActivity(activity);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
		}
		req.setAttribute("succeed", "添加活动成功");
		req.getRequestDispatcher("/companys/company_add_activity.jsp").forward(req, resp);
		
	}
	/**
	 * 删除活动逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void companyDeleteActivity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		companyActivityService.deleteDataId(id, "t_company_activity");;
		resp.sendRedirect("company_activity_page");
	}
	
	/**
	 * 跳转到修改活动页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCompanyUpdateActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		CompanyActivity activity = companyActivityService.queryActById(id);
		req.setAttribute("activity", activity);
		req.getRequestDispatcher("/companys/company_update_activity.jsp").forward(req, resp);
	}
	
	/**
	 * 修改活动信息逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void companyUpdateActivity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			@SuppressWarnings("deprecation")
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			CompanyActivity activity = companyActivityService.queryActById(req.getParameter("id"));
			String lastImg = activity.getImage();
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for(FileItem fileItem : fileItems) {
					if(fileItem.isFormField()) {
						String fileName = fileItem.getFieldName();
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING);
						if (fileName.equals("name")) {
							activity.setName(fileValue);
						} else if (fileName.equals("des")) {
							activity.setDes(fileValue);
						} else if (fileName.equals("created_time")) {
							activity.setCreated_time(Date.valueOf(fileValue));
						}
					}else {
						String fileName = fileItem.getName();
						String newFileName = UUID.randomUUID().toString() + ".jpg";
						if (fileName.equals("") || fileName == null) {
							activity.setImage(lastImg);
						} else {
							String upload = WebUtil.mkUpload(req, "supply");
							FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
							activity.setImage(WebUtil.SON_FILE + "/" + newFileName);
						}
						
					}
				}
				
				companyActivityService.updateAct(activity);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
		}
		resp.sendRedirect("company_activity_page");
	}

	/**
	 * 分页查看所有用户的预约信息，手机号为不可见
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryNotAuditAppointment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // 获取到要查询第几页的数据
		String pageSizeStr = req.getParameter("pageSize"); // 获取到指定每一页显示多少条数据
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
		int pageNo = 1; // 默认是查询第1页的数据
		int pageSize = 5; // 每页数据的大小
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = appointmentService.queryCountByCompanyId(company.getId()); // 获取到数据总个数
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
		Pager<Appointment> pager = appointmentService.queryByCompanyId(pageNo, pageSize, company.getId());
		List<Appointment> appointments = pager.getResult();
		req.setAttribute("appointments", appointments);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/companys/company_query_not_audit_appointment.jsp").forward(req, resp);
	}

	/**
	 * 查看预约信息，点击查看预约后，手机号变可见，显示出预约用户的详细信息，方便去联系用户
	 * ，在这之前，的判断此用户的预约是否已经被别的公司接受了，一条预约最多只能是5个公司去查看
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void queryAppointment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
		String company_id = company.getId();
		String app_id = req.getParameter("app_id");
		if (appointmentViewService.queryAppointmentViewByAppIdAndCompanyId(app_id, company_id)) {
			req.setAttribute("errorMes", "您已经接受该预约了, 可在已接受预约中查看");
			req.getRequestDispatcher("/companys/company_query_not_audit_appointment.jsp").forward(req, resp);
		} else {
			if (appointmentViewService.queryCountByAppId(app_id) > Constants.MAX_QUERY) {
				req.setAttribute("errorMes", "该预约已经超出了最大的查看范围");
				req.getRequestDispatcher("/companys/company_query_not_audit_appointment.jsp").forward(req, resp);
			} else {
				AppointmentView appointmentView = new AppointmentView();
				appointmentView.setApp_id(app_id);
				appointmentView.setCompany_id(company_id);
				appointmentView.setCreated_time(DateUtil.getDate());
				appointmentViewService.addAppointmentView(appointmentView);
				Appointment appointment = appointmentService.queryAppointmentById(app_id);
				req.setAttribute("appointment", appointment);
				req.getRequestDispatcher("/companys/company_query_appointment_mes.jsp").forward(req, resp);
			}
		}
	}

	/**
	 * 查看本身装修公司已经查看过的预约信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryAuditAppointment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
		String company_id = company.getId();
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
		int total = appointmentViewService.queryCountByCompanyId(company_id);// 获取到数据总个数
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
		Pager<AppointmentView> pager = appointmentViewService.queryByPage(pageNo, pageSize, company_id);
		List<AppointmentView> appointmentViews = pager.getResult();
		req.setAttribute("appointmentViews", appointmentViews);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/companys/company_query_audit_appointment.jsp").forward(req, resp);
	}

	/**
	 * 删除预约信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteAppointment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		appointmentService.deleteDataId(id, "t_appointment_view");
		resp.sendRedirect("query_audit_appointment");
	}

}
