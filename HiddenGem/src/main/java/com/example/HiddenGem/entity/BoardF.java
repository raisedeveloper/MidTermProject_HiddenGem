package com.example.HiddenGem.entity;

import java.time.LocalDateTime;

public class BoardF {
	private int fid;
	private String uid;
	private String title;
	private String foodCategory;
	private String opening;
	private String location;
	private String tel;
	private String info;
	private LocalDateTime modTime;
	private int isDeleted;
	private int viewCount;
	private int likeCount;
	private int replyCount;
	private int mid;
	private int rid;
	private String uname;

	public BoardF() {
	}

	public BoardF(int fid, String uid, String title, String foodCategory, String opening, String location, String tel,
			String info, LocalDateTime modTime, int isDeleted, int viewCount, int likeCount, int replyCount, int mid,
			int rid, String uname) {
		this.fid = fid;
		this.uid = uid;
		this.title = title;
		this.foodCategory = foodCategory;
		this.opening = opening;
		this.location = location;
		this.tel = tel;
		this.info = info;
		this.modTime = modTime;
		this.isDeleted = isDeleted;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
		this.mid = mid;
		this.rid = rid;
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "BoardF [fid=" + fid + ", uid=" + uid + ", title=" + title + ", foodCategory=" + foodCategory
				+ ", opening=" + opening + ", location=" + location + ", tel=" + tel + ", info=" + info + ", modTime="
				+ modTime + ", isDeleted=" + isDeleted + ", viewCount=" + viewCount + ", likeCount=" + likeCount
				+ ", replyCount=" + replyCount + ", mid=" + mid + ", rid=" + rid + ", uname=" + uname + "]";
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public LocalDateTime getModTime() {
		return modTime;
	}

	public void setModTime(LocalDateTime modTime) {
		this.modTime = modTime;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}