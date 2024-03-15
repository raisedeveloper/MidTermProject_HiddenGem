 package com.example.HiddenGem.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@GetMapping("/map")
	public String map() {
		return "boardC/api";
	}
	String location = "대전광역시 유성구 궁동";
	Float[] coords = findGeoPoint(location);

	System.out.println(location + ": " + coords[0] + ", " + coords[1]);
	public static Float[] findGeoPoint(String location) {

	    if (location == null)
	      return null;

	    // setAddress : 변환하려는 주소 (경기도 성남시 분당구 등)
	    // setLanguate : 인코딩 설정
	    GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();

	    try {
	        Geocoder geocoder = new Geocoder();
	        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

	        if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {
	            GeocoderResult geocoderResult=geocoderResponse.getResults().iterator().next();
	            LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();

	            Float[] coords = new Float[2];
	            coords[0] = latitudeLongitude.getLat().floatValue();
	            coords[1] = latitudeLongitude.getLng().floatValue();
	            ​return coords;
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
}

