package com.noteshare.spider.chinasky.services.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.noteshare.spider.chinasky.services.RealTimeService;
import com.noteshare.spider.common.util.SpiderConstant;
import com.noteshare.spider.common.util.SpiderUtil;

import net.sf.json.JSONObject;

public class RealTimeServiceImpl implements RealTimeService{

	@Override
	public Element getTodayDiv(Document doc,String id) {
		//处理返回数据
		Elements results = SpiderUtil.getElements(SpiderConstant.IDSELECTOR,id,doc);
		if(null != results && !results.isEmpty())
			return results.get(0);
		return null;
	}

	@Override
	public JSONObject getTodayData(Document doc) {
		Element body = doc.getElementsByTag("body").get(0);
		String dataSK = ""; 
		if(null != body){
			dataSK = body.html();
		}
		dataSK = dataSK.replace("var dataSK = ", "");
		JSONObject jsonObject = JSONObject.fromObject(dataSK);
		return jsonObject;
	}

	@Override
	public JSONObject getLiveIndex(Document doc) {
		Elements elements = SpiderUtil.getElements(SpiderConstant.CLASSSELECTOR,"livezs",doc);
		Element elementLive = elements.get(0);
		Element ul = elementLive.getElementsByTag("ul").get(0);
		Elements lis = ul.getElementsByTag("li");
		JSONObject jsonResult = new JSONObject();
		for (Element element : lis) {
			JSONObject json = new JSONObject();
			String type = element.getElementsByTag("em").get(0).text();
			String value = element.getElementsByTag("span").get(0).text();
			String content = element.getElementsByTag("p").get(0).text();
			json.put("value", value);
			json.put("content", content);
			jsonResult.put(type, json);
		}
		return jsonResult;
	}

	@Override
	public JSONObject get7dData(Document doc) {
		Elements elements = SpiderUtil.getElements(SpiderConstant.IDSELECTOR,"7d",doc);
		//获取第一个ul
		Element firstUl = elements.get(0).getElementsByTag("ul").get(0);
		Elements lis = firstUl.getElementsByTag("li");
		JSONObject json = new JSONObject();
		Date today = new Date();
        Calendar calendar = new GregorianCalendar(); 
        calendar.setTime(today); 
        calendar.add(Calendar.DATE,-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Element element : lis) {
			JSONObject jsontemp = new JSONObject();
			//			天气
			Element weaElement = element.getElementsByClass("wea").get(0);
			String wea = weaElement.text();
			//			白天温度
			//			晚上温度
			Element temElement = element.getElementsByClass("tem").get(0);
			String daytimeTem = temElement.getElementsByTag("span").get(0).text();
			String nightTem = temElement.getElementsByTag("i").get(0).text();
			//			白天风向
			//			晚上风向
			Element winElement = element.getElementsByClass("win").get(0);
			String daytimeWin = winElement.getElementsByTag("span").get(0).attr("title");
			String nightWin = winElement.getElementsByTag("span").get(1).attr("title");
			//			风力
			String winPower = winElement.getElementsByTag("i").get(0).text();
			//			采集时间
			calendar.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
			Date keyDate = calendar.getTime();
			String dayStr = sdf.format(keyDate);
			jsontemp.put("wea", wea);
			jsontemp.put("daytimeTem", daytimeTem);
			jsontemp.put("nightTem", nightTem);
			jsontemp.put("daytimeWin", daytimeWin);
			jsontemp.put("nightWin", nightWin);
			jsontemp.put("winPower", winPower);
			json.put(dayStr, jsontemp);
		}
		return json;
	}
	
	/*
	 * 次方方法是根据首页的24小时数据解析实时数据，数据并不是很准，留作备用
	 * public Map<String, String> getTodayDataMethod1(Element todayElement) {
		//数据集合
		Map<String,String> dataMap = new HashMap<String, String>();
		Elements elementScripts = todayElement.parent().getElementsByTag("script");
		String observe24h_data = "";
		for (Element element : elementScripts) {
			String text = element.html();
			if(null != text && text.contains("observe24h_data")){
				observe24h_data = text;
			}
		}
		if("" != observe24h_data){
			observe24h_data = observe24h_data.replace("var observe24h_data = ", "");
			JSONObject json = JSONObject.fromObject(observe24h_data);
			JSONObject jsonod = json.getJSONObject("od");
			//updatetime
			String updatetime = jsonod.getString("od0");
			String city = jsonod.getString("od1");
			JSONArray jsonArr = jsonod.getJSONArray("od2");
			//获取最新的一条数据记录
			//温度
			String temperature = "";
			//湿度
			String humidity = "";
			//风向
			String windDirection = "";
			//风力
			String windPower = "";
			//采集时间
			String getTime = "";
			if(null != jsonArr && !jsonArr.isEmpty()){
				
			}
			
		}else{
			return null;
		}
		return dataMap;
	}
*/
}
