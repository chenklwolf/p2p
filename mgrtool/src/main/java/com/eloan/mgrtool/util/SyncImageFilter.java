package com.eloan.mgrtool.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class SyncImageFilter implements Filter {

	public static final String uploadImagePath = "e:/uploadImages";

	private ServletContext servletContext;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.servletContext = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String filePath = req.getRequestURI();
		File imageFile = new File(servletContext.getRealPath(filePath));
		if (imageFile.exists()) {
			chain.doFilter(request, response);
		} else {
			File sourceFile = new File(uploadImagePath,
					FilenameUtils.getName(filePath));
			if (sourceFile.exists()) {
				imageFile.createNewFile();
				FileUtils.copyFile(sourceFile, imageFile);
				resp.setHeader("Cache-Control", "no-store");
				resp.setHeader("Pragma", "no-cache");
				resp.setDateHeader("Expires", 0);
				ServletOutputStream responseOutputStream = response
						.getOutputStream();
				responseOutputStream.write(FileUtils.readFileToByteArray(imageFile));
				responseOutputStream.flush();
				responseOutputStream.close();
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
