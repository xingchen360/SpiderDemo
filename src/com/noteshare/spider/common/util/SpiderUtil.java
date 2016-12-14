package com.noteshare.spider.common.util;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.noteshare.spider.common.beans.RequestParams;
import com.noteshare.spider.common.exceptions.SpiderException;

public class SpiderUtil {

	/**
	 * @Description: 获取html文档对象
	 * @param resParams : 请求参数
	 * @author     : NoteShare
	 * Create Date : 2016年12月9日 下午12:09:32
	 * @return 返回html文档对象
	 * @throws IOException
	 */
	public static Document getDocument(RequestParams resParams,Map<String,String> headerMap) throws IOException {
		//判断请求路径是否合法
		boolean bool = Validation.validateRule(resParams.getLink());
		if(bool){
			Connection conn = Jsoup.connect(resParams.getLink());
			if(null != headerMap && !headerMap.isEmpty()){
				conn.headers(headerMap);
			}
			Map<String,String> resMap = resParams.getParamMap();
			//添加请求参数
			if(null != resMap && !resMap.isEmpty()){
				conn.data(resMap);
			}
			// 设置请求类型
			Document doc = null;
			switch (resParams.getRequestType())
			{
			case SpiderConstant.GET:
				doc = conn.timeout(100000).get();
				break;
			case SpiderConstant.POST:
				doc = conn.timeout(100000).post();
				break;
			}
			return doc;
		}else{
			throw new SpiderException("链接路径不合法");
		}
	}
	/**
	 * @Description: 添加的一个获取doc的方法，尝试解决EOFException问题,主要是了为了可以指定编码
	 * @param resParams
	 * @param headerMap
	 * @return doc ： 文档对象
	 * @throws IOException
	 * @author     : NoteShare
	 * Create Date : 2016年12月14日 下午5:51:02
	 * @throws
	 */
	public static Document getDocumentByJsoupParse(RequestParams resParams,Map<String,String> headerMap) throws IOException {
		//判断请求路径是否合法
		boolean bool = Validation.validateRule(resParams.getLink());
		if(bool){
			Document doc = Jsoup.parse(new URL(resParams.getLink()).openStream(), "UTF-8", resParams.getLink());
			return doc;
		}else{
			throw new SpiderException("链接路径不合法");
		}
	}
	/**
	 * @Description: 根据选择器类型和选择器的值获取对应的元素节点集合
	 * @param selectorType：选择器类型
	 * @param selectText ：选择器的值
	 * @param doc ： 文档对象
	 * @return Elements ： 返回选择的元素集合
	 * @author     : NoteShare
	 * Create Date : 2016年12月9日 下午3:10:33
	 * @throws
	 */
	public static Elements getElements(int selectorType,String selectText,Document doc){
		//处理返回数据
		Elements results = new Elements();
		switch (selectorType)
		{
		case SpiderConstant.IDSELECTOR:
			Element result = doc.getElementById(selectText);
			results.add(result);
			break;
		case SpiderConstant.CLASSSELECTOR:
			results = doc.getElementsByClass(selectText);
			break;
		case SpiderConstant.ATTRSELECTOR:
			results = doc.select(selectText);
			break;
		default:
			results = doc.getElementsByTag(SpiderConstant.DEFAULTTAG);
		}
		return results;
	}
}
