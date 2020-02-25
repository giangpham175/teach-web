package info.hotrogv.dbhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import info.hotrogv.model.BaiGiang;
import info.hotrogv.model.KhoaHoc;
import info.hotrogv.model.TaiLieu;

public class TaiLieuDAO extends BaseDAO {
	private final static String INSERT_QUERY = "INSERT INTO baigiang(tenbaigiang,video,mota,idkhoahoc) VALUES(?,?,?,?)";
	private final static String SELECT_QUERY = "SELECT* FROM khoahoc WHERE idgiaovien = ?";
	private final static String SELECT_ALL_BAIGIANG = "SELECT baigiang.idbaigiang,tenbaigiang,video,baigiang.mota,baigiang.idkhoahoc,khoahoc.mota as tenkhoahoc FROM baigiang JOIN khoahoc ON baigiang.idkhoahoc = khoahoc.idkhoahoc WHERE baigiang.idkhoahoc = ?";
	private final static String SELECT_BAIGIANG = "SELECT *FROM baigiang WHERE baigiang.idbaigiang = ?";
	private final static String SELECT_ALL_TAILIEU_BY_BAIGIANGID = "SELECT *FROM tailieu WHERE idbaigiang = ?";
	
	public static boolean themMoiBaiGiang(BaiGiang bg) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(INSERT_QUERY);
			stmt.setString(1, bg.getTenBaiGiang());
			stmt.setString(2, bg.getVideo());
			stmt.setString(3, bg.getMota());
			stmt.setInt(4, bg.getIdKhoaHoc());
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
	public static boolean capNhatBaiGiang(BaiGiang bg) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(INSERT_QUERY);
			stmt.setString(1, bg.getTenBaiGiang());
			stmt.setString(2, bg.getVideo());
			stmt.setString(3, bg.getMota());
			stmt.setInt(4, bg.getIdKhoaHoc());
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

	public static BaiGiang layThongTinBaiGiang(int id) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SELECT_BAIGIANG);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			BaiGiang bg = null;
			while (rs.next()) {
				bg = new BaiGiang();
	            bg.setIdBaiGiang(rs.getInt("idBaiGiang"));
	            bg.setMota(rs.getString("mota"));
	            bg.setVideo(rs.getString("video"));
	            bg.setTenBaiGiang(rs.getString("tenbaigiang"));
	        }
			stmt.close();
			
			stmt = con.prepareStatement(SELECT_ALL_TAILIEU_BY_BAIGIANGID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			ArrayList<TaiLieu> listTl = new ArrayList<>();
			TaiLieu tl = null;
			while (rs.next()) {
				tl = new TaiLieu();
	            tl.setTaiLieuID(rs.getInt("tailieuid"));
	            tl.setMoTa(rs.getString("mota"));
	            tl.setFile(rs.getString("file"));
	            tl.setTieuDe(rs.getString("tieude"));
	            tl.setIdBaiGiang(rs.getInt("idbaigiang"));
	            listTl.add(tl);
	        }
			bg.setTailieu(listTl);
			return bg;
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
	
	public static ArrayList<BaiGiang> layDanhSachTaiLieu(int baigiangId) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SELECT_ALL_TAILIEU_BY_BAIGIANGID);
			stmt.setInt(1, baigiangId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<BaiGiang> list = new ArrayList<>();
			BaiGiang bg = null;
			while (rs.next()) {
				bg = new BaiGiang();
	            bg.setIdBaiGiang(rs.getInt("idBaiGiang"));
	            bg.setMota(rs.getString("mota"));
	            bg.setVideo(rs.getString("video"));
	            bg.setTenBaiGiang(rs.getString("tenbaigiang"));
	            bg.setTenKhoaHoc(rs.getString("tenkhoahoc"));
	            list.add(bg);
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
