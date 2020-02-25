package info.hotrogv.dbhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import info.hotrogv.model.KhoaHoc;
import info.hotrogv.model.SinhVienKhoaHoc;
import info.hotrogv.model.TaiKhoan;

public class SVKHDAO extends BaseDAO{
	private final static String INSERT_QUERY = "INSERT INTO sinhvienkhoahoc(sinhvienid,khoahocid) VALUES(?,?)";
	private final static String DELETE_QUERY = "DELETE from sinhvienkhoahoc WHERE sinhvienid=? AND khoahocid = ?";
	private final static String SELECT_QUERY = "SELECT taikhoan.* FROM taikhoan JOIN sinhvienkhoahoc ON taikhoan.TaiKhoanID = sinhvienkhoahoc.sinhvienid WHERE khoahocid=?";

	private final static String KIEMTRASV_QUERY = "SELECT* from sinhvienkhoahoc WHERE sinhvienid=? AND khoahocid = ?";

	public static boolean themMoiSinhVien(SinhVienKhoaHoc sv) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(INSERT_QUERY);
			stmt.setInt(1, sv.getMaSinhVien());
			stmt.setInt(2, sv.getMaKhoaHoc());
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
	public static boolean xoaSinhVien(SinhVienKhoaHoc sv) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(DELETE_QUERY);
			stmt.setInt(1, sv.getMaSinhVien());
			stmt.setInt(2, sv.getMaKhoaHoc());
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
	public static SinhVienKhoaHoc kiemTraSinhVien(SinhVienKhoaHoc sv) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(KIEMTRASV_QUERY);
			stmt.setInt(1, sv.getMaSinhVien());
			stmt.setInt(2, sv.getMaKhoaHoc());
			ResultSet rs = stmt.executeQuery();
			ArrayList<TaiKhoan> list = new ArrayList<>();
			SinhVienKhoaHoc tk = null;
			while (rs.next()) {
				tk = new SinhVienKhoaHoc();
	            tk.setMaKhoaHoc(rs.getInt("khoahocid"));
	            tk.setMaSinhVien(rs.getInt("sinhvienid"));
	        }
			return tk;
		} catch (SQLException e) {
			return null;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
	}
	public static ArrayList<TaiKhoan> layDanhSachSinhVien(int id) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SELECT_QUERY);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			ArrayList<TaiKhoan> list = new ArrayList<>();
			TaiKhoan tk = null;
			while (rs.next()) {
				tk = new TaiKhoan();
	            tk.setTaiKhoanID(rs.getInt("taikhoanid"));
	            tk.setEmail(rs.getString("email"));
	            tk.setSdt(rs.getString("sdt"));
	            tk.setTenHocVien(rs.getString("hoten"));
	            list.add(tk);
	        }
			return list;
		} catch (SQLException e) {
			return null;
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
