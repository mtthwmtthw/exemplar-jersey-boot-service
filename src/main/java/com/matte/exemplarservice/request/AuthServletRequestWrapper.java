package com.matte.exemplarservice.request;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AuthServletRequestWrapper extends HttpServletRequestWrapper{
	private Map<String, String> additionalHeaders;
	private List<String> headersToIgnore;
	
	public AuthServletRequestWrapper(
			HttpServletRequest request, 
			Map<String, String> additionalHeaders, 
			List<String> headersToIgnore) {
		super(request);
		this.additionalHeaders = filterHeadersToIgnore(additionalHeaders, headersToIgnore);
		this.headersToIgnore = headersToIgnore;
	}
	
	@Override
    public String getHeader(String name) {
		String headerValue = additionalHeaders.get(name);
		
		if (headersToIgnore.contains(name)) {
			return null;
		}
        
        if (headerValue != null){
            return headerValue;
        }
        
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }
	
	@Override
	public Enumeration<String> getHeaderNames() {
		HttpServletRequest servletRequest = (HttpServletRequest)getRequest();
		Set<String> headers = new HashSet<String>();
		
		for (Enumeration<String> e = servletRequest.getHeaderNames();  e.hasMoreElements();) {
			String key = (String) e.nextElement();
			if (!headersToIgnore.contains(key)) {
				headers.add(key);
			}
		}
		
		return Collections.enumeration(headers);
	}
	
	private static Map<String, String> filterHeadersToIgnore(Map<String, String> additionalHeaders, List<String> headersToIgnore) {
		Map<String, String> filteredHeaders = new HashMap<>();
		
		for (Map.Entry<String, String> entry : additionalHeaders.entrySet()) {
			if (!headersToIgnore.contains(entry.getKey())) {
				filteredHeaders.put(entry.getKey(), entry.getValue());
			}
		}
		
		return filteredHeaders;
	}
	
}
