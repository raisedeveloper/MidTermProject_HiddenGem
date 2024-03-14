package com.example.HiddenGem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.HiddenGem.entity.BoardC;
import com.example.abbs.entity.Board;

@Mapper
public interface BoardCDao {

	@Select("SELECT b.*, u.uname FROM board b"
			+ " JOIN users u ON b.uid=u.uid"
			+ " WHERE b.bid=#{bid}")
	BoardC getBoardC(int bid);
	
	@Select("select count(b.bid) from board b"
			+ " JOIN users u ON b.uid=u.uid"
			+ " where b.isDeleted=0 and ${field} like #{query}")
	int getBoardCCount(String field, String query);
	
	@Select("SELECT b.*, u.uname FROM board b"
			+ " JOIN users u ON b.uid=u.uid"
			+ " WHERE b.isDeleted=0 and ${field} like #{query}"
			+ " ORDER BY b.modTime desc"
			+ " LIMIT #{count} OFFSET #{offset}")
	List<BoardC> getBoardCList(String field, String query, int count, int offset);
	
	@Insert("insert into board values(default, #{title}, #{content}, #{uid}, "
			+ " default, default, default, default, default, #{files})")
	void insertBoardC(BoardC boardC);
	
	@Update("update board set title=#{title}, content=#{content}, modTime=now(), "
			+ " files=#{files} where bid=#{bid}")
	void updateBoardC(BoardC boardC);
	
	@Update("update board set isDeleted=1 where bid=#{bid}")
	void deleteBoardC(int bid);
	
	@Update("update board set ${field}=${field}+1 where bid=#{bid}")
	void increaseCount(String field, int bid);	
	
	@Update("update board set likeCount=#{count} where bid=#{bid}")
	void updateLikeCount(int bid, int count);
	
}