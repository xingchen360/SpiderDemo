package com.noteshare.spider.common.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
	
	
	
	public static void main(String[] args) throws Exception {
		WebClient wc = new WebClient(BrowserVersion.CHROME);
	    wc.getOptions().setUseInsecureSSL(true);  
	    wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true  
	    wc.getOptions().setCssEnabled(false); // 禁用css支持  
	    wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常  
	    wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
	    wc.getOptions().setDoNotTrackEnabled(false);  
	    HtmlPage page = wc.getPage("http://www.weather.com.cn/weather1d/101190202.shtml");
	    ////*[@id="today"]
		List<HtmlDivision> todayDivs = (List<HtmlDivision>)page.getByXPath("//*[@id=\"today\"]");
	    System.out.println(todayDivs);
	    System.out.println(todayDivs.get(0).asXml());
	    System.out.println(todayDivs.get(0).asText());
	    System.out.println(todayDivs.get(0).getTextContent());
	}
}

class MinimalTest {
public void testtt() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
    WebClient client = new WebClient();
    client.getOptions().setJavaScriptEnabled(false);
    client.getOptions().setCssEnabled(false);
    System.out.println("Fetching front page");
    HtmlPage frontPage = client.getPage("http://living.scotsman.com/sectionhome.aspx?sectionID=7063");
    List<ArticleInfo> articleInfos = extractArticleInfo(frontPage);

    for (ArticleInfo info : articleInfos)
    {
        System.out.println("Title: " + info.getTitle());
        System.out.println("Intro: " + info.getFirstPara());
        System.out.println("Link: " + info.getLink());
    }
}

@SuppressWarnings("unchecked") // xpath returns List<?>
private static List<ArticleInfo> extractArticleInfo(HtmlPage frontPage) {
    System.out.println("Extracting article links");
    List<HtmlDivision> articleDivs = (List<HtmlDivision>) frontPage.getByXPath("//div[@class='article']");
    System.out.println(String.format("Found %d articles", articleDivs.size()));
    List<ArticleInfo> articleLinks = new ArrayList<ArticleInfo>(articleDivs.size());
    for (HtmlDivision div : articleDivs) {
        articleLinks.add(ArticleInfo.constructFromArticleDiv(div));
    }
    return articleLinks;
}

private static class ArticleInfo {
    private final String title;
    private final String link;
    private final String firstPara;

    public ArticleInfo(final String link, final String title, final String firstPara) {
        this.link = link;
        this.title = title;
        this.firstPara = firstPara;
    }
    public static ArticleInfo constructFromArticleDiv(final HtmlDivision div) {
        String link = ((DomText) div.getFirstByXPath("//a/@href/text()")).asText();
        String title = ((DomText) div.getFirstByXPath("//span[@class='mth3']/text()")).asText();
        String firstPara = ((DomText) div.getFirstByXPath("//span[@class='mtp']/text()")).asText();
            return new ArticleInfo(link, title, firstPara);
        }
        public String getTitle() {
            return title;
        }
        public String getFirstPara() {
            return firstPara;
        }
        public String getLink() {
            return link;
        }
    }  
}