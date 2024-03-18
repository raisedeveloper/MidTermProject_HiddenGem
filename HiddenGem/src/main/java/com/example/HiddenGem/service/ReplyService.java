package com.example.HiddenGem.service;

import java.util.List;

import com.example.HiddenGem.entity.Reply; 



public interface ReplyService {
	
	List<Reply> getReplyList(int fid);
	
	void insertReply(Reply reply);
	
	
}
