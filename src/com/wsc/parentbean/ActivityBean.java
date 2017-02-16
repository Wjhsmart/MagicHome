package com.wsc.parentbean;

/**
 * 活动类，是所有活动类型的父类，继承至BaseBean
 * @author Administrator
 *
 */
public class ActivityBean extends BaseBean {

	private String name; // 活动名称
	private String image; // 活动图片路径
	private String des; // 描述
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
