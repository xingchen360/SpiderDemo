package com.noteshare.spider.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.noteshare.spider.chinasky.services.RealTimeService;
import com.noteshare.spider.chinasky.services.impl.RealTimeServiceImpl;
import com.noteshare.spider.common.beans.RequestParams;

import net.sf.json.JSONObject;

public class TestSpiderUtil {
	private RequestParams resParam = null;
	
	@Before
	public void init(){
		Map<String,String> paramMap = new HashMap<String, String>();
		//以下是拿实时数据的路径
		resParam = new RequestParams("http://d1.weather.com.cn/sk_2d/101190202.html?_=1481508057075", 0,paramMap);
		//以下是拿生活指数数据的请求路径
		//resParam = new RequestParams("http://www.weather.com.cn/weather1d/101190202.shtml",0,paramMap);
		//以下是获取七天天气数据请求路径
		//resParam = new RequestParams("http://www.weather.com.cn/weather/101190202.shtml",0,paramMap);
	}
	
	/**
	 * @Description: 获取实时数据测试，测试时请修改init()方法中的路径
	 * @throws IOException
	 * @author     : NoteShare
	 * Create Date : 2016年12月10日 下午1:17:22
	 * @throws
	 * 测试结果：{"nameen":"jiangyin","cityname":"江阴","city":"101190202","temp":"11","tempf":"51","WD":"东风","wde":"E ","WS":"3级","wse":"&lt;12km/h","SD":"58%","time":"12:55","weather":"多云","weathere":"Cloudy","weathercode":"d01","qy":"1026","njd":"暂无实况","sd":"58%","rain":"0","rain24h":"0","aqi":"60","limitnumber":"","aqi_pm25":"60","date":"12月10日(星期六)"}
	 */
	@Test
	public void getTodayDataTest(){
		try {
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Referer", "http://www.weather.com.cn/weather1d/101190202.shtml");
			Document doc = SpiderUtil.getDocument(resParam,headerMap);
			RealTimeService realTimeService = new RealTimeServiceImpl();
			JSONObject json = realTimeService.getTodayData(doc);
			System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 获取生活指数数据测试，测试时请修改init()方法中的路径
	 * @throws IOException
	 * @author     : NoteShare
	 * Create Date : 2016年12月10日 上午10:26:32
	 * @throws
	 * 测试结果：{"紫外线指数":{"value":"最弱","content":"辐射弱，涂擦SPF8-12防晒护肤品。"},"感冒指数":{"value":"较易发","content":"天较凉，增加衣服，注意防护。"},"穿衣指数":{"value":"较冷","content":"建议着厚外套加毛衣等服装。"},"洗车指数":{"value":"较适宜","content":"无雨且风力较小，易保持清洁度。"},"运动指数":{"value":"较适宜","content":"气温较低，推荐您进行室内运动。"},"空气污染扩散指数":{"value":"中","content":"易感人群应适当减少室外活动。"}}
	 */
	@Ignore
	@Test
	public void getLiveIndexTest() throws IOException{
		Document doc = SpiderUtil.getDocument(resParam,null);
		RealTimeService realTimeService = new RealTimeServiceImpl();
		JSONObject json = realTimeService.getLiveIndex(doc);
		System.out.println(json);
	}
	
	/**
	 * @Description: 获取7天天气预报数据
	 * @throws IOException
	 * @author     : NoteShare
	 * Create Date : 2016年12月10日 下午3:00:58
	 * @throws
	 * 测试结果：
	 * {"2016-12-10":{"wea":"多云","daytimeTem":"12","nightTem":"7℃","daytimeWin":"东北风","nightWin":"东北风","winPower":"微风"},"2016-12-11":{"wea":"多云","daytimeTem":"14","nightTem":"8℃","daytimeWin":"东南风","nightWin":"东南风","winPower":"微风"},"2016-12-12":{"wea":"阴","daytimeTem":"17","nightTem":"9℃","daytimeWin":"北风","nightWin":"北风","winPower":"微风"},"2016-12-13":{"wea":"小雨转阴","daytimeTem":"13","nightTem":"4℃","daytimeWin":"北风","nightWin":"北风","winPower":"3-4级"},"2016-12-14":{"wea":"阴转多云","daytimeTem":"9","nightTem":"-1℃","daytimeWin":"西北风","nightWin":"西北风","winPower":"4-5级转3-4级"},"2016-12-15":{"wea":"多云","daytimeTem":"7","nightTem":"-2℃","daytimeWin":"西北风","nightWin":"西北风","winPower":"微风"},"2016-12-16":{"wea":"多云","daytimeTem":"9","nightTem":"2℃","daytimeWin":"东南风","nightWin":"东南风","winPower":"微风"}}
	 */
	@Ignore
	@Test
	public void get7dDataTest(){
		try {
			Document doc = SpiderUtil.getDocument(resParam,null);
			RealTimeService realTimeService = new RealTimeServiceImpl();
			JSONObject json = realTimeService.get7dData(doc);
			System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
