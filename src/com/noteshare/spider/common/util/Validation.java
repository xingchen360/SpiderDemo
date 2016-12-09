package com.noteshare.spider.common.util;

public class Validation {
	/**
	 * @Description: 对请求路径进行验证
	 * @param link : 请求路径
	 * @return 	   : 返回验证结果
	 * @author     : NoteShare
	 * Create Date : 2016年12月9日 上午11:58:36
	 * @throws
	 */
	public static boolean validateRule(String link)
	{
		if (null == link || "".equals(link) || (!link.startsWith("http://") && !link.startsWith("https://")))
		{
			return false;
		}
		return true;
	}
}
