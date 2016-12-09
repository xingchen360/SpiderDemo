package com.noteshare.spider.common.beans;

import java.util.Map;

public class RequestParams {
	
	public RequestParams(){
		
	}
	/**
	 * @Description: 请求参数构造方法
	 * @param link : 请求地址
	 * @param requestType ： 请求类型
	 * @param paramMap：请求参数映射
	 * @author     : NoteShare
	 * Create Date : 2016年12月9日 下午1:24:28
	 * @throws
	 */
	public RequestParams(String link,int requestType,Map<String,String> paramMap){
		this.link = link;
		this.requestType = requestType;
		this.paramMap = paramMap;
	}
	/**
	 * 链接地址
	 */
	private String link;
	/**
	 * 请求类型(0:GET;1:POST);默认是GET请求
	 */
	private int requestType = 0;
	/**
	 * 请求参数map映射
	 */
	private Map<String,String> paramMap;
	
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public Map<String, String> getParamMap() {
		return paramMap;
	}
	
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
}
