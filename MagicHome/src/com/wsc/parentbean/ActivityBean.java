package com.wsc.parentbean;

/**
 * ��࣬�����л���͵ĸ��࣬�̳���BaseBean
 * @author Administrator
 *
 */
public class ActivityBean extends BaseBean {

	private String name; // �����
	private String image; // �ͼƬ·��
	private String des; // ����
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "ActivityBean [name=" + name + ", image=" + image + ", des=" + des + "]";
	}
	
	
}
