package com.example.HiddenGem.entity;

import java.time.LocalDateTime;

public class BoardC {
	private int cid;
	private String uid;
	private String title;
	private String content;
	private LocalDateTime modTime;
	private String files;
	private int isDeleted;
	private int viewCount;
	private int likeCount;
	private String uname;

	public BoardC() {
	}

	public BoardC(int cid, String uid, String title, String content, LocalDateTime modTime, int isDeleted,
			int viewCount, int likeCount, String uname) {
		this.cid = cid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.modTime = modTime;
		this.isDeleted = isDeleted;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.uname = uname;
	}

	public BoardC(String uid, String title, String content, String files) {
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.files = files;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "BoardC [cid=" + cid + ", uid=" + uid + ", title=" + title + ", content=" + content + ", modTime="
				+ modTime + ", files=" + files + ", isDeleted=" + isDeleted + ", viewCount=" + viewCount
				+ ", likeCount=" + likeCount + ", uname=" + uname + "]";
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}