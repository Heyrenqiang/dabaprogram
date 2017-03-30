package name.lxm.targets.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.StringBuffer;


/**
 * A simple HTTP server to save the time
 * 
 * @author Xiaoming Mar 12, 2017
 *
 */
public class HttpServer extends Thread{
	private int listenport;
	
	private Router router;
	private static boolean bRun = true;
	
	public HttpServer(int port)
	{
		listenport = port;
		router = new Router();
	}
	
	
	public void run()
	{
		try {
			ServerSocket ss = new ServerSocket(listenport);
			Socket s ;
			bRun = true;
			while(bRun && (s = ss.accept()) != null){ //保持监听状态
				InputStreamReader isr = new InputStreamReader(s.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				PrintWriter pw = new PrintWriter(s.getOutputStream());
				String line;
				String resp;
				//首先处理输入信息
				while(true){
					line = br.readLine();
					if(line == null || line.trim().isEmpty()){ //如果不是合法的HTTP请求的话
						resp = DefaultHandler.INSTANCE.doProcess("");
						pw.print(httpHead(resp.length()));
						pw.println(resp);
						pw.flush();
						pw.close();
						br.close();
						s.close();								
						break;
					}
					if(line.startsWith("GET") || line.startsWith("POST")){ //合法的HTTP请求
						System.out.println("Recieved Request: " + line);
						String[] sa = line.split(" ");
						//寻找合适的处理程序
						Handler handler = router.findActionHandler(sa[1]);
						if(handler != null) //找到了
							resp = handler.doProcess(sa[1]);
						else //没找到，用默认的
							resp = DefaultHandler.INSTANCE.doProcess(line);
						pw.print(httpHead(resp.getBytes().length));
						pw.println(resp);
						pw.flush();
						pw.close();
						br.close();
						s.close();
						break;
					}
				}
			}
			ss.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private String httpHead(int length) {
		StringBuffer sb = new StringBuffer();
		sb.append("HTTP/1.1 200 OK\r\n");
		sb.append("Content-Length: ");
		sb.append(length);
		sb.append("\r\nContent-Type: Application/JSON; charset=UTF-8");
		sb.append("\r\n");
		sb.append("\r\n");
		return sb.toString();
	}


	/**
	 * This main() is used for test only.
	 * @param args
	 */
	public static void main(String[] args)
	{
		HttpServer server = new HttpServer(4444);
		server.run();
	}
}
