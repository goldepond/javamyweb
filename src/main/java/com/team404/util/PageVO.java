package com.team404.util;

import lombok.Data;

@Data
public class PageVO {
	private int startPage;
	private int endPage;
	private boolean next;
	private boolean prev;
	
	private int total;
	private int pageNum;
	private int amount;
	
	private Criteria cri;
	
	public PageVO(Criteria cri, int total) {
		this.pageNum = cri.getPageNum();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		this.endPage = (int)Math.ceil(this.pageNum/10.0)*10;
		this.startPage = this.endPage - 10 +1;
		
		//실제 마지막 번호
		//ㄷㅌ) 53개면 마지막 
		int realEnd = (int)Math.ceil(this.total/(double)this.amount);
		
		if(this.endPage > realEnd)
		{
			this.endPage = realEnd;
			//마지막에 도달했을때 보여질 번호 변경
		}
		
		this.prev = this.startPage >1;
		this.next = realEnd > this.endPage;
	}

}
