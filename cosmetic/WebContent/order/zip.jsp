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
	String dong = request.getParameter("post_name");
	String prevPage = request.getParameter("prevPage");
	String url = request.getRequestURI();
	System.out.println(prevPage);
	System.out.println(url);
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
%>
<script language="javascript">
	function search() {
		if (document.search_form.post_name.value == "") {
			alert("찾고자하는 읍/면/동을 입력하세요~");
			document.search_form.post_name.focus();
			return false;
		} else {
			document.search_form.submit;
		}
	}
	
	function set(zip1, zip2, s_ok, prevPage) {
		// 보내는 사람 주소 변경시
		if(prevPage == "sender"){
			opener.document.payForm.sadno1.value = zip1;
			opener.document.payForm.sadno2.value = zip2;
			opener.document.payForm.sad1.value = s_ok;
		// 받는 사람 주소 변경시
		}else if(prevPage == "reciever"){
// 			alert("reciever");
// 			alert(zip1);
// 			alert(zip2);
// 			alert(s_ok);
			opener.document.payForm.radno1.value = zip1;
			opener.document.payForm.radno2.value = zip2;
			opener.document.payForm.rad1.value = s_ok;
		}
		self.close();
	}
</script>
<html>
<head>
<title>우편번호 검색</title>
</head>
<body onLoad="document.search_form.post_name.focus()">
	<form method="post" action="zip.jsp?prevPage=<%=prevPage%>" name="search_form">
		<table width="420" border="0" height="79" align="center">
			<tr>
				<td height="22" align="center">우편번호 검색</td>
			</tr>
			<tr>
				<td height="30" align="center">읍/면/동 단위로 입력해 주세요(예:역삼)</td>
			</tr>
		</table>
		<table width="316" border="0" cellspacing="0" align="center">
			<tr>
				<td width="92" height="32" align="center">검색단어</td>
				<td width="224" height="32">
					<%						
						System.out.println("넣기전 : " + prevPage);
						request.setAttribute("prevPage", prevPage);
						if (request.getParameter("post_name") == null) {
					%> <input type="text" name="post_name" size="20"> <%
 	} else {
 %> <input type="text" name="post_name" size="20"
					value="<%=Uni2Ksc(dong)%>"> <%
 	}
 %> <input type="submit" name="submit" value="찾 기"
					onClick="search()">
				</td>
			</tr>
		</table>
		<table width="420" border="0" cellspacing="0" align="center">
			<tr height="25" bgcolor="#6699FF">
				<td width="60" align="center"><font color=white>POST</font></td>
				<td width="360" align="center"><font color=white>주소</font></td>
			<tr>
				<%
				
				con = JdbcUtil.getConnection(); 
					
				if (request.getParameter("post_name") != null) { 
					
					
					String sql = " select * from postno where dong like '" + Uni2Ksc(dong) + "%' "; 
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery(sql); 
					for (int i=1; rs.next(); i++) {
						int zipcode = rs.getInt("zipcode");
						String zipcode1 = Integer.toString(zipcode);
						System.out.println(zipcode1);
						String s1 = rs.getString("sido"); 
						String s2 = rs.getString("sigungu"); 
						String s3 = rs.getString("dong"); 					
						String s4 = rs.getString("bunji"); 
						String s4_1=null;
						
						if(s4==null){
							s4_1="";
						}else{
							s4_1=s4;
						}
						
						String s_ok = s1 + " " + s2 + " " + s3 + " " + s4_1; 
						String zip01 = zipcode1.substring(0,3); 
						String zip02 = zipcode1.substring(3,6);
						%>
						
						<tr>
							<td width="100" bgcolor="aliceblue" align="center"><%=zip01%>-<%=zip02%></td>
							<td width="360"><a href="javascript:set('<%=zip01%>','<%=zip02%>','<%=s_ok%>','<%=prevPage%>')"><%=s_ok%></a></td>
						</tr>
						<%
					} // for-end
				} else {
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
