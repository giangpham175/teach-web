package info.hotrogv.dbhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import info.hotrogv.model.KhoaHoc;
import info.hotrogv.model.SinhVienKhoaHoc;
import info.hotrogv.model.TaiKhoan;

public class UserDAO extends BaseDAO {
	private final static String SELECT_QUERY = "SELECT * FROM taikhoan where taikhoanid=? and quyen='SV' AND taikhoanid NOT IN(SELECT sinhvienid FROM sinhvienkhoahoc WHERE khoahocid = ?)";
	private final static String SELECT_USER_QUERY = "SELECT * FROM taikhoan where taikhoanid=?";
	private final static String SELECT_USER_QUERY2 = "SELECT * FROM taikhoan where tentaikhoan=?";
	private final static String UPDATE_USER_QUERY = "UPDATE taikhoan SET hoten=?,hinhanh=?,ngaysinh=?,gioitinh=?,diachi=?,sdt=?,email=?,matkhau=? WHERE taikhoanid=?";
	private final static String INSERT_QUERY = "INSERT INTO taikhoan(hoten,tentaikhoan,email,sdt,matkhau,quyen) VALUES(?,?,?,?,?,?)";

	public static boolean capNhatTaiKhoan(TaiKhoan tk) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(UPDATE_USER_QUERY);
			stmt.setString(1, tk.getTenHocVien());
			stmt.setString(2, tk.getHinhAnh());
			stmt.setString(3, tk.getNgaySinh());
			stmt.setString(4, tk.getGioiTinh());
			stmt.setString(5, tk.getAddress());
			stmt.setString(6, tk.getSdt());
			stmt.setString(7, tk.getEmail());
			stmt.setString(8, tk.getPassword());
			stmt.setInt(9, tk.getTaiKhoanID());
			int result = stmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			return false;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static TaiKhoan layTaiKhoan(int id) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SELECT_USER_QUERY);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			TaiKhoan tk = null;
			while (rs.next()) {
				tk = new TaiKhoan();
				tk.setTaiKhoanID(rs.getInt("taikhoanid"));
				tk.setEmail(rs.getString("email"));
				tk.setSdt(rs.getString("sdt"));
				tk.setTenHocVien(rs.getString("hoten"));
				tk.setAddress(rs.getString("diachi"));
				tk.setGioiTinh(rs.getString("gioitinh"));
				tk.setPassword(rs.getString("matkhau"));
				tk.setHinhAnh(rs.getString("hinhanh"));
				tk.setNgaySinh(rs.getString("ngaysinh"));
			}
			return tk;
		} catch (SQLException e) {
			return null;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	public static TaiKhoan layTaiKhoan(String tentaikhoan) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SELECT_USER_QUERY2);
			stmt.setString(1, tentaikhoan);
			ResultSet rs = stmt.executeQuery();
			TaiKhoan tk = null;
			while (rs.next()) {
				tk = new TaiKhoan();
				tk.setTaiKhoanID(rs.getInt("taikhoanid"));
				tk.setTenTaiKhoan(rs.getString("tentaikhoan"));
				tk.setEmail(rs.getString("email"));
				tk.setSdt(rs.getString("sdt"));
				tk.setTenHocVien(rs.getString("hoten"));
				tk.setAddress(rs.getString("diachi"));
				tk.setGioiTinh(rs.getString("gioitinh"));
				tk.setPassword(rs.getString("matkhau"));
				tk.setHinhAnh(rs.getString("hinhanh"));
				tk.setNgaySinh(rs.getString("ngaysinh"));
				tk.setQuyen(rs.getString("quyen"));
			}
			return tk;
		} catch (SQLException e) {
			return null;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	public static TaiKhoan layTaiKhoan(int id, int khoahocid) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SELECT_QUERY);
			stmt.setInt(1, id);
			stmt.setInt(2, khoahocid);
			ResultSet rs = stmt.executeQuery();
			TaiKhoan tk = null;
			while (rs.next()) {
				tk = new TaiKhoan();
				tk.setTaiKhoanID(rs.getInt("taikhoanid"));
				tk.setEmail(rs.getString("email"));
				tk.setSdt(rs.getString("sdt"));
				tk.setTenHocVien(rs.getString("hoten"));
			}
			return tk;
		} catch (SQLException e) {
			return null;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public static boolean themTaiKhoan(TaiKhoan tk) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(INSERT_QUERY);
			stmt.setString(1, tk.getTenHocVien());
			stmt.setString(2, tk.getTenTaiKhoan());
			stmt.setString(3, tk.getEmail());
			stmt.setString(4, tk.getSdt());
			stmt.setString(5, tk.getPassword());
			stmt.setString(6,tk.getQuyen());
			
			int result = stmt.executeUpdate();
			return result >0;
		} catch (SQLException e) {
			return false;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
	}
	
}
