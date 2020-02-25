package info.hotrogv.model;

import java.util.ArrayList;

public class BaiGiang {
	private int idBaiGiang;
	private String tenBaiGiang;
	private String video;
	private String mota;
	private int idKhoaHoc;
	private String tenKhoaHoc;
	private ArrayList<TaiLieu> tailieu = new ArrayList<>();
	private ArrayList<BinhLuan> binhluan = new ArrayList<>();
	public ArrayList<BinhLuan> getBinhluan() {
		return binhluan;
	}
	public void setBinhluan(ArrayList<BinhLuan> binhluan) {
		this.binhluan = binhluan;
	}
	public int getIdBaiGiang() {
		return idBaiGiang;
	}
	public void setIdBaiGiang(int idBaiGiang) {
		this.idBaiGiang = idBaiGiang;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public int getIdKhoaHoc() {
		return idKhoaHoc;
	}
	public void setIdKhoaHoc(int idKhoaHoc) {
		this.idKhoaHoc = idKhoaHoc;
	}
	public String getTenBaiGiang() {
		return tenBaiGiang;
	}
	public void setTenBaiGiang(String tenBaiGiang) {
		this.tenBaiGiang = tenBaiGiang;
	}
	public String getTenKhoaHoc() {
		return tenKhoaHoc;
	}
	public void setTenKhoaHoc(String tenKhoaHoc) {
		this.tenKhoaHoc = tenKhoaHoc;
	}
	public ArrayList<TaiLieu> getTailieu() {
		return tailieu;
	}
	public void setTailieu(ArrayList<TaiLieu> tailieu) {
		this.tailieu = tailieu;
	}
	
}
