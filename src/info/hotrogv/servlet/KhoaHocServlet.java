package info.hotrogv.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.hotrogv.dbhelper.KhoaHocDAO;
import info.hotrogv.dbhelper.SVKHDAO;
import info.hotrogv.model.KhoaHoc;

/**
 * Servlet implementation class GiaoVienServlet
 */
@WebServlet("/GiaoVienServlet")
public class KhoaHocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhoaHocServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private int pagesize = 6;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int page = 1;
	        if(request.getParameter("page") != null)
	            page = Integer.parseInt(request.getParameter("page"));
	        int userID =Integer.parseInt( request.getSession().getAttribute("taikhoanid").toString());
	        String quyen =request.getSession().getAttribute("quyen").toString();
	        if(quyen.equalsIgnoreCase("GV")){
				ArrayList<KhoaHoc> list = KhoaHocDAO.layDanhSachKhoaHoc(userID,(page-1)*pagesize,pagesize);
				request.setAttribute("dskh", list);
				int totalpage= (int) Math.ceil(KhoaHocDAO.demSoRecord() * 1.0 / pagesize);
				request.setAttribute("totalpage", totalpage);
				request.setAttribute("curpage", page);
	        }else{
				ArrayList<KhoaHoc> list = KhoaHocDAO.layDanhSachKhoaHoc((page-1)*pagesize,pagesize);
				request.setAttribute("dskh", list);
				int totalpage= (int) Math.ceil(KhoaHocDAO.demSoRecord() * 1.0 / pagesize);
				request.setAttribute("totalpage", totalpage);
				request.setAttribute("curpage", page);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("trangkhoahocGV.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID =Integer.parseInt( request.getSession().getAttribute("taikhoanid").toString());
		String contactHeading = request.getParameter("contactHeading");
		String contactND = request.getParameter("contactND");
		try {
			//replace this userID by login id get from  session
			KhoaHocDAO.themMoiKhoahoc(new KhoaHoc(contactHeading,contactND,userID));
			request.setAttribute("message", "Đã thêm mới khóa học");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("message", "Đã xảy ra lỗi vui lòng kiểm tra lại");
			e.printStackTrace();
		}
		//change target page here to redirect
		//RequestDispatcher view = request.getRequestDispatcher("trangkhoahocGV.jsp");
		//view.forward(request, response);
		try {
			request.setAttribute("dskh", KhoaHocDAO.layDanhSachKhoaHoc(userID,0,pagesize));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("trangkhoahocGV.jsp").forward(request, response);
	}



}
