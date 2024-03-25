package com.example.HiddenGem.service;

import java.util.List;

import com.example.HiddenGem.entity.Like;


public interface LikeService {
	
	Like getLike(int cid, String uid);

	Like getLikeByLid(int lid);

	List<Like> getLikeList(int cid);

	void insertLike(Like like);

	int toggleLike(Like like); // value가 0이면 1로 바꾸고, 1이면 0으로

	int getLikeCount(int cid);
}
