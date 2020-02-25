package info.hotrogv.model;

public class SinhVienKhoaHoc {
	private int maSinhVien;
	private int maKhoaHoc;
	
	public SinhVienKhoaHoc() {
		this.maSinhVien = 0;
		this.maKhoaHoc = 0;
	}
	
	public SinhVienKhoaHoc(int maSinhVien, int maKhoaHoc) {
		this.maSinhVien = maSinhVien;
		this.maKhoaHoc = maKhoaHoc;
	}
	public int getMaSinhVien() {
		return maSinhVien;
	}
	public void setMaSinhVien(int maSinhVien) {
		this.maSinhVien = maSinhVien;
	}
	public int getMaKhoaHoc() {
		return maKhoaHoc;
	}
	public void setMaKhoaHoc(int maKhoaHoc) {
		this.maKhoaHoc = maKhoaHoc;
	}
	
}
