package com.hbbs;

public class HBbsDTO {

	private int bbsId;
	private String bbsCategory;
	private String userId;
	private String bbsTitle;
	private String bbsContent;
	private String createdDate;
	private String updetedDate;
	private int hitCount;

	public int getBbsId() {
		return bbsId;
	}

	public void setBbsId(int bbsId) {
		this.bbsId = bbsId;
	}

	public String getBbsCategory() {
		return bbsCategory;
	}

	public void setBbsCategory(String bbsCategory) {
		this.bbsCategory = bbsCategory;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBbsTitle() {
		return bbsTitle;
	}

	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}

	public String getBbsContent() {
		return bbsContent;
	}

	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdetedDate() {
		return updetedDate;
	}

	public void setUpdetedDate(String updetedDate) {
		this.updetedDate = updetedDate;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

}
