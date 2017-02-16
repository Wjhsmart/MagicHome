package com.wsc.parentbean;

/**
 * 案例类，是所有案例类的父类，继承至BaseBean
 * @author Administrator
 *
 */
public class CaseBean extends BaseBean {

	private String name; // 案例名称
	private String plot_name; // 小区名称
	private String style; // 装修风格
	private String image1; // 图片路径1
	private String image2; // 路径2
	private String image3; // 路径3
	private String image4; // 路径4
	private String image5; // 路径5
	private String des; // 描述
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlot_name() {
		return plot_name;
	}
	public void setPlot_name(String plot_name) {
		this.plot_name = plot_name;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getImage5() {
		return image5;
	}
	public void setImage5(String image5) {
		this.image5 = image5;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "CaseBean [name=" + name + ", plot_name=" + plot_name + ", style=" + style + ", image1=" + image1
				+ ", image2=" + image2 + ", image3=" + image3 + ", image4=" + image4 + ", image5=" + image5 + ", des="
				+ des + "]";
	}
	
}
