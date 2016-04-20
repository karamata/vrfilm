package com.edinnova.vrfilm.dao.repo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KPaging<T> implements Serializable {
	/** The page. */
	protected int page;
	
	/** The page size. */
	protected int pageSize;
	
	/** The total rows. */
	protected Integer totalRows;
	
	/** The list. */
	protected List<T> list = new ArrayList<T>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page > 0) {
			page = page - 1;
		}
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}