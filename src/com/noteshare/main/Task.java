package com.noteshare.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import org.jsoup.nodes.Document;

import com.noteshare.spider.chinasky.dao.SkyDao;
import com.noteshare.spider.chinasky.dao.impl.SkyDaoImpl;
import com.noteshare.spider.chinasky.services.SkyService;
import com.noteshare.spider.chinasky.services.impl.SkyServiceImpl;
import com.noteshare.spider.common.beans.RequestParams;
import com.noteshare.spider.common.util.SpiderUtil;
import com.noteshare.utils.Config;

import net.sf.json.JSONObject;
/**
 * @ClassName			: Task 
 * @Description			: 拉取天气数据的任务；此类添加了修改任务间隔执行时间的方法setPeriod，并添加了控制任务暂停与开启的变量flag。
 * @author 				： NoteShare 
 * @date 				： 2016年12月11日 下午10:12:29
 */
public class Task extends TimerTask{
	//标志任务是否开始执行，默认不执行
	private boolean flag = false;
	
	public void setPeriod(long period) {  
        //缩短周期，执行频率就提高  
        setDeclaredField(TimerTask.class, this, "period", period);  
    } 
	
	//通过反射修改字段的值  
    static boolean setDeclaredField(Class<?> clazz, Object obj,String name, Object value) {  
        try {
            Field field = clazz.getDeclaredField(name);  
            field.setAccessible(true);  
            field.set(obj, value);  
            return true;  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            return false;  
        }
    }  
	
	@Override
	public void run() {
		if(flag){
			//System.out.println("任务开始执行！！！");
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(now);
			Config config = new Config("properties.properties");
			String logPath = config.parseString("logPath");
			String filesPath = logPath + "skylog\\";
			File files = new File(filesPath);
			if(!files.exists()){
				files.mkdirs();
			}
			//日志文件
			File file = new File(filesPath + today + ".log");
			FileOutputStream fos = null;
			PrintWriter pw = null;
			SkyDao dao = new SkyDaoImpl();
			try {
				fos = new FileOutputStream(file,true);
				pw = new PrintWriter(fos);
				SkyService skyService = new SkyServiceImpl();
				//实时数据获取
				try{
					Map<String,String> realParamMap = new HashMap<String, String>();
					RequestParams realResParam = new RequestParams("http://d1.weather.com.cn/sk_2d/101190202.html?_=1481362573951",0,realParamMap);
					Map<String,String> realHeaderMap = new HashMap<String, String>();
					realHeaderMap.put("Referer", "http://www.weather.com.cn/weather1d/101190202.shtml");
					Document realDoc = SpiderUtil.getDocument(realResParam,realHeaderMap);
					//实时数据
					JSONObject realJson = skyService.getRealData(realDoc);
					//System.out.println("===========实时数据=====start=======");
					//System.out.println(realJson);
					dao.addRealData(realJson);
					//System.out.println("===========实时数据======end======");
				}catch(Exception e){
					pw.write(new Date().toString() +  ":====抓取实时数据异常;异常信息:\n" + e.getMessage() + "\n");
				}
				//当天数据
				try{
					Map<String,String> todayParamMap = new HashMap<String, String>();
					RequestParams todayResParam = new RequestParams("http://www.weather.com.cn/weather1d/101190202.shtml",0,todayParamMap);
					Document todayDoc = SpiderUtil.getDocument(todayResParam,null);
					//实时数据
					JSONObject todayJson = skyService.getTodayData(todayDoc);
					//System.out.println("===========实时数据=====start=======");
					dao.addTodayData(todayJson);
					//System.out.println("===========实时数据======end======");
				}catch(Exception e){
					pw.write(new Date().toString() +  ":====抓取实时数据异常;异常信息\n" + e.getMessage() + "\n");
				}
				//生活指数
				try {
					Map<String,String> liveParamMap = new HashMap<String, String>();
					RequestParams liveResParam = new RequestParams("http://www.weather.com.cn/weather1d/101190202.shtml",0,liveParamMap);
					Document liveDoc = SpiderUtil.getDocument(liveResParam,null);
					SkyService liveTimeService = new SkyServiceImpl();
					JSONObject liveJson = liveTimeService.getLiveIndex(liveDoc);
					//System.out.println("===========生活指数======start=====");
					//System.out.println(liveJson);
					dao.addLiveData(liveJson);
					dao.addLiveData2(liveJson);
					//System.out.println("===========生活指数======end======");
				} catch (IOException e) {
					pw.write(new Date().toString() +  ":====抓取生活指数异常;异常信息:\n" + e.getMessage() + "\n");
				}
				//7天数据
				try {
					Map<String,String> sevdParamMap = new HashMap<String, String>();
					RequestParams sevdResParam = new RequestParams("http://www.weather.com.cn/weather/101190202.shtml",0,sevdParamMap);
					Document sevdDoc = SpiderUtil.getDocument(sevdResParam,null);
					JSONObject sevdjson = skyService.get7dData(sevdDoc);
					//System.out.println("===========7天数据======start=====");
					//System.out.println(sevdjson);
					dao.add7DayData(sevdjson);
					//System.out.println("===========7天数据======end======");
				} catch (IOException e) {
					pw.write(new Date().toString() +  ":====抓取7天数据异常;异常信息:\n" + e.getMessage() + "\n");
				}
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}finally {
				if(null != fos){
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(null != pw){
					pw.close();
				}
			}
		}else{
			//System.out.println("任务已被暂停执行！！！");
		}
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
