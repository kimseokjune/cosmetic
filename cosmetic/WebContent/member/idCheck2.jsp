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
	String id = request.getParameter("id");
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String idhave="아이디가 존재합니다.";
	String idnohave ="아이디가 존재하지 않습니다.";
%>
<script>
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
	if (search_form.id.value.length < 5){         
	       alert("아이디길이가 너무 짧습니다.");
	       search_form.id.focus();
	       return false;
	
}
}
	function set(id) {
		opener.document.joinForm.id.value = id;
		
		self.close();
	}
</script>
<html>
<head>
<title>아이디 검색</title>
</head>
<body onLoad="document.search_form.id.focus()">
	<form method="post" action="idCheck2.jsp" name="search_form" onSubmit="return check()">
		<table width="420" border="0" height="79" align="center">
			<tr>
				<td height="22" align="center">아이디 검색</td>
			</tr>
			<tr>
				<td height="30" align="center">검색할 아이디를 입력해주세요</td>
			</tr>
		</table>
		<table width="316" border="0" cellspacing="0" align="center">
			<tr>
				<td width="92" height="32" align="center">검색아이디</td>
				<td width="224" height="32">
					<%
						if (request.getParameter("id") == null) {
					%> <input type="text" name="id" size="20"> <%
 	} else {
 %> <input type="text" name="id" size="20"
					value="<%=Uni2Ksc(id)%>"> <%
 	}
 %> <input type="submit" name="submit" value="찾 기">
				</td>
			</tr>
		</table>
		<table width="420" border="0" cellspacing="0" align="center">
			<tr height="25" bgcolor="#6699FF">
				
			<tr>
				<%
				
				con = JdbcUtil.getConnection(); 
					
				if (request.getParameter("id") != null) { 
					
					int rst = 0;
					try {
						con = JdbcUtil.getConnection();
						String sql = "select * from member where id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, id);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							System.out.println("여기까지");
							%>
							<tr>
								
								<td width="360">
									<%=idhave%></td>
							</tr>
							
							<%
						}else{
							%>
							<tr>
								
								<td width="360"><%=idnohave%><a
									href="javascript:set('<%=id%>')">아이디 사용</a></td>
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
