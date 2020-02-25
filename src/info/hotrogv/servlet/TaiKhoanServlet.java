package info.hotrogv.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.json.JSONObject;

import info.hotrogv.dbhelper.BaiGiangDAO;
import info.hotrogv.dbhelper.KhoaHocDAO;
import info.hotrogv.dbhelper.SVKHDAO;
import info.hotrogv.dbhelper.UserDAO;
import info.hotrogv.model.BaiGiang;
import info.hotrogv.model.GLOBALCONSTANT;
import info.hotrogv.model.KhoaHoc;
import info.hotrogv.model.SinhVienKhoaHoc;
import info.hotrogv.model.TaiKhoan;

/**
 * Servlet implementation class TaiKhoanServlet
 */
@WebServlet("/TaiKhoanServlet")
public class TaiKhoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaiKhoanServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("actionname");
		TaiKhoan tk = null;

		int userID =Integer.parseInt( request.getSession().getAttribute("taikhoanid").toString());
		try {
			if (action == null) {
				tk = UserDAO.layTaiKhoan(userID);
				request.setAttribute("taikhoan", tk);
				request.getRequestDispatcher("trangchinhsuathongtincanhan.jsp").forward(request, response);
			}else if (action.equals("getjson")) {
				tk = UserDAO.layTaiKhoan(Integer.parseInt(request.getParameter("id")),
						Integer.parseInt(request.getParameter("makhoahoc")));
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				JSONObject json = new JSONObject();
				try {
					if (tk != null) {
						json.put("result", 1);
						json.put("id", tk.getTaiKhoanID());
						json.put("name", tk.getTenHocVien());
						json.put("sdt", tk.getSdt());
						json.put("email", tk.getEmail());
					} else {
						json.put("result", 0);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.print(json.toString());
				out.flush();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (ServletFileUpload.isMultipartContent(request)) {

			int userID =Integer.parseInt( request.getSession().getAttribute("taikhoanid").toString());
			try {

				List<FileItem> multiparts = new ServletFileUpload(

						new DiskFileItemFactory()).parseRequest(request);
				FileItem file = null;
				TaiKhoan tk = new TaiKhoan();
				// get taikhoanid from session
				tk.setTaiKhoanID(userID);
				String actionName = null;
				for (FileItem item : multiparts) {

					if (!item.isFormField()) {
						file = item;
					} else {
						String fieldname = item.getFieldName();
						if (fieldname.equals("hoten")) {
							tk.setTenHocVien(item.getString());
							continue;
						}
						if (fieldname.equals("ngaysinh")) {
							tk.setNgaySinh(item.getString());
							continue;
						}
						if (fieldname.equals("gioitinh")) {
								tk.setGioiTinh(item.getString());
							continue;
						}
						if (fieldname.equals("diachi")) {
							tk.setAddress(item.getString());
							continue;
						}
						if (fieldname.equals("sdt")) {
							tk.setSdt(item.getString());
							continue;
						}
						if (fieldname.equals("email")) {
							tk.setEmail(item.getString());
							continue;
						}
						if (fieldname.equals("password")) {
							tk.setPassword(item.getString().trim());
							continue;
						}
						if (fieldname.equals("actionname")) {
							// get taikhoanid from session
							actionName = item.getString();
							continue;
						}
					}
				}

				if (actionName.equals("update")) {
					TaiKhoan tmp = UserDAO.layTaiKhoan(tk.getTaiKhoanID());
					tk.setHinhAnh(tmp.getHinhAnh());
					if (!file.getName().equals("") && tmp != null) {
						String name = new File(file.getName()).getName();
						name = "avatar" + tk.getTaiKhoanID() + name.substring(name.indexOf("."));
						String path = GLOBALCONSTANT.UPLOAD_PATH + File.separator + name;
						file.write(new File(path));
						tk.setHinhAnh(name);
					}
					if (tk != null) {
						if (tk.getPassword().equals("")) {
							tk.setPassword(tmp.getPassword());
						}
						UserDAO.capNhatTaiKhoan(tk);
						request.getSession().setAttribute("message", "Đã cập nhật thông tin tài khoản");
						request.getSession().setAttribute("taikhoan", tk);
						response.sendRedirect("taikhoan");
					}
				}

			} catch (Exception ex) {

				request.getSession().setAttribute("message", "File Upload Failed due to " + ex);

			}

		} else {
			String actionName = request.getParameter("actionname");
			if (actionName.equals("login")) {
				String taikhoan = request.getParameter("tentaikhoan");
				String pw = request.getParameter("password");
				TaiKhoan tk;
				try {
					tk = UserDAO.layTaiKhoan(taikhoan);
					if(tk!=null&&tk.getPassword().equals(pw)){
						request.getSession().setAttribute("taikhoanid", tk.getTaiKhoanID());
						request.getSession().setAttribute("tentaikhoan", tk.getTenTaiKhoan());
						request.getSession().setAttribute("quyen", tk.getQuyen());
						response.sendRedirect("khoahoc");
					}else{

						request.setAttribute("message", "Sai tai khoan hoac mat khau");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					request.setAttribute("message", "Sai tai khoan hoac mat khau");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else if (actionName.equals("dangky")) {
				String hoten = request.getParameter("fullname");
				String tentaikhoan = request.getParameter("username");
				String email = request.getParameter("email");
				String sdt = request.getParameter("phone");
				String password = request.getParameter("password");
				
				TaiKhoan tk = new TaiKhoan();
				
				
				tk.setTenHocVien(hoten);
				tk.setTenTaiKhoan(tentaikhoan);
				tk.setEmail(email);
				tk.setSdt(sdt);
				tk.setPassword(password);
				tk.setQuyen("SV");
				
				
				try {
					//replace this userID by login id get from  session
					UserDAO.themTaiKhoan(tk);
					request.setAttribute("message", "Đã thêm mới tài khoản mới");
					response.sendRedirect("login.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Đã xảy ra lỗi vui lòng kiểm tra lại");
					e.printStackTrace();
				}
			}
		} 
		
		
		// request.getRequestDispatcher("trangchinhsuathongtincanhan.jsp").forward(request,
		// response);
	}

}
