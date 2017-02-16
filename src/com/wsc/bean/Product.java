package com.wsc.bean;

import com.wsc.parentbean.BaseBean;

/**
 * 商品表，继承至BaseBean
 * 
 * @author Administrator
 *
 */
public class Product extends BaseBean {

	private String supply_id; // 建材商编号
	private String name; // 商品名称
	private float price; // 商品价格
	private float sale_price; // 商品折扣后价格
	private String image; // 商品图片路径
	private String des; // 描述
	private String status; // 状态 Y 上架 N 下架
	private boolean collected; // 是否被收藏
	private Supply supply; // 建材商

	public boolean getCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	public String getSupply_id() {
		return supply_id;
	}

	public void setSupply_id(String supply_id) {
		this.supply_id = supply_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSale_price() {
		return sale_price;
	}

	public void setSale_price(float sale_price) {
		this.sale_price = sale_price;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Product [supply_id=" + supply_id + ", name=" + name + ", price=" + price + ", sale_price=" + sale_price
				+ ", image=" + image + ", des=" + des + ", status=" + status + "]";
	}

}
