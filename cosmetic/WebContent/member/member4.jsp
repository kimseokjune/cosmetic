<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function isNum(obj) {
		var sValue = obj.value;
		var idx = sValue.length;
		var ValidChars = "0123456789";
		var IsNumber = true;
		var Char;
		for (i = 0; i < idx && IsNumber == true; i++) {
			Char = sValue.charAt(i);
			if (ValidChars.indexOf(Char) == -1) {
				alert("숫자를 입력 하세요.");
				obj.value = "";
				obj.focus();
				IsNumber = false;
			}
		}
		return IsNumber;
	}
	function check() {
		if ((joinForm.hp1.value.length < 3) || (joinForm.hp2.value.length < 3)
				|| (joinForm.hp3.value.length < 4)) {
			alert("핸드폰번호를 입력해 주십시오.");
			joinForm.hp1.focus();
			return false;
		} else if (!joinForm.hp1.value.match("^[0][1][0-9]")) {
			alert("잘못된 핸드폰 번호 입니다.");
			joinForm.hp1.focus();
			return false;
		}

		if (joinForm.tel1.value.length != 2) {
			if ((joinForm.tel1.value.length < 2)
					|| (joinForm.tel2.value.length < 3)
					|| (joinForm.tel3.value.length < 4)) {
				alert("전화번호를 입력해 주십시오.");
				joinForm.tel1.focus();
				return false;
			} else if (!joinForm.tel1.value.match("^[0][3,4,5,6]")) {
				alert("잘못된 전화번호 입니다.");
				joinForm.tel1.focus();
				return false;
			}
		} else if ((joinForm.tel1.value.length == 2)
				&& (!joinForm.tel1.value.match("^[0][2]"))) {
			alert("잘못된 전화번호 입니다.");
			joinForm.tel1.focus();
			return false;
		} else if ((joinForm.tel1.value.length > 2)) {
			if ((joinForm.tel2.value.length < 3)
					|| (joinForm.tel3.value.length < 4)) {
				alert("잘못된 전화번호 입니다.");
				joinForm.tel1.focus();
				return false;
			}
		}
		var reg_email = /^[0-9A-Z]([-_\.]?[0-9A-Z])*@[0-9A-Z]([-_\.]?[0-9A-Z])*\.[A-Z]{2,6}$/i;
		var email = document.joinForm.email.value;
		if (!reg_email.test(email)) {
			alert("이메일 주소가 유효하지 않습니다");
			return false;
		}

		if (!document.joinForm.pw.value) {
			alert("Password를 입력하세요");
			return false;
		}
		if (!document.joinForm.npw.value) {
			alert("새로운 Password를 입력하세요");
			return false;
		}
		if (!document.joinForm.cnpw.value) {
			alert("새로운 확인 Password 를 입력하세요");
			return false;
		}
		if (document.joinForm.npw.value != document.joinForm.cnpw.value) {
			alert("새로운 Password가 서로다릅니다.");
			return false;
		}

		if (joinForm.npw.value.length < 4) {
			alert("Password는 4자 이상입니다.");
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
				|| !document.modifyFormad1.value) {
			alert("주소를 입려하세요");
			return false;
		}
		if (!document.joinForm.ad2.value) {
			alert("상세 주소를 입려하세요");
			return false;
		}

	}
	// function echeck() {

	// 	var email = document.joinForm.email.value;

	// 	if (email.length < 1 || email == null) {
	// 		alert("중복체크할 이메일을 입력하십시오");
	// 		return false;
	// 	}
	// 	var reg_email = /^[0-9A-Z]([-_\.]?[0-9A-Z])*@[0-9A-Z]([-_\.]?[0-9A-Z])*\.[A-Z]{2,6}$/i ;
	// 	 if(!reg_email.test(email))
	// 	  {
	// 		 alert("유효한 이메일 주소가 아닙니다");
	// 		 return false;
	// 	  }
	// 	var url = "eCheck2.jsp?email=" + email;
	// 	window.open(url, "get", "height = 180, width = 300");
	// }
	function echeck() {
		window
				.open(
						"eCheck3.jsp",
						"아이디체크",
						"scrollbars=yes, width=450, height=300, resizable=yes, menubar=no, top=150, left=260");
		return true;
	}
	function passchk() {
		var pass = document.joinForm.npw.value;
		var pass2 = document.joinForm.cnpw.value;
		if (pass2.length == 0 || pass2 == null) {
			document.joinForm.chk.value = "비밀번호를 입력하세요";
		} else if (pass != pass2) {
			document.joinForm.chk.value = "비밀번호가 다릅니다.";
			$("#pw_status").css({
				"color" : "red",
				"font-weight" : "bold"
			});
		} else {
			if(pass2.length < 4){
			document.joinForm.chk.value = "비밀번호가 너무 짧습니다";
			}else{
			document.joinForm.chk.value = "비밀번호가 동일합니다.";
			$("#pw_status").css({
				"color" : "#4d90fe",
					"font-weight" : "bold"
			});
			
			}
		}
		return;
	}
	function zipfind() {
		window
				.open(
						"zip.jsp",
						"우편번호",
						"scrollbars=yes, width=450, height=300, resizable=yes, menubar=no, top=150, left=260");
		return true;
	}
