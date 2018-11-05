package cn.bj.pojo;

import java.io.Serializable;

public class Mobile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String   mobilenumber;
	private String  mobilearea;
	private String  mobiletype;
	private int  areacode;
	private int  postcode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getMobilearea() {
		return mobilearea;
	}
	public void setMobilearea(String mobilearea) {
		this.mobilearea = mobilearea;
	}
	public String getMobiletype() {
		return mobiletype;
	}
	public void setMobiletype(String mobiletype) {
		this.mobiletype = mobiletype;
	}
	public int getAreacode() {
		return areacode;
	}
	public void setAreacode(int areacode) {
		this.areacode = areacode;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Mobile [id=" + id + ", mobilenumber=" + mobilenumber + ", mobilearea=" + mobilearea + ", mobiletype="
				+ mobiletype + ", areacode=" + areacode + ", postcode=" + postcode + "]";
	}
	
	
}
