package com.example.HiddenGem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HiddenGem.entity.BoardF;

@Service
public interface BoardFService {
	public static final int COUNT_PER_PAGE = 5; // 한 페이지당 글의 목록 및 갯수
	public static final int PAGE_PER_SCREEN = 10; // 한 화면에 표시되는 페이지 갯수
	
	BoardF getBoardF(int fid);
	
	int getBoardFCount(String field, String query);
	
	List<BoardF> getBoardFList(int page, String field, String query);
	
	void insertBoardF(BoardF boardF);
	
	void increaseViewCount(int fid);
	
	void increaseReplyCount(int fid);
	
	void increaseLikeCount(int fid);
	
	void updateLikeCount(int fid, int count);
	
	List<BoardF> getBoardFListUsedMain();
}