</script>
<style type="text/css">
.layer_content2 input[type="text"],input[type="password"] {
	padding: 6px 7px 6px 7px;
	margin: 2px 0px 2px 0px;
}

#mtable {
	margin: 0 auto;
	width: 700px;
}

#pw_status {
	background-color: rgba(255, 255, 255, 0);
	border: none;
}

#text_right {
	text-align: right;
}

.menu {
	font-weight: bold;
}
</style>

</head>
<body>
<form action="../member/member4Process.do" method="post"
				name="joinForm" onSubmit="return check()">
	<div class="layer_wrapper layer_box_effect">

		<div class="layer_title">
			<img src="../member/img/sign.png">회원정보수정
		</div>
		<div class="layer_content2">
			<Br> <Br>
			<!-- 	폼태그를 이용해서 member4Process.do로 이동시킨다 -->
			

				<table id="mtable">
					<tr>
						<td class="menu">아이디</td>
						<td><%=session.getAttribute("id")%></td>
					</tr>
					<tr>
						<td class="menu">현재 비밀번호</td>
						<td><input type="password" name="pw"></td>
					</tr>
					<tr>
						<td class="menu">새 비밀번호</td>
						<td><input type="password" name="npw" /></td>
					</tr>
					<tr>
						<td class="menu">새 비밀번호확인</td>
						<td><input type="password" name="cnpw" onblur="passchk()" />&nbsp;
							<input type="text" style="border-width: 0px" size="20" name="chk"
							value="비밀번호를 입력하세요" readonly="readonly" id="pw_status"></td>
					</tr>
					<tr>
						<td class="menu">이름</td>
						<td><input type="text" name="name" value="${name }" /></td>
					</tr>
					<tr>
					<td class="menu">이메일</td>
					<td><input type="text" name="email" value="${email }"
						readonly="readonly" />&nbsp;<input type="button" value="이메일중복체크"
						onclick="echeck()" class="myButton" /></td>
					</tr>
					<tr>
						<td class="menu">주소</td>
						<td><input type="text" size="4" name="adno1" value="${zip1 }" />-<input
							type="text" size="4" name="adno2" value="${zip2 }" />&nbsp;<input
							type="button" value="우편번호검색" onclick="zipfind()" class="myButton" /><br />
							<input type="text" name="ad1" value="${address }" />&nbsp;<input
							type="text" name="ad2" value="${daddress }" /></td>
					</tr>
					<tr>
						<td class="menu">핸드폰</td>
						<td><input type="text" size="4" name="hp1" value="${hp1}"
							maxlength="3" onblur="isNum(this)" />-<input type="text"
							size="4" name="hp2" value="${hp2}" maxlength="4"
							onblur="isNum(this)" />-<input type="text" size="4" name="hp3"
							value="${hp3}" maxlength="4" onblur="isNum(this)" /></td>
					</tr>
					<tr>
						<td class="menu">전화번호</td>
						<td><input type="text" size="4" name="tel1" value="${tel1}"
							maxlength="4" onblur="isNum(this)" />-<input type="text"
							size="4" name="tel2" value="${tel2}" maxlength="4"
							onblur="isNum(this)" />-<input type="text" size="4" name="tel3"
							value="${tel3}" maxlength="4" onblur="isNum(this)" /></td>
					</tr>
				</table>
		</div>

		<div class="layer_submit">
			<input type="submit" value="정보수정" id="mem" class="myButton" /> <input
				type="button" value="이전으로" id="back" class="myButton"
				onclick="history.back();" />


		</div>
		
	</div>
</form>
</body>
</html>