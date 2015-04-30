<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>



<link rel="stylesheet" href="../mypage/css/normalize.css">
<link rel="stylesheet" href="../mypage/css/style.css">
<script src="../mypage/js/modernizr.js"></script>
<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
<script src="../mypage/js/index.js"></script>


  <!--Pattern HTML-->

        <!--Begin Pattern HTML-->
        <div class="accordion clearfix">
            <div class="accordion-innerwrap">
                <ul class="panel-container list-reset" tabindex="1">
                    <li><h4 id="trigger1" class="panel-title"><b class="icon panel-closed" aria-hidden="true">&oplus;</b> 나의 정보 </h4>
                        <ul class="panel-content list-reset visuallyhidden" aria-expanded="false">
                            <li><b class="icon"></b><a href="../member/member3.do"> 회원정보수정</a></abbr></li>
                            <li><b class="icon"></b><a href="../member/withdraw.do"> 회원탈퇴</a></abbr></li>
                           
                        </ul>
                    </li>
                </ul>



                <ul class="panel-container list-reset" tabindex="2">
                    <li><h4 id="trigger2" class="panel-title"><b class="icon panel-closed" aria-hidden="true">&oplus;</b> 나의 쇼핑정보 </h4>
                        <ul class="panel-content visuallyhidden list-reset" aria-expanded="false">
                            <li><b class="icon"></b><a href="../order/customerList.do">주문조회</a></li>
                            <li><b class="icon"></b><a href="../cart/list.do">장바구니</a></li>

                        </ul>
                    </li>
                </ul>


              

            </div>
        </div>





