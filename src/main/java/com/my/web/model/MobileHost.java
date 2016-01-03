package com.my.web.model;

import java.util.List;

public class MobileHost {
	private String host;
	private String ip;
	private List<String> serviceList;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public List<String> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<String> serviceList) {
		this.serviceList = serviceList;
	}
	
	public MobileHost() {
		
	}
	public MobileHost(String host, String ip, List<String> serviceList) {
		this.host = host;
		this.ip = ip;
		this.serviceList = serviceList;
	}
	
}
