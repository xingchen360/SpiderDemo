package com.noteshare.spider.chinasky.services.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.noteshare.spider.chinasky.services.RealTimeService;
import com.noteshare.spider.common.util.SpiderConstant;
import com.noteshare.spider.common.util.SpiderUtil;

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
	public Map<String, String> getTodayData(Element todayElement) {
		//数据集合
		Map<String,String> dataMap = new HashMap<String, String>();
		if(null != todayElement){
			//1:获取数据更新时间
			//*[@id="fc_24h_internal_update_time"]
			Element updateTimeElement = todayElement.getElementById("fc_24h_internal_update_time");
			String updateTime = null;
			if(null != updateTimeElement){
				updateTime = updateTimeElement.val();
			}
			dataMap.put("updateTime", updateTime);
			//2:获取温度
			//*[@id="today"]/div[1]/div/div[3]/span
			String temperature = null;
			try{
				Element temperatureElement = todayElement.getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementsByTag("div").get(2).getElementsByTag("span").get(0);
				temperature = temperatureElement.text();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				dataMap.put("temperature", temperature);
			}
			//3:湿度
			//*[@id="today"]/div[1]/div/div[1]/span
			//*[@id="today"]/div[1]/div/div[1]/em
			String humidityType = null;
			String humidityValue = null;
			try{
				//类型
				Element humidityTypeElement = todayElement.getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementsByTag("span").get(0);
				humidityType = humidityTypeElement.text();
				//值
				Element humidityValueElement = todayElement.getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementsByTag("em").get(0);
				humidityValue = humidityValueElement.text();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				dataMap.put("humidityType", humidityType);
				dataMap.put("humidityValue", humidityValue);
			}
			//4：风向;风力
			//*[@id="today"]/div[1]/div/div[2]/span
			//*[@id="today"]/div[1]/div/div[2]/em
			String windDirection = null;
			String windPower = null;
			try{
				//类型
				Element windDirectionElement = todayElement.getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementsByTag("div").get(1).getElementsByTag("span").get(0);
				windDirection = windDirectionElement.text();
				//值
				Element windPowerElement = todayElement.getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementsByTag("div").get(1).getElementsByTag("em").get(0);
				windPower = windPowerElement.text();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				dataMap.put("windDirection", windDirection);
				dataMap.put("windPower", windPower);
			}
			//5:采集时间
			long now = new Date().getTime();
			dataMap.put("fetchTime", "" + now);
			return dataMap;
		}else{
			return null;
		}
	}
}
