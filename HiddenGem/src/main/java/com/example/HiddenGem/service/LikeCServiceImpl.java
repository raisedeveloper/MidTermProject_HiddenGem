package com.example.HiddenGem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HiddenGem.dao.LikeCDao;
import com.example.HiddenGem.entity.LikeC;


@Service
public class LikeCServiceImpl implements LikeCService {
	@Autowired private LikeCDao likeDao;
	
	@Override
	public LikeC getLike(int cid, String uid) {
		return likeDao.getLike(cid, uid);
	}

	@Override
	public LikeC getLikeByLid(int lid) {
		return likeDao.getLikeByLid(lid);
	}

	@Override
	public List<LikeC> getLikeList(int cid) {
		return likeDao.getLikeList(cid);
	}

	@Override
	public void insertLike(LikeC like) {
		likeDao.insertLike(like);
	}

	@Override
	public int toggleLike(LikeC like) {
		like = likeDao.getLike(like.getCid(), like.getUid());
		int value = like.getValue() == 0 ? 1 : 0;
		like.setValue(value);
		likeDao.updateLike(like);
		return value;
	}

	@Override
	public int getLikeCount(int cid) {
		List<LikeC> list =likeDao.getLikeList(cid);
		int count = 0;
		for (LikeC LikeC :list)
			count += LikeC.getValue();
		return count;
	}


}
