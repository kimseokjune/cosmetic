<%@page import="com.vyon.jdbc.JdbcUtil"%>
<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="java.sql.*, java.io.*"%>
<%!public static String Uni2Ksc(String str)
			throws UnsupportedEncodingException {
		if (str == null)
			return null;
		return new String(str.getBytes("8859_1"), "KSC5601");
	}

	public static String Ksc2Uni(String str)
			throws UnsupportedEncodingException {
		if (str == null)
			return null;
		return new String(str.getBytes("KSC5601"), "8859_1");
	}%>
<%
	String email = request.getParameter("email");
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String emailHave="이메일이 존재합니다.";
	String emailNoHave ="이메일이 존재하지 않습니다.";
%>
<script language="javascript">
// 	function search() {
// 		if (document.search_form.post_name.value == "") {
// 			alert("찾고자하는 읍/면/동을 입력하세요~");
// 			document.search_form.post_name.focus();
// 			return false;
// 		} else {
// 			document.search_form.submit;
// 		}
// 	}
function check(){ 
var email = document.search_form.email.value;

		if (email.length < 1 || email == null) {
			alert("중복체크할 이메일을 입력하십시오");
return false; 		
}
		
		 var reg_email = /^[0-9A-Z]([-_\.]?[0-9A-Z])*@[0-9A-Z]([-_\.]?[0-9A-Z])*\.[A-Z]{2,6}$/i ;
		 if(!reg_email.test(email))
		  {
			 alert("유효한 이메일 주소가 아닙니다");
 			 return false;
		  }
}
	function set(email) {
		opener.document.joinForm.email.value = email;
		
		self.close();
	}
</script>
<html>
<head>
<title>이메일 검색</title>
</head>
<body onLoad="document.search_form.email.focus()">
	<form method="post" action="eCheck3.jsp" name="search_form" onSubmit="return check()">
		<table width="420" border="0" height="79" align="center">
			<tr>
				<td height="22" align="center">이메일 검색</td>
			</tr>
			<tr>
				<td height="30" align="center">검색할 이메일을 입력해주세요</td>
			</tr>
		</table>
		<table width="316" border="0" cellspacing="0" align="center">
			<tr>
				<td width="92" height="32" align="center">검색이메일</td>
				<td width="224" height="32">
					<%
						if (request.getParameter("email") == null) {
					%> <input type="text" name="email" size="20"> <%
 	} else {
 %> <input type="text" name="email" size="20"
					value="<%=Uni2Ksc(email)%>"> <%
 	}
 %> <input type="submit" name="submit" value="찾 기"
					onClick="search()">
				</td>
			</tr>
		</table>
		<table width="420" border="0" cellspacing="0" align="center">
			<tr height="25" bgcolor="#6699FF">
				
			<tr>
				<%
				
				con = JdbcUtil.getConnection(); 
					
				if (request.getParameter("email") != null) { 
					
					int rst = 0;
					try {
						con = JdbcUtil.getConnection();
						String sql = "select * from member where email=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, email);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							System.out.println("여기까지");
							%>
							<tr>
								
								<td width="360">
									<%=emailHave%></td>
							</tr>
							
							<%
						}else{
							%>
							<tr>
								
								<td width="360"><%=emailNoHave%><a
									href="javascript:set('<%=email%>')">이메일 사용</a></td>
							</tr>
							
							<% 
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						JdbcUtil.close(pstmt);
					}
					
				}
					
// 					String sql = " select * from postno where dong like '" + Uni2Ksc(dong) + "%' "; 
// 					pstmt = con.prepareStatement(sql);
// 					rs = pstmt.executeQuery(sql); 
// 					for (int i=1; rs.next(); i++) {
// 						int zipcode = rs.getInt("zipcode");
// 						String zipcode1 = Integer.toString(zipcode);
// 						System.out.println(zipcode1);
// 					String s1 = rs.getString("sido"); String s2 = rs.getString("sigungu"); 
// 					String s3 = rs.getString("dong"); 
					
// 					String s4 = rs.getString("bunji"); 
// 					String s4_1=null;
// 					if(s4==null){
// 						s4_1="";
// 					}else{
// 						s4_1=s4;
// 					}
// 					String s_ok = s1 + " " + s2 + " " + s3 + " " + s4_1; 
// 					String zip1 = zipcode1.substring(0,3); String zip2 = zipcode1.substring(3,6); 
			
			
else{
			%>
			<tr>
				<td width="60"></td>
				<td width="360">[검색하세요]</td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
	<%
		if (con != null) {
			con.close();
		}
	%>
</body>
</html>
