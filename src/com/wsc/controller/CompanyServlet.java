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
			// ��ʾװ�޹�˾�Ļ�����Ϣ
			showPersonalMesPage(req, resp);
		} else if (method.equals("return_index_page")) {
			// ���÷�����ҳ��ķ���
			showReturnIndexPage(req, resp);
		} else if (method.equals("update_personal_mes_page")) {
			// ������ʾ�޸�װ�޹�˾��Ϣҳ��
			showUpdatePersonalMesPage(req, resp);
		} else if (method.equals("update_mes")) {
			// ���ô����޸�װ�޹�˾��Ϣ���߼�����
			updateMes(req, resp);
		} else if (method.equals("update_personal_pwd_page")) {
			// ������ʾ�޸�����ҳ��
			showUpdatePersonalPwdPage(req, resp);
		} else if (method.equals("update_pwd")) {
			// ���ø���װ�޹�˾��¼����ķ���
			updatePwd(req, resp);
		} else if (method.equals("exit")) {
			// �˳���¼
			exit(req, resp);
		} else if (method.equals("company_add_case_page")) {
			// ������ʾ���װ�޹�˾����ҳ��
			showAddCasePage(req, resp);
		} else if (method.equals("company_add_case")) {
			// �������װ�޹�˾�����߼�
			addCase(req, resp);
		} else if (method.equals("query_case_all_page")) {
			// �鿴����װ�޹�˾����ҳ��
			showQueryCaseAllPage(req, resp);
		} else if (method.equals("company_update_case_page")) {
			// ��ҳ��ת�����޸�װ�޹�˾������Ϣҳ��
			showUpdateCaseMesPage(req, resp);
		} else if (method.equals("company_update_case")) {
			// �����޸�װ�޹�˾������Ϣ�߼�
			updateCaseMes(req, resp);
		} else if (method.equals("company_activity_page")) {
			//��ת����ҳ�鿴�ҳ
			showCompanyActivityPage(req, resp);
		} else if(method.equals("company_add_activity_page")) {
			//��ת����ӻҳ
			showCompanyAddActivityPage(req, resp);
		} else if(method.equals("company_add_activity")) {
			//��ӻ�����߼�
			companyAddActivity(req, resp);
		} else if(method.equals("company_delete_activity")) {
			//ɾ����߼�����
			companyDeleteActivity(req, resp);
		} else if (method.equals("company_update_activity_page")) {
			//��ת���޸Ļҳ
			showCompanyUpdateActivityPage(req, resp);
		} else if(method.equals("company_update_activity")) {
			//�޸Ļ�߼�����
			companyUpdateActivity(req, resp);
		} else if(method.equals("query_not_audit_appointment")) {
			// �鿴δ���ܵ�ԤԼ����ҳ��ʾ�����û���ԤԼ��Ϣ���ֻ���Ϊ���ɼ�
			showQueryNotAuditAppointment(req, resp);
		} else if (method.equals("query_appointment")) {
			// �鿴ԤԼ�����Բ鿴ĳ���û�ԤԼ����ϸ��Ϣ���ֻ���Ϊ�ɼ�����ԤԼ��¼�����һ������
			queryAppointment(req, resp);
		} else if (method.equals("query_audit_appointment")) {
			// �鿴�ѽ��ܵ�ԤԼ
			showQueryAuditAppointment(req, resp);
		} else if (method.equals("delete_appointment")) {
			// ɾ���Ѿ��������ԤԼ
			deleteAppointment(req, resp);
		}
	}

	/**
	 * ��ʾװ�޹�˾��Ϣҳ��
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
			req.setAttribute("errorMes", "�˺���ʧЧ�������µ�¼");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("lastLoginTime", DateUtil.getDateStr(company.getLast_login_time()));
			req.getRequestDispatcher("/companys/company_personal_mes.jsp").forward(req, resp);
		}
		
	}

	/**
	 * ��ҳ��ת������ҳ
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
			req.setAttribute("enterpriseErrorMes", "�˺���ʧЧ�������µ�¼");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

	/**
	 * ��ҳ��ת�����޸�װ�޹�˾��Ϣҳ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/companys/company_update_mes.jsp").forward(req, resp);
	}

	/**
	 * �޸�װ�޹�˾��Ϣ�߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateMes(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		session = req.getSession();
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		Company company = (Company) session.getAttribute("company");
		if (isMultipart) { // ��ʾJSPҳ���post���ݰ������ļ�����
			@SuppressWarnings("deprecation")
			FileItemFactory fileItemFactory = new DiskFileItemFactory(); // �û�������input����Ӧ��FileItem����
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			String lastLogo = company.getLogo(); // ��ȡ��ԭ����logo
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) { // �ж��Ƿ�Ϊ��ͨ���ֶ�
						String fileName = fileItem.getFieldName(); // ��ȡ����nameֵ
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING); // ��ȡ������valueֵ�������ñ���
						if (fileName.equals("name")) { // ��ȡ������name��input
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
						
					} else { // ��ʾ���ļ����ֶ�
						String fileName = fileItem.getName(); // ��ȡ���ļ�������
						String newFileName = UUID.randomUUID().toString() + ".jpg";
						if (fileName == null || fileName.equals("")) {
							company.setLogo(lastLogo);
						} else {
							InputStream is = fileItem.getInputStream(); // ��ȡ���ļ�������
							String upload = WebUtil.mkUpload(req, "company");
							FileUtils.copyInputStreamToFile(is, new File(upload + "/" + newFileName));
							company.setLogo(WebUtil.SON_FILE + "/" + newFileName);
						}

					}
				}
				companyService.updateCompany(company); // ����Company����
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
				
		} else {
			// ��ʾû���ļ����ݣ�����Ҫ�����ļ�
		}
		resp.sendRedirect("query_personal_mes_page");
	}

	/**
	 * ��ҳ��ױ�����޸�װ�޹�˾�����ҳ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePersonalPwdPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/companys/company_update_pwd.jsp").forward(req, resp);
	}

	/**
	 * ����װ�޹�˾�޸�������߼�����
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
			req.setAttribute("succeed", "�����Ѿ����ģ��´ε�¼��ʹ��������");
			WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
			req.getRequestDispatcher("/companys/company_update_pwd.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMes", "����������");
			req.getRequestDispatcher("/companys/company_update_pwd.jsp").forward(req, resp);
		}
	}

	/**
	 * �˳���¼
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
	 * ��ʾ���װ�޹�˾����ҳ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showAddCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/companys/company_add_case.jsp").forward(req, resp);
	}

	/**
	 * ��Ӱ���
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
		req.setAttribute("succeed", "��Ӱ����ɹ�");
		req.getRequestDispatcher("/companys/company_add_case.jsp").forward(req, resp);
		
	}

	/**
	 * ��ѯ����װ�޹�˾����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryCaseAllPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
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
		int total = companyCaseService.queryCountByCompanyId(company.getId()); // ��ȡ�������ܸ���
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
		Pager<CompanyCase> pager = companyCaseService.queryByPageAndCompanyId(pageNo, pageSize, company.getId());
		List<CompanyCase> companyCases = pager.getResult();
		req.setAttribute("companyCases", companyCases);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/companys/company_query_case.jsp").forward(req, resp);
	}

	/**
	 * ��ʾ�޸İ�����Ϣҳ��
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
	 * ����װ�޹�˾����
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
	 * ��ת����ҳ�鿴�ҳ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCompanyActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize"); // ��ȡ��ָ��ÿһҳ��ʾ����������
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = companyActivityService.queryCountCompanyId(company.getId()); // ��ȡ�������ܸ���
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
		Pager<CompanyActivity> pager = companyActivityService.queryByCompanyId(pageNo, pageSize, company.getId());
		List<CompanyActivity> activitys = pager.getResult();
		req.setAttribute("activitys", activitys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/companys/company_query_activity.jsp").forward(req, resp);		
	}
	
	/**
	 * ��ת����ӻҳ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCompanyAddActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/companys/company_add_activity.jsp").forward(req, resp);
	}
	/**
	 * ��ӻ�߼����� 
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
		req.setAttribute("succeed", "��ӻ�ɹ�");
		req.getRequestDispatcher("/companys/company_add_activity.jsp").forward(req, resp);
		
	}
	/**
	 * ɾ����߼�����
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
	 * ��ת���޸Ļҳ
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
	 * �޸Ļ��Ϣ�߼�����
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
	 * ��ҳ�鿴�����û���ԤԼ��Ϣ���ֻ���Ϊ���ɼ�
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryNotAuditAppointment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo"); // ��ȡ��Ҫ��ѯ�ڼ�ҳ������
		String pageSizeStr = req.getParameter("pageSize"); // ��ȡ��ָ��ÿһҳ��ʾ����������
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
		int pageNo = 1; // Ĭ���ǲ�ѯ��1ҳ������
		int pageSize = 5; // ÿҳ���ݵĴ�С
		if (pageSizeStr != null) {
			try {
				pageSize = Integer.valueOf(pageSizeStr);
			} catch (NumberFormatException e) {
				
			}
		}
		int total = appointmentService.queryCountByCompanyId(company.getId()); // ��ȡ�������ܸ���
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
		Pager<Appointment> pager = appointmentService.queryByCompanyId(pageNo, pageSize, company.getId());
		List<Appointment> appointments = pager.getResult();
		req.setAttribute("appointments", appointments);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/companys/company_query_not_audit_appointment.jsp").forward(req, resp);
	}

	/**
	 * �鿴ԤԼ��Ϣ������鿴ԤԼ���ֻ��ű�ɼ�����ʾ��ԤԼ�û�����ϸ��Ϣ������ȥ��ϵ�û�
	 * ������֮ǰ�����жϴ��û���ԤԼ�Ƿ��Ѿ�����Ĺ�˾�����ˣ�һ��ԤԼ���ֻ����5����˾ȥ�鿴
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
			req.setAttribute("errorMes", "���Ѿ����ܸ�ԤԼ��, �����ѽ���ԤԼ�в鿴");
			req.getRequestDispatcher("/companys/company_query_not_audit_appointment.jsp").forward(req, resp);
		} else {
			if (appointmentViewService.queryCountByAppId(app_id) > Constants.MAX_QUERY) {
				req.setAttribute("errorMes", "��ԤԼ�Ѿ����������Ĳ鿴��Χ");
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
	 * �鿴����װ�޹�˾�Ѿ��鿴����ԤԼ��Ϣ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showQueryAuditAppointment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Company company = (Company) session.getAttribute("company");
		String company_id = company.getId();
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
		int total = appointmentViewService.queryCountByCompanyId(company_id);// ��ȡ�������ܸ���
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
		Pager<AppointmentView> pager = appointmentViewService.queryByPage(pageNo, pageSize, company_id);
		List<AppointmentView> appointmentViews = pager.getResult();
		req.setAttribute("appointmentViews", appointmentViews);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/companys/company_query_audit_appointment.jsp").forward(req, resp);
	}

	/**
	 * ɾ��ԤԼ��Ϣ
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
