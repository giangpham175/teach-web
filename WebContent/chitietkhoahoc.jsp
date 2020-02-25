<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
</head>
<body>
	<div class="container">
		<header>
			<div class="header-topbar" style="background-color: #195690;">
				<div class="container">
					<div class="topbar-left pull-left">
						<div class="email">
							<a href=""><i class="topbar-icon fa fa-envelope-o"></i><span
								style="color: white">LTW Sáng T3</span></a>
						</div>
						<div class="hotline">
							<a href=""><i class="topbar-icon fa fa-phone"></i><span
								style="color: white">0909090909</span></a>
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
			<div id="top-nav" class="navbar navbar-inverse navbar-static-top"
				style="padding-right: 30px; background-color: white">


				<div class="navbar-header">
					<div class="logo pull-left">
						<a href="trangchu.jsp" class="header-logo"><img
							src="img/logo.png" style="width: 160px" alt="" /></a>
					</div>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<form class="navbar-form navbar-left" method="GET" role="search">
						<div class="form-group">
							<input type="text" name="q" class="form-control"
								placeholder="Tìm kiếm">
						</div>
						<button type="submit" class="btn btn-default">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span
								class="glyphicon glyphicon-comment" style="color: #ffa500"></span>Thảo
								luận<span class="label label-primary">42</span> </a>
							<ul class="dropdown-menu">
								<li><a href="#"><span class="label label-warning">7:00
											AM</span>Chào, Thầy!</a></li>
								<li><a href="#"><span class="label label-warning">8:00
											AM</span>Cho e hỏi JSP Servlet là gì ạ?</a></li>
								<li><a href="#"><span class="label label-warning">9:00
											AM</span>Để thiết kế được trang web thì mình học gì ạ?</a></li>
								<li class="divider"></li>
								<li><a href="#" class="text-center">Xem tất cả</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span
								class="glyphicon glyphicon-envelope" style="color: #f08080"></span>Hộp
								thư <span class="label label-info">32</span> </a>
							<ul class="dropdown-menu">
								<li><a href="#"><span class="label label-warning">4:00
											AM</span>Trần Thị Thơm gửi thư...</a></li>
								<li><a href="#"><span class="label label-warning">4:30
											AM</span>Huỳnh Nhật Thành gửi thư ...</a></li>
								<li><a href="#"><span class="label label-warning">5:00
											AM</span>Trường Giang gửi thư...</a></li>
								<li class="divider"></li>
								<li><a href="#" class="text-center">Xem tất cả</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span class="glyphicon glyphicon-user"
								style="color: #4169e1"></span><%=request.getAttribute("tentaikhoan")%><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#"><span class="glyphicon glyphicon-user"></span>Cá
										nhân</a></li>
								<li><a href="#"><span class="glyphicon glyphicon-cog"></span>Cài
										đặt</a></li>
								<li class="divider"></li>
								<li><a href="#"><span class="glyphicon glyphicon-off"></span>Đăng
										xuất</a></li>
							</ul></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->

			</div>
			<!-- /container -->

		</header>
		<!-- Main -->
		<div class="container">
<% String message = (String)request.getAttribute("message");%>
<script type="text/javascript">
    var msg = "<%=message%>";
    if(msg!='null')
    	alert(msg);
