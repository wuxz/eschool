package com.dc.eschool.controller.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.net.*;

import com.dc.eschool.util.*;

public class DownloadServlet extends HttpServlet
{
  private static final String CONTENT_TYPE = "text/html; charset=GBK";
  /**Initialize global variables*/
  public void init() throws ServletException
  {
  }
  /**Process the HTTP Get request*/
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
//    response.setContentType(CONTENT_TYPE);
//    PrintWriter out = response.getWriter();
//    out.println("<html>");
//    out.println("<head><title>WordServlet</title></head>");
//    out.println("<body>");
//    out.println("<p>The servlet has received a GET. This is the reply.</p>");
//    out.println("</body></html>");
	doPost(request,response);
  }
  /**Process the HTTP Post request*/
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//  {
//    response.setContentType(CONTENT_TYPE);
//    PrintWriter out = response.getWriter();
//    out.println("<html>");
//    out.println("<head><title>WordServlet</title></head>");
//    out.println("<body>");
//    out.println("<p>The servlet has received a POST. This is the reply.</p>");
//    out.println("</body></html>");
//
  {
		String filename = request.getParameter("filename");
		int nHash = Integer.parseInt(request.getParameter("filecode"));
		if (nHash != filename.hashCode())
		{
			response.getWriter().println("非法的下载请求!");

			return;
		}

		String savename = request.getParameter("savename");

	  ServletOutputStream  out           =
		  response.getOutputStream ();

  //---------------------------------------------------------------
  // Set the output data's mime type
  //---------------------------------------------------------------

	  response.setContentType( "application/zip"  );  // MIME type for pdf doc

  //---------------------------------------------------------------
  // create an input stream from fileURL
  //---------------------------------------------------------------

		PropertiesManager properMgr=new PropertiesManager();
		String uploadPath=properMgr.getUploadPath();
		String sPath=request.getRealPath(uploadPath);
		String fileURL = sPath + filename;
		Debug.println("fileURL :" + fileURL);


//          "http://www.adobe.com/aboutadobe/careeropp/pdfs/adobeapp.pdf";

  //------------------------------------------------------------
  // Content-disposition header - don't open in browser and
  // set the "Save As..." filename.
  // *There is reportedly a bug in IE4.0 which  ignores this...
  //------------------------------------------------------------
	  response.setHeader("Content-disposition", "attachment; filename=" + savename);

  //-----------------------------------------------------------------
  // PROXY_HOST and PROXY_PORT should be your proxy host and port
  // that will let you go through the firewall without authentication.
  // Otherwise set the system properties and use URLConnection.getInputStream().
  //-----------------------------------------------------------------
	  BufferedInputStream  bis = null;
	  BufferedOutputStream bos = null;

	  try {
		  // Use Buffered Stream for reading/writing.
		  bis = new BufferedInputStream(new FileInputStream(fileURL));
		  bos = new BufferedOutputStream(out);

		  byte[] buff = new byte[2048];
		  int bytesRead;

		  // Simple read/write loop.
		  while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			  bos.write(buff, 0, bytesRead);
		  }

	  } catch(final IOException e) {
		  e.printStackTrace();
		  throw e;
	  } catch(final Exception e) {
		  e.printStackTrace();
	  } finally {
		try
		{
		  if (bis != null)
			  bis.close();
		}
		catch (Throwable e)
		{
		}

		try
		{
		  if (bos != null)
			  bos.close();
		}
		catch (Throwable e)
		{
		}
	  }
  }

  /**Clean up resources*/
  public void destroy()
  {
  }
}