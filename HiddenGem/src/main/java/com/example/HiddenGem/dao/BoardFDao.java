package com.example.HiddenGem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.HiddenGem.entity.BoardF;

@Mapper
public interface BoardFDao {
	
	@Select("SELECT b.*, u.uname FROM boardF b"
			+ " JOIN users u ON b.uid=u.uid"
			+ "	WHERE b.fid=#{fid}")
	BoardF getBoardF(int fid);
	
	@Select("select count(b.fid) from boardF b"
	         + " JOIN users u ON b.uid=u.uid"
	         + " where b.isDeleted=0 and ${field} like #{query}")
	int getBoardCount(String field, String query);
	
	@Select("SELECT b.*, u.uname FROM boardf b"
			+ " JOIN users u ON b.uid=u.uid"
			+ " WHERE b.isDeleted=0 and ${field} like #{query}"
			+ " ORDER BY b.modTime DESC"
			+ " LIMIT #{count} OFFSET #{offset}")
	List<BoardF> getBoardFList(String field, String query, int count, int offset);
	
	@Insert("insert into boardF values(default, #{uid}, #{title}, #{foodCategory}, #{opening}, "
			+ " #{location}, #{tel}, #{info}, default, default, default, default, "
			+ " default, #{foodImg})")
	void insertBoardF(BoardF board);
	
	@Update("update boardF set ${field}=${field}+1 where fid=#{fid}")
	void increaseCount(String field, int fid);
	
	@Update("update boardF set likeCount=#{count} where fid=#{fid}")
	void updateLikeCount(int fid, int count);
	
	@Select("SELECT * FROM boardf WHERE isDeleted =0 "
	         + "   ORDER BY likeCount DESC")
	   List<BoardF> getBoardFListUsedMain();
}
