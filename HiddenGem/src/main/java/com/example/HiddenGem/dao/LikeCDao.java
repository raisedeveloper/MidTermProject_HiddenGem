package com.example.HiddenGem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.HiddenGem.entity.LikeC;


@Mapper
public interface LikeCDao {
	
	@Select("select * from likec where cid=#{cid} and uid=#{uid}")
	LikeC getLike(int cid, String uid);
	
	@Select("select * from likec where lid=#{lid}")
	LikeC getLikeByLid(int lid);
	
	@Select("select * from likec where cid=#{cid}")
	List<LikeC> getLikeList(int cid);
	
	@Insert("insert into likec values(default, #{uid}, #{cid}, #{value})")
	void insertLike(LikeC like);
	
	// update likes set value=if(value=0,1,0) where lid=#{lid}
	@Update("update likec set value=#{value} where lid=#{lid}")
	void updateLike(LikeC like);
	
}