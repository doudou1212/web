<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="bean.ListFile"%>
<jsp:useBean id="dirBean" class="bean.ListFile"/>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <meta content="text/html; charset=utf-8" http-equiv="content-type">
<script type="text/javascript" src="jquery-2.1.1.js"></script>
<script type="text/javascript"  src="jquery-2.1.1.min.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	<script language="javascript">
    	function show()
    	{
    		alert(basePath);
    	}
    </script>
	
	<script type="text/javascript">
	var name;

		function getname(){
			$("#info tr").click(function(){
				alert("niaho");
				
				name=$(this).find("td").eq(0).text();
				alert("name="+name);
				alert(name.length);
		        
				
				$.ajax({
				type:"GET",
				url:"/web/UpLoadFile",
				data:{file:name},
				contentType: "application/x-www-form-urlencoded;charset=UTF-8",
				success: function()
				{
					alert("success");
				}
			});
				
				
				
			}
			)
			
			
			
		}
	</script>

	
  </head>

  
   <body>
    Plsease select a file to upload. <br />
    <form action="/web/UpLoadFile" method="post" enctype="multipart/form-data">
    	<input type="file" name="file" size="50" /><br />
    	<input type="submit" value="Upload File" name="upload" />
    </form>
    <input type="button" onclick="alert();" value="for some test" />
    

 <!--  <form method="get" action="/web/UpLoadFile">
    <table >
       <c:forEach var="name" items="${filename}">   

        <tr>
           <td name="fname" >${name}</td> -->

        <!--   <td>
              <a href ="/web/UpLoadFile?filename=${name}">download</a>
           </td>   -->
         <!--  <td>
              <input type="button" value="download" id="button" onclick="getname()" />
           </td>
        </tr>
 
      </c:forEach>
    </table>    
   </form> -->
   
    <form method="get" action="/web/UpLoadFile">
   <table id="info">
    <c:forEach var="file" items="${dirBean.dirname}">
     <tr>
       <td>${file}</td>
       <!-- <td> <a href ="/web/UpLoadFile?filename=${file}">download</a></td>-->
       <td> <input type="button" value="download" id="button" onclick="getname()" /></td>
     </tr>
     </c:forEach> 
     </table>
     </form>   
  </body>
</html>
