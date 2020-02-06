package com.ssafy.project1.util.naverLogIn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins= {"*"},maxAge=6000)
public class HttpConnectionNaver {
	
	private static final String mydomain = "http%3A%2F%2F127.0.0.1%3A8090%2Fapi%2Fexternal%2Fcallback_naver";
	private static final String clientId = "nsrxqIjEGhhBf9jdPBFD";
	private static final String clientSecret = "lQoLe3M29W";
	private static final String requestUrl = "https://nid.naver.com/oauth2.0/authorize?client_id=" + clientId + "&response_type=code&redirect_uri="+ mydomain + "&state="; 

	public static String makeApiUrl(String state, String code) {
		String accessUrl = "https://nid.naver.com/oauth2.0/token?client_id=" + clientId + "&client_secret=" + clientSecret
			    + "&grant_type=authorization_code" + "&state=" + state + "&code=" + code;
		return accessUrl;
	}
	
//	public static NaverMember getNaverMem(String apiURL) {
//		
//		String access_token ="";
//		NaverMember naverMember = null;
//		try {
//			URL url = new URL(apiURL);
//			HttpURLConnection con = (HttpURLConnection)url.openConnection();
//			con.setRequestMethod("GET");
//			int responseCode = con.getResponseCode();
//			BufferedReader br;
//			if(responseCode==200) {
//				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			}else {
//				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			}
//			String inputLine="";
//			StringBuffer res = new StringBuffer();
//			while((inputLine=br.readLine())!=null) {
//				res.append(inputLine);
//			}
//			br.close();
//			if(responseCode==200) { // 성공적으로 토큰을 가져온다면
//				String nickName, email, name;
//				JSONParser parser = new JSONParser();
//				JSONObject jsonObj = (JSONObject)parser.parse(res.toString());
//				access_token = (String)jsonObj.get("access_token");
//				String info = getUserInfo(access_token);
//				
//				JSONParser parser2 = new JSONParser();
//				JSONObject jsonObj2 = (JSONObject)parser2.parse(info);
//				JSONObject jsonObj3 = (JSONObject)jsonObj2.get("response");
//				nickName = (String)jsonObj3.get("nickname");
//				email = (String)jsonObj3.get("email");
//				name = (String)jsonObj3.get("name");
//				naverMember = new NaverMember(name, nickName, email);
//            }
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return naverMember;
//	}
	public static NaverMember getNaverMember(String access_token) {
		String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
        System.out.println("header= "+header);
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            
            String info = res.toString();
			System.out.println(info);
			JSONParser parser2 = new JSONParser();
			JSONObject jsonObj2 = (JSONObject)parser2.parse(info);
			JSONObject jsonObj3 = (JSONObject)jsonObj2.get("response");
			String nickName = (String)jsonObj3.get("nickname");
			String email = (String)jsonObj3.get("email");
			String name = (String)jsonObj3.get("name");
			NaverMember naverMember = new NaverMember(name, nickName, email);
            
            return naverMember;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
	
//	 static private String getUserInfo(String access_token) {
//	        String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
//	        System.out.println("header= "+header);
//	        try {
//	            String apiURL = "https://openapi.naver.com/v1/nid/me";
//	            URL url = new URL(apiURL);
//	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//	            con.setRequestMethod("GET");
//	            con.setRequestProperty("Authorization", header);
//	            int responseCode = con.getResponseCode();
//	            BufferedReader br;
//	            if(responseCode==200) { // 정상 호출
//	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//	            } else {  // 에러 발생
//	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//	            }
//	            String inputLine;
//	            StringBuffer res = new StringBuffer();
//	            while ((inputLine = br.readLine()) != null) {
//	                res.append(inputLine);
//	            }
//	            br.close();
//	            return res.toString();
//	        } catch (Exception e) {
//	            System.err.println(e);
//	            return "Err";
//	        }
//	    }
}
