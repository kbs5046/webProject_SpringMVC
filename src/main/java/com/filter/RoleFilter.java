//package com.filter;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * Servlet Filter implementation class RoleFilter
// */
//@WebFilter( filterName = "RoleFilter")
//public class RoleFilter implements Filter {
//
//	 
//	private Map<String,String> roleAndUrl = new HashMap<>();
//	
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		
//		roleAndUrl.put("deleteSignUp", "admin");
//	}
//	
//	/* @see Filter#destroy()
//	 */
//	public void destroy() {
//		
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		
//		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
//		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
//		
//		String resourceName = httpServletRequest.getServletPath();
//		
//		if(resourceName.contains("delete")) {
//			HttpSession httpSession = httpServletRequest.getSession();
//			if(httpSession != null) {
//				String role = (String)httpSession.getAttribute("role");
//				if(role.equalsIgnoreCase("admin")) {
//					chain.doFilter(request, response);
//				}
//				else {
//					httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/paginateUsers?message= Hey! you can not delete it");
//				}
//				
//			}
//		}
//		
//		/*if(resourceName.contains("editSignUp")) {
//			HttpSession httpSession = httpServletRequest.getSession();
//			if(httpSession != null) {
//				int uid = (int)httpSession.getAttribute("uid");
//				String selectedID = (String)httpSession.getAttribute("selectedID");
//				int sID = Integer.parseInt(selectedID);
//				String role = (String)httpSession.getAttribute("role");
//				if(uid == sID || role.equalsIgnoreCase("admin")) {
//					chain.doFilter(request, response);
//				}
//				else {
//					httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/users?message= Hey! you can not edit others info");
//				}
//				
//			}
//		}*/
//		
//		else {
//			chain.doFilter(request, response);
//		}
//		
//		
//	}
//
//
//}
