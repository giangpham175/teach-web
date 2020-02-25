package info.hotrogv.dbhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import info.hotrogv.model.KhoaHoc;
import info.hotrogv.model.TaiKhoan;

public class KhoaHocDAO extends BaseDAO {
	private final static String INSERT_QUERY = "INSERT INTO khoahoc(mota,idbaigiang,idgiaovien) VALUES(?,?,?)";
	private final static String SELECT_QUERY = "SELECT* FROM khoahoc WHERE idgiaovien = ? limit ?,?";
	private final static String SELECT_QUERY2 = "SELECT* FROM khoahoc limit ?,?";

	public static boolean themMoiKhoahoc(KhoaHoc kh) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(INSERT_QUERY);
			stmt.setString(1, kh.getContactHeading());
			stmt.setString(2, kh.getContactND());
			stmt.setInt(3, kh.getIdGiaoVien());
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
	public static int demSoRecord() throws SQLException {
		Connection con = getConnection();
		Statement stmt = null;
		int total = 0;
		try {
			stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) total FROM khoahoc");
			while (rs.next()) {
				total = rs.getInt("total");
	        }
			return total;
		} catch (SQLException e) {
			return 0;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
	}
	public static ArrayList<KhoaHoc> layDanhSachKhoaHoc(int id,int offset,int pagesize) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SELECT_QUERY);
			stmt.setInt(1, id);
			stmt.setInt(2, offset);
			stmt.setInt(3, pagesize);
			ResultSet rs = stmt.executeQuery();
			ArrayList<KhoaHoc> list = new ArrayList<>();
			KhoaHoc tk = null;
			while (rs.next()) {
				tk = new KhoaHoc();
	            tk.setIdKhoaHoc(rs.getInt("idkhoahoc"));
	            tk.setContactHeading(rs.getString("mota"));
	            tk.setContactND(rs.getString("idbaigiang"));
	            tk.setIdGiaoVien(rs.getInt("idgiaovien"));
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
	public static ArrayList<KhoaHoc> layDanhSachKhoaHoc(int offset,int pagesize) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SELECT_QUERY2);
			stmt.setInt(1, offset);
			stmt.setInt(2, pagesize);
			ResultSet rs = stmt.executeQuery();
			ArrayList<KhoaHoc> list = new ArrayList<>();
			KhoaHoc tk = null;
			while (rs.next()) {
				tk = new KhoaHoc();
	            tk.setIdKhoaHoc(rs.getInt("idkhoahoc"));
	            tk.setContactHeading(rs.getString("mota"));
	            tk.setContactND(rs.getString("idbaigiang"));
	            tk.setIdGiaoVien(rs.getInt("idgiaovien"));
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
