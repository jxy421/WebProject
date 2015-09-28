package com.netctoss.entity;

public class Page {
	//��ʾ�ڼ�ҳ���ݣ�Ĭ�ϵ�һҳ
	private Integer page = 1;
	//һҳĬ����ʾ5��
	private Integer pageSize = 5;//��Ա������pageSize
	//���ҳ��
	private Integer totalPage = 1;
	
	public Page() {
		super();
	}
	public Page(Integer page, Integer pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	//getter������begin
	public Integer getBegin(){
		return (page-1)*pageSize;
	}
	//setter������begin
	public Integer getPage() {
		return page;
	}
	
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
