package com.noteshare.spider.common.util;

public class SpiderConstant {
	/**
	 * GET请求
	 */
	public static final int GET = 0;
	/**
	 * POST请求
	 */
	public static final int POST = 1;
	/**
	 *  请求延迟时间
	 */
	public static final long RESTIMEOUT = 10000;
	
	/**
	 * ID选择器
	 */
	public static final int IDSELECTOR = 0;
	/**
	 * CLASS选择器
	 */
	public static final int CLASSSELECTOR = 1;
	/**
	 * 属性选择器
	 */
	public static final int ATTRSELECTOR = 3;
	/**
	 * 默认获取标签
	 */
	public static final String DEFAULTTAG = "body";
}
