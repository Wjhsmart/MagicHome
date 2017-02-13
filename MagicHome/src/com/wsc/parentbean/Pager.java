package com.wsc.parentbean;

import java.util.List;

public class Pager<T> {

	private int pageNo; // 第几页
	private int pageSize; // 每页显示多少条数据
	private int total; // 总页数
	private List<T> result; // 用来存放结果

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Pager [pageNo=" + pageNo + ", pageSize=" + pageSize + ", total=" + total + ", result=" + result + "]";
	}

}
