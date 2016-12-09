package com.noteshare.spider.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.noteshare.spider.chinasky.services.RealTimeService;
import com.noteshare.spider.chinasky.services.impl.RealTimeServiceImpl;
import com.noteshare.spider.common.beans.RequestParams;

public class TestSpiderUtil {
	private RequestParams resParam = null;
	
	@Before
	public void init(){
		Map<String,String> paramMap = new HashMap<String, String>();
		resParam = new RequestParams("http://www.weather.com.cn/weather1d/101190202.shtml",0,paramMap);
	}
	@Ignore
	@Test
	public void getDocumentTest() throws IOException{
		Document doc = SpiderUtil.getDocument(resParam);
		RealTimeService realTimeService = new RealTimeServiceImpl();
		Element todayElement = realTimeService.getTodayDiv(doc,"today");
		Map<String,String> todayDataMap = realTimeService.getTodayData(todayElement);
		System.out.println(todayDataMap);
	}
	
	
	
	public static void main(String[] args) {
		WebClient wc = new WebClient(BrowserVersion.CHROME);
	    wc.getOptions().setUseInsecureSSL(true);  
	    wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true  
	    wc.getOptions().setCssEnabled(false); // 禁用css支持  
	    wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常  
	    wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
	    wc.getOptions().setDoNotTrackEnabled(false);  
	    HtmlPage page = wc.getPage("http://localhost:8080/strurts2fileupload/main.html");  
	  
	    DomNodeList<DomElement> links = page.getElementsByTagName("a");  
	  
	    for (DomElement link : links) {  
	        System.out  
	                .println(link.asText() + "  " + link.getAttribute("href"));  
	    }  
	}
}
