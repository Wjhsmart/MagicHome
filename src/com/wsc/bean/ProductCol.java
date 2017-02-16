package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * 商品收藏表，继承至收藏表
 * @author Administrator
 *
 */
public class ProductCol extends CollectBean {
	
	private String product_id; // 建材编号
	private Product product; // 商品

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "ProductCol [product_id=" + product_id + "]";
	}

}
