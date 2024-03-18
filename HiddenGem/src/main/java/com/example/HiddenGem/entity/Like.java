package com.example.HiddenGem.entity;

public class Like {
	private int lid;
	private String uid;
	private int fid;
	private int value;

	public Like() {
	}

	public Like(int lid, String uid, int fid, int value) {
		super();
		this.lid = lid;
		this.uid = uid;
		this.fid = fid;
		this.value = value;
	}

	public Like(String uid, int fid, int value) {
		this.uid = uid;
		this.fid = fid;
		this.value = value;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LikeC [lid=" + lid + ", uid=" + uid + ", fid=" + fid + ", value=" + value + "]";
	}

}