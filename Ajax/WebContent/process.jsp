<%@page import="java.util.Set"%>
<%@page import="org.json.simple.parser.JSONParser"%>

<%@page import="java.io.BufferedReader"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	//JSON문자열 가공하기 위한 문자조작변수
	StringBuffer sb = new StringBuffer();
	
	//JSON문자열 파싱 클래스
	JSONParser jsonParser=new JSONParser();	
	JSONObject jsonObj = null;
	String json = null;     //JSON형식의 String값을 저장할 변수
	String line = null;    //line buffer에서 받아온 값을 저장할 변수

	
	try{
		BufferedReader buffer=request.getReader();
		while((line=buffer.readLine())!=null)
		{
			json=line;
			sb.append(line);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	//JSON문자열로 부터 JSON 객체를 추출한다.
	jsonObj=(JSONObject)jsonParser.parse(json);
	
	//값출력
	Set jsonKeySet=jsonObj.keySet();
	for(Object key:jsonKeySet)
	{
		out.print(key+" : "+jsonObj.get(key)+"<br/>");
	}
	
%>