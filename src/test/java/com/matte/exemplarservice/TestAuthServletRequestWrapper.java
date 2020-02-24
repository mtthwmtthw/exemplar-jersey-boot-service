package com.matte.exemplarservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mockito.Mockito;

import com.matte.exemplarservice.request.AuthServletRequestWrapper;

public class TestAuthServletRequestWrapper {

	private AuthServletRequestWrapper classUnderTest; 
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTH_CODE = "123123124";
	
	@Test
	public void testGetHeadersFromOriginalRequest() {
		HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
		Map<String, String> emptyHeaderMap = new HashMap<>();
		List<String> emptyHeaderNamesToIgnore = new ArrayList<>();
		
		when(httpServletRequest.getHeaderNames()).thenReturn(returnMockAuthHeader());
		when(httpServletRequest.getHeader(AUTHORIZATION_HEADER)).thenReturn(AUTH_CODE);
		
		classUnderTest = new AuthServletRequestWrapper(httpServletRequest, emptyHeaderMap, emptyHeaderNamesToIgnore);
		
		assertNotNull(classUnderTest.getHeader("Authorization"));
	}
	
	@Test
	public void testAddingAdditionalHeaders() {
		HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
		Map<String, String> additionalHeaders = new HashMap<>();
		List<String> emptyHeaderNamesToIgnore = new ArrayList<>();
		additionalHeaders.put(AUTHORIZATION_HEADER, AUTH_CODE);
		additionalHeaders.put("Sher", "pa");
		
		classUnderTest = new AuthServletRequestWrapper(httpServletRequest, additionalHeaders, emptyHeaderNamesToIgnore);
		assertTrue(classUnderTest.getHeader(AUTHORIZATION_HEADER).equals(AUTH_CODE));
		assertTrue(classUnderTest.getHeader("Sher").equals("pa"));
	}

	@Test
	public void testServletHeadersToIgnore() {
		HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
		List<String> headerNamesToIgnore = new ArrayList<>();
		Map<String, String> emptyCustomHeaderMap = new HashMap<>();
		
		headerNamesToIgnore.add(AUTHORIZATION_HEADER);
		
		when(httpServletRequest.getHeaderNames()).thenReturn(returnMockAuthHeader());
		when(httpServletRequest.getHeader(AUTHORIZATION_HEADER)).thenReturn(AUTH_CODE);
		
		classUnderTest = new AuthServletRequestWrapper(httpServletRequest, emptyCustomHeaderMap, headerNamesToIgnore);
		
		assertNull(classUnderTest.getHeader("Authorization"));
	}
	
	@Test
	public void testProvidedHeadersToIgnore() {
		HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
		Map<String, String> additionalHeaders = new HashMap<>();
		List<String> headerNamesToIgnore = new ArrayList<>();
		
		additionalHeaders.put(AUTHORIZATION_HEADER, AUTH_CODE);
		additionalHeaders.put("Sher", "pa");
		
		headerNamesToIgnore.add(AUTHORIZATION_HEADER);
		
		classUnderTest = new AuthServletRequestWrapper(httpServletRequest, additionalHeaders, headerNamesToIgnore);
		
		assertNull(classUnderTest.getHeader(AUTHORIZATION_HEADER));
		assertTrue(classUnderTest.getHeader("Sher").equals("pa"));
	}
	
	private Enumeration<String> returnMockAuthHeader() {
		Map<String, String> authHeader = new HashMap<>();
		authHeader.put(AUTHORIZATION_HEADER, AUTH_CODE);
		final Enumeration<String> headerEnum = Collections.enumeration(authHeader.keySet());
		
		return headerEnum;
	}
	
}
