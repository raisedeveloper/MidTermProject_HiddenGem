package com.example.HiddenGem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.HiddenGem.entity.Menu;


@Mapper
public interface MenuDao {
		
//	@Select("select * from menu m join boardf f on m.foodTitle=f.title where m.foodTitle=#{foodTitle}")
//	List<Menu> getMenuList(String foodTitle);
	
	@Select("select * from menu m join boardf f on m.foodTitle=f.title where m.foodTitle=#{foodTitle}")
	Menu getMenuByName(String name);

}