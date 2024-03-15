package com.example.HiddenGem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HiddenGem.dao.BoardCDao;
import com.example.HiddenGem.entity.BoardC;


@Service
public class BoardCServiceImpl implements BoardCService {
	@Autowired private BoardCDao boardCDao;

	@Override
	public BoardC getBoardC(int cid) {
		return boardCDao.getBoardC(cid);
	}

	@Override
	public int getBoardCCount(String field, String query) {
		query = "%" + query + "%";
		return boardCDao.getBoardCCount(field, query);
	}

	@Override
	public List<BoardC> getBoardCList(int page, String field, String query) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		return boardCDao.getBoardCList(field, query, COUNT_PER_PAGE, offset);
	}

	@Override
	public void insertBoardC(BoardC boardC) {
		boardCDao.insertBoardC(boardC);
	}

	@Override
	public void updateBoardC(BoardC boardC) {
		boardCDao.updateBoardC(boardC);
	}

	@Override
	public void deleteBoardC(int cid) {
		boardCDao.deleteBoardC(cid);
	}

	@Override
	public void increaseViewCount(int cid) {
		String field = "viewCount";
		boardCDao.increaseCount(field, cid);
	}

	@Override
	public void increaseReplyCount(int cid) {
		String field = "replyCount";
		boardCDao.increaseCount(field, cid);
	}

	@Override
	public void increaseLikeCount(int cid) {
		String field = "likeCount";
		boardCDao.increaseCount(field, cid);
	}
	
	@Override
	public void updateLikeCount(int cid, int count) {
		boardCDao.updateLikeCount(cid, count);
	}


}