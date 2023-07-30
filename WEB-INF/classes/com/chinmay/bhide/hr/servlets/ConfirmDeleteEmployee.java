package com.chinmay.bhide.hr.servlets;
import com.chinmay.bhide.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;
public class ConfirmDeleteEmployee extends HttpServlet{
	public void doGet(HttpServletRequest request , HttpServletResponse response){
		try{
			String employeeId = "";
			employeeId = request.getParameter("employeeId");
			EmployeeDAO employeeDAO = new EmployeeDAO();
			try{
				EmployeeDTO employee = employeeDAO.getByEmployeeId(employeeId);
				PrintWriter pw = response.getWriter();
				//response.setContentType("text/html");
				pw.println("<!DOCTYPE HTML>");
				pw.println("<html lang = 'en'>");
				pw.println("<head>");
				pw.println("<title>HR Application</title>");
				pw.println("<script>");
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
				pw.println("<a href='/styleone/employeeView'>Designation</a><br><br>");
				pw.println("<b>Employee</b><br>");
				pw.println("<a href='/styleone/index.html'>Home</a>");
				pw.println("</div>");
				pw.println("<!--left panel ends here-->");
				pw.println("<!--right panel starts here-->");
				pw.println("<div style='height:65vh;margin-left:120px;margin-right:10px;margin-bottom:10px;margin-top:10px;padding:10px;border:1px solid black;text-align:center'>");
				pw.println("<h2>Employee (Delete Module)</h2>");
				pw.println("<form method='post' action='/styleone/deleteEmployee'>");
				pw.println("<input type='hidden' id='employeeId' name='employeeId' value='"+employeeId+"'>");
				int designationCode = employee.getDesignationCode();
				DesignationDAO designationDAO = new DesignationDAO();
				DesignationDTO designationDTO = designationDAO.getByCode(designationCode);
				String designationTitle = designationDTO.getTitle();
				pw.println("Name : ");
				pw.println("<b>"+employee.getName()+"</b><br><br>");
				pw.println("Designation : ");
				pw.println("<b>"+designationTitle+"</b><br><br>");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String stringDateOfBirth = simpleDateFormat.format(employee.getDateOfBirth());
				pw.println("Date of birth : ");
				pw.println("<b>"+stringDateOfBirth+"</b><br><br>");
				pw.println("Gender : ");
				if(employee.getGender().equals("M")){
					pw.println("<b>Male</b><br><br>");
				}
				else{
					pw.println("<b>Female</b><br><br>");
				}
				pw.println("Nationality : ");
				if(employee.getIsIndian()){
					pw.println("<b>Indian</b><br><br>");
				}
				else{
					pw.println("<b>Not an Indain</b><br><br>");
				}
				pw.println("Basic salary : ");
				pw.println("<b>"+employee.getBasicSalary().toPlainString()+"</b><br><br>");
				pw.println("Pan Number : ");
				pw.println("<b>"+employee.getPanNumber()+"</b><br><br>");
				pw.println("Aadhar Number : ");
				pw.println("<b>"+employee.getAadharNumber()+"</b><br><br>");
				pw.println("Are you sure you want to delete this Employee ?? <br><br>");
				pw.println("<button type='submit'>Delete</button>");
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
			}catch(DAOException daoe){
				sendBackView(response);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response){
		doGet(request,response);
	}
	private void sendBackView(HttpServletResponse response){
		try{
			List<EmployeeDTO> employees = new EmployeeDAO().getAll();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<!DOCTYPE HTML>");
			pw.println("<html lang = 'en'>");
			pw.println("<head>");
			pw.println("<title>HR Application</title>");
			pw.println("<script>");
			pw.println("function Employee(){");
			pw.println("	this.employeeId = \"\";");
			pw.println("	this.name = \"\";");
			pw.println("	this.designationCode = 0;");
			pw.println("	this.designation = \"\";");
			pw.println("	this.dateOfBirth = \"\";");
			pw.println("	this.gender = \"\";");
			pw.println("	this.isIndian = true;");
			pw.println("	this.basicSalary = 0;");
			pw.println("	this.panNumber = \"\";");
			pw.println("	this.aadharNumber = \"\";");
			pw.println("}");
			pw.println("var selectedRow = null;");
			pw.println("var employees = [];");
			SimpleDateFormat simpleDateFormat;
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			int i = 0;
			for(EmployeeDTO employee:employees){
				pw.println("var employee = new Employee();");
				pw.println("employee.employeeId=\""+employee.getEmployeeId()+"\";");
				pw.println("employee.name = \""+employee.getName()+"\";");
				pw.println("employee.designationCode = "+employee.getDesignationCode()+";");
				pw.println("employee.designation = \""+employee.getDesignation()+"\";");
				pw.println("employee.dateOfBirth = \""+simpleDateFormat.format(employee.getDateOfBirth())+"\";");
				pw.println("employee.gender = \""+employee.getGender()+"\";");
				pw.println("employee.isIndian = "+employee.getIsIndian()+";");
				pw.println("employee.basicSalary = "+employee.getBasicSalary().toPlainString()+";");
				pw.println("employee.panNumber = \""+employee.getPanNumber()+"\";");
				pw.println("employee.aadharNumber = \""+employee.getAadharNumber()+"\";");
				pw.println("employees["+i+"] = employee;");
				i++;
			}
			pw.println("function selectEmployee(row,employeeId){");
			pw.println("	if(row == selectedRow) return;");
			pw.println("	if(selectedRow!=null){");
			pw.println("		selectedRow.style.background=\"white\";");
			pw.println("		selectedRow.style.color=\"black\";	");
			pw.println("	}");
			pw.println("	row.style.background=\"grey\";");
			pw.println("	row.style.color=\"white\";");
			pw.println("	selectedRow = row;");
			pw.println("	var i;");
			pw.println("	for(i = 0 ; i < employees.length ; i++){");
			pw.println("		if(employees[i].employeeId == employeeId){");
			pw.println("			break;");
			pw.println("		}");
			pw.println("	}");
			pw.println("	var emp = employees[i];");
			pw.println("	document.getElementById(\"detailPanel_employeeId\").innerHTML=emp.employeeId; ");
			pw.println("	document.getElementById(\"detailPanel_name\").innerHTML=emp.name;");
			pw.println("	document.getElementById(\"detailPanel_designation\").innerHTML=emp.designation;");
			pw.println("	document.getElementById(\"detailPanel_dateOfBirth\").innerHTML=emp.dateOfBirth;");
			pw.println("	document.getElementById(\"detailPanel_gender\").innerHTML=emp.gender;");
			pw.println("	if(emp.isIndian){");
			pw.println("		document.getElementById(\"detailPanel_isIndian\").innerHTML=\"Yes\";");
			pw.println("	}");
			pw.println("	else{");
			pw.println("		document.getElementById(\"detailPanel_isIndian\").innerHTML=\"No\";");
			pw.println("	}");
			pw.println("	document.getElementById(\"detailPanel_basicSalary\").innerHTML=emp.basicSalary;");
			pw.println("	document.getElementById(\"detailPanel_panNumber\").innerHTML=emp.panNumber;");
			pw.println("	document.getElementById(\"detailPanel_aadharNumber\").innerHTML=emp.aadharNumber;");
			pw.println("}");
			pw.println("</script>");
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
			pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
			pw.println("<b>Employee</b><br><br><br><br>");
			pw.println("<a href='/styleone/index.html'>Home</a><br><br>");
			pw.println("</div>");
			pw.println("<!--left panel ends here-->");
			pw.println("<!--right panel starts here-->");
			pw.println("<div style='height:65vh;margin-left:120px;margin-right:10px;margin-bottom:10px;margin-top:10px;padding:10px;border:1px solid black'>");
			pw.println("<h2>Employee</h2>");
			pw.println("");
			pw.println("<div style='height:30vh;margin-left:10px;margin-right:10px;margin-bottom:10px;margin-top:10px;padding:10px;border:1px solid black;overflow:scroll'>");
			pw.println("<table border='1'>");
			pw.println("<thead>");
			pw.println("<tr>");
			pw.println("<th colspan='6' style='text-align:right;padding:5px'>");
			pw.println("<a href='/styleone/getEmployeeAddForm'>Add Employee</a>");
			pw.println("</th>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<th style='width:60px;text-align:center'>S.NO.</th>");
			pw.println("<th style='width:200px;text-align:center'>Id.</th>");
			pw.println("<th style='width:200px;text-align:center'>Name</th>");
			pw.println("<th style='width:200px;text-align:center'>Designation</th>");
			pw.println("<th style='width:100px;text-align:center'>Edit</th>");
			pw.println("<th style='width:100px;text-align:center'>Delete</th>");
			pw.println("</tr>");
			pw.println("</thead>");
			pw.println("<tbody>");
			int sno = 1;
			for(EmployeeDTO employee:employees){
				pw.println("<tr style='cursor:pointer' onclick='selectEmployee(this,\""+employee.getEmployeeId()+"\")'>");
				pw.println("<td style='text-align:right'>"+sno+".</td>");
				pw.println("<td style='text-align:left'>"+employee.getEmployeeId()+"</td>");
				pw.println("<td style='text-align:left'>"+employee.getName()+"</td>");
				pw.println("<td style='text-align:left'>"+employee.getDesignation()+"</td>");
				pw.println("<td style='text-align:center'><a href='/styleone/editEmployee?employeeId="+employee.getEmployeeId()+"'>Edit</a></td>");
				pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteEmployee?employeeId="+employee.getEmployeeId()+"'>Delete</a></td>");
				pw.println("</tr>");
				sno++;
			}
			pw.println("</tbody>");
			pw.println("</table>");
			pw.println("</div>");
			pw.println("<div style='height:20vh;margin-left:10px;margin-right:10px;margin-bottom:10px;margin-top:10px;padding:10px;border:1px solid black'>");
			pw.println("<label style='background:grey;color:white;padding-left:5px;padding-right:5px'>Details</label><br><br>");
			pw.println("<table border='0' cellspacing ='5px' width='100%'>");
			pw.println("<tr>");
			pw.println("<td>Employee Id : <span id = 'detailPanel_employeeId' style='margin-right:50px'></span></td>");
			pw.println("<td>Name : <span id = 'detailPanel_name' style='margin-right:50px'></span></td>");
			pw.println("<td>Designation : <span id = 'detailPanel_designation' style='margin-right:50px'></span></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Date Of Birth : <span id = 'detailPanel_dateOfBirth' style='margin-right:50px'></spna></td>");
			pw.println("<td>Gender : <span id = 'detailPanel_gender' style='margin-right:50px'></span></td>");
			pw.println("<td>Is Indian : <span id = 'detailPanel_isIndian' style='margin-right:50px'></span></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Basic Salary : <span id = 'detailPanel_basicSalary' style='margin-right:50px'></span></td>");
			pw.println("<td>Pan Number : <span id = 'detailPanel_panNumber' style='margin-right:50px'></span></td>");
			pw.println("<td>Aadhar Number : <span id = 'detailPanel_aadharNumber' style='margin-right:50px'></span></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</div>");
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
}