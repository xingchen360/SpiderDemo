package com.noteshare.spider.chinasky.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.UUID;

import com.noteshare.spider.chinasky.dao.SkyDao;
import com.noteshare.utils.DBUtil;

import net.sf.json.JSONObject;

public class SkyDaoImpl implements SkyDao{

	@Override
	public void addLiveData(JSONObject json) {
		//{"紫外线指数":{"value":"最弱","content":"辐射弱，涂擦SPF8-12防晒护肤品。"},
		//"感冒指数":{"value":"较易发","content":"天较凉，增加衣服，注意防护。"},
		//"穿衣指数":{"value":"较冷","content":"建议着厚外套加毛衣等服装。"},
		//"洗车指数":{"value":"较适宜","content":"无雨且风力较小，易保持清洁度。"},
		//"运动指数":{"value":"较适宜","content":"气温较低，推荐您进行室内运动。"},
		//"空气污染扩散指数":{"value":"中","content":"易感人群应适当减少室外活动。"}}
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
					Date gathertime = new Date(new java.util.Date().getTime());
					pstmt.setString(3, value);
					pstmt.setString(4, content);
					pstmt.setDate(5, gathertime);
					pstmt.execute();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		//{"2016-12-10":{"wea":"多云","daytimeTem":"12","nightTem":"7℃","daytimeWin":"东北风","nightWin":"东北风","winPower":"微风"},
		//"2016-12-11":{"wea":"多云","daytimeTem":"14","nightTem":"8℃","daytimeWin":"东南风","nightWin":"东南风","winPower":"微风"},
		//"2016-12-12":{"wea":"阴","daytimeTem":"17","nightTem":"9℃","daytimeWin":"北风","nightWin":"北风","winPower":"微风"},
		//"2016-12-13":{"wea":"小雨转阴","daytimeTem":"13","nightTem":"4℃","daytimeWin":"北风","nightWin":"北风","winPower":"3-4级"},
		//"2016-12-14":{"wea":"阴转多云","daytimeTem":"9","nightTem":"-1℃","daytimeWin":"西北风","nightWin":"西北风","winPower":"4-5级转3-4级"},
		//"2016-12-15":{"wea":"多云","daytimeTem":"7","nightTem":"-2℃","daytimeWin":"西北风","nightWin":"西北风","winPower":"微风"},
		//"2016-12-16":{"wea":"多云","daytimeTem":"9","nightTem":"2℃","daytimeWin":"东南风","nightWin":"东南风","winPower":"微风"}}
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into T_SEVEN_DATA(XH,RQ,WEA,DAYTIMETEM,NIGHTTEM,DAYTIMEWIN,NIGHTWIN,WINPOWER,CJSJ,STARTTIME) values(?,?,?,?,?,?,?,?,?,?)";
			if(null != json){
				@SuppressWarnings("unchecked")
				Set<String> keySet = json.keySet();
				for (String key : keySet) {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, UUID.randomUUID().toString());
					pstmt.setString(2, key);
					JSONObject valueJson = (JSONObject) json.get(key);
					String wea = valueJson.getString("wea");
					String daytimeTem = valueJson.getString("daytimeTem");
					String nightTem = valueJson.getString("nightTem");
					String daytimeWin = valueJson.getString("daytimeWin");
					String nightWin = valueJson.getString("nightWin");
					String winPower = valueJson.getString("winPower");
					Date gathertime = new Date(new java.util.Date().getTime());
					pstmt.setString(3, wea);
					pstmt.setString(4, daytimeTem);
					pstmt.setString(5, nightTem);
					pstmt.setString(6, daytimeWin);
					pstmt.setString(7, nightWin);
					pstmt.setString(8, winPower);
					pstmt.setDate(9, gathertime);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					pstmt.setString(10, sdf.format(new java.util.Date()));
					pstmt.execute();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		//{"nameen":"jiangyin","cityname":"江阴","city":"101190202",
		//"temp":"11","tempf":"51","WD":"东风","wde":"E ","WS":"3级",
		//"wse":"&lt;12km/h","SD":"58%","time":"12:55","weather":"多云",
		//"weathere":"Cloudy","weathercode":"d01","qy":"1026","njd":"暂无实况",
		//"sd":"58%","rain":"0","rain24h":"0","aqi":"60","limitnumber":"",
		//"aqi_pm25":"60","date":"12月10日(星期六)"}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into T_TODAY_DATA(XH,NAMEEN,CITYNAME,CITY,TEMP,TEMPF,WD,WDE,WS,WSE,SD,UPDATETIME,WEATHER,WEATHERE,AQI,AQI_PM25,CJSJ) " + 
			"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
				pstmt.setDate(17, new Date(new java.util.Date().getTime()));
				pstmt.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtil.closeCon(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
