package com.example.HiddenGem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HiddenGem.dao.BoardFDao;
import com.example.HiddenGem.entity.BoardF;

@Service
public class BoardFServiceImpl implements BoardFService {
	@Autowired BoardFDao boardFDao;
	
	@Override
	public BoardF getBoardF(int fid) {
		return boardFDao.getBoardF(fid);
	}

	@Override
	public int getBoardFCount(String field, String query) {
		query = "%" + query + "%";
		return boardFDao.getBoardCount(field, query);
	}

	@Override
	public List<BoardF> getBoardFList(int page, String field, String query) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		return boardFDao.getBoardFList(field, query, COUNT_PER_PAGE, offset);
	}

	@Override
	public void insertBoardF(BoardF boardF) {
		boardFDao.insertBoardF(boardF);
		
	}


	@Override
	public void increaseViewCount(int fid) {
		String field = "viewCount";
		boardFDao.increaseCount(field, fid);
	}

	@Override
	public void increaseReplyCount(int fid) {
		String field = "replyCount";
		boardFDao.increaseCount(field, fid);
		
	}

	@Override
	public void increaseLikeCount(int fid) {
		String field = "likeCount";
		boardFDao.increaseCount(field, fid);
		
	}

	@Override
	public void updateLikeCount(int fid, int count) {
		boardFDao.updateLikeCount(fid, count);
		
	}

	@Override
	public List<BoardF> getBoardFListUsedMain() {
		return boardFDao.getBoardFListUsedMain();
	}


}
