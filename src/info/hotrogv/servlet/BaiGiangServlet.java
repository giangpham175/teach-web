package info.hotrogv.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import info.hotrogv.dbhelper.BaiGiangDAO;
import info.hotrogv.dbhelper.KhoaHocDAO;
import info.hotrogv.dbhelper.SVKHDAO;
import info.hotrogv.model.BaiGiang;
import info.hotrogv.model.BinhLuan;
import info.hotrogv.model.GLOBALCONSTANT;
import info.hotrogv.model.SinhVienKhoaHoc;
import info.hotrogv.model.TaiKhoan;

/**
 * Servlet implementation class BaiGiangServle
 */
@WebServlet("/BaiGiangServle")
public class BaiGiangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaiGiangServlet() {
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
		String action = request.getParameter("action").toString();
		if (action != null && action.equals("dsbg")) {
			try {
				int idKhoaHoc = Integer.parseInt(request.getParameter("id").toString());
				request.setAttribute("dsbg", BaiGiangDAO.layDanhSachBaiGiang(idKhoaHoc));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("khoahocid", request.getParameter("id").toString());
			request.getRequestDispatcher("danhsachbaigiang.jsp").forward(request, response);
		} else if (action != null && action.equals("chitietbaigiang")) {
			try {
				int idBaiGiang = Integer.parseInt(request.getParameter("id").toString());
				if(!request.getSession().getAttribute("quyen").toString().equalsIgnoreCase("GV")){
					SinhVienKhoaHoc tmp = new SinhVienKhoaHoc();
					tmp.setMaSinhVien(Integer.parseInt(request.getSession().getAttribute("taikhoanid").toString()));
					tmp.setMaKhoaHoc(idBaiGiang);
					SinhVienKhoaHoc tk = SVKHDAO.kiemTraSinhVien(tmp);
					if(tk==null){
						response.sendRedirect("trangdangkykhoahoc.jsp");
						return;
					}
				}
				request.setAttribute("baigiang", BaiGiangDAO.layThongTinBaiGiang(idBaiGiang));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("trangchitietbaigianggv.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idBaiGiang = null, mota = null;
		String actionname = "";
		String tailieuid = null;
		String tieude = null;
		String noidung = null;
		String khoahocid = null;
		int userID =Integer.parseInt( request.getSession().getAttribute("taikhoanid").toString());
		String tenTaiKhoan =request.getSession().getAttribute("tentaikhoan").toString();
		if (ServletFileUpload.isMultipartContent(request)) {

			try {

				List<FileItem> multiparts = new ServletFileUpload(

						new DiskFileItemFactory()).parseRequest(request);
				FileItem file = null;
				for (FileItem item : multiparts) {

					if (!item.isFormField()) {
						file = item;

					} else {
						String fieldname = item.getFieldName();
						if (fieldname.equals("idbaigiang")) {
							idBaiGiang = item.getString();
						}
						if (fieldname.equals("khoahocid")) {
							khoahocid = item.getString();
						}
						if (fieldname.equals("mota")) {
							mota = item.getString();
						}
						if (fieldname.equals("tieude")) {
							tieude = item.getString();
						}
						if (fieldname.equals("actionname")) {
							actionname = item.getString();
						}
						if (fieldname.equals("tailieuid")) {
							tailieuid = item.getString();
						}
					}
				}
				BaiGiang bg = null;
				if(idBaiGiang!=null){
				request.setAttribute("idbaigiang", idBaiGiang);
				bg = BaiGiangDAO.layThongTinBaiGiang(Integer.parseInt(idBaiGiang));
				}
				if (actionname.equals("deletetailieu")) {
					if (tailieuid != null) {
						BaiGiangDAO.xoaTaiLieu(Integer.parseInt(tailieuid));
						request.setAttribute("message", "Ä�aÌƒ xoÌ�a");
					} else
						request.setAttribute("message", "ThÃ´ng tin khÃ´ng hÆ¡Ì£p lÃªÌ£");
				} else if (actionname.equals("add")) {
					bg = new BaiGiang();
						if (file != null) {
							String name = new File(file.getName()).getName();
							name = "video1" + name.substring(name.indexOf("."));
							File varTmpDir = new File(name);
							boolean exists = varTmpDir.exists();
							String uuid = null;
							while (exists) {
								uuid = UUID.randomUUID().toString();
								name = "video" + uuid + name.substring(name.indexOf("."));
								exists = varTmpDir.exists();
							}
							file.write(new File(GLOBALCONSTANT.UPLOAD_PATH + File.separator + name));
							bg.setVideo(name);
						}
						bg.setTenBaiGiang(tieude);
						bg.setIdKhoaHoc(Integer.parseInt(khoahocid));
						bg.setMota(mota);
					BaiGiangDAO.themMoiBaiGiang(bg);
					request.setAttribute("message", "Ä�aÌƒ thÃªm mÆ¡Ì�i baÌ€i giaÌ‰ng");
				}  else if (actionname.equals("update")) {
					if (bg != null) {
						if (file != null) {
							String name = new File(file.getName()).getName();
							name = "video" + idBaiGiang + name.substring(name.indexOf("."));
							file.write(new File(GLOBALCONSTANT.UPLOAD_PATH + File.separator + name));
							bg.setVideo(name);
						}
						bg.setMota(mota);
					}
					BaiGiangDAO.capNhatBaiGiang(bg);
					request.setAttribute("message", "Cap nhat bai giang thanh cong");
				} else if (actionname.equals("addtailieu")) {
					String url = "";
					if (bg != null) {
						if (file != null) {
							String name = new File(file.getName()).getName();
							String uuid = UUID.randomUUID().toString();
							name = "tailieu" + uuid + name.substring(name.indexOf("."));
							File varTmpDir = new File(name);
							boolean exists = varTmpDir.exists();
							while (exists) {
								uuid = UUID.randomUUID().toString();
								name = "tailieu" + uuid + name.substring(name.indexOf("."));
								exists = varTmpDir.exists();
							}
							file.write(new File(GLOBALCONSTANT.UPLOAD_PATH + File.separator + name));

							url = name;
						}
					}
					BaiGiangDAO.themTaiLieu(tieude, mota, url, idBaiGiang);
					request.setAttribute("message", "Ä�aÌƒ thÃªm mÆ¡Ì�i taÌ€i liÃªÌ£u");
				}
				// File uploaded successfully

				//request.setAttribute("baigiang", BaiGiangDAO.layThongTinBaiGiang(Integer.parseInt(idBaiGiang)));
				//request.setAttribute("message", "File Uploaded Successfully");

			} catch (Exception ex) {

				request.setAttribute("message", "File Upload Failed due to " + ex);

			}

		} else {
			actionname = request.getParameter("actionname");
			idBaiGiang = request.getParameter("idbaigiang");
			if (actionname.equals("binhluan")) {
				noidung = request.getParameter("noidung");
				BinhLuan bl = new BinhLuan();
				bl.setNoidung(noidung);
				bl.setTaikhoanid(userID);
				bl.setTentaikhoan(tenTaiKhoan);
				bl.setIdbaigiang(Integer.parseInt(idBaiGiang));
				try {
					BaiGiangDAO.themBinhLuan(bl);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(actionname.equals("delete")){
				try {
					khoahocid = request.getParameter("khoahocid");
					BaiGiangDAO.xoaBaiGiang(Integer.parseInt(idBaiGiang));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		//request.getRequestDispatcher("trangchitietbaigianggv.jsp").forward(request, response);
		if(actionname.equals("add")||actionname.equals("delete")){
			response.sendRedirect("baigiang?action=dsbg&id="+khoahocid);
		}else
		response.sendRedirect("baigiang?action=chitietbaigiang&id="+idBaiGiang);

	}

}
