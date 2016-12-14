package com.noteshare.spider.chinasky.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.UUID;

import com.noteshare.spider.chinasky.dao.SkyDao;
import com.noteshare.spider.common.exceptions.SpiderException;
import com.noteshare.spider.common.util.SpiderConstant;
import com.noteshare.utils.DBUtil;

import net.sf.json.JSONObject;

public class SkyDaoImpl implements SkyDao{

	@Override
	public void addLiveData(JSONObject json) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_live_data(XH,LX,ZHI,DESNR,CJSJ) values(?,?,?,?,?)";
			if(null != json){
				@SuppressWarnings("unchecked")
				Set<String> keySet = json.keySet();
				for (String key : keySet) {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, UUID.randomUUID().toString());
					pstmt.setString(2, key);
					JSONObject valueJson = (JSONObject) json.get(key);
					String value = valueJson.getString("value");
					String content = valueJson.getString("content");
					pstmt.setString(3, value);
					pstmt.setString(4, content);
					pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
					pstmt.execute();
				}
			}
		} catch (Exception e) {
			if(SpiderConstant.DEBUG){
				e.printStackTrace();
			}
			throw new SpiderException("插入t_live_data数据异常:" + e.getMessage());
		}finally {
			try {
				DBUtil.closeCon(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void addLiveData2(JSONObject json) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into T_LIVE_DATA2(XH,ZWXVALUE,ZWXCONTENT,GMVALUE,GMCONTENT,CYVALUE,CYCONTENT,XCVALUE,XCCONTENT,YDVALUE,YDCONTENT,KQWRKSVALUE,KQWRKSCONTENT,CJSJ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if(null != json){
				JSONObject zwxJson = json.getJSONObject("紫外线指数");
				JSONObject gmJson = json.getJSONObject("感冒指数");
				JSONObject cyJson = json.getJSONObject("穿衣指数");
				JSONObject xcJson = json.getJSONObject("洗车指数");
				JSONObject ydJson = json.getJSONObject("运动指数");
				JSONObject kqwrksJson = json.getJSONObject("空气污染扩散指数");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, UUID.randomUUID().toString());
				String ZWXVALUE = (null != zwxJson ? zwxJson.getString("value") : "");
				String ZWXCONTENT = (null != zwxJson ? zwxJson.getString("content") : "");
				pstmt.setString(2, ZWXVALUE);
				pstmt.setString(3, ZWXCONTENT);
				String GMVALUE = (null != gmJson ? gmJson.getString("value") : "");
				String GMCONTENT = (null != gmJson ? gmJson.getString("content") : "");
				pstmt.setString(4, GMVALUE);
				pstmt.setString(5, GMCONTENT);
				String CYVALUE = (null != cyJson ? cyJson.getString("value") : "");
				String CYCONTENT = (null != cyJson ? cyJson.getString("content") : "");
				pstmt.setString(6, CYVALUE);
				pstmt.setString(7, CYCONTENT);
				String XCVALUE = (null != xcJson ? xcJson.getString("value") : "");
				String XCCONTENT = (null != xcJson ? xcJson.getString("content") : "");
				pstmt.setString(8, XCVALUE);
				pstmt.setString(9, XCCONTENT);
				String YDVALUE = (null != ydJson ? ydJson.getString("value") : "");
				String YDCONTENT = (null != ydJson ? ydJson.getString("content") : "");
				pstmt.setString(10, YDVALUE);
				pstmt.setString(11, YDCONTENT);
				String KQWRKSVALUE = (null != kqwrksJson ? kqwrksJson.getString("value") : "");
				String KQWRKSCONTENT = (null != kqwrksJson ? kqwrksJson.getString("content") : "");
				pstmt.setString(12, KQWRKSVALUE);
				pstmt.setString(13, KQWRKSCONTENT);
				pstmt.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
				pstmt.execute();
			}
		} catch (Exception e) {
			if(SpiderConstant.DEBUG){
				e.printStackTrace();
			}
			throw new SpiderException("插入t_live_data数据异常:" + e.getMessage());
		}finally {
			try {
				DBUtil.closeCon(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void add7DayData(JSONObject json) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into T_SEVEN_DATA(XH,RQ,WEA,DAYTIMETEM,NIGHTTEM,DAYTIMEWIN,NIGHTWIN,WINPOWER,CJSJ,RQDES,DAYTIMEWEA,NIGHTWEA,DAYTIMEWINDPOWER,NIGHTWINDPOWER) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if(null != json){
				@SuppressWarnings("unchecked")
				Set<String> keySet = json.keySet();
				for (String key : keySet) {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, UUID.randomUUID().toString());
					pstmt.setDate(2,new Date(sdf.parse(key).getTime()));
					JSONObject valueJson = (JSONObject) json.get(key);
					String wea = valueJson.getString("wea");
					String daytimeTem = valueJson.getString("daytimeTem");
					String nightTem = valueJson.getString("nightTem");
					String daytimeWin = valueJson.getString("daytimeWin");
					String nightWin = valueJson.getString("nightWin");
					String winPower = valueJson.getString("winPower");
					String rqdes = valueJson.getString("rqdes");
					pstmt.setString(3, wea);
					pstmt.setString(4, daytimeTem);
					pstmt.setString(5, nightTem);
					pstmt.setString(6, daytimeWin);
					pstmt.setString(7, nightWin);
					pstmt.setString(8, winPower);
					pstmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
					pstmt.setString(10,rqdes);
					//将wea拆分为白天晚上
					String daytimewea = wea;
					String nightwea = wea;
					if(null != wea && !"".equals(wea) && wea.contains("转")){
						daytimewea = wea.split("转")[0];
						nightwea = wea.split("转")[1];
					}
					//将winPower拆分为白天晚上
					String daytimewinpower = winPower;
					String nightwinpower = winPower;
					if(null != winPower && !"".equals(winPower) && winPower.contains("-")){
						daytimewinpower = winPower.split("-")[0] + "级";
						nightwinpower = winPower.split("-")[1];
					}
					pstmt.setString(11,daytimewea);
					pstmt.setString(12,nightwea);
					pstmt.setString(13,daytimewinpower);
					pstmt.setString(14,nightwinpower);
					pstmt.execute();
				}
			}
		} catch (Exception e) {
			if(SpiderConstant.DEBUG){
				e.printStackTrace();
			}
			throw new SpiderException("插入T_SEVEN_DATA数据异常:" + e.getMessage());
		}finally {
			try {
				DBUtil.closeCon(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addRealData(JSONObject json) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into T_REAL_DATA(XH,NAMEEN,CITYNAME,CITY,TEMP,TEMPF,WD,WDE,WS,WSE,SD,UPDATETIME,WEATHER,WEATHERE,AQI,AQI_PM25,CJSJ,RQ) " + 
			"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if(null != json){
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, UUID.randomUUID().toString());
				pstmt.setString(2, json.getString("nameen"));
				pstmt.setString(3, json.getString("cityname"));
				pstmt.setString(4, json.getString("city"));
				pstmt.setString(5, json.getString("temp"));
				pstmt.setString(6, json.getString("tempf"));
				pstmt.setString(7, json.getString("WD"));
				pstmt.setString(8, json.getString("wde"));
				pstmt.setString(9, json.getString("WS"));
				pstmt.setString(10, json.getString("wse"));
				pstmt.setString(11, json.getString("SD"));
				//time
				String time = json.getString("time");
				java.util.Date now = new java.util.Date();
				String nowStr = sdf.format(now);
				String UPDATETIME = nowStr + ":" + time;
				pstmt.setString(12, UPDATETIME);
				pstmt.setString(13, json.getString("weather"));
				pstmt.setString(14, json.getString("weathere"));
				pstmt.setString(15, json.getString("aqi"));
				pstmt.setString(16, json.getString("aqi_pm25"));
				pstmt.setTimestamp(17, new Timestamp(System.currentTimeMillis()));
				pstmt.setString(18, json.getString("date"));
				pstmt.execute();
			}
		} catch (Exception e) {
			if(SpiderConstant.DEBUG){
				e.printStackTrace();
			}
			throw new SpiderException("插入T_REAL_DATA数据异常:" + e.getMessage());
		}finally {
			try {
				DBUtil.closeCon(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addTodayData(JSONObject json) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into T_TODAY_DATA(XH,DAYTIMETEM,DAYTIMEWEATHER,DAYTIMEWINDDIRECTION,DAYTIMEWINDLEVEL,NIGHTTEM,NIGHTWEATHER,NIGHTWINDDIRECTION,NIGHTWINDLEVEL,CJSJ) " + 
			"values(?,?,?,?,?,?,?,?,?,?)";
			if(null != json){
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, UUID.randomUUID().toString());
				pstmt.setString(2, json.getString("daytime_temperature"));
				pstmt.setString(3, json.getString("daytime_weather"));
				pstmt.setString(4, json.getString("daytime_windDirection"));
				pstmt.setString(5, json.getString("daytime_windPower"));
				pstmt.setString(6, json.getString("night_temperature"));
				pstmt.setString(7, json.getString("night_weather"));
				pstmt.setString(8, json.getString("night_windDirection"));
				pstmt.setString(9, json.getString("night_windPower"));
				pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
				pstmt.execute();
			}
		} catch (Exception e) {
			if(SpiderConstant.DEBUG){
				e.printStackTrace();
			}
			throw new SpiderException("插入T_TODAY_DATA数据异常" + e.getMessage());
		}finally {
			try {
				DBUtil.closeCon(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
