package com.noteshare.spider.chinasky.services.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.noteshare.spider.chinasky.services.SkyService;
import com.noteshare.spider.common.util.SpiderConstant;
import com.noteshare.spider.common.util.SpiderUtil;

import net.sf.json.JSONObject;

public class SkyServiceImpl implements SkyService{

	@Override
	public JSONObject getTodayData(Document doc) {
		JSONObject json = new JSONObject();
		Elements elements = SpiderUtil.getElements(SpiderConstant.IDSELECTOR, "today", doc);
		Element todayElement = elements.get(0);
		Element tDivElement = todayElement.getElementsByClass("t").get(0);
		Element ulElement = tDivElement.getElementsByTag("ul").get(0);
		Elements liElements = ulElement.getElementsByTag("li");
		Element daytimeElment = liElements.get(0);
		Element nightElement = liElements.get(1);
		//白天
		String daytime_weather = daytimeElment.getElementsByClass("wea").text();
		String daytime_temperature = daytimeElment.getElementsByClass("tem").get(0).getElementsByTag("span").text();
		String daytime_windDirection = daytimeElment.getElementsByClass("win").get(0).getElementsByTag("span").attr("title");
		String daytime_windPower = daytimeElment.getElementsByClass("win").get(0).getElementsByTag("span").text();
		//晚上
		String night_weather = nightElement.getElementsByClass("wea").text();
		String night_temperature = nightElement.getElementsByClass("tem").get(0).getElementsByTag("span").text();
		String night_windDirection = nightElement.getElementsByClass("win").get(0).getElementsByTag("span").attr("title");
		String night_windPower = nightElement.getElementsByClass("win").get(0).getElementsByTag("span").text();
		json.put("daytime_weather", daytime_weather);
		json.put("daytime_temperature", daytime_temperature);
		json.put("daytime_windDirection", daytime_windDirection);
		json.put("daytime_windPower", daytime_windPower);
		json.put("night_weather", night_weather);
		json.put("night_temperature", night_temperature);
		json.put("night_windDirection", night_windDirection);
		json.put("night_windPower", night_windPower);
		return json;
	}

	@Override
	public JSONObject getRealData(Document doc) {
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
			String daytimeTem = "";
			String nightTem = "";
			//晚上时分只有一个值
			try{daytimeTem = temElement.getElementsByTag("span").get(0).text();}catch(Exception e){}
			try{nightTem = temElement.getElementsByTag("i").get(0).text();}catch(Exception e){}
			//			白天风向
			//			晚上风向
			Element winElement = element.getElementsByClass("win").get(0);
			String daytimeWin = "";
			try{daytimeWin = winElement.getElementsByTag("span").get(0).attr("title");}catch(Exception e){}
			String nightWin = "";
			try{nightWin = winElement.getElementsByTag("span").get(1).attr("title");}catch(Exception e){}
			//			风力
			String winPower = winElement.getElementsByTag("i").get(0).text();
			//			采集时间
			calendar.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
			Date keyDate = calendar.getTime();
			String dayStr = sdf.format(keyDate);
			//日期描述
			String rqdes = element.getElementsByTag("h1").text();
			jsontemp.put("wea", wea);
			jsontemp.put("daytimeTem", daytimeTem);
			jsontemp.put("nightTem", nightTem);
			jsontemp.put("daytimeWin", daytimeWin);
			jsontemp.put("nightWin", nightWin);
			jsontemp.put("winPower", winPower);
			jsontemp.put("rqdes", rqdes);
			json.put(dayStr, jsontemp);
		}
		return json;
	}
}
