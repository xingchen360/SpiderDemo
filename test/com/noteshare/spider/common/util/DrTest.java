package com.noteshare.spider.common.util;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;

import com.ibatis.common.jdbc.ScriptRunner;
import com.noteshare.utils.DBUtil;

public class DrTest {
	public static void main(String[] args) {
		try {
			Connection conn = DBUtil.getConnection();
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			runner.runScript(new FileReader(new File("E:/gitcodes/eclipsegitres/SpiderDemo/test/com/noteshare/spider/common/util/INITBASE.sql")));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
