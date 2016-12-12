package com.noteshare.spider.chinasky.dao;

import net.sf.json.JSONObject;

public interface SkyDao {
	
	/**
	 * @Description: 插入实时数据
	 * @param json : 爬取到的json数据
	 * @author     : NoteShare
	 * Create Date : 2016年12月12日 下午2:05:39
	 * @throws
	 */
	public void addRealData(JSONObject json);
	
	/**
	 * @Description: 插入生活指数数据
	 * @param json : 爬取到的json数据
	 * @author     : NoteShare
	 * Create Date : 2016年12月12日 上午11:13:15
	 * @throws
	 */
	public void addLiveData(JSONObject json);
	/**
	 * @Description: 插入7天天气数据
	 * @param json : 爬取到的json数据
	 * @author     : NoteShare
	 * Create Date : 2016年12月12日 下午12:06:27
	 * @throws
	 */
	public void add7DayData(JSONObject json);
}
