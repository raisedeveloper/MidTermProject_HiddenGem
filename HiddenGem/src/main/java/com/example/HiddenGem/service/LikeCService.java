package com.example.HiddenGem.service;

import java.util.List;

import com.example.HiddenGem.entity.LikeC;


public interface LikeCService {
	LikeC getLike(int cid, String uid);

	LikeC getLikeByLid(int lid);

	List<LikeC> getLikeList(int cid);

	void insertLike(LikeC like);

	int toggleLike(LikeC like); // value가 0이면 1로 바꾸고, 1이면 0으로

	int getLikeCount(int cid);
}
