package com.example.HiddenGem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HiddenGem.dao.LikeDao;
import com.example.HiddenGem.entity.Like;


@Service
public class LikeServiceImpl implements LikeService {
	@Autowired private LikeDao likeDao;
	
	@Override
	public Like getLike(int fid, String uid) {
		return likeDao.getLike(fid, uid);
	}

	@Override
	public Like getLikeByLid(int lid) {
		return likeDao.getLikeByLid(lid);
	}

	@Override
	public List<Like> getLikeList(int fid) {
		return likeDao.getLikeList(fid);
	}

	@Override
	public void insertLike(Like like) {
		likeDao.insertLike(like);
	}

	@Override
	public int toggleLike(Like like) {
		like = likeDao.getLike(like.getFid(), like.getUid());
		int value = like.getValue() == 0 ? 1 : 0;
		like.setValue(value);
		likeDao.updateLike(like);
		return value;
	}

	@Override
	public int getLikeCount(int fid) {
		List<Like> list =likeDao.getLikeList(fid);
		int count = 0;
		for (Like LikeC :list)
			count += LikeC.getValue();
		return count;
	}


}
