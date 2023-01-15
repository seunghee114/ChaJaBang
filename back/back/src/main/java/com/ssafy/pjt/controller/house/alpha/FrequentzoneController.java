package com.ssafy.pjt.controller.house.alpha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frequent")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class FrequentzoneController {
	
	@GetMapping(value="/child/{sidogugun}", produces = "application/json; charset=utf-8")
	public ResponseEntity<?> childAccident(@PathVariable("sidogugun") String sidogugun) throws IOException {
		String servicekey = "xcuFoZoMRX77SQYIL9EDahYswj90iA2Az%2Ftk2WVwqhb9dMXhW2WNQGbNLcS0Vzm0";
		System.out.println("sidogugun " + sidogugun);
		String sido = sidogugun.substring(0, 2);
		String gugun = sidogugun.substring(2, 5);
		
		ArrayList<Integer> result = new ArrayList<>();
		for(int year = 2012; year < 2021; year++) {
			StringBuilder urlBuilder = new StringBuilder(
					"http://taas.koroad.or.kr/data/rest/frequentzone/child");
			urlBuilder.append("?" + URLEncoder.encode("authKey", "UTF-8") + "="+ servicekey);
			
			urlBuilder.append("&"+URLEncoder.encode("searchYearCd", "UTF-8")+ "=" + URLEncoder.encode(year+"", "UTF-8"));
			urlBuilder.append("&"+URLEncoder.encode("siDo", "UTF-8")+ "=" + URLEncoder.encode(sido, "UTF-8"));
			urlBuilder.append("&"+URLEncoder.encode("guGun", "UTF-8")+ "=" + URLEncoder.encode(gugun, "UTF-8"));
			
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			JSONObject json = XML.toJSONObject(sb.toString());
			JSONObject response = ((JSONObject) json.get("response")).getJSONObject("body");
			
			
			int totalCount = response.getInt("totalCount");
			result.add(totalCount);
		}
		return new ResponseEntity<List<Integer>>(result, HttpStatus.OK);
	}
	@GetMapping(value="/oldman/{sidogugun}", produces = "application/json; charset=utf-8")
	public ResponseEntity<?> oldmanAccident(@PathVariable("sidogugun") String sidogugun) throws IOException {
		String servicekey = "IwB8Y2G28bODtem8SeBcBZZLygVLoGl%2B8zfNjVvCnp2QfI6vmHTuIalFUmmFWm6K";
		System.out.println("sidogugun " + sidogugun);
		String sido = sidogugun.substring(0, 2);
		String gugun = sidogugun.substring(2, 5);
		
		ArrayList<Integer> result = new ArrayList<>();
		for(int year = 2012; year < 2021; year++) {
			StringBuilder urlBuilder = new StringBuilder(
					"http://taas.koroad.or.kr/data/rest/frequentzone/oldman");
			urlBuilder.append("?" + URLEncoder.encode("authKey", "UTF-8") + "="+ servicekey);
			
			urlBuilder.append("&"+URLEncoder.encode("searchYearCd", "UTF-8")+ "=" + URLEncoder.encode(year+"", "UTF-8"));
			urlBuilder.append("&"+URLEncoder.encode("siDo", "UTF-8")+ "=" + URLEncoder.encode(sido, "UTF-8"));
			urlBuilder.append("&"+URLEncoder.encode("guGun", "UTF-8")+ "=" + URLEncoder.encode(gugun, "UTF-8"));
			
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			JSONObject json = XML.toJSONObject(sb.toString());
			JSONObject response = ((JSONObject) json.get("response")).getJSONObject("body");
			
			
			int totalCount = response.getInt("totalCount");
			result.add(totalCount);
		}
		return new ResponseEntity<List<Integer>>(result, HttpStatus.OK);
	}
	@GetMapping(value="/bicycle/{sidogugun}", produces = "application/json; charset=utf-8")
	public ResponseEntity<?> bicycleAccident(@PathVariable("sidogugun") String sidogugun) throws IOException {
		String servicekey = "%2FJiHim3NpIHCnDBUK3i19sQN6vkjz0x3AZHarSksNicJ0bz%2BIblLXQifspQuHg7e";
		System.out.println("sidogugun " + sidogugun);
		String sido = sidogugun.substring(0, 2);
		String gugun = sidogugun.substring(2, 5);
		
		ArrayList<Integer> result = new ArrayList<>();
		for(int year = 2012; year < 2021; year++) {
			StringBuilder urlBuilder = new StringBuilder(
					"http://taas.koroad.or.kr/data/rest/frequentzone/bicycle");
			urlBuilder.append("?" + URLEncoder.encode("authKey", "UTF-8") + "="+ servicekey);
			
			urlBuilder.append("&"+URLEncoder.encode("searchYearCd", "UTF-8")+ "=" + URLEncoder.encode(year+"", "UTF-8"));
			urlBuilder.append("&"+URLEncoder.encode("siDo", "UTF-8")+ "=" + URLEncoder.encode(sido, "UTF-8"));
			urlBuilder.append("&"+URLEncoder.encode("guGun", "UTF-8")+ "=" + URLEncoder.encode(gugun, "UTF-8"));
			
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			JSONObject json = XML.toJSONObject(sb.toString());
			JSONObject response = ((JSONObject) json.get("response")).getJSONObject("body");
			
			
			int totalCount = response.getInt("totalCount");
			result.add(totalCount);
		}
		return new ResponseEntity<List<Integer>>(result, HttpStatus.OK);
	}
}
