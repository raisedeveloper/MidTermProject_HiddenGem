package com.example.HiddenGem.entity;

import java.time.LocalDateTime;

public class BoardF {
	// 음식점 디테일에 필요한 것들 
	private int fid; 
	private String title;
	private String foodCategory;
	private String opening;
	private String location;
	private String tel;
	private String info;
	private String foodImg;
	private int isDeleted;
	
	// 메뉴랑 연결 
//	private int mid;
	
	// user와 연결: view 등에 쓰임
	private String uid;
		
	// count 관련
	private int viewCount;
	private int likeCount;
	private int replyCount;
	
	// 댓글 관련
	private LocalDateTime modTime;
	
	
	/*
	 * 생성자
	 */
	public BoardF() {
	}


	public BoardF(int fid, String title, String foodCategory, String opening, String location, String tel, String info,
			String foodImg, int isDeleted, String uid, int viewCount, int likeCount, int replyCount,
			LocalDateTime modTime) {
		this.fid = fid;
		this.title = title;
		this.foodCategory = foodCategory;
		this.opening = opening;
		this.location = location;
		this.tel = tel;
		this.info = info;
		this.foodImg = foodImg;
		this.isDeleted = isDeleted;
		this.uid = uid;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
		this.modTime = modTime;
	}
	
	


	public BoardF(String title, String foodCategory, String opening, String location, String tel, String info, String uid,
			String foodImg) {
		
		this.uid = uid;
		this.title = title;
		this.foodCategory = foodCategory;
		this.opening = opening;
		this.location = location;
		this.tel = tel;
		this.info = info;
		this.foodImg = foodImg;
	}


	public int getFid() {
		return fid;
	}


	public void setFid(int fid) {
		this.fid = fid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getFoodCategory() {
		return foodCategory;
	}


	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}


	public String getOpening() {
		return opening;
	}


	public void setOpening(String opening) {
		this.opening = opening;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public String getFoodImg() {
		return foodImg;
	}


	public void setFoodImg(String foodImg) {
		this.foodImg = foodImg;
	}


	public int getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public int getViewCount() {
		return viewCount;
	}


	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}


	public int getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public int getReplyCount() {
		return replyCount;
	}


	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}


	public LocalDateTime getModTime() {
		return modTime;
	}


	public void setModTime(LocalDateTime modTime) {
		this.modTime = modTime;
	}


	@Override
	public String toString() {
		return "BoardF [fid=" + fid + ", title=" + title + ", foodCategory=" + foodCategory + ", opening=" + opening
				+ ", location=" + location + ", tel=" + tel + ", info=" + info + ", foodImg=" + foodImg + ", isDeleted="
				+ isDeleted + ", uid=" + uid + ", viewCount=" + viewCount + ", likeCount=" + likeCount + ", replyCount="
				+ replyCount + ", modTime=" + modTime + "]";
	}
	
	

}