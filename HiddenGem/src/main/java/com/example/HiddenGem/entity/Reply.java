package com.example.HiddenGem.entity;

import java.time.LocalDateTime;

public class Reply {
	private int rid;
	private String comment;
	private LocalDateTime regTime;
	private int isMine;
	private String uname;

	// user와 연결
	private String uid;
	// 보드와 연결
	private int fid;

	public Reply() {
	}

	public Reply(int rid, String comment, LocalDateTime regTime, int isMine, String uname, String uid, int fid) {
		super();
		this.rid = rid;
		this.comment = comment;
		this.regTime = regTime;
		this.isMine = isMine;
		this.uname = uname;
		this.uid = uid;
		this.fid = fid;
	}

	public Reply(String comment, int isMine, String uid, int fid) {
		super();
		this.comment = comment;
		this.isMine = isMine;
		this.uid = uid;
		this.fid = fid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

	public int getIsMine() {
		return isMine;
	}

	public void setIsMine(int isMine) {
		this.isMine = isMine;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", comment=" + comment + ", regTime=" + regTime + ", isMine=" + isMine + ", uname="
				+ uname + ", uid=" + uid + ", fid=" + fid + "]";
	}

}
