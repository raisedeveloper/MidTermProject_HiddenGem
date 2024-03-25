package com.example.HiddenGem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HiddenGem.dao.MenuDao;
import com.example.HiddenGem.entity.Menu;


@Service
public class MenuServiceImpl implements MenuService {
	@Autowired private MenuDao dao;
	
	/*
	 * @Override public List<Menu> getMenuList(String foodTitle) { return
	 * dao.getMenuList(foodTitle); }
	 */

	@Override
	public Menu getMenuByName(String name) {
		return dao.getMenuByName(name);
	}

}
