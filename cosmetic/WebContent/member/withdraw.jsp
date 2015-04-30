<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#search_id{
width:700px;
margin: 0 auto;
}
</style>
</head>
<body>



	<div class="layer_wrapper layer_box_effect">
		<div class="layer_title">
			<img src="../member/img/letters13.png">&nbsp;회원탈퇴
		</div>
		<div class="layer_content">

			<form action="../member/withdrawProcess.do" method="post" name="form">
<Br><Br>
				<table id= "search_id">
					<tr>
			
						<td><b>고객님께서 회원 탈퇴를 원하신다니 저희 쇼핑몰의 서비스가 많이 부족하고 미흡했나 봅니다. <br> 불편하셨던
							점이나 불만사항을 알려주시면 적극 반영해서 고객님의 불편함을 해결해 드리도록 노력하겠습니다.<br>  아울러 회원 탈퇴시의
							아래 사항을 숙지하시기 바랍니다.</b> <br><br>1. 회원 탈퇴 시 고객님의 정보는 상품 반품 및 A/S를 위해 전자상거래 등에서의
							소비자 보호에 관한 법률에 의거한 고객정보 보호정책에따라 관리 됩니다. <br> <br> 2. 탈퇴 시 고객님께서 보유하셨던 적립금은
							삭제 됩니다. <br> <br></td>
					</tr>
					<tr >
					
						<td id="pw"><b>비밀번호를 입력하세요:</b> &nbsp;<input type="password" name="pw" /></td>
					</tr>
				</table>


				<div class="layer_submit">

					<input type="submit" value="탈퇴" id="mem" class="myButton"/> <input type="button"
						value="취소" id="back" onclick="history.back();" class="myButton"/>

				</div>
			</form>
		</div>
	</div>
	<br><br>
</body>
</html>