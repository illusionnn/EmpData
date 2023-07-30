package com.chinmay.bhide.hr.servlets;
import com.chinmay.bhide.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddDesignation extends HttpServlet{
	public void doGet(HttpServletRequest request , HttpServletResponse response){
		PrintWriter pw = null;
		String title = "";
		try{
			pw = response.getWriter();
			title = request.getParameter("title");
			DesignationDTO designation = new DesignationDTO();
			designation.setTitle(title);
			DesignationDAO designationDAO;
			designationDAO = new DesignationDAO();
			designationDAO.add(designation);


			pw.println("<!DOCTYPE HTML>");
			pw.println("<html lang = 'en'>");
			pw.println("<head>");
			pw.println("<title>HR Application</title>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<!--Main container starts here-->");
			pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
			pw.println("<!--header container starts here-->");
			pw.println("<div style='margin:5px;width:120hw;height:auto;border:1px solid black'>");
			pw.println("<img style='width:30px;float:left;margin-top:-5px'src = '/styleone/images/logo.png'>");
			pw.println("<div style:'font-size:20px'>Style One</div>");
			pw.println("</div>");
			pw.println("<!--header container ends here-->");
			pw.println("<!--content-section starts here-->");
			pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
			pw.println("<!--left panel starts here-->");
			pw.println("<div style='height:65vh;margin:10px;float:left;padding:10px;border:1px solid black'>");
			pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
			pw.println("<a href='/styleone/employeeView'>Employee</a>");
			pw.println("</div>");
			pw.println("<!--left panel ends here-->");
			pw.println("<!--right panel starts here-->");
			pw.println("<div style='height:65vh;margin-left:120px;margin-right:10px;margin-bottom:10px;margin-top:10px;padding:10px;border:1px solid black;text-align:center'>");
			pw.println("<h3>Notification</h3>");
			pw.println("Designation added<br>Add more designations<br>");
			pw.println("<table style='align:center'>");
			pw.println("<tr>");
			pw.println("<td>");
			pw.println("<form action='/styleone/AddDesignation.html'>");
			pw.println("<button type='submit'>Yes</button>");
			pw.println("</form>");
			pw.println("</td>");
			pw.println("<td>");
			pw.println("<form action='/styleone/designationsView'>");
			pw.println("<button type='submit'>No</button>");
			pw.println("</form>");
			pw.println("</td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</div>");
			pw.println("<!--right panel ends here-->");
			pw.println("</div>");
			pw.println("<!--content-section ends here-->");
			pw.println("<!--footer starts here-->");
			pw.println("<div style='width:90hw;height:auto;margin:5px;border:1px solid white;text-align:center'>");
			pw.println("&copy; Chinmay Bhide 2020");
			pw.println("</div>");
			pw.println("<!--footer ends here-->");
			pw.println("</div>");
			pw.println("<!--Main container ends here-->");
			pw.println("</body>");
			pw.println("</html>");


		}catch(DAOException daoe){


			pw.println("<!DOCTYPE HTML>");
			pw.println("<html lang = 'en'>");
			pw.println("<head>");
			pw.println("<title>HR Application</title>");
			pw.println("<script>");
			pw.println("function validateForm(frm){");
				pw.println("var title = frm.title.value.trim();");
				pw.println("var titleErrorSection = document.getElementById('titleErrorSection');");
				pw.println("titleErrorSection.innerHTML='';");
				pw.println("if(title.length==0){");
					pw.println("titleErrorSection.innerHTML='Required';");
					pw.println("frm.title.focus();");
					pw.println("return false;");
				pw.println("}");
				pw.println("return true;");
			pw.println("}");
			pw.println("</script>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<!--Main container starts here-->");
			pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
			pw.println("<!--header container starts here-->");
			pw.println("<div style='margin:5px;width:120hw;height:auto;border:1px solid black'>");
			pw.println("<img style='width:30px;float:left;margin-top:-5px'src = '/styleone/images/logo.png'>");
			pw.println("<div style:'font-size:20px'>Style One</div>");
			pw.println("</div>");
			pw.println("<!--header container ends here-->");
			pw.println("<!--content-section starts here-->");
			pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
			pw.println("<!--left panel starts here-->");
			pw.println("<div style='height:65vh;margin:10px;float:left;padding:10px;border:1px solid black'>");
			pw.println("<b>Designations</b><br>");
			pw.println("<a href='/styleone/employeeView'>Employee</a><br><br>");
			pw.println("<a href='/styleone/index.html'>Home</a>");
			pw.println("</div>");
			pw.println("<!--left panel ends here-->");
			pw.println("<!--right panel starts here-->");
			pw.println("<div style='height:65vh;margin-left:120px;margin-right:10px;margin-bottom:10px;margin-top:10px;padding:10px;border:1px solid black;text-align:center'>");
			pw.println("<h2>Designation (Add module)</h2>");
			pw.println("<div style='color:red'>"+daoe.getMessage()+"</div>");
			pw.println("<form action='/styleone/addDesignation' onsubmit='return validateForm(this)'>");
			pw.println("Designation");
			pw.println("<input type='text' id='title' name='title' maxlength='35' size='36' value='"+title+"'>");
			pw.println("<span id='titleErrorSection' style='color:red'></span><br><br>");
			pw.println("<button type='submit'>Add</button>");
			pw.println("</from>");
			pw.println("</div>");
			pw.println("<!--right panel ends here-->");
			pw.println("</div>");
			pw.println("<!--content-section ends here-->");
			pw.println("<!--footer starts here-->");
			pw.println("<div style='width:90hw;height:auto;margin:5px;border:1px solid white;text-align:center'>");
			pw.println("&copy; Chinmay Bhide 2020");
			pw.println("</div>");
			pw.println("<!--footer ends here-->");
			pw.println("</div>");
			pw.println("<!--Main container ends here-->");
			pw.println("</body>");
			pw.println("</html>");


		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response){
		doGet(request,response);
	}
}