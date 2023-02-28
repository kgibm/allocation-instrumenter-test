package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllocationTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int size = AllocationInstrumenterInitializer.THRESHOLD * 2;
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("Allocating object of size " + size);
		out.flush();
		
		byte[] buffer = new byte[size];
		
		out.println("Finished allocating " + buffer);
		out.flush();
	}
}
