package com.wsc.parentbean;

import java.util.List;

public class Pager<T> {

	private int pageNo; // �ڼ�ҳ
	private int pageSize; // ÿҳ��ʾ����������
	private int total; // ��ҳ��
	private List<T> result; // ������Ž��

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
