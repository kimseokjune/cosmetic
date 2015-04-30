<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<style>


</style>


<link rel="stylesheet" href="../admin/css/style.css">
<script src="../admin/js/modernizr.js"></script>
<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
<script src="../admin/js/index.js"></script>

  <!--Pattern HTML-->
    <div id="pattern" class="pattern">

        <!--Begin Pattern HTML-->
        <div class="accordion clearfix">
            <div class="accordion-innerwrap">
                <ul class="panel-container list-reset" tabindex="1">
                    <li><h4 id="trigger1" class="panel-title"><b class="icon panel-closed" aria-hidden="true">&oplus;</b> 회원관리 </h4>
                        <ul class="panel-content list-reset visuallyhidden" aria-expanded="false">
                            <li> <a href="../admin/memberList2.do"> 회원리스트 </a> </li>
                            <li><b class="icon"></b><a href="../admin/memberModify3.do"> 회원정보수정</a></abbr></li>
                           
                        </ul>
                    </li>
                </ul>



                <ul class="panel-container list-reset" tabindex="2">
                    <li><h4 id="trigger2" class="panel-title"><b class="icon panel-closed" aria-hidden="true">&oplus;</b> 상품관리 </h4>
                        <ul class="panel-content visuallyhidden list-reset" aria-expanded="false">
                            <li><b class="icon"></b> <a href="../goods/admin_list.do?cate=all&sort=sale&method=asc"> 상품 목록 </a></li>
                            <li><b class="icon"></b> <a href="../goods/admin_write.do">상품 등록</a></li>
                     
                        </ul>
                    </li>
                </ul>



                <ul class="panel-container list-reset" tabindex="3">
                    <li><h4 id="trigger3" class="panel-title"><b class="icon panel-closed" aria-hidden="true">&oplus;</b> 주문관리</h4>
                        <ul class="panel-content visuallyhidden list-reset" aria-expanded="false">
                            <li><b class="icon"></b><a href="../order/adminList.do">주문관리</a></li>
                        </ul>
                    </li>
                </ul>


                <ul class="panel-container list-reset" tabindex="4">
                    <li><h4 id="trigger4" class="panel-title"><b class="icon panel-closed" aria-hidden="true">&oplus;</b> 게시판관리</h4>
                        <ul class="panel-content visuallyhidden list-reset" aria-expanded="false">
                            <li><b class="icon"></b><a href="../admin/board_setting.do"> 게시판 옵션 설정 </a></li>

                        </ul>
                    </li>
                </ul>




            </div>
        </div>
    </div>



   <br>
   <Br>


