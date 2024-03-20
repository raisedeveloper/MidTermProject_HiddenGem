package com.example.HiddenGem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.HiddenGem.entity.BoardF;
import com.example.HiddenGem.entity.User;

@Mapper
public interface UserDao {
	@Select("select * from users where uid=#{uid}")
	User getUser(String uid);

	@Select("select * from users where isDeleted=0 order by regDate desc" + " limit #{count} offset #{offset}")
	List<User> getUserList(int count, int offset);

	@Insert("insert into users values (#{uid}, #{pwd}, #{uname}, #{email}, default, default,"
			+ " #{profile}, #{access}, #{sns}, #{link}, #{statusMessage})")
	void insertUser(User user);

	@Update("update users set pwd=#{pwd}, uname=#{uname}, email=#{email}, profile=#{profile}, sns=#{sns}, link=#{link}, statusMessage=#{statusMessage}"
			+ "  where uid=#{uid}")
	void updateUser(User user);

	@Update("update users set isDeleted=1 where uid=#{uid}")
	void deleteUser(String uid);

	@Select("select count(uid) from users where isDeleted=0")
	int getUserCount();

	@Select("select * from boardf u join likef f on u.fid=f.fid where f.value=1 and f.uid=#{uid} order by u.likeCount DESC")
	List<BoardF> getUserLikeList(String uid);

}