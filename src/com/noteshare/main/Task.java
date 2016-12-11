package com.noteshare.main;

import java.lang.reflect.Field;
import java.util.TimerTask;
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
			System.out.println("任务开始执行！！！");
			/*try {
				Map<String, String> paramMap = new HashMap<String, String>();
				RequestParams resParam = new RequestParams("http://www.weather.com.cn/weather1d/101190202.shtml", 0, paramMap);
				Document doc = SpiderUtil.getDocument(resParam, null);
				RealTimeService realTimeService = new RealTimeServiceImpl();
				JSONObject json = realTimeService.getLiveIndex(doc);
				File file = new File("D:\\test.txt");
				FileOutputStream fos = new FileOutputStream(file,true);
				PrintWriter pw = new PrintWriter(fos);
				pw.write(json.toString());
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}else{
			System.out.println("任务已被暂停执行！！！");
		}
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
