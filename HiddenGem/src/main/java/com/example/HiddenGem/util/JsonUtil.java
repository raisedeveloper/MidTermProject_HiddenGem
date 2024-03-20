package com.example.HiddenGem.util;

import java.util.List;

import org.apache.commons.httpclient.Header;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component // 총칭
public class JsonUtil {
	
	@SuppressWarnings("unchecked")
	public String list2Json(List<String> list) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("list", list);
		return jsonObj.toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> json2List(String jsonStr) {
		JSONParser parser = new JSONParser();
		List<String> list =null;
		try {
			JSONObject jsonObj	= (JSONObject) parser.parse(jsonStr);
			JSONArray jsonArr = (JSONArray) jsonObj.get("list");
			list = (List<String>) jsonArr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
