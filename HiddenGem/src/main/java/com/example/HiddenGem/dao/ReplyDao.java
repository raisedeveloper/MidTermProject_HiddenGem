package com.example.HiddenGem.dao;

import java.util.List; 

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.HiddenGem.entity.Reply;


@Mapper
public interface ReplyDao {
	
	@Select("select r.*, u.uname from reply r"
			+ " join users u on r.uid=u.uid"
			+ " where r.fid=#{fid}")
	List<Reply> getReplyList(int fid);
	
	@Insert("insert into reply values(default, #{comment}, default, #{uid},#{fid}, #{isMine})")
	void insertReply(Reply reply);
	
	
}
