package com.wsc.bean;

import com.wsc.parentbean.BaseBean;

/**
 * ��Ʒ���̳���BaseBean
 * 
 * @author Administrator
 *
 */
public class Product extends BaseBean {

	private String supply_id; // �����̱��
	private String name; // ��Ʒ����
	private float price; // ��Ʒ�۸�
	private float sale_price; // ��Ʒ�ۿۺ�۸�
	private String image; // ��ƷͼƬ·��
	private String des; // ����
	private String status; // ״̬ Y �ϼ� N �¼�
	private boolean collected; // �Ƿ��ղ�
	private Supply supply; // ������

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
