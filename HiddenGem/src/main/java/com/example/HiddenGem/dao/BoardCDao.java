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

	@Select("SELECT b.*, u.uname FROM boardc b"
			+ " JOIN users u ON b.uid=u.uid"
			+ " WHERE b.cid=#{cid}")
	BoardC getBoardC(int cid);
	
	@Select("select count(b.cid) from boardc b"
			+ " JOIN users u ON b.uid=u.uid"
			+ " where b.isDeleted=0 and ${field} like #{query}")
	int getBoardCCount(String field, String query);
	
	@Select("SELECT b.*, u.uname FROM boardc b"
			+ " JOIN users u ON b.uid=u.uid"
			+ " WHERE b.isDeleted=0 and ${field} like #{query}"
			+ " ORDER BY b.modTime desc"
			+ " LIMIT #{count} OFFSET #{offset}")
	List<BoardC> getBoardCList(String field, String query, int count, int offset);
	
	@Insert("insert into boardc values(default, #{title}, #{content}, #{uid}, "
			+ " default, default, default, default, default, #{files})")
	void insertBoardC(BoardC boardC);
	
	@Update("update boardc set title=#{title}, content=#{content}, modTime=now(), "
			+ " files=#{files} where cid=#{cid}")
	void updateBoardC(BoardC boardC);
	
	@Update("update boardc set isDeleted=1 where cid=#{cid}")
	void deleteBoardC(int cid);
	
	@Update("update boardc set ${field}=${field}+1 where cid=#{cid}")
	void increaseCount(String field, int cid);	
	
	@Update("update boardc set likeCount=#{count} where cid=#{cid}")
	void updateLikeCount(int cid, int count);
	
}