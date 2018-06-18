package com.zhangli.networkconnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Calendar;
import java.util.Date;

public class NetworkConnector {
	public static void main(String[] args) throws Exception {
		NetworkConnector networkConnector = new NetworkConnector();
		networkConnector.bootstrap();
		
	}
	
	public void bootstrap() throws Exception {
        
		String name = null;
		String passwd = null;		
//		Random random = new Random();
		Calendar instance = Calendar.getInstance();
		Date date = instance.getTime();
		InputStream ins = NetworkConnector.class.getResourceAsStream("/info");
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ins));
		String aline;
		while((aline = bufferedReader.readLine()) != null) {
			if(aline.trim().equals("")) {
				continue;
			}
			if(name == null) {
				name = aline.trim().split(" ")[0];
				continue;
			}
			if(passwd == null && name != null) {
				passwd = aline.trim().split(" ")[0];
				break;
			}
			if(passwd != null && name != null) {
				break;
			}
		}

		String newPasswd = "";
		for(int i=0; i<passwd.length() - 1; i++) {
			newPasswd = newPasswd + "*";
		}
		newPasswd = newPasswd  + passwd.charAt(passwd.length() - 1);
		System.out.println("name: " + name + "\t"+ "passwd: " + newPasswd);   //    + "passwd: " + passwd + "\t" 
//		System.out.println("passwd: " + passwd);  
		String cmd = "ping www.baidu.com";
		String loginUrl = "http://202.38.64.59/cgi-bin/ip";
		String connectUrl = "http://202.38.64.59/cgi-bin/ip";
		
		/*
				<input type="radio" name="type" value="0" id="t0"><label for="t0"> 1教育网出口(国际,仅用教育网访问,适合看文献)</label><br>
				<input type="radio" name="type" value="1" id="t1"><label for="t1"> 2电信网出口(国际,到教育网走教育网)</label><br>
				<input type="radio" name="type" value="2" id="t2"><label for="t2"> 3联通网出口(国际,到教育网走教育网)</label><br>
				<input type="radio" name="type" value="3" id="t3"><label for="t3"> 4电信网出口2(国际,到教育网免费地址走教育网)</label><br>
				<input type="radio" name="type" value="4" id="t4"><label for="t4"> 5联通网出口2(国际,到教育网免费地址走教育网)</label><br>
				<input type="radio" name="type" value="5" checked="" id="t5"><label for="t5"> 6电信网出口3(国际,到教育网走教育网,到联通走联通)</label><br>
				<input type="radio" name="type" value="6" id="t6"><label for="t6"> 7联通网出口3(国际,到教育网走教育网,到电信走电信)</label><br>
				<input type="radio" name="type" value="7" id="t7"><label for="t7"> 8教育网出口2(国际,国内用电信和联通,国际用教育网)</label><br><br>
				<input type="radio" name="type" value="8" id="t8"><label for="t8"> 9移动测试国际出口(国际,无P2P或带宽限制)</label><br><br>
				<br>如访问文献资源或进行P2P下载，建议使用1或9出口<br><br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/link.html" target="_blank">几种出口的解释</a>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://202.38.64.1" target="_blank">各出口流量</a>
		 */

		// [0-8] 9种出口形势
		int connectPort = (int)(Math.random() * 9); 
		String connectParam = "cmd=set&url=URL&type=" + connectPort + "&exp=50400&go=+%BF%AA%CD%A8%CD%F8%C2%E7+";
		connectParam = "cmd=set&url=URL&type=6&exp=14400&go=+%BF%AA%CD%A8%CD%F8%C2%E7+";
		String loginParam = "cmd=login&url=URL&ip=202.38.73.99&name="+name+"&password="+passwd+"&savepass=on&go=%B5%C7%C2%BC%D5%CA%BB%A7";
		Process exec; // = Runtime.getRuntime().exec(cmd);
		BufferedReader br;

		InputStream in;
		StringBuilder sb;
		String line;
		String retStr; 
		System.out.println("自动联网程序启动(powered by zhangli)...");
		while (true) {
			@SuppressWarnings("deprecation")
			int hours = date.getHours();
//			System.out.println(hours);
			if(hours > 1 && hours < 9) {   // 凌晨1点以后 和早上9点之前不联网
				Thread.sleep(1000 * 20 *60);   //一次睡20分钟
				continue;
			}
			boolean isConnected = false;
			String sessionID = "";
			exec = Runtime.getRuntime().exec(cmd);

			Thread.sleep(1000 * 5); // 稍等片刻之后再读取cmd执行的结果

			in = exec.getInputStream();

			br = new BufferedReader(new InputStreamReader(in));
			sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			retStr = sb.toString();
			
			if (retStr.contains("回复")) {
				isConnected = true;
			} else {
				if(retStr.contains("超时")) {					
					isConnected = false;
				}
			}

//			System.out.println("是否联网：" + (isConnected ? "是" : "否") );
			
			if(!isConnected) {
				System.out.println("检测到网络断开，正在重连...");
				
				//没有联网的话。。。。
				String[] response = HttpRequest.sendPost(loginUrl,loginParam);
				sessionID = response[1];
				if(response[0].contains("开通网络") ){    //登录成功
					System.err.println("登录成功！！！");
					//发送http get 请求  
					String response1 = HttpRequest.sendGet(connectUrl, connectParam, sessionID);
//					System.err.println(response);
					if(response1.contains("刷新显示")) {
						System.err.println("成功联网！！！\t端口：" +connectPort);
					}else {
						System.err.println("联网失败，未知错误！！！");
					}
					
				}else{
					System.err.println("登录失败！请检测用户名和密码是否正确。");
				}
			}			

			
			close(br, in);
			exec.destroyForcibly();
			
			Thread.sleep(1000 * 2 * 60); // 3分钟自己启动检查是否联网  1000 * 3 * 60
			
//			break;
		}

	}

	private static void close(BufferedReader br, InputStream in) throws IOException {
		if (br != null) {
			br.close();
		}
		if (in != null) {
			in.close();
		}
	}
}
