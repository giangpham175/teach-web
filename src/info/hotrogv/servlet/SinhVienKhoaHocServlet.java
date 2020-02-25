package info.hotrogv.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import info.hotrogv.dbhelper.SVKHDAO;
import info.hotrogv.model.SinhVienKhoaHoc;

/**
 * Servlet implementation class SinhVienKhoaHoc
 */
@WebServlet("/SinhVienKhoaHoc")
public class SinhVienKhoaHocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SinhVienKhoaHocServlet() {
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
			try {
				request.setAttribute("dssv", SVKHDAO.layDanhSachSinhVien(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("makhoahoc", request.getParameter("id"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("chitietkhoahoc.jsp").forward(request, response);

	}

	// <c:remove var="message" scope="session" />
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String actionName = request.getParameter("actionname");
			if (actionName.equals("add")) {
				SVKHDAO.themMoiSinhVien(new SinhVienKhoaHoc(Integer.parseInt(request.getParameter("userid")),
						Integer.parseInt(request.getParameter("makhoahoc"))));
				request.setAttribute("message", "Đã thêm");
			} else {
				SVKHDAO.xoaSinhVien(new SinhVienKhoaHoc(Integer.parseInt(request.getParameter("userid")),
						Integer.parseInt(request.getParameter("makhoahoc"))));
				request.setAttribute("message", "Đã xóa");
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Lỗi");
		}
		try {
			request.setAttribute("dssv",
					SVKHDAO.layDanhSachSinhVien(Integer.parseInt(request.getParameter("makhoahoc"))));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("makhoahoc", request.getParameter("makhoahoc"));
		request.getRequestDispatcher("/chitietkhoahoc.jsp").forward(request, response);
	}

}
