package com.ssafy.project1.util.kakaoLogIn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HttpConnectionKakao {
	public static String getAccessToken(String code) {
		String accessUrl = "https://kauth.kakao.com/oauth/token";
		
		String grant_type = "authorization_code";
//		String redirect_uri= "http://192.168.0.3:8090/api/external/callback_kakao";
		String redirect_uri= "http://192.168.100.52:8080";	
		String client_id = "68d5df6c12f276676619cce888d07cda";
		String param = "grant_type="+grant_type+"&redirect_uri="+redirect_uri+"&client_id="+client_id+"&code="+code;
		String token="";
		URL url;
		try {
			url = new URL(accessUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			PrintWriter printWriter = new PrintWriter(con.getOutputStream());
			printWriter.print(param);
			printWriter.close();
			
			StringBuffer res = new StringBuffer();
			String inputLine=null;
			InputStream is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while((inputLine = br.readLine())!=null) {
				res.append(inputLine);
			}
			br.close();
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject)parser.parse(res.toString());
			token = (String)jsonObj.get("access_token");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return token;
	}
	static public KakaoMember getUserInfo(String access_token) {
        String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
        String apiURL = "https://kapi.kakao.com/v2/user/me";
        KakaoMember mem = null;
        try {
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
			JSONParser parser2 = new JSONParser();
			JSONObject jsonObj2 = (JSONObject)parser2.parse(info);
			JSONObject jsonObj3 = (JSONObject)jsonObj2.get("properties");
			String nickName = (String)jsonObj3.get("nickname");
			jsonObj3 = (JSONObject)jsonObj2.get("kakao_account");
			String email = (String)jsonObj3.get("email");
			mem = new KakaoMember(email,nickName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return mem;
	}
}