</script>
			<div class="col-md-9">
				<div>
					<span><span style="font-weight: bold; color: blue">DANH SÁCH HỌC VIÊN</span>
					<span style="float:right;font-weight: bold; color: blue"><a href="baigiang?id=<%= (String)request.getAttribute("makhoahoc")%>&action=dsbg">Xem tài liệu của khóa học</a></span></span>
					<hr>
				</div>
				<div class="table-responsive">
					<form class="navbar-form navbar-right" method="GET" role="search">
						<div class="form-group">
							<input type="text" name="q" class="form-control"
								placeholder="Tìm kiếm">
						</div>
						<button type="submit" class="btn btn-default">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</form>
					<table id="mytable" class="table table-bordred table-striped">

						<thead>
							<th>Họ và tên</th>
							<th>Số điện thoại</th>
							<th>Email</th>

						</thead>
						<tbody>
							<c:forEach var="item" items="${dssv}">
								<tr>
									<td><c:out value="${item.tenHocVien}" /></td>
									<td><c:out value="${item.sdt}" /></td>
									<td><c:out value="${item.email}" /></td>
									
					<%
					String quyen = session.getAttribute("quyen").toString();
    if ( quyen != null && quyen.equalsIgnoreCase("GV")) {
%>
									<td>
										<p data-placement="top" data-toggle="tooltip" title="Edit">
											<button class="btn btn-primary btn-xs" data-title="Edit"
												data-toggle="modal" data-target="#edit">
												<span class="glyphicon glyphicon-envelope"></span>
											</button>
										</p>
									</td>
									<td>
										<button class="btn btn-warning btn-xs">
											<a href="trangchinhsuathongtincanhan.jsp"><span
												class="glyphicon glyphicon-pencil"></span></a>
										</button>
									</td>
									<td>
									<form action"svkh" method="post">
									<input type="hidden" value="${item.taiKhoanID}" name="userid" />
									<input type="hidden" value="<%= (String)request.getAttribute("makhoahoc") %>" name="makhoahoc" />
									<input type="hidden" value="delete" name="actionname" />
										<p data-placement="top" data-toggle="tooltip" title="Delete">
											<button type="submit" onclick="return doConfirm();" class="btn btn-danger btn-xs" data-title="Delete"
												data-toggle="modal" data-target="#delete">
												<span class="glyphicon glyphicon-trash"></span>
											</button>
										</p></form>
									</td><%
    }
%>
								</tr>
							</c:forEach>

						</tbody>
					</table>
					<%
					String quyen = session.getAttribute("quyen").toString();
					    if ( quyen != null && quyen.equalsIgnoreCase("GV")) {
					%>
					<p data-placement="top" data-toggle="tooltip" title="Delete">
						<button class="btn btn-success btn-lg" data-toggle="modal"
							data-target="#myModal" data-title="AddUser" data-toggle="modal">
							<span class="glyphicon glyphicon-user"></span> Thêm học viên
						</button>
					</p><%
    }
%>
					<!-- Modal -->
					<div id="myModal" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<form method="post" action="svkh">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Thêm học viên</h4>
									</div>
									<div class="modal-body" style="height: 220px;">
										<div class="form-group col-xs-12">
											<div class="col-xs-4">
											
									<input type="hidden" value="add" name="actionname" />
												<label for="email">ID:</label>
											</div>
											<div class="col-xs-4">
												<input type="hidden" id="makhoahoc" name="makhoahoc" value="<%= (String)request.getAttribute("makhoahoc") %>"/>
												<input type="text" class="form-control" name="userid" id="userid">
											</div>
											<div class="col-xs-4">
												<button class="btn btn-default" type="button"
													onclick="searchUser()">Tìm kiếm</button>
											</div>
										</div>
										<div class="form-group col-xs-12">
											<div class="col-xs-12">
												<label for="email">Tên học viên:</label><span
													class="boldtext" id="tenhocvien"></span><br />
											</div>
										</div>
										<div class="form-group col-xs-12">
											<div class="col-xs-12">
												<label for="email">Số điện thoại:</label><span
													class="boldtext" id="sdt"></span><br />
											</div>
										</div>
										<div class="form-group col-xs-12">
											<div class="col-xs-12">
												<label for="email">Email:</label><span class="boldtext"
													id="email"></span><br />
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-default">Thêm</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</form>
						</div>

					</div><!-- end modal -->
				</div>

				<div class="clearfix"></div>
				<ul class="pagination pull-right">
					<li class="disabled"><a href="#"><span
							class="glyphicon glyphicon-chevron-left"></span></a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span></a></li>
				</ul>
			</div>
		</div>


	</div>

	<!--Footer-->
	<div class="footer" class="row">
		<div class="container">
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
		function searchUser() {
			$.getJSON("taikhoan?id=" + $('#userid').val()+'&makhoahoc='+$('#makhoahoc').val()+'&actionname=getjson', function(result) {
				if (result.result == 1) {
					$('#tenhocvien').text(result.name);
					$('#email').text(result.email);
					$('#sdt').text(result.sdt);
				} else {
					$('#tenhocvien').text("");
					$('#email').text("");
					$('#sdt').text("");
					alert("Không tìm thấy");
				}
			});
		}
		function doConfirm(){
			return confirm('Bạn có chắc muốn xóa học viên này khỏi khóa học?');
		}
	</script>
</body>

</html>
