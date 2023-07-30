package com.chinmay.bhide.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.math.*;
import com.chinmay.bhide.hr.dl.*;
import java.util.*;
import java.text.*;
public class UpdateEmployee extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		try{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String employeeId = request.getParameter("employeeId");
			System.out.println(employeeId);
			String name = request.getParameter("name");
			System.out.println(name);
			int designationCode = Integer.parseInt(request.getParameter("designationCode"));
			Date dateOfBirth = simpleDateFormat.parse(request.getParameter("dateOfBirth"));
			String gender = request.getParameter("gender");
			String isIndian = request.getParameter("isIndian");
			if(isIndian==null) isIndian = "N";
			BigDecimal basicSalary = new BigDecimal(request.getParameter("basicSalary"));
			String panNumber = request.getParameter("panNumber");
			String aadharNumber = request.getParameter("aadharNumber");
			DesignationDAO designationDAO = new DesignationDAO();
			EmployeeDAO employeeDAO = new EmployeeDAO();
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			try{
				if(employeeDAO.employeeIdExists(employeeId)==false){
					sendBackView(response);
					return;
				}
				boolean designationCodeExists = designationDAO.designationCodeExists(designationCode);
				boolean panNumberExists = false;
				EmployeeDTO employeeDTO;
				try{
					employeeDTO = employeeDAO.getByPanNumber(panNumber);
					if(employeeDTO.getEmployeeId().equalsIgnoreCase(employeeId) == false){
						panNumberExists = true;
					}
				}catch(DAOException daoe){
					panNumberExists = false;
				}
				boolean aadharNumberExists = false;
				//EmployeeDTO employeeDTO;
				try{
					employeeDTO = employeeDAO.getByAadharNumber(aadharNumber);
					if(employeeDTO.getEmployeeId().equalsIgnoreCase(employeeId) == false){
						aadharNumberExists = true;
					}
				}catch(DAOException daoe){
					aadharNumberExists = false;
				}
				if(designationCodeExists == false || panNumberExists == true || aadharNumberExists == true){
					

					
					pw = response.getWriter();
					pw.println("<!DOCTYPE HTML>");
					pw.println("<html lang = 'en'>");
					pw.println("<head>");
					pw.println("<title>HR Application</title>");
					pw.println("<script>");
					pw.println("function validateForm(frm){");
					pw.println("	var firstInvalidComponent = null;");
					pw.println("	var valid = true;");
					pw.println("	var name = frm.name.value.trim();");
					pw.println("	var nameErrorSection = document.getElementById('nameErrorSection');");
					pw.println("	nameErrorSection.innerHTML='';");
					pw.println("	if(name.length==0){");
					pw.println("		nameErrorSection.innerHTML='Name required';");
					pw.println("		valid = false;");
					pw.println("		firstInvalidComponent = frm.name;");
					pw.println("	}");
					pw.println("	var designationCode = frm.designationCode.value;");
					pw.println("	var designationCodeErrorSection = document.getElementById('designationCodeErrorSection');");
					pw.println("	designationCodeErrorSection.innerHTML='';");
					pw.println("	if(designationCode==-1){");
					pw.println("		designationCodeErrorSection.innerHTML='Select Designation';");
					pw.println("		valid = false;");
					pw.println("		if(firstInvalidComponent==null)firstInvalidComponent = frm.designationCode;");
					pw.println("	}");
					pw.println("	var dateOfBirth = frm.dateOfBirth.value;");
					pw.println("	var dateOfBirthErrorSection = document.getElementById('dateOfBirthErrorSection');");
					pw.println("	dateOfBirthErrorSection.innerHTML='';");
					pw.println("	if(dateOfBirth.length==0){");
					pw.println("		dateOfBirthErrorSection.innerHTML='Select date of birth';");
					pw.println("		valid = false;");
					pw.println("		if(firstInvalidComponent==null)firstInvalidComponent = frm.dateOfBirth;");
					pw.println("	}");
					pw.println("	var genderErrorSection = document.getElementById('genderErrorSection');");
					pw.println("	genderErrorSection.innerHTML='';");
					pw.println("	if(frm.gender[0].checked==false && frm.gender[1].checked==false){");
					pw.println("		genderErrorSection.innerHTML='Select Gender';");
					pw.println("		valid = false;");
					pw.println("	}");
					pw.println("	var basicSalary = frm.basicSalary.value.trim();");
					pw.println("	var basicSalaryErrorSection = document.getElementById('basicSalaryErrorSection');");
					pw.println("	basicSalaryErrorSection.innerHTML='';");
					pw.println("	if(basicSalary.length==0){");
					pw.println("		basicSalaryErrorSection.innerHTML='Basic salary required';");
					pw.println("		valid = false;");
					pw.println("		if(firstInvalidComponent==null)firstInvalidComponent = frm.basicSalary;");
					pw.println("	}");
					pw.println("	else{");
					pw.println("		var v = '0123456789.';");
					pw.println("		var e = 0;");
					pw.println("		var isBasicSalaryValid = true;");
					pw.println("		while(e<basicSalary.length){");
					pw.println("			if(v.indexOf(basicSalary.charAt(e))==-1){");
					pw.println("				basicSalaryErrorSection.innerHTML='Invalid Basic salary';");
					pw.println("				valid = false;");
					pw.println("				if(firstInvalidComponent==null)firstInvalidComponent = frm.basicSalary;");
					pw.println("				isBasicSalaryValid = false;");
					pw.println("				break;");
					pw.println("			}");
					pw.println("			e++;");
					pw.println("		}");
					pw.println("		if(isBasicSalaryValid){");
					pw.println("			var dot = basicSalary.indexOf('.');");
					pw.println("			if(dot!=-1){");
					pw.println("				var numberOfFractions = basicSalary.length-(dot+1);");
					pw.println("				if(numberOfFractions>2){");
					pw.println("					basicSalaryErrorSection.innerHTML='Invalid Basic salary';");
					pw.println("					valid = false;");
					pw.println("					if(firstInvalidComponent==null)firstInvalidComponent = frm.basicSalary;");
					pw.println("				}");
					pw.println("			}");
					pw.println("		}");
					pw.println("	}");
					pw.println("	var panNumber = frm.panNumber.value.trim();");
					pw.println("	var panNumberErrorSection = document.getElementById('panNumberErrorSection');");
					pw.println("	panNumberErrorSection.innerHTML='';");
					pw.println("	if(panNumber.length==0){");
					pw.println("		panNumberErrorSection.innerHTML='Pan Number required';");
					pw.println("		valid = false;");
					pw.println("		if(firstInvalidComponent==null)firstInvalidComponent = frm.panNumber;");
					pw.println("	}");
					pw.println("	var aadharNumber = frm.aadharNumber.value.trim();");
					pw.println("	var adharNumberErrorSection = document.getElementById('aadharNumberErrorSection');");
					pw.println("	aadharNumberErrorSection.innerHTML='';");
					pw.println("	if(aadharNumber.length==0){");
					pw.println("		aadharNumberErrorSection.innerHTML='Aadhar Number required';");
					pw.println("		valid = false;");
					pw.println("		if(firstInvalidComponent==null)firstInvalidComponent = frm.aadharNumber;");
					pw.println("	}");
					pw.println("	if(!valid) firstInvalidComponent.focus();");
					pw.println("	return valid;");
					pw.println("}");
					pw.println("function cancelAddition(){");
					pw.println("	document.getElementById('cancelAdditionForm').submit();");
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
					pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
					pw.println("<b>Employee</b><br><br>");
					pw.println("<a href='/styleone/index.html'>Home</a>");
					pw.println("</div>");
					pw.println("<!--left panel ends here-->");
					pw.println("<!--right panel starts here-->");
					pw.println("<div style='height:65vh;margin-left:120px;margin-right:10px;margin-bottom:10px;margin-top:10px;padding:10px;border:1px solid black'>");
					pw.println("<h2>Employee (Edit module)</h2>");
					pw.println("<form method='post' action='/styleone/updateEmployee' onsubmit='return validateForm(this)'>");
					pw.println("<input type = 'hidden' id = 'employeeId' name = 'employeeId' value = '"+employeeId+"'>");
					pw.println("<table>");
					pw.println("<tr>");
					pw.println("<td>Name</td>");
					pw.println("<td><input type='text' id='name' name='name' maxlength='50' size='51' value='"+name+"'>");
					pw.println("<span id='nameErrorSection' style='color:red'></span><br><br></td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td>Designation</td>");
					pw.println("<td><select id='designationCode' name='designationCode' >");
					pw.println("<option value='-1'>&lt;Select Designation&gt;</option>");
					//DesignationDAO designationDAO;
					designationDAO = new DesignationDAO();
					List<DesignationDTO> designations = designationDAO.getAll();
					int code;
					String title;
					for(DesignationDTO designation:designations){
						code = designation.getCode();
						title = designation.getTitle();
						if(code == designationCode){
							pw.println("<option value='"+code+"'>"+title+"</option>");
						}
						else{
							pw.println("<option selected value='"+code+"'>"+title+"</option>");
						}	
					}
					pw.println("</select>");
					if(designationCodeExists == false){
						pw.println("<span id='designationCodeErrorSection' style='color:red'>Invalid Designation</span>");
					}
					else{
						pw.println("<span id='designationCodeErrorSection' style='color:red'></span>");
					}
					pw.println("</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td><br>Date of birth</td>");
					pw.println("<td><br><input type='date' id='dateOfBirth' name='dateOfBirth' value='"+simpleDateFormat.format(dateOfBirth)+"'>");
					pw.println("<span id='dateOfBirthErrorSection' style='color:red'></span>");
					pw.println("</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td><br>Gender</td>");
					pw.println("<td>");
					if(gender.equals("M")==false){
						pw.println("<br><input type='radio' id='male' name='gender' value='M'>Male");
					}
					else{
						pw.println("<br><input checked type='radio' id='male' name='gender' value='M'>Male");
					}
					pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
					if(gender.equals("F")==false){
						pw.println("<input type='radio' id='female' name='gender' value='F'>Female");
					}
					else{
						pw.println("<input checked type='radio' id='female' name='gender' value='F'>Female");
					}
					pw.println("<span id='genderErrorSection' style='color:red'></span>");
					pw.println("</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td><br>Indian ?</td>");
					pw.println("<td>");
					if(isIndian.equals("Y")){
						pw.println("<br><input checked type='checkbox' name='isIndian' id='isIndian'>");
					}
					else{
						pw.println("<br><input type='checkbox' name='isIndian' id='isIndian'>");
					}
					pw.println("</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td><br>Basic Salary</td>");
					pw.println("<td><br><input type='text' style='text-align:right' id='basicSalary' name='basicSalary' maxLength='13' value='"+basicSalary.toPlainString()+"'>");
					pw.println("<span id='basicSalaryErrorSection' style='color:red'></span>");
					pw.println("</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td><br>PAN Number</td>");
					pw.println("<td><br><input type='text' id='panNumber' name='panNumber' value='"+panNumber+"'>");
					if(panNumberExists){
						pw.println("<span id='panNumberErrorSection' style='color:red'>PAN Number exists</span>");
					}
					else{
						pw.println("<span id='panNumberErrorSection' style='color:red'></span>");
					}
					pw.println("</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td><br>Aadhar Number</td>");
					pw.println("<td><br><input type='text' id='aadharNumber' name='aadharNumber' value='"+aadharNumber+"'>");
					if(aadharNumberExists){
						pw.println("<span id='aadharNumberErrorSection' style='color:red'>Aadhar Number Exists</span>");
					}
					else{
						pw.println("<span id='aadharNumberErrorSection' style='color:red'></span>");
					}
					pw.println("</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td><br><button type='submit'>Update</button></td>");
					pw.println("<td><br><button type='Button' onclick='cancelAddition()'>Cancel</button></td>");
					pw.println("</tr>");
					pw.println("</from>");
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
					pw.println("<form id ='cancelAdditionForm' action='/styleone/employeeView'>");
					pw.println("</form>");
					pw.println("</body>");
					pw.println("</html>");
					return;//return add form recent because of problems
				}
			}catch(DAOException daoe){
				System.out.println(daoe);//remove after testing
			}
			EmployeeDTO employee = new EmployeeDTO();
			employee.setEmployeeId(employeeId);
			employee.setName(name);
			employee.setDesignationCode(designationCode);
			employee.setDateOfBirth(dateOfBirth);
			employee.setGender(gender);
			employee.setIsIndian(isIndian.equals("Y"));
			employee.setBasicSalary(basicSalary);
			employee.setPanNumber(panNumber);
			employee.setAadharNumber(aadharNumber);
			try{
				System.out.println("hello2 "+employeeId);
				employeeDAO.update(employee);
				System.out.println("hello1");
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
				pw.println("Employee updated<br>");
				pw.println("<form action='/styleone/employeeView'>");
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
				//recreate form and error message at top of form 
				//and send back the page
				System.out.println("ahghas"+daoe.getMessage()); 
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
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
				System.out.println(employee.getDesignation());
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