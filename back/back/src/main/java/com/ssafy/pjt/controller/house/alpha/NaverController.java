package com.ssafy.pjt.controller.house.alpha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/naver")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class NaverController {

	@GetMapping(value = "/news", produces = "application/json; charset=utf-8")
	public String news(String keyword) {
		String clientId = "bgbHjwmHco4MN04hAsnU"; // 애플리케이션 클라이언트 아이디
		String clientSecret = "xMZq4h_hyW"; // 애플리케이션 클라이언트 시크릿

		String text = null;
		try {
			text = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text; // JSON 결과
		// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
		// // XML 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);

		System.out.println(responseBody); // 네이버가 주는 결과데이터
		return responseBody;
	}
	
	@GetMapping(value = "/road/{start}/{goal}/{fueltype}/{option}", produces = "application/json; charset=utf-8")
	public String road(@PathVariable("start") String start, @PathVariable("goal") String goal,@PathVariable("fueltype") String fueltype,@PathVariable("option") String option) {
		System.out.println("!!!!!!!!!!!!들어오긴 함");
		System.out.println("start : " + start);
		System.out.println("goal : " + goal);
		System.out.println("fueltype : " + fueltype);
		System.out.println("option : " + option);
		
		String clientId = "4ihh2kc4lx"; // 애플리케이션 클라이언트 아이디
		String clientSecret = "R5xM6LM3F4FlDoItGVOtVHCxO6a3v0EvNJDZPVZk"; // 애플리케이션 클라이언트 시크릿
		

		String apiURL = "https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start="+start; // JSON 결과
		apiURL += "&goal="+goal+"&option="+option+"&fueltype="+ fueltype;
		// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
		// // XML 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-NCP-APIGW-API-KEY-ID", clientId);
		requestHeaders.put("X-NCP-APIGW-API-KEY", clientSecret);
		String responseBody = get(apiURL, requestHeaders);
		System.out.println(responseBody); // 네이버가 주는 결과데이터
		
		return responseBody;
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 오류 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
		}
	}
}
