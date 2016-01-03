package com.my.web.controller.mobileservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.alibaba.fastjson.JSON;
import com.my.web.model.MobileHost;

public class GsdController implements Controller {
	/**
	 * host->MobileHost
	 */
	private ConcurrentHashMap<String, MobileHost> map = new ConcurrentHashMap<String, MobileHost>();

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if(action == null || action.equals(""))
			return null;
		else{
			action = action.trim().toLowerCase();
			switch(action){
				case "register":
					handleRegisterRequest(request, response);
					break;
				case "update":
					handleUpdateRequest(request, response);
					break;
				case "query":
					handleQueryRequest(request, response);
					break;
				default:
					break;
			}
		}
		return null;
	}

	/**
	 * client query ip according to host
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void handleQueryRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String host = request.getParameter("host");
		MobileHost mh = map.get(host);
		
		PrintWriter pw=response.getWriter();
		pw.print(JSON.toJSONString(mh));
		pw.flush();
		pw.close();
	}

	/**
	 * MobileHost update ip
	 * @param request
	 * @param response
	 */
	private void handleUpdateRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String host = request.getParameter("host");
		String ip = request.getParameter("ip");
		MobileHost mh = map.get(host);
		mh.setIp(ip);
	}

	/**
	 * MobileHost register itself ip and services
	 * @param request
	 * @param response
	 */
	private void handleRegisterRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String json = request.getParameter("mobilehost");
		MobileHost mh = JSON.parseObject(json, MobileHost.class);
		map.put(mh.getHost(), mh);
	}

	/**
	 * unit test
	 */
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		MobileHost mh = new MobileHost("cml", "192.168.2.78", list);
		ConcurrentHashMap<String, MobileHost> map = new ConcurrentHashMap<String, MobileHost>();
		map.put("cml", mh);
		
		mh = map.get("cml");
		mh.setIp("192.168.2.79");
		
		System.out.println("ip:"+map.get("cml").getIp());
	}
}
