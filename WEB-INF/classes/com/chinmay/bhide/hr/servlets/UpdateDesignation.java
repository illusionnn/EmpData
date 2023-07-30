package com.chinmay.bhide.hr.servlets;
import com.chinmay.bhide.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class UpdateDesignation extends HttpServlet{
	public void doGet(HttpServletRequest request , HttpServletResponse response){
		PrintWriter pw = null;
		String title = "";
		int code = 0;
		try{
			pw = response.getWriter(); 
			title = request.getParameter("title");
			if(title==null){
				sendBackView(response);
				return;
			}
			try{
				code = Integer.parseInt(request.getParameter("code"));
			}catch(NumberFormatException nfe){
				sendBackView(response);
				return;
			}
			DesignationDTO designation = new DesignationDTO();
			designation.setCode(code);
			designation.setTitle(title);
			DesignationDAO designationDAO;
			designationDAO = new DesignationDAO();
			designationDAO.update(designation);

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
			pw.println("Designation updated<br>");
			pw.println("<form action='/styleone/designationsView'>");
			pw.println("<button type='submit'>OK</button>");
			pw.println("</form>");
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
			//send back the form to update with error message
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
				pw.println("<h2>Designation (Edit module)</h2>");
				pw.println("<div style='color:red'>"+daoe.getMessage()+"</div>");
				pw.println("<form method='post' action='/styleone/updateDesignation' onsubmit='return validateForm(this)'>");
				pw.println("Designation");
				pw.println("<input type='hidden' id='code' name='code' value='"+code+"'>");
				pw.println("<input type='text' id='title' name='title' maxlength='35' size='36' value='"+title+"'>");
				pw.println("<span id='titleErrorSection' style='color:red'></span><br><br>");
				pw.println("<button type='submit'>Update</button>");
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
	private void sendBackView(HttpServletResponse response){
		try{
			DesignationDAO designationDAO;
			designationDAO = new DesignationDAO();
			List<DesignationDTO> designations;
			designations = designationDAO.getAll();
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");
		
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
			pw.println("<a href='/styleone/index.html'><img style='width:30px;float:left;margin-top:-5px'src = '/styleone/images/logo.png'></a>");
			pw.println("<div style:'font-size:20px'>Style One</div>");
			pw.println("</div>");
			pw.println("<!--header container ends here-->");
			pw.println("<!--content-section starts here-->");
			pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
			pw.println("<!--left panel starts here-->");
			pw.println("<div style='height:65vh;margin:10px;float:left;padding:10px;border:1px solid black'>");
			pw.println("<b>Designations</b><br>");
			pw.println("<a href='/styleone/employeeView'>Employee</a>");
			pw.println("</div>");
			pw.println("<!--left panel ends here-->");
			pw.println("<!--right panel starts here-->");
			pw.println("<div style='height:65vh;margin-left:120px;margin-right:10px;margin-bottom:10px;margin-top:10px;padding:10px;overflow:scroll;border:1px solid black'>");
			pw.println("<h2>Designations</h2>");
			pw.println("<table border='1'>");
			pw.println("<thead>");
			pw.println("<tr>");
			pw.println("<th colspan='4' style='text-align:right;padding:5px'>");
			pw.println("<a href='/styleone/AddDesignation.html'>Add new designation</a>");
			pw.println("</th>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<th style='width:60px;text-align:center'>S.NO.</th>");
			pw.println("<th style='width:200px;text-align:center'>Designation</th>");
			pw.println("<th style='width:100px;text-align:center'>Edit</th>");
			pw.println("<th style='width:100px;text-align:center'>Delete</th>");
			pw.println("</tr>");
			pw.println("</thead>");
			pw.println("<tbody>");
		
			int x;
			DesignationDTO designationDTO;
			int code;
			String title;
			int sno = 0;
			for(x = 0 ; x<designations.size() ; x++){
				sno++;
				designationDTO = designations.get(x);
				code = designationDTO.getCode();
				title = designationDTO.getTitle();
				pw.println("<tr>");
				pw.println("<td style='text-align:right'>"+sno+"</td>");
				pw.println("<td style='text-align:left'>"+title+"</td>");
				pw.println("<td style='text-align:center'><a href='/styleone/editDesignation?code="+code+"'>Edit</a></td>");
				pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteDesignation?code="+code+"'>Delete</a></td>");
				pw.println("</tr>");
			}

			pw.println("</tbody>");
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
			
		}catch(DAOException dao){
			System.out.println(dao.getMessage());//remove after testing and setup 500 (internal error page)
		}catch(Exception e){
			System.out.println(e.getMessage());//remove after testing and setup 500 (internal error page)
		}
	}
}