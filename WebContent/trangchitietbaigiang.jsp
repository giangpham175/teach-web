<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta charset="utf-8">
<title>Website Cá Nhân Hỗ Trợ Việc Giảng Dạy Của Giảng Viên</title>

<!-- Tích hợp bộ CSS của Booststrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- CSS Riêng -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="shortcut icon" href="img/icon.ico" />
<!--  Responsive-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,400,700">
<script src="jquery/jquery-3.1.0.js"></script>

<script>
	var request;
	function postComment() {
		var cauhoi = document.commentform.cauhoi.value;
		var noidung = document.commentform.noidung.value;

		var url = "getThongTinComment.jsp?cauhoi=" + cauhoi + "&noidung="
				+ noidung;

		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}

		try {
			request.onreadystatechange = function() {
				if (request.readyState == 4) {
					var val = request.responseText;
					document.getElementById('mylocation').innerHTML = val;
				}
			}//end of function  
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
</script>
</head>

<body">

	<div class="container">
		<!-- HEADER-->
		<header>
			<div class="header-topbar">
				<div class="container">
					<div class="topbar-left pull-left">
						<div class="email">
							<a href=""><i class="topbar-icon fa fa-envelope-o"></i><span>LTW
									Sáng T3</span></a>
						</div>
						<div class="hotline">
							<a href=""><i class="topbar-icon fa fa-phone"></i><span>0909090909</span></a>
						</div>

					</div>


					<div class="topbar-right pull-right">
						<div class="socials">
							<a href="https://www.facebook.com/" class="facebook"><i
								class="fa fa-facebook"></i></a> <a href="https://www.youtube.com/"
								class="youtube"><i class="fa fa-youtube"></i></a>
						</div>
						<div class="group-sign-in"></div>
					</div>


				</div>
			</div>

			<div class="header-main homepage-01">
				<div class="container">
					<div class="header-main-wrapper">
						<div class="navbar-header">
							<div class="logo pull-left">
								<a href="trangchu.jsp" class="header-logo"><img
									src="img/logo.png" alt="" /></a>
							</div>
							<button type="button" data-toggle="collapse"
								data-target=".navigation" class="navbar-toggle edugate-navbar">
								<span class="icon-bar"></span><span class="icon-bar"></span><span
									class="icon-bar"></span>
							</button>
						</div>
						<div class="col-sm-3 col-md-3">
							<form class="navbar-form" role="search">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Search"
										name="q">
									<div class="input-group-btn">
										<button class="btn btn-default" type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
							</form>
						</div>
						<nav class="navigation collapse navbar-collapse pull-right">
							<ul class="nav-links nav navbar-nav">

								<li class="active"><a href="#" class="main-menu">Trang
										chủ </a></li>
								<li class="dropdown"><a href="#"
									class="main-menu data-toggle="dropdown "">Các khóa học <span
										class="fa fa-angle-down icons-dropdown"></span></a>
									<ul class="dropdown-menu edugate-dropdown-menu-1">
										<li><a href="#" class="link-page">Android</a></li>
										<li><a href== "#" class="link-page">Java</a></li>
										<li><a href== "#" class="link-page">Java</a></li>
										<li><a href== "#" class="link-page">Lập trình web</a></li>
									</ul></li>

								<li class=""><a href="#" class="main-menu">Liên hệ </a></li>

								<li class="dropdown"><a href="#"
									class="glyphicon glyphicon-bell" id="thongbao"></a></li>
								<li class="dropdown pull-right" id="user"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown">Chào, Pé Dứa
										<span class="glyphicon glyphicon-user text-center"></span>
								</a>
									<ul class="dropdown-menu">
										<li><a href="#">Tin nhắn<span
												class="badge pull-right"> 42 </span></a></li>
										<li class="divider"></li>
										<li><a href="trangthongtincanhan.html">Hồ sơ của bạn<span
												class="glyphicon glyphicon-user pull-right"></span></a></li>
										<li class="divider"></li>
										<li><a href="#">Cài đặt<span
												class="glyphicon glyphicon-cog pull-right"></span></a></li>
										<li class="divider"></li>
										<li><a href="#">Trợ giúp <span
												class="glyphicon glyphicon-flag pull-right"></span></a></li>
										<li class="divider"></li>
										<li><a href="#">Đăng xuất <span
												class="glyphicon glyphicon-log-out pull-right"></span></a></li>
									</ul></li>
								<div class="nav-search  hide">
									<form>
										<input type="text" placeholder="Tìm kiếm" class="searchbox" />
										<button type="submit" class="searchbutton fa fa-search"></button>
									</form>
								</div>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</header>
		<!-- End Header -->
		<!--Content-->
		<div class="content">
			<!--Devide Body-->
			<div class="content-body">
				<div class="container">
					<h1 style="color: red; font-size: 50; font-weight: bold;">BẠN
						ĐANG XEM BÀI GIẢNG</h1>
					<hr>
					<div id="exTab1">
						<ul class="nav nav-pills">
							<li class="active"><a href="#1a" data-toggle="tab">Video</a>
							</li>
							<li><a href="#2a" data-toggle="tab">Tài liệu</a></li>
							<li><a href="#3a" data-toggle="tab">Bài Tập</a></li>
							<li><a href="#4a" data-toggle="tab">Thảo luận</a></li>
							<!--Dành cho Giảng Viên-->
							<li><a href="#5a" data-toggle="tab">Thông báo</a></li>
						</ul>
					</div>

					<div class="tab-content clearfix">
						<div class="tab-pane active" id="1a">
							<h3 style="color: blue; font-size: 25">
								<strong>${baigiang.tenBaiGiang}</strong>
							</h3>
							<hr>

							<video id="videoarea" controls="controls" poster="" src=""></video>
							<br>
							<hr>
							
								<input type="hidden" value="update" name="actionname" /> <input
									type="hidden" name="idbaigiang" value="${baigiang.idBaiGiang}" />
								
								<div></div>
								<br />
								<div class="panel panel-default">
									<div class="panel-heading">Tóm tắt video</div>
									<div class="panel-body">
										<div class="form-group" style="width: 100%;">
											<textarea class="form-control" style="width: 100%;"
												name="mota" rows="5" id="mota">${baigiang.mota}</textarea>
										</div>
									</div>
								</div>
							</form>
							<hr>

						</div>
						<c:if test=$""></c:if>


					</div>
				</div>
			</div>
			<!--End Divide Body->
		
		<!--End Content-->
			<!--Footer-->
			<div class="footer" class="row">
				<div class="container">
					<div id="contact" class="container-fluid bg-grey">
						<h3>Lập trình Web Nhóm 14</h3>
						<br>
						<div class="row">
							<div class="col-sm-5">
								<p>Nhóm 14, Sáng Thứ 3 Môn Lập trình Web</p>
								<p>
									<span class="glyphicon glyphicon-map-marker"></span> Võ Văn
									Ngân, Q.Thủ Đức, TP.HCM
								</p>
								<p>
									<span class="glyphicon glyphicon-phone"></span>0909090909
								</p>
								<p>
									<span class="glyphicon glyphicon-envelope"></span>
									pilu15051996@gmail.com
								</p>
							</div>
							<div class="col-sm-7 slideanim">
								<div class="row">
									<div class="col-sm-6 form-group">
										<input class="form-control" id="name" name="name"
											placeholder="Tên" type="text" required>
									</div>
									<div class="col-sm-6 form-group">
										<input class="form-control" id="email" name="email"
											placeholder="Địa chỉ email" type="email" required>
									</div>
								</div>
								<textarea class="form-control" id="comments" name="comments"
									placeholder="Nội dung" rows="5"></textarea>
								<br>
								<div class="row">
									<div class="col-sm-12 form-group">
										<button class="btn btn-default pull-right" type="submit">Gởi</button>
									</div>
								</div>
							</div>
						</div>
					</div>

					<img id="googleMap" style="height: auto; width: 100%;"
						src="img/map.PNG" alt="">
					<div class="col-md-12 text-center">
						<img style="width: 40px; height: 40px; padding: 5px"
							src="img/logo.jpg"> &copy 2016 - BẢN QUYỀN THUỘC VỀ NHÓM 14
						LTW
					</div>

				</div>
			</div>
			<!-- End Footer-->
		</div>
		<img id="back-to-top" src="img/back-to-top.png">
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Tích hợp JS của Bootstrap -->
		<script src="js/bootstrap.min.js"></script>
		<script src="js/main.js"></script>
		<script>
			function doConfirm() {
				return confirm('Bạn có chắc muốn xóa tài liệu này khỏi bài giảng?');
			}
			countdown('10/19/2013', [ 'days', 'hours', 'minutes', 'seconds' ],
					function() {
						console.log('done');
					})
		</script>
</body>

</html>
