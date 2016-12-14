package com.noteshare.spider.chinasky.services;


import org.jsoup.nodes.Document;

import net.sf.json.JSONObject;

public interface SkyService {
	/**
	 * @Description: 获取今日天气数据
	 * @param doc ： 文档对象
	 * @return ：返回需要的数据映射关系
	 * @author     : NoteShare
	 * Create Date : 2016年12月9日 下午3:16:20
	 * @throws
	 */
	public JSONObject getTodayData(Document doc);
	/**
	 * @Description: 获取实时数据
	 * @param Document dom 文档对象
	 * @return 返回需要的数据映射关系
	 * @author     : NoteShare
	 * Create Date : 2016年12月9日 下午3:30:27
	 * @throws
	 */
	public JSONObject getRealData(Document doc);
	/**
	 * @Description: 获取生活指数
	 * @param doc ： 文档对象
	 * @return  ： 返回生活指数数据
	 * @author     : NoteShare
	 * Create Date : 2016年12月10日 下午12:27:19
	 * @throws
	 */
	public JSONObject getLiveIndex(Document doc);
	
	/**
	 * @Description: 获取7天天气数据
	 * @param doc ： 文档对象
	 * @return ：返回7天天气数据
	 * @author     : NoteShare
	 * Create Date : 2016年12月10日 下午2:24:01
	 * @throws
	 */
	public JSONObject get7dData(Document doc);
}
