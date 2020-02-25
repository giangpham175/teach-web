package info.hotrogv.model;

public class KhoaHoc {
	private int idKhoaHoc;
	private String contactHeading;
	private String contactND;
	private int idGiaoVien;
	public KhoaHoc(){
		contactHeading = "";
		contactND = "";
		idGiaoVien = 0;
	}
	public KhoaHoc(String contactHeading2, String contactND2,int idGiaoVien) {
		this.contactHeading = contactHeading2;
		this.contactND = contactND2;
		this.idGiaoVien = idGiaoVien;
	}
	public String getContactHeading() {
		return contactHeading;
	}
	public void setContactHeading(String contactHeading) {
		this.contactHeading = contactHeading;
	}
	public String getContactND() {
		return contactND;
	}
	public void setContactND(String contactND) {
		this.contactND = contactND;
	}
	public int getIdGiaoVien() {
		return idGiaoVien;
	}
	public void setIdGiaoVien(int idGiaoVien) {
		this.idGiaoVien = idGiaoVien;
	}
	public int getIdKhoaHoc() {
		return idKhoaHoc;
	}
	public void setIdKhoaHoc(int idKhoaHoc) {
		this.idKhoaHoc = idKhoaHoc;
	}
	
}
