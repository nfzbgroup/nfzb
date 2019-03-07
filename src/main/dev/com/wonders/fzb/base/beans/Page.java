package com.wonders.fzb.base.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 4544131522428794149L;

	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final int MAX_PAGE_SIZE = 100;

	private int pageSize = 10;

	private int start;

	private int currentPageSize;

	private int totalSize;

	private List<T> result;

	private int currentPageNo;

	private int totalPageCount;

	public Page() {
		this(0, 0, 0, 10, new ArrayList<T>());
	}

	public Page(int start) {
		this(start, 10, -1, 10, new ArrayList<T>());
	}

	public Page(int start, int currentPageSize, int totalSize, int pageSize, List<T> data) {
		this.currentPageSize = currentPageSize;
		this.pageSize = pageSize;
		this.start = start;
		this.totalSize = totalSize;
		this.result = data;
		
		if(start -1 > 0)
			this.currentPageNo = ((start - 1) / pageSize + 1);
		else
			this.currentPageNo = 1;
		
		if ((totalSize == 0) && (currentPageSize == 0)) {
			this.currentPageNo = 1;
			this.totalPageCount = 1;
		}else{
			this.totalPageCount = ((totalSize + pageSize - 1) / pageSize);
		}
	}

	public List<T> getResult() {
		return this.result;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public boolean hasNextPage() {
		return getCurrentPageNo() < getTotalPageCount();
	}

	public boolean hasPreviousPage() {
		return getCurrentPageNo() > 1;
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		int end = getStart() + getCurrentPageSize() - 1;
		if (end < 0) {
			end = 0;
		}
		return end;
	}

	public int getStartOfPreviousPage() {
		return Math.max(this.start - this.pageSize, 1);
	}

	public int getStartOfNextPage() {
		return this.start + this.currentPageSize;
	}

	public static int getStartOfAnyPage(int pageNo) {
		return getStartOfAnyPage(pageNo, 10);
	}

	public static int getStartOfAnyPage(int pageNo, int pageSize) {
		int startIndex = (pageNo - 1) * pageSize + 1;
		if (startIndex < 1) {
			startIndex = 1;
		}
		return startIndex;
	}

	public int[] getNeighbouringPage(int size) {
		int left = this.currentPageNo - size;
		int right = this.currentPageNo + size;
		int begin = left > 0 ? left : 1;
		int end = right < this.totalPageCount ? right : this.totalPageCount;
		int[] num = new int[end - begin + 1];
		for (int i = 0; i < num.length; i++) {
			num[i] = (begin + i);
		}
		return num;
	}

	public int getCurrentPageSize() {
		return this.currentPageSize;
	}

	public int getTotalSize() {
		return this.totalSize;
	}

	public int getCurrentPageNo() {
		return this.currentPageNo;
	}

	public int getNextPageNo() {
		return this.currentPageNo + 1;
	}

	public int getPrevPageNo() {
		return hasPreviousPage() ? getCurrentPageNo() - 1 : getCurrentPageNo();
	}

	public int getTotalPageCount() {
		return this.totalPageCount;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		if (this.pageSize > 0) {
			this.totalPageCount = ((totalSize + this.pageSize - 1) / this.pageSize);
		}
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public void setCurrentPageSize(int currentPageSize) {
		this.currentPageSize = currentPageSize;
	}
}