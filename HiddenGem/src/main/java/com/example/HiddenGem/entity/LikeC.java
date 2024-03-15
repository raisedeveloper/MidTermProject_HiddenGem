package com.example.HiddenGem.entity;

public class LikeC {
	private int lid;
	private String uid;
	private int cid;
	private int value;

	public LikeC() {
	}

	public LikeC(int lid, String uid, int cid, int value) {
		super();
		this.lid = lid;
		this.uid = uid;
		this.cid = cid;
		this.value = value;
	}

	public LikeC(String uid, int cid, int value) {
		this.uid = uid;
		this.cid = cid;
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

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LikeC [lid=" + lid + ", uid=" + uid + ", cid=" + cid + ", value=" + value + "]";
	}

}