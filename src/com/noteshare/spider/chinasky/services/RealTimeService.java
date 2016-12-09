package com.noteshare.spider.chinasky.services;


import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public interface RealTimeService {
	/**
	 * @Description: 根据id获取今日天气数据
	 * @param doc ： 文档对象
	 * @param id ： 对应元素的id
	 * @return ：返回获取到的元素
	 * @author     : NoteShare
	 * Create Date : 2016年12月9日 下午3:16:20
	 * @throws
	 */
	public Element getTodayDiv(Document doc,String id);
	/**
	 * @Description: 获取实时数据
	 * @param todayElement today元素对象
	 * @return 返回需要的数据映射关系
	 * @author     : NoteShare
	 * Create Date : 2016年12月9日 下午3:30:27
	 * @throws
	 */
	public Map<String,String> getTodayData(Element todayElement);
}
