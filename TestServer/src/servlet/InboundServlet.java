package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inbound")
public class InboundServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    public InboundServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setCharacterEncoding(request.getCharacterEncoding());
		System.out.println("Server Response Encoding is "+response.getCharacterEncoding());
		Enumeration<String> keys = request.getHeaderNames();
		while(keys.hasMoreElements()) {
		    String key = keys.nextElement();
		    System.out.println("IN HEADER [" + key + "][" + request.getHeader(key) +"]");
		}
	    InputStream is = request.getInputStream();
	    ByteArrayOutputStream baos = null;
	    byte[] inData = null;
	    byte[] outData = null;
	    try {
	        baos = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        while(true) {
	            int len = is.read(buffer);
	            if(len < 0) {
	                break;
	            }
	            baos.write(buffer, 0, len);
	        }
	        inData = baos.toByteArray();
	        System.out.println("InboundServlet in[" + new String(inData) + "]");
	        outData = process(inData);
	    } catch (Exception e) {
            e.printStackTrace();
            outData = e.getMessage().getBytes();
        } finally {
            response.getOutputStream().write(outData);
        }
	}

	private byte[] process(byte[] inData) {
	    return inData;
	}
}
