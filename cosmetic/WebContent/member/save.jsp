<%@page import="com.vyon.jdbc.JdbcUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*, java.io.*" %> 
<% 
String szIrum = request.getParameter("irum"); 
String szTel1 = request.getParameter("tel1"); 
String szTel2 = request.getParameter("tel2"); 
String szTel3 = request.getParameter("tel3"); 
String szZip1 = request.getParameter("zip1"); 
String szZip2 = request.getParameter("zip2"); 
String szZip = szZip1 + szZip2; 
String szTel = szTel1 + szTel2 + szTel3; 
String szAddress = request.getParameter("address"); 

Connection con = null; 
PreparedStatement pstmt = null; 
ResultSet rs = null; 

try { 
	con = JdbcUtil.getConnection(); 
  con.setAutoCommit(false); 
} 
catch(Exception e) {  
	 e.printStackTrace();
} 
String sql = "insert into customer values (?,?,?,?)"; 
pstmt = con.prepareStatement(sql); 
pstmt.setString(1,Uni2Ksc(szIrum)); 
pstmt.setString(2,szZip); 
pstmt.setString(3,Uni2Ksc(szAddress)); 
pstmt.setString(4,szTel); 
pstmt.executeUpdate(); 
con.commit(); 

response.sendRedirect("register.jsp"); 
%> 

<%! 
public static String Uni2Ksc(String str) throws UnsupportedEncodingException { 
      if(str==null) return null; 
      return new String(str.getBytes("8859_1"),"KSC5601"); 
}   

public static  String Ksc2Uni(String str) throws UnsupportedEncodingException { 
      if(str==null) return null; 
      return new String(str.getBytes("KSC5601"),"8859_1"); 
}    
%> 