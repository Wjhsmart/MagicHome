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

import com.wsc.bean.Designer;
import com.wsc.bean.DesignerCase;
import com.wsc.common.Constants;
import com.wsc.common.DateUtil;
import com.wsc.common.EncryptUtil;
import com.wsc.common.WebUtil;
import com.wsc.parentbean.Pager;
import com.wsc.service.CommonService;
import com.wsc.service.CommonServiceImpl;
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

public class DesignerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2959434541816718872L;

	private HttpSession session;
	private DesignerService designerService;
	private DesignerCaseService designerCaseService;
	private ProductService productService;
	private CompanyService companyService;
	private CompanyCaseService companyCaseService;
	private CommonService commonService;
	
	public DesignerServlet() {
		designerService = new DesignerServiceImpl();
		designerCaseService = new DesignerCaseServiceImpl();
		productService = new ProductServiceImpl();
		companyService = new CompanyServiceImpl();
		companyCaseService = new CompanyCaseServiceImpl();
		commonService = new CommonServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getURIMethod(req);
		session = req.getSession();
		if (method.equals("register")) {
			//ע���߼�
			register(req, resp);
		} else if (method.equals("login")) {
			//��¼�߼�
			login(req, resp);
		} else if (method.equals("designer_personal_page")) {
			//��ת����������ҳ
			showDesignerPersonalPage(req, resp);
		} else if (method.equals("designer_update_mes_page")) {
			//��ת���޸ĸ�����Ϣҳ
			showDesignerUpdateMesPage(req, resp);
		} else if (method.equals("designer_update_mes")) {
			//�޸ĸ�����Ϣ�߼�����
			designerUpdateMes(req, resp);
		} else if (method.equals("return_index_page")) {
			//�������ʦ��ҳ
			showDesignerIndexPage(req, resp);
		} else if (method.equals("designer_update_pwd_page")) {
			//��ת���޸�����ҳ
			showDesignerUpdatePwdPage(req, resp);
		} else if (method.equals("designer_update_pwd")) {
			// �޸������߼�����
			designerUpdatePwd(req, resp);
		} else if (method.equals("designer_add_case_page")) {
			// ��ת����Ӱ���ҳ��
			showDesignerAddCasePage(req, resp);
		} else if (method.equals("designer_add_case")) {
			// ��Ӱ����߼�����
			designerAddCase(req, resp);
		} else if (method.equals("designer_case_page")) {
			//��ת����ҳ�鿴����
			designerCasePage(req, resp);
		} else if (method.equals("designer_update_case_page")) {
			// ��ת���޸İ���ҳ
			showDesignerUpdateCasePage(req, resp);
		} else if (method.equals("designer_update_case")) {
			// �޸İ����߼�����
			designerUpdateCase(req, resp);
		} else if (method.equals("exit")) {
			// �˳���¼
			exit(req, resp);
		}
		
		
	}

	/**
	 * ע���߼��� ע��ɹ���ת��¼ҳ��ʧ��������ת��ע��ҳ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		String phone = req.getParameter("phone");
		String experience = req.getParameter("option");
		WebUtil.setDivDisplay(req, "none", "none", "block", "", "checked='checked'");
		if (name != null && !name.equals("") && email != null && !email.equals("") && pwd != null && !pwd.equals("") && phone != null && !phone.equals("") && experience != null && !experience.equals("")) {
			Designer designer = new Designer();
			WebUtil.setDivDisplay(req, "none", "none", "block", "", "checked='checked'");
			if (WebUtil.checkEmail(commonService, email, req, resp)) {
				if (pwd.length() < 6) {
					req.setAttribute("errorMes", "���볤�ȱ���6λ����");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				} else if (phone.length() != 11) {
					req.setAttribute("errorMes", "�ֻ��������Ϊ11λ����");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				} else {
					designer.setE_mail(email);
					designer.setPassword(EncryptUtil.encrypt(pwd));
					designer.setName(name);
					designer.setHead_icon(Constants.DEFAULR_DESIGNER_HEADICON);
					designer.setPhone(phone);
					designer.setAddress("���� ");
					designer.setExperience(experience);
					designer.setStyle("��Լ");
					designer.setDes("���ʦ");
					designer.setCreated_time(DateUtil.getDate());
					designer.setChecked("N");
					designer.setChecked_time(DateUtil.getDate());
					designer.setLast_login_time(DateUtil.getDate());
					designer.setLogin_count(0);
					designer.setStatus("N");
					designerService.addDesigner(designer);
					req.setAttribute("designerSucceed", "�����Ѿ��ύ�������ĵȴ����");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("errorMes", "�Ѵ��ڸ��û�");
				req.getRequestDispatcher("/register.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("designerErrorMes", "�����ֶβ���Ϊ��");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * ��¼�߼�������¼�ɹ���ת�����ʦ����ҳ  ��ʧ����ת�ص�¼ҳ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pwd = EncryptUtil.encrypt(req.getParameter("pwd"));
		WebUtil.setDivDisplay(req, "none", "none", "block", "checked='checked'", "");
		if (email != null && !email.equals("") && pwd != null && !pwd.equals("")) {
			Designer designer = designerService.queryCurr(email, pwd);
			if (designer.getStatus() == null || designer.getStatus().equals("")) {
				req.setAttribute("designerErrorMes", "�������������");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			} else {
				if (designer.getStatus().equals("Y")) {
					designer.setLast_login_time(DateUtil.getDate());
					designer.setLogin_count(designer.getLogin_count() + 1);
					designerService.updateDesigner(designer);
					session.setAttribute("designer", designer);
					req.getRequestDispatcher("/designers/designer_index.jsp").forward(req, resp);
				}else {
					req.setAttribute("designerErrorMes", "�˺Ų�����");
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}
			}
		} else {
			req.setAttribute("designerErrorMes", "��������벻��Ϊ��");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
	
	/**
	 * ��ת����������ҳ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDesignerPersonalPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Designer designer = (Designer) session.getAttribute("designer");
		WebUtil.setDivDisplay(req, "none", "none", "block", "checked='checked'", "");
		if (designer == null) {
			req.setAttribute("errorMes", "�˺���ʧЧ�������µ�¼");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("lastLoginTime", DateUtil.getDateStr(designer.getLast_login_time()));
			req.getRequestDispatcher("/designers/designer_personal_mes.jsp").forward(req, resp);
		}
	}

	/**
	 * ��ת���޸ĸ�����Ϣҳ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDesignerUpdateMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/designers/designer_update_mes.jsp").forward(req, resp);
	}
	
	/**
	 * �޸ĸ�����Ϣ�߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void designerUpdateMes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		Designer designer = (Designer) session.getAttribute("designer");
		if (isMultipart) { // ��ʾJSPҳ���post���ݰ������ļ�����
			@SuppressWarnings("deprecation")
			FileItemFactory fileItemFactory = new DiskFileItemFactory(); // �û�������input����Ӧ��FileItem����
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			String lastHeadicon = designer.getHead_icon(); // ��ȡ��ԭ����headicon
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) { // �ж��Ƿ�Ϊ��ͨ���ֶ�
						String fileName = fileItem.getFieldName(); // ��ȡ����nameֵ
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING); // ��ȡ������valueֵ�������ñ���
						if (fileName.equals("name")) { // ��ȡ������name��input
							designer.setName(fileValue);
						} else if (fileName.equals("phone")) {
							designer.setPhone(fileValue);
						} else if (fileName.equals("address")) {
							designer.setAddress(fileValue);
						} else if (fileName.equals("experience")) {
							designer.setExperience(fileValue);
						} else if (fileName.equals("style")) {
							designer.setStyle(fileValue);
						} else if (fileName.equals("des")) {
							designer.setDes(fileValue);
						}
						
					} else { // ��ʾ���ļ����ֶ�
						String fileName = fileItem.getName(); // ��ȡ���ļ�������
						String newFileName = UUID.randomUUID().toString() + ".jpg";
						if (fileName == null || fileName.equals("")) {
							designer.setHead_icon(lastHeadicon);
						} else {
							InputStream is = fileItem.getInputStream(); // ��ȡ���ļ�������
							String upload = WebUtil.mkUpload(req, "designer");
							FileUtils.copyInputStreamToFile(is, new File(upload + "/" + newFileName));
							designer.setHead_icon(WebUtil.SON_FILE + "/" + newFileName);
						}

					}
				}
				designerService.updateDesigner(designer); // ����designer����
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
				
		} else {
			// ��ʾû���ļ����ݣ�����Ҫ�����ļ�
		}
		resp.sendRedirect("designer_personal_page");
	}

	/**
	 * �������ʦ��ҳ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDesignerIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (session.getAttribute("designer") != null) {
			req.getRequestDispatcher("/designers/designer_index.jsp").forward(req, resp);
		} else {
			WebUtil.setDivDisplay(req, "none", "none", "block", "checked='checked'", "");
			req.setAttribute("designerErrorMes", "�˺���ʧЧ�������µ�¼");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
	
	/**
	 *  ��ת���޸�����ҳ
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showDesignerUpdatePwdPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/designers/designer_update_pwd.jsp").forward(req, resp);
	}

	/**
	 * �޸������߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void designerUpdatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Designer designer = (Designer) session.getAttribute("designer");
		String pwd = EncryptUtil.encrypt(req.getParameter("pwd"));
		String oldPwd = EncryptUtil.encrypt(req.getParameter("oldPwd"));
		if (designer.getPassword().equals(oldPwd)) {
			designer.setPassword(pwd);
			designerService.updateDesigner(designer);
			req.setAttribute("succeed", "�����Ѿ����ģ��´ε�¼��ʹ��������");
			WebUtil.setDivDisplay(req, "none", "none", "block", "checked='checked'", "");
			req.getRequestDispatcher("/designers/designer_update_pwd.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMes", "����������");
			req.getRequestDispatcher("/designers/designer_update_pwd.jsp").forward(req, resp);
		}
	}

	/**
	 * ��ת����Ӱ���ҳ��
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDesignerAddCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/designers/designer_add_case.jsp").forward(req, resp);
	}

	/**
	 * ��Ӱ����߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void designerAddCase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			DesignerCase designerCase = new DesignerCase();
			
			try {
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for(FileItem fileItem : fileItems) {
					if(fileItem.isFormField()) {
						String fileName = fileItem.getFieldName();
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING);
						if (fileName.equals("designer_id")) {
							designerCase.setDesigner_id(fileValue);
						} else if (fileName.equals("name")) {
							designerCase.setName(fileValue);
						} else if (fileName.equals("plot_name")) {
							designerCase.setPlot_name(fileValue);
						} else if (fileName.equals("style")) {
							designerCase.setStyle(fileValue);
						} else if (fileName.equals("des")) {
							designerCase.setDes(fileValue);
						}
						designerCase.setCreated_time(DateUtil.getDate());
					}else {
						String fileName = fileItem.getName();
						String name = fileItem.getFieldName();
						if (name.equals("image1")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage1(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage1(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image2")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage2(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage2(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image3")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage3(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage3(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image4")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage4(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage4(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image5")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage5(Constants.DEFAULT_IMAGE);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage5(WebUtil.SON_FILE + "/" + newFileName);
							}
						}
					}
				}
				designerCaseService.addDesignerCase(designerCase);
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {

		}
		resp.sendRedirect("designer_case_page");
		
	}

	/**
	 * ��ҳ�鿴���а���
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void designerCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Designer designer = (Designer) session.getAttribute("designer");
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
		int total = commonService.queryCount("t_designer_case"); // ��ȡ�������ܸ���
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
		Pager<DesignerCase> pager = designerCaseService.queryDesignerCaseByDesignerId(pageNo, pageSize, designer.getId()); // ���ݷ�ҳ��ѯ���ݿ�
		List<DesignerCase> designerCases = pager.getResult();
		req.setAttribute("designerCases", designerCases);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/designers/designer_query_case.jsp").forward(req, resp);
	}
	
	/**
	 * ��ת���޸İ���ҳ��
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDesignerUpdateCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DesignerCase designerCase = designerCaseService.queryDesignerCaseById(req.getParameter("id"));
		req.setAttribute("designerCase", designerCase);
		req.getRequestDispatcher("/designers/designer_update_case.jsp").forward(req, resp);
	}

	/**
	 *  �޸İ����߼�����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void designerUpdateCase(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			DesignerCase designerCase = designerCaseService.queryDesignerCaseById(req.getParameter("id"));
			String lastImg1 = designerCase.getImage1();
			String lastImg2 = designerCase.getImage2();
			String lastImg3 = designerCase.getImage3();
			String lastImg4 = designerCase.getImage4();
			String lastImg5 = designerCase.getImage5();
			try {
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for(FileItem fileItem : fileItems) {
					if(fileItem.isFormField()) {
						String fileName = fileItem.getFieldName();
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING);
						if (fileName.equals("name")) {
							designerCase.setName(fileValue);
						} else if (fileName.equals("plot_name")) {
							designerCase.setPlot_name(fileValue);
						} else if (fileName.equals("style")) {
							designerCase.setStyle(fileValue);
						} else if (fileName.equals("des")) {
							designerCase.setDes(fileValue);
						} else if (fileName.equals("created_time")) {
							designerCase.setCreated_time(Date.valueOf(fileValue));
						}
					}else {
						String fileName = fileItem.getName();
						String name = fileItem.getFieldName();
						if (name.equals("image1")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage1(lastImg1);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage1(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image2")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage2(lastImg2);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage2(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image3")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage3(lastImg3);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage3(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image4")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage4(lastImg4);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage4(WebUtil.SON_FILE + "/" + newFileName);
							}
						} else if (name.equals("image5")) {
							if (fileName == null || fileName.equals("")) {
								designerCase.setImage5(lastImg5);
							} else {
								String upload = WebUtil.mkUpload(req, "designerCase");
								String newFileName = UUID.randomUUID().toString() + ".jpg";
								FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
								designerCase.setImage5(WebUtil.SON_FILE + "/" + newFileName);
							}
						}
						
					}
				}
				
				designerCaseService.updateCase(designerCase);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
		}
		resp.sendRedirect("designer_case_page");
	}
	
	/**
	 * �˳���¼
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute("designer");
		WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, "");
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
	
	
	
	
	
	
	
	
}
