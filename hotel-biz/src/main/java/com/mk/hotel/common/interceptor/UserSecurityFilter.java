package com.mk.hotel.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserSecurityFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(UserSecurityFilter.class);

	private List<String> excludePatterns;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		writeLogInfo(request, response);

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		String url = ((HttpServletRequest) request).getRequestURI();
		if (this.matchExcludePatterns(((HttpServletRequest) request).getContextPath(), url)) {

			//
			String accessToken = ((HttpServletRequest) request).getHeader("token");
			logger.info("token :{}",accessToken);

			if (StringUtils.isNotBlank(accessToken)) {
				if ("88db86e86f4884ab2244bff28b9252e3".equals(accessToken)) {
					chain.doFilter(request, response);
					return;
				}
			}

			//
			response.setContentType("application/json");
			Map<String, Object> errorMap = Maps.newHashMap();
			errorMap.put("success","F");
			errorMap.put("errorMessage", "token error");
			response.getWriter().write(new ObjectMapper().writeValueAsString(errorMap));
			response.flushBuffer();

		} else {
			logger.info("not matchExcludePatterns");
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		String initParameter = arg0.getInitParameter("excludePatterns");
		this.excludePatterns = Splitter.on(',').trimResults().omitEmptyStrings().splitToList(initParameter);
	}

	/**
	 *
	 * @param contextpath
	 * @param url
	 * @return
	 */
	public boolean matchExcludePatterns(String contextpath, String url) {
		boolean isExist = false;
		for (String prefix : this.excludePatterns) {
			if (url.startsWith(contextpath + prefix)) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}

	private void writeLogInfo(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String requesturl = httpServletRequest.getRequestURI();

	}
}
