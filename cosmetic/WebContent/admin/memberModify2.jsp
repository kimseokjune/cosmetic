<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function echeck() {
	window.open(
					"eCheck3.jsp",
					"아이디체크",
					"scrollbars=yes, width=450, height=300, resizable=yes, menubar=no, top=150, left=260");
	return true;
}

function check() {
	if ( (joinForm.hp1.value.length < 3) || (joinForm.hp2.value.length < 3) || (joinForm.hp3.value.length < 4) ){
        alert("핸드폰번호를 입력해 주십시오.");
        joinForm.hp1.focus();
        return false;
      }else if (!joinForm.hp1.value.match("^[0][1][0-9]")) {
        alert("잘못된 핸드폰 번호 입니다.");
        joinForm.hp1.focus();
        return false;  
      }
     
	
	if (!document.joinForm.pw.value) {
		alert("Password를 입력하세요");
		return false;
	}
	if (joinForm.pw.value.length <4){
		alert("Password는 4자 이상입니다");
		return false;
	}
	if (!document.joinForm.name.value) {
		alert("이름을 입력하세요.");
		return false;
	}
	if (!document.joinForm.email.value) {
		alert("email을 입력하세요.");
		return false;
	}
	if (!document.joinForm.adno1.value || !document.joinForm.adno2.value
			|| !document.joinForm.ad1.value) {
		alert("주소를 입려하세요");
		return false;
	}
	if (!document.joinForm.ad2.value) {
		alert("상세 주소를 입려하세요");
		return false;
	}
	if (joinForm.tel1.value.length != 2) {  
	if ( (joinForm.tel1.value.length < 2) || (joinForm.tel2.value.length < 3) || (joinForm.tel3.value.length < 4) ){         
	       alert("전화번호를 입력해 주십시오.");
	       joinForm.tel1.focus();
	       return false;
	     }else if (!joinForm.tel1.value.match("^[0][3,4,5,6]")) {
	       alert("잘못된 전화번호 입니다.");
	       joinForm.tel1.focus();
	       return false;
	     }
	   }else if  ( (joinForm.tel1.value.length == 2)&&(!joinForm.tel1.value.match("^[0][2]")) ){
	     alert("잘못된 전화번호 입니다.");
	     joinForm.tel1.focus();
	     return false;
	   }else if ( (joinForm.tel1.value.length >2 )){
		   if ((joinForm.tel2.value.length < 3) || (joinForm.tel3.value.length < 4) ){
			   alert("잘못된 전화번호 입니다.");
			     joinForm.tel1.focus();
			     return false;
		   }
	   }

}
function echeck() {

	var email = document.joinForm.email.value;

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
	var url = "eCheck3.jsp?email=" + email;
	window.open(url, "get", "height = 180, width = 300");
}
function zipfind() {
	window.open(
					"zip.jsp",
					"우편번호",
					"scrollbars=yes, width=450, height=300, resizable=yes, menubar=no, top=150, left=260");
	return true;
}

</script>
<style type="text/css">
.layer_content th {
	background-color: #ccc;
	border-top: 1px solid #999;
	border-bottom: 1px solid #999;
	height: 50px;
}

.layer_content table {
	width: 850px;
	margin: 0 auto;
}

.border-top {
	border-top: 1px solid #999
}

.border-bottom {
	border-bottom: 1px solid #999
}
#tr_td_align{text-align: right;}
.layer_content caption{text-align: right;}
</style>
</head>
<body>
	<div>
		<div class="layer_wrapper layer_box_effect">
			<div class="layer_title">
				<img src="../member/img/magnifier.png">&nbsp;&lt;회원정보수정&gt;

			</div>
			<div class="layer_content">
				<!-- 	폼태그를 이용해서 member4Process.do로 이동시킨다 -->
				<form action="../admin/admin2Process.do" method="post"
					name="joinForm" onSubmit="return check()">
<br>
					<table id="mtable">
					<caption><a href="../admin/withdraw.do">회원강제탈퇴</a></caption>
						<tr>
							<th class="menu">아이디</th>
							<td  class="border-top">${id }</td>
						</tr>


						<tr>
							<th class="menu">비밀번호</th>
							<td  class="border-top"><input type="text" name="pw" value="${pw }" /></td>
						</tr>

						<tr>
							<th class="menu">이름</th>
							<td  class="border-top"><input type="text" name="name" value="${name }" /></td>
						</tr>
						<tr>
							<th class="menu">이메일</th>
							<td  class="border-top"><input type="text" name="email" value="${email }"
								readonly="readonly" />&nbsp;<input type="button" value="이메일중복체크"
								onclick="echeck()" /></td>
						</tr>
						<tr>
							<th class="menu">주소</th>
							<td  class="border-top"><input type="text" size="4" name="adno1"
								value="${zip1 }" />-<input type="text" size="4" name="adno2"
								value="${zip2 }" />&nbsp;<input type="button" value="우편번호검색"
								onclick="zipfind()" /><br /> <input type="text" name="ad1"
								value="${address }" />&nbsp;<input type="text" name="ad2"
								value="${daddress }" /></td>
						</tr>
						<tr>
							<th class="menu">핸드폰</th>
							<td  class="border-top"><input type="text" size="4" name="hp1" value="${hp1}"
								maxlength="3" onblur="isNum(this)" />-<input type="text"
								size="4" name="hp2" value="${hp2}" maxlength="4"
								onblur="isNum(this)" />-<input type="text" size="4" name="hp3"
								value="${hp3}" maxlength="4" onblur="isNum(this)" /></td>
						</tr>
						<tr>
							<th class="menu">전화번호</th>
							<td  class="border-top border-bottom"><input type="text" size="4" name="tel1" value="${tel1}"
								maxlength="4" onblur="isNum(this)" />-<input type="text"
								size="4" name="tel2" value="${tel2}" maxlength="4"
								onblur="isNum(this)" />-<input type="text" size="4" name="tel3"
								value="${tel3}" maxlength="4" onblur="isNum(this)" /></td>
						</tr>
						<tr>
						<td colspan="2" id="tr_td_align" ><input type="submit" value="정보수정" id="mem" /> <input
							type="button" value="이전으로" id="back" onclick="history.back();" />
						</td>
						</tr>
					

					</table>


				</form>
			
					
			
			</div>
		</div>
	</div>

</body>
</html>