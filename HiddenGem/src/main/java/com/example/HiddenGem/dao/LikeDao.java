package com.example.HiddenGem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.HiddenGem.entity.Like;


@Mapper
public interface LikeDao {
	
	@Select("select * from likef where fid=#{fid} and uid=#{uid}")
	Like getLike(int fid, String uid);
	
	@Select("select * from likef where lid=#{lid}")
	Like getLikeByLid(int lid);
	
	@Select("select * from likef where fid=#{fid}")
	List<Like> getLikeList(int fid);
	
	@Insert("insert into likef values(default, #{uid}, #{fid}, #{value})")
	void insertLike(Like like);
	
	// update likes set value=if(value=0,1,0) where lid=#{lid}
	@Update("update likef set value=#{value} where lid=#{lid}")
	void updateLike(Like like);
	
}