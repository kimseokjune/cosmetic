<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="section_js/jquery.cycle.all.js"></script>
<style>
#section {
	width: 100%;
}

ul {
	list-type: none;
	list-style: none;
	padding: 0;
	margin: 0;
}

.slider {
	margin: 0 auto;
	width: 1160px;
	height: 440px;
	-moz-box-shadow: 0px 0px 12px #b4b4b4;
	-webkit-box-shadow: 0px 0px 12px #b4b4b4;
	box-shadow: 0px 0px 12px #b4b4b4;
}
</style>
<script language="javascript">
	$(document).ready(function() {
		$('#slider1').cycle({
			fx : 'fade', //'scrollLeft,scrollDown,scrollRight,scrollUp',blindX, blindY, blindZ, cover, curtainX, curtainY, fade, fadeZoom, growX, growY, none, scrollUp,scrollDown,scrollLeft,scrollRight,scrollHorz,scrollVert,shuffle,slideX,slideY,toss,turnUp,turnDown,turnLeft,turnRight,uncover,ipe ,zoom
			speed : 'slow',
			timeout : 2100
		});
	});
</script>

<div id="section">
			<div class="container">
				<div class="slider">
					<ul id="slider1">
						<li><img border="0" src="../main/img/main01.jpg" width="1160"
							height="440" /></li>
						<li><img border="0" src="../main/img/main02.jpg" width="1160"
							height="440" /></li>

						<li><img border="0" src="../main/img/main03.jpg" width="1160"
							height="440" /></li>

						<li><img border="0" src="../main/img/main04.jpg" width="1160"
							height="440" /></li>

						<li><img border="0" src="../main/img/main05.jpg" width="1160"
							height="440" /></li>
					</ul>
				</div>
			</div>

	</font></span>

</div>