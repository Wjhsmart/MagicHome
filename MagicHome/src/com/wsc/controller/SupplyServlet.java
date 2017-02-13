package com.wsc.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.wsc.bean.Company;
import com.wsc.bean.Product;
import com.wsc.bean.Supply;
import com.wsc.bean.SupplyActivity;
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
import com.wsc.service.SupplyActivityService;
import com.wsc.service.SupplyActivityServiceImpl;
import com.wsc.service.SupplyService;
import com.wsc.service.SupplyServiceImpl;

public class SupplyServlet extends HttpServlet {

	private static final long serialVersionUID = -1599973430846322025L;
	
	private SupplyService supplyService;
	private HttpSession session;
	private CommonService commonService;
	private ProductService productService;
	private SupplyActivityService supplyActivityService;
	private CompanyService companyService;
	private DesignerService designerService;
	private CompanyCaseService companyCaseService;
	private DesignerCaseService designerCaseService;
	
	public SupplyServlet() {
		supplyService = new SupplyServiceImpl();
		commonService = new CommonServiceImpl();
		productService = new ProductServiceImpl();
		supplyActivityService = new SupplyActivityServiceImpl();
		companyService = new CompanyServiceImpl();
		companyCaseService = new CompanyCaseServiceImpl();
		designerCaseService = new DesignerCaseServiceImpl();
		designerService = new DesignerServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getURIMethod(req);
		session = req.getSession();
		if (method.equals("register")){
			//注册逻辑处理
			register(req, resp);
		} else if (method.equals("login")){
			//登录逻辑处理
			login(req, resp);
		} else if (method.equals("supply_personal_page")) {
			//跳转到个人中心
			showSupplyPersonalPage(req, resp);
		} else if (method.equals("return_index_page")) {
			//返回主页
			showIndexPage(req, resp);
		} else if (method.equals("supply_update_pwd_page")) {
			//跳转到更改密码页
			showUpdatePwdPage(req, resp);
		} else if (method.equals("supply_update_pwd")) {
			//更改密码逻辑处理
			supplyUpdatePwd(req, resp);
		} else if (method.equals("supply_update_mes_page")) {
			//跳转到个人中心修改信息页
			showUpdateMesPage(req, resp);
		} else if (method.equals("supply_update_mes")) {
			//修改信息逻辑处理
			supplyUpdateMes(req, resp);
		} else if (method.equals("supply_product_page")) {
			//跳转到分页查看商品页
			showSupplyProductPage(req, resp);
		} else if (method.equals("supply_add_product_page")) {
			//跳转到添加商品页
			showSupplyAddProductPage(req, resp);
		} else if (method.equals("supply_add_product")){
			//添加商品处理逻辑
			supplyAddProduct(req, resp);
		} else if (method.equals("supply_update_product_page")) {
			//跳转到修改商品页
			showSupplyUpdateProductPage(req, resp);
		} else if (method.equals("supply_update_product")) {
			//修改商品处理逻辑
			supplyUpdateProduct(req, resp);
		} else if (method.equals("supply_activity_page")) {
			//跳转到分页查看活动页
			showSupplyActivityPage(req, resp);
		} else if(method.equals("supply_add_activity_page")) {
			//跳转到添加活动页
			showSupplyAddActivityPage(req, resp);
		} else if(method.equals("supply_add_activity")) {
			//添加活动处理逻辑
			supplyAddActivity(req, resp);
		} else if(method.equals("supply_delete_activity")) {
			//删除活动逻辑处理
			supplyDeleteActivity(req, resp);
		} else if (method.equals("supply_update_activity_page")) {
			//跳转到修改活动页
			showSupplyUpdateActivityPage(req, resp);
		} else if(method.equals("supply_update_activity")) {
			//修改活动逻辑处理
			supplyUpdateActivity(req, resp);
		} else if (method.equals("exit")) {
			// 退出登录
			exit(req, resp);
		}
		
	}

	/**
	 * 注册逻辑，注册成功跳转到登录页，失败重新回到注册页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String principal = req.getParameter("principal");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		String phone = req.getParameter("phone");
		String value = req.getParameter("radio");
		if (name != null && !name.equals("") && principal != null && !principal.equals("") && email != null && !email.equals("") && pwd != null && !pwd.equals("") && phone != null && !phone.equals("")) {
			if (value.equals("2")) {
				Supply supply = new Supply();
				WebUtil.setDivDisplay(req, "none", "block", "none", "", "checked='checked'");
				if (WebUtil.checkEmail(commonService, email, req, resp)) {
					if (pwd.length() < 6) {
						req.setAttribute("errorMes", "密码长度必须6位以上");
						req.getRequestDispatcher("/register.jsp").forward(req, resp);
					} else if (phone.length() != 11) {
						req.setAttribute("errorMes", "手机号码必须为11位数字");
						req.getRequestDispatcher("/register.jsp").forward(req, resp);
					} else {
						supply.setE_mail(email);
						supply.setPassword(EncryptUtil.encrypt(pwd));
						supply.setName(name);
						supply.setPrincipal(principal);
						supply.setLogo(Constants.DEFAULT_IMAGE);
						supply.setAddress("赣州 ");
						supply.setPhone(phone);
						supply.setTel("4006666");
						supply.setOpen_date(DateUtil.getDate());
						supply.setLongitude(116);
						supply.setLatitude(26.46f);
						supply.setDes("公司的介绍");
						supply.setCreated_time(DateUtil.getDate());
						supply.setChecked("N");
						supply.setChecked_time(DateUtil.getDate());
						supply.setLast_login_time(DateUtil.getDate());
						supply.setLogin_count(0);
						supply.setStatus("N");
						supplyService.addSupply(supply);
						req.setAttribute("succeed", "申请入驻成功，请等待管理员审核");
						req.getRequestDispatcher("/register.jsp").forward(req, resp);
						
					}
				} else {
					req.setAttribute("errorMes", "已存在该用户");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				}
			} else if (value.equals("1")) {
				Company company = new Company();
				WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
				if (WebUtil.checkEmail(commonService, email, req, resp)) {
					if (pwd.length() < 6) {
						req.setAttribute("errorMes", "密码长度必须6位以上");
						req.getRequestDispatcher("/register.jsp").forward(req, resp);
					} else if (phone.length() != 11) {
						req.setAttribute("errorMes", "手机号码必须为11位数字");
						req.getRequestDispatcher("/register.jsp").forward(req, resp);
					} else {
						company.setE_mail(email);
						company.setPassword(EncryptUtil.encrypt(pwd));
						company.setName(name);
						company.setPrincipal(principal);
						company.setLogo(Constants.DEFAULT_IMAGE);
						company.setAddress("赣州 ");
						company.setPhone(phone);
						company.setTel("4008888");
						company.setOpen_date(DateUtil.getDate());
						company.setLongitude(116);
						company.setLatitude(26.46f);
						company.setDes("公司的介绍");
						company.setCreated_time(DateUtil.getDate());
						company.setChecked("N");
						company.setChecked_time(DateUtil.getDate());
						company.setLast_login_time(DateUtil.getDate());
						company.setLogin_count(0);
						company.setStatus("N");
						supplyService.addCompany(company);
						req.setAttribute("succeed", "申请入驻成功，请等待管理员审核");
						req.getRequestDispatcher("/register.jsp").forward(req, resp);
					}
				} else {
					req.setAttribute("errorMes", "已存在该用户");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				}
				
			}
		} else {
			WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
			req.setAttribute("errorMes", "所有字段不能为空");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * 登录逻辑，成功转发到建材商管理页面，失败重新转发到登录页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pwd = EncryptUtil.encrypt(req.getParameter("pwd"));
		String value = req.getParameter("radio");
		Supply supply = supplyService.queryCurr(email, pwd);
		Company company = supplyService.queryCurrCom(email, pwd);
		if (value.equals("2")) {
			WebUtil.setDivDisplay(req, "none", "block", "none", "", "checked='checked'");
			if (email.equals(supply.getE_mail()) && pwd.equals(supply.getPassword())) {
				if (supply.getStatus().endsWith("Y")) {
					supply.setLast_login_time(DateUtil.getDate());
					supply.setLogin_count(supply.getLogin_count() + 1);
					supplyService.updateSupply(supply);
					session.setAttribute("supply", supply);
					req.getRequestDispatcher("/supplys/supply_index.jsp").forward(req, resp);
				} else {
					req.setAttribute("enterpriseErrorMes", "账号不可用");
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("enterpriseErrorMes", "账号密码有误");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} else if (value.equals("1")) {
			WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
			if (email.equals(company.getE_mail()) && pwd.equals(company.getPassword())) {
				if (company.getStatus().equals("Y")) {
					company.setLast_login_time(DateUtil.getDate());
					company.setLogin_count(company.getLogin_count() + 1);
					supplyService.updateCompany(company);
					session.setAttribute("company", company);
					req.getRequestDispatcher("/companys/company_index.jsp").forward(req, resp);
				} else {
					req.setAttribute("enterpriseErrorMes", "账号密码有误");
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("enterpriseErrorMes", "账号密码有误");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			
		}
		
	}

	/**
	 * 跳转到建材商个人中心
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showSupplyPersonalPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Supply supply = (Supply) session.getAttribute("supply");
		WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
		if (supply == null) {
			req.setAttribute("errorMes", "账号已失效，请重新登录");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("last_login_time", DateUtil.getDateStr(supply.getLast_login_time()));
			req.getRequestDispatcher("/supplys/supply_personal.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 跳转回主页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showIndexPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (session.getAttribute("supply") != null) {
			req.getRequestDispatcher("/supplys/supply_index.jsp").forward(req, resp);
		} else {
			WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
			req.setAttribute("designerErrorMes", "账号已失效，请重新登录");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * 跳转到修改密码页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdatePwdPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/supplys/supply_update_pwd.jsp").forward(req, resp);
	}
	
	/**
	 * 建材商修改密码逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void supplyUpdatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Supply supply = (Supply) session.getAttribute("supply");
		String pwd = EncryptUtil.encrypt(req.getParameter("pwd"));
		String oldPwd = EncryptUtil.encrypt(req.getParameter("oldPwd"));
		if (supply.getPassword().equals(oldPwd)) {
			supply.setPassword(pwd);
			supplyService.updateSupply(supply);
			req.setAttribute("succeed", "密码已经更改，下次登录请使用新密码");
			WebUtil.setDivDisplay(req, "none", "block", "none", "checked='checked'", "");
			req.getRequestDispatcher("/supplys/supply_update_pwd.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMes", "旧密码错误");
			req.getRequestDispatcher("/supplys/supply_update_pwd.jsp").forward(req, resp);
		}
	}

	/**
	 * 跳转到修改信息页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showUpdateMesPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/supplys/supply_update_mes.jsp").forward(req, resp);
	}

	/**
	 * 个人中心修改信息逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void supplyUpdateMes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Supply supply = (Supply) session.getAttribute("supply");
		String name = req.getParameter("name");
		String principal = req.getParameter("principal");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String tel = req.getParameter("tel");
		String open_dateStr = req.getParameter("open_date");
		Date open_date = Date.valueOf(open_dateStr);
		String longitudeStr = req.getParameter("longitude");
		float longitude = Float.valueOf(longitudeStr);
		String latitudeStr = req.getParameter("latitude");
		float latitude = Float.valueOf(latitudeStr);
		String des = req.getParameter("des");
		supply.setName(name);
		supply.setPrincipal(principal);
		supply.setAddress(address);
		supply.setPhone(phone);
		supply.setTel(tel);
		supply.setOpen_date(open_date);
		supply.setLongitude(longitude);
		supply.setLatitude(latitude);
		supply.setDes(des);
		supplyService.updateSupply(supply);
		resp.sendRedirect("supply_personal_page");
	}

	/**
	 * 跳转分页查看商品页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showSupplyProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Supply supply = (Supply) session.getAttribute("supply");
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
		int total = commonService.queryCountByTableId(supply.getId(), "supply_id", "t_product"); // 获取到数据总个数
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
		Pager<Product> pager = productService.queryBySupplyId(pageNo, pageSize, supply.getId());
		List<Product> products = pager.getResult();
		req.setAttribute("products", products);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/supplys/supply_query_product.jsp").forward(req, resp);
	}

	/**
	 * 跳转到添加商品页面
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showSupplyAddProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/supplys/supply_add_product.jsp").forward(req, resp);
	}

	/**
	 * 添加商品逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void supplyAddProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			Product product = new Product();
			try {
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for(FileItem fileItem : fileItems) {
					if(fileItem.isFormField()) {
						String fileName = fileItem.getFieldName();
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING);
						if (fileName.equals("supply_id")) {
							product.setSupply_id(fileValue);
						} else if (fileName.equals("name")) {
							product.setName(fileValue);
						} else if (fileName.equals("price")) {
							float price = 0;
							try {
								price = Float.valueOf(fileValue);
							}catch (NumberFormatException e) {
								
							}
							product.setPrice(price);
						} else if (fileName.equals("sale_price")) {
							if (fileValue != null) {
								float sale_price = 0;
								try {
									sale_price = Float.valueOf(fileValue);
								}catch (NumberFormatException e) {
									
								}
								product.setSale_price(sale_price);
							} else {
								
							}
						} else if (fileName.equals("des")) {
							product.setDes(fileValue);
						} else if (fileName.equals("status")) {
							product.setStatus(fileValue);
						}
						product.setCreated_time(DateUtil.getDate());
					}else {
						String fileName = fileItem.getName();
						String newFileName = UUID.randomUUID().toString() + ".jpg";
						if (fileName == null || fileName.equals("")) {
							product.setImage(Constants.DEFAULT_IMAGE);
						} else {
							String upload = WebUtil.mkUpload(req, "supplyProduct");
							FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
							product.setImage(WebUtil.SON_FILE + "/" + newFileName);
						}
						
					}
				}
				if (product.getSale_price() <= 0) {
					product.setSale_price(product.getPrice());
				}
				supplyService.addProduct(product);
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
		}
		resp.sendRedirect("supply_product_page");
	}

	/**
	 * 跳转到修改商品页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showSupplyUpdateProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Product product = supplyService.queryProById(id);
		req.setAttribute("product", product);
		req.getRequestDispatcher("/supplys/supply_update_product.jsp").forward(req, resp);
	}

	/**
	 * 修改商品处理逻辑
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void supplyUpdateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			Product product = supplyService.queryProById(req.getParameter("id"));
			String lastImg = product.getImage();
			try {
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for(FileItem fileItem : fileItems) {
					if(fileItem.isFormField()) {
						String fileName = fileItem.getFieldName();
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING);
						if (fileName.equals("name")) {
							product.setName(fileValue);
						} else if (fileName.equals("price")) {
							float price = 0;
							try {
								price = Float.valueOf(fileValue);
							}catch (NumberFormatException e) {
								
							}
							product.setPrice(price);
						} else if (fileName.equals("sale_price")) {
							float sale_price = 0;
							try {
								sale_price = Float.valueOf(fileValue);
							}catch (NumberFormatException e) {
								
							}
							product.setSale_price(sale_price);
						} else if (fileName.equals("des")) {
							product.setDes(fileValue);
						} else if (fileName.equals("status")) {
							product.setStatus(fileValue);
						} else if (fileName.equals("created_time")) {
							product.setCreated_time(Date.valueOf(fileValue));
						}
					}else {
						String fileName = fileItem.getName();
						String newFileName = UUID.randomUUID().toString() + ".jpg";
						if (fileName.equals("") || fileName == null) {
							product.setImage(lastImg);
						} else {
							String upload = WebUtil.mkUpload(req, "supply");
							FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
							product.setImage(WebUtil.SON_FILE + "/" + newFileName);
						}
						
					}
				}
				
				productService.updateProduct(product);
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
		}
		resp.sendRedirect("supply_product_page");
	}

	/**
	 * 跳转到查看活动页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showSupplyActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		Supply supply = (Supply) session.getAttribute("supply");
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
		int total = commonService.queryCountByTableId(supply.getId(), "supply_id", "t_supply_activity"); // 获取到数据总个数
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
		Pager<SupplyActivity> pager = supplyActivityService.queryBySupplyId(pageNo, pageSize, supply.getId());
		List<SupplyActivity> activitys = pager.getResult();
		req.setAttribute("activitys", activitys);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPageSize", pageSize);
		req.getRequestDispatcher("/supplys/supply_query_activity.jsp").forward(req, resp);
	}

	/**
	 * 跳转到添加活动页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showSupplyAddActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/supplys/supply_add_activity.jsp").forward(req, resp);
	}
	
	/**
	 * 添加活动逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void supplyAddActivity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			SupplyActivity activity = new SupplyActivity();
			
			try {
				List<FileItem> fileItems = fileUpload.parseRequest(req);
				for(FileItem fileItem : fileItems) {
					if(fileItem.isFormField()) {
						String fileName = fileItem.getFieldName();
						String fileValue = fileItem.getString(Constants.DEFAULT_CODING);
						if (fileName.equals("supply_id")) {
							activity.setSupply_id(fileValue);
						} else if (fileName.equals("name")) {
							activity.setName(fileValue);
						} else if (fileName.equals("des")) {
							activity.setDes(fileValue);
						}
						activity.setCreated_time(DateUtil.getDate());
					}else {
						String fileName = fileItem.getName();
						String newFileName = UUID.randomUUID().toString() + ".jpg";
						if (fileName == null || fileName.equals("")) {
							activity.setImage(Constants.DEFAULT_IMAGE);
						} else {
							String upload = WebUtil.mkUpload(req, "supplyActivity");
							FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(upload + "/" + newFileName));
							activity.setImage(WebUtil.SON_FILE + "/" + newFileName);
						}
					}
				}
				supplyService.addActivity(activity);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
		}
		req.setAttribute("succeed", "添加活动成功");
		req.getRequestDispatcher("/supplys/supply_add_activity.jsp").forward(req, resp);
	}
	/**
	 * 根据id删除活动
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void supplyDeleteActivity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		supplyService.deleteActByid(id);
		resp.sendRedirect("supply_activity_page");
	}

	/**
	 * 跳转到修改活动页
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showSupplyUpdateActivityPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		SupplyActivity activity = supplyService.queryActById(id);
		req.setAttribute("activity", activity);
		req.getRequestDispatcher("/supplys/supply_update_activity.jsp").forward(req, resp);
		
	}

	/**
	 * 修改活动逻辑处理
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void supplyUpdateActivity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setHeaderEncoding(Constants.DEFAULT_CODING);
			SupplyActivity activity = supplyService.queryActById(req.getParameter("id"));
			String lastImg = activity.getImage();
			try {
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
				
				supplyService.updateAct(activity);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
		}
		resp.sendRedirect("supply_activity_page");
	}

	/**
	 * 退出登录
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute("supply");
		WebUtil.setIndexData(req, productService, companyService, designerService, designerCaseService, companyCaseService, "");
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}

}
