package hu.leone.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Utils {
	public static void closePopup(HttpServletResponse response, String url) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html>");
		out.write("<body onLoad='setTimeout(function() {" + 
				(url == null ? "window.opener.location.reload();" : "window.opener.location.href=\"" + url + "\";") 
				+ "window.close();},3000);'>");
		out.write("Sikeres művelet!");
		out.write("</body>");
		out.write("</html>");
		out.close();
	}
}

/*

<body onLoad='setTimeout(function() {
		window.opener.location.reload();
		window.opener.location.href=\"" + url + "\";") 
		window.close();},3000);'>
		
		Sikeres művelet!
		
		</body>
		</html>
		
*/