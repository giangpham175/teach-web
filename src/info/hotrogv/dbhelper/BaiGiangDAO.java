package info.hotrogv.dbhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import info.hotrogv.model.BaiGiang;
import info.hotrogv.model.BinhLuan;
import info.hotrogv.model.KhoaHoc;
import info.hotrogv.model.SinhVienKhoaHoc;
import info.hotrogv.model.TaiLieu;

public class BaiGiangDAO extends BaseDAO {
	private final static String INSERT_QUERY = "INSERT INTO baigiang(tenbaigiang,video,mota,idkhoahoc) VALUES(?,?,?,?)";
	private final static String INSERT_TAILIEU_QUERY = "INSERT INTO tailieu(tieude,mota,file,idbaigiang) VALUES(?,?,?,?)";
	private final static String INSERT_BINHLUAN_QUERY = "INSERT INTO comment(noidung,taikhoanid,tentaikhoan,idbaigiang) VALUES(?,?,?,?)";
	private final static String UPDATE_QUERY = "UPDATE baigiang SET mota = ?, video = ? WHERE idbaigiang = ?";
	private final static String SELECT_BINHLUAN_QUERY = "SELECT* FROM comment WHERE idbaigiang = ?";
	private final static String SELECT_ALL_BAIGIANG = "SELECT baigiang.idbaigiang,tenbaigiang,video,baigiang.mota,baigiang.idkhoahoc,khoahoc.mota as tenkhoahoc FROM baigiang JOIN khoahoc ON baigiang.idkhoahoc = khoahoc.idkhoahoc WHERE baigiang.idkhoahoc = ?";
	private final static String SELECT_BAIGIANG = "SELECT *FROM baigiang WHERE baigiang.idbaigiang = ?";
	private final static String SELECT_TAILIEU = "SELECT *FROM tailieu WHERE idbaigiang = ?";
	private final static String DELETE_TAILIEU = "DELETE FROM tailieu WHERE tailieuid=?";
	private final static String DELETE_BAIGIANG = "DELETE FROM baigiang WHERE idbaigiang=?";
	
	public static boolean xoaBaiGiang(int id) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(DELETE_BAIGIANG);
			stmt.setInt(1, id);
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

	public static boolean themTaiLieu(String tieude, String mota, String file, String baigiangid) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(INSERT_TAILIEU_QUERY);
			stmt.setString(1, tieude);
			stmt.setString(2, mota);
			stmt.setString(3, file);
			stmt.setString(4, baigiangid);
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

	public static boolean themBinhLuan(BinhLuan bl) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(INSERT_BINHLUAN_QUERY);
			stmt.setString(1, bl.getNoidung());
			stmt.setInt(2, bl.getTaikhoanid());
			stmt.setString(3, bl.getTentaikhoan());
			stmt.setInt(4, bl.getIdbaigiang());
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

	public static boolean xoaTaiLieu(int id) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(DELETE_TAILIEU);
			stmt.setInt(1, id);
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

	public static boolean capNhatBaiGiang(BaiGiang bg) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(UPDATE_QUERY);
			stmt.setString(1, bg.getMota());
			stmt.setString(2, bg.getVideo());
			stmt.setInt(3, bg.getIdBaiGiang());
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

			stmt = con.prepareStatement(SELECT_TAILIEU);
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
			// get binh luan
			stmt.close();

			stmt = con.prepareStatement(SELECT_BINHLUAN_QUERY);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			ArrayList<BinhLuan> listBl = new ArrayList<>();
			BinhLuan bl = null;
			while (rs.next()) {
				bl = new BinhLuan();
				bl.setId(rs.getInt("id"));
				bl.setNoidung(rs.getString("noidung"));
				bl.setTaikhoanid(rs.getInt("taikhoanid"));
				bl.setTentaikhoan(rs.getString("tentaikhoan"));
				listBl.add(bl);
			}
			bg.setBinhluan(listBl);
			return bg;
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

	public static ArrayList<BaiGiang> layDanhSachBaiGiang(int id) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SELECT_ALL_BAIGIANG);
			stmt.setInt(1, id);
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
			if (con != null) {
				con.close();
			}
		}
	}
}
