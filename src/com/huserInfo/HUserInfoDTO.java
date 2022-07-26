package com.huserInfo;

public class HUserInfoDTO {

	private String userId;
	private String userGrant;
	private String lastDate;
	private int createdCount;
	private int commentCount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserGrant() {
		return userGrant;
	}

	public void setUserGrant(String userGrant) {
		this.userGrant = userGrant;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public int getCreatedCount() {
		return createdCount;
	}

	public void setCreatedCount(int createdCount) {
		this.createdCount = createdCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

}
