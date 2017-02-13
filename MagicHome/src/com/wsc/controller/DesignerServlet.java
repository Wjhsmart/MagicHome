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
			//注册逻辑
			register(req, resp);
		} else if (method.equals("login")) {
			//登录逻辑
			login(req, resp);
		} else if (method.equals("designer_personal_page")) {
			//跳转到个人中心页
			showDesignerPersonalPage(req, resp);
		} else if (method.equals("designer_update_mes_page")) {
			//跳转到修改个人信息页
			showDesignerUpdateMesPage(req, resp);
		} else if (method.equals("designer_update_mes")) {
			//修改个人信息逻辑处理
			designerUpdateMes(req, resp);
		} else if (method.equals("return_index_page")) {
			//返回设计师首页
			showDesignerIndexPage(req, resp);
		} else if (method.equals("designer_update_pwd_page")) {
			//跳转到修改密码页
			showDesignerUpdatePwdPage(req, resp);
		} else if (method.equals("designer_update_pwd")) {
			// 修改密码逻辑处理
			designerUpdatePwd(req, resp);
		} else if (method.equals("designer_add_case_page")) {
			// 跳转到添加案例页面
			showDesignerAddCasePage(req, resp);
		} else if (method.equals("designer_add_case")) {
			// 添加案例逻辑处理
			designerAddCase(req, resp);
		} else if (method.equals("designer_case_page")) {
			//跳转到分页查看案例
			designerCasePage(req, resp);
		} else if (method.equals("designer_update_case_page")) {
			// 跳转到修改案例页
			showDesignerUpdateCasePage(req, resp);
		} else if (method.equals("designer_update_case")) {
			// 修改案例逻辑处理
			designerUpdateCase(req, resp);
		} else if (method.equals("exit")) {
			// 退出登录
			exit(req, resp);
		}
		
		
	}

	/**
	 * 注册逻辑， 注册成功跳转登录页，失败重新跳转回注册页
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
					req.setAttribute("errorMes", "密码长度必须6位以上");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				} else if (phone.length() != 11) {
					req.setAttribute("errorMes", "手机号码必须为11位数字");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				} else {
					designer.setE_mail(email);
					designer.setPassword(EncryptUtil.encrypt(pwd));
					designer.setName(name);
					designer.setHead_icon(Constants.DEFAULR_DESIGNER_HEADICON);
					designer.setPhone(phone);
					designer.setAddress("赣州 ");
					designer.setExperience(experience);
					designer.setStyle("简约");
					designer.setDes("设计师");
					designer.setCreated_time(DateUtil.getDate());
					designer.setChecked("N");
					designer.setChecked_time(DateUtil.getDate());
					designer.setLast_login_time(DateUtil.getDate());
					designer.setLogin_count(0);
					designer.setStatus("N");
					designerService.addDesigner(designer);
					req.setAttribute("designerSucceed", "申请已经提交，请耐心等待审核");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("errorMes", "已存在该用户");
				req.getRequestDispatcher("/register.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("designerErrorMes", "所有字段不能为空");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * 登录逻辑处理，登录成功跳转到设计师管理页  ，失败跳转回登录页
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
				req.setAttribute("designerErrorMes", "邮箱或密码有误");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			} else {
				if (designer.getStatus().equals("Y")) {
					designer.setLast_login_time(DateUtil.getDate());
					designer.setLogin_count(designer.getLogin_count() + 1);
					designerService.updateDesigner(designer);
					session.setAttribute("designer", designer);
					req.getRequestDispatcher("/designers/designer_index.jsp").forward(req, resp);
				}else {
					req.setAttribute("designerErrorMes", "账号不可用");
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}
			}
		} else {
			req.setAttribute("designerErrorMes", "邮箱和密码不能为空");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 跳转到个人中心页
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
			req.setAttribute("errorMes", "账号已失效，请重新登录");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("lastLoginTime", DateUtil.getDateStr(designer.getLast_login_time()));
			req.getRequestDispatcher("/designers/designer_personal_mes.jsp").forward(req, resp);
		}
	}

	/**
	 * 跳转到修改个人信息页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDesignerUpdateMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/designers/designer_update_mes.jsp").forward(req, resp);
	}
	
	/**
	 * 修改个人信息逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void designerUpdateMes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		Designer designer = (Designer) session.getAttribute("designer");
		if (isMultipart) { // 表示JSP页面的post数据包含有文件内容
			@SuppressWarnings("deprecation")
			FileItemFactory fileItemFactory = new DiskFileItemFactory(); // 用户创建与input表单对应的FileItem对象
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			String lastHeadicon = designer.getHead_icon(); // 获取到原来的headicon
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) { // 判断是否为普通表单字段
						String fileName = fileItem.getFieldName(); // 获取表单的name值
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING); // 获取到表单的value值，并设置编码
						if (fileName.equals("name")) { // 获取到的是name的input
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
						
					} else { // 表示是文件表单字段
						String fileName = fileItem.getName(); // 获取到文件的名称
						String newFileName = UUID.randomUUID().toString() + ".jpg";
						if (fileName == null || fileName.equals("")) {
							designer.setHead_icon(lastHeadicon);
						} else {
							InputStream is = fileItem.getInputStream(); // 获取到文件输入流
							String upload = WebUtil.mkUpload(req, "designer");
							FileUtils.copyInputStreamToFile(is, new File(upload + "/" + newFileName));
							designer.setHead_icon(WebUtil.SON_FILE + "/" + newFileName);
						}

					}
				}
				designerService.updateDesigner(designer); // 更新designer对象
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
				
		} else {
			// 表示没有文件内容，不需要处理文件
		}
		resp.sendRedirect("designer_personal_page");
	}

	/**
	 * 返回设计师主页
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
			req.setAttribute("designerErrorMes", "账号已失效，请重新登录");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
	
	/**
	 *  跳转到修改密码页
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showDesignerUpdatePwdPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/designers/designer_update_pwd.jsp").forward(req, resp);
	}

	/**
	 * 修改密码逻辑处理
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
			req.setAttribute("succeed", "密码已经更改，下次登录请使用新密码");
			WebUtil.setDivDisplay(req, "none", "none", "block", "checked='checked'", "");
			req.getRequestDispatcher("/designers/designer_update_pwd.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMes", "旧密码有误");
			req.getRequestDispatcher("/designers/designer_update_pwd.jsp").forward(req, resp);
		}
	}

	/**
	 * 跳转到添加案例页面
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showDesignerAddCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/designers/designer_add_case.jsp").forward(req, resp);
	}

	/**
	 * 添加案例逻辑处理
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
	 * 分页查看所有案例
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void designerCasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Designer designer = (Designer) session.getAttribute("designer");
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
		int total = commonService.queryCount("t_designer_case"); // 获取到数据总个数
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
		Pager<DesignerCase> pager = designerCaseService.queryDesignerCaseByDesignerId(pageNo, pageSize, designer.getId()); // 根据分页查询数据库
		List<DesignerCase> designerCases = pager.getResult();
		req.setAttribute("designerCases", designerCases);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/designers/designer_query_case.jsp").forward(req, resp);
	}
	
	/**
	 * 跳转到修改案例页面
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
	 *  修改案例逻辑处理
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
	 * 退出登录
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
