package com.wsc.parentbean;

import java.util.Date;

/**
 * �����࣬���з����ߵĸ��࣬�̳���BaseBean
 * @author Administrator
 *
 */
public class ProviderBean extends UserBean {

	private String principal; // ������
	private String logo; // ��˾logoͼƬ·��
	private String address; // ��ַ
	private String tel; // �̶��绰
	private Date open_date; // ��������
	private float longitude; // ����
	private float latitude; // γ��
	private String des; // ����
	private String checked; // �Ƿ����  Y���ͨ���� Nδ���
	private Date checked_time; // ���ʱ��
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getOpen_date() {
		return open_date;
	}
	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public Date getChecked_time() {
		return checked_time;
	}
	public void setChecked_time(Date checked_time) {
		this.checked_time = checked_time;
	}
	@Override
	public String toString() {
		return "ProviderBean [principal=" + principal + ", logo=" + logo + ", address=" + address + ", tel=" + tel
				+ ", open_date=" + open_date + ", longitude=" + longitude + ", latitude=" + latitude + ", des=" + des
				+ ", checked=" + checked + ", checked_time=" + checked_time + "]";
	}
	
}
