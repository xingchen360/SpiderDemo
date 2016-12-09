package com.noteshare.spider.test;

import java.util.List;

import org.junit.Ignore;

import com.noteshare.spider.bean.LinkTypeData;
import com.noteshare.spider.core.ExtractService;
import com.noteshare.spider.rule.Rule;

public class Test
{
	@org.junit.Test
	public void getDatasByClass1()
	{
		Rule rule = new Rule("http://itnoteshare.com/user/星辰/validateNickname.htm",null, null,"cont_right", Rule.CLASS, Rule.POST);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		printf(extracts);
	}
	@Ignore
	@org.junit.Test
	public void getDatasByClass()
	{
		Rule rule = new Rule(
				"http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery",
				new String[] { "query.enterprisename","query.registationnumber" }, new String[] { "子长县乔森照明门市","" },
				"cont_right", Rule.CLASS, Rule.POST);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		printf(extracts);
	}
	
	@Ignore
	@org.junit.Test
	public void getDatasByCssQuery()
	{
		Rule rule = new Rule("http://www.11315.com/search",
				new String[] { "name" }, new String[] { "兴网" },
				"div.g-mn div.con-model", Rule.SELECTION, Rule.GET);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		printf(extracts);
	}

	public void printf(List<LinkTypeData> datas)
	{
		for (LinkTypeData data : datas)
		{
			System.out.println(data.getLinkText());
			System.out.println(data.getLinkHref());
			System.out.println("***********************************");
		}

	}
}
