package com.example.HiddenGem.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HiddenGem.dao.ReplyDao;
import com.example.HiddenGem.entity.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired private ReplyDao replyDao;

	@Override
	public List<Reply> getReplyList(int fid) {
		return replyDao.getReplyList(fid);
	}

	@Override
	public void insertReply(Reply reply) {
		replyDao.insertReply(reply);
		
	}
	
	
	
}
