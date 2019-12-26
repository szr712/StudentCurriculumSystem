<%@page import="studentCurriculum.StudentBean"%>
<%@page import="studentCurriculum.CourseBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.BufferedWriter"%>

<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<title>高校选课管理系统</title>
	<meta charset="UTF-8">
	<meta name="description" content="overview &amp; stats" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

	<!-- page specific plugin styles -->

	<!-- text fonts -->
	<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

	<!-- ace styles -->
	<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

	<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
	<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
	<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />



	<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

	<!-- inline styles related to this page -->

	<!-- ace settings handler -->
	<script src="assets/js/ace-extra.min.js"></script>

	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

	<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<body class="no-skin">

	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<div class="navbar-container ace-save-state" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left">
				<a href="index.html" class="navbar-brand"> <small> <i class="fa fa-graduation-cap"></i> 高校选课管理 &nbsp;

					</small>
				</a>
			</div>

			<div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue dropdown-modal"><a data-toggle="dropdown" href="#" class="dropdown-toggle">
							<img class="nav-user-photo" src="assets/images/avatars/avatar4.png" alt="Jason's Photo" />
							<span class="user-info"> <small>欢迎,</small>

								<%
									String value = null;
									Cookie cookie = null;
									Cookie[] cookies = request.getCookies();
									if (cookies != null) {
										for (int i = 0; i < cookies.length; i++) {
											cookie = cookies[i];
											if (cookie.getName().equals("name"))
												value = cookie.getValue();

										}
									}
									if (value != null) {
								%> <%=value%> <%
 	}
 %>

							</span> <i class="ace-icon fa fa-caret-down"></i>
						</a>

						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">


							<li><a href="StuProfile"> <i class="ace-icon fa fa-user"></i> 个人信息
								</a></li>

							<li class="divider"></li>

							<li><a href="logout"> <i class="ace-icon fa fa-power-off"></i> 退出登录
								</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<!-- /.navbar-container -->
	</div>

	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {}
		</script>

		<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
			<script type="text/javascript">
				try {
					ace.settings.loadState('sidebar')
				} catch (e) {}
			</script>

			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<a class="btn btn-success" href="TeaCourse"> <i class="ace-icon fa fa-table"></i>
					</a> <a class="btn btn-info" href="TeaChoose"> <i class="ace-icon fa fa-info"></i>
					</a> <a class="btn btn-warning" href="TeaGrade"> <i class="ace-icon fa fa-pencil"></i>
					</a>


				</div>

				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span>

					<span class="btn btn-warning"></span>


				</div>
			</div>
			<!-- /.sidebar-shortcuts -->

			<ul class="nav nav-list">

				<li class=""><a href="TeaProfile"> <i class="menu-icon fa fa-user"></i> <span class="menu-text">
							个人信息 </span> <b class="arrow"></b>

				<li class=""><a href="TeaCourse"> <i class="menu-icon fa fa-table"></i> <span class="menu-text">
							任课查询 </span>
					</a> <b class="arrow"></b>
				<li class=""><a href="TeaSetup"> <i class="menu-icon fa fa-database"></i> <span class="menu-text">
						开课 </span>
				</a> <b class="arrow"></b>
				<li class="active open"><a href="#" class="dropdown-toggle"> <i class="menu-icon fa fa-info"></i> <span
							class="menu-text">
							成绩查询 </span><b class="arrow fa fa-angle-down"></b>
					</a> <b class="arrow"></b>
					<ul class="submenu">

						<li class="">
							<a href="TeaGpa">
								<i class="menu-icon fa fa-caret-right"></i>
								均绩查询
							</a>

							<b class="arrow"></b>
						</li>

						<li class="active">
							<a href="TeaSingle">
								<i class="menu-icon fa fa-caret-right"></i>
								单门成绩及排名
							</a>

							<b class="arrow"></b>
						</li>

						<li class="">
							<a href="TeaAve">
								<i class="menu-icon fa fa-caret-right"></i>
								平均成绩
							</a>

							<b class="arrow"></b>
						</li>

						
					</ul>
				</li>
				<li class=""><a href="TeaIn"> <i class="menu-icon fa fa-pencil"></i> <span class="menu-text">
							成绩录入 </span>
					</a> <b class="arrow"></b>
				<li class=""><a href="TeaCPassword"> <i class="menu-icon fa fa-cog"></i> <span class="menu-text">
							修改密码 </span>
					</a> <b class="arrow"></b>
			</ul>
			<!-- /.nav-list -->

			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
					data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>
		</div>

		<div class="main-content">
			<div class="main-content-inner">


				<div class="page-content">
					<div class="ace-settings-container" id="ace-settings-container">
						<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
							<i class="ace-icon fa fa-cog bigger-130"></i>
						</div>

						<div class="ace-settings-box clearfix" id="ace-settings-box">
							<div class="pull-left width-50">
								<div class="ace-settings-item">
									<div class="pull-left">
										<select id="skin-colorpicker" class="hide">
											<option data-skin="no-skin" value="#438EB9">#438EB9</option>
											<option data-skin="skin-1" value="#222A2D">#222A2D</option>
											<option data-skin="skin-2" value="#C6487E">#C6487E</option>
											<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
										</select>
									</div>
									<span>&nbsp; 选择皮肤</span>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
										id="ace-settings-navbar" autocomplete="off" /> <label class="lbl"
										for="ace-settings-navbar"> 固定导航栏</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
										id="ace-settings-sidebar" autocomplete="off" /> <label class="lbl"
										for="ace-settings-sidebar"> 固定侧边栏</label>
								</div>



								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"
										autocomplete="off" /> <label class="lbl" for="ace-settings-rtl"> 镜像</label>
								</div>

								<!-- <div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
										id="ace-settings-add-container" autocomplete="off" />
									<label class="lbl" for="ace-settings-add-container">
										Inside
										<b>.container</b>
									</label>
								</div> -->
							</div>
							<!-- /.pull-left -->

							<!-- <div class="pull-left width-50">
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover"
										autocomplete="off" />
									<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact"
										autocomplete="off" />
									<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight"
										autocomplete="off" />
									<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
								</div>
							</div>/.pull-left -->
						</div>
						<!-- /.ace-settings-box -->
					</div>
					<!-- /.ace-settings-container -->

					<div class="page-header">
						<h1>单门成绩及排名 </h1>
					</div>
					<!-- /.page-header -->
					<div class="row">
						<form action="TeaSingle" method="GET">
							<div class="row">
								<div class="col-xs-1"></div>
								<div class="col-xs-3">
									<label class="block"> <span
										class="block input-icon input-icon-right"> <select
											name="num" class="col-xs-10">
												<%
													ArrayList<String> aList = (ArrayList<String>) request.getAttribute("alist");
													String num=(String)request.getAttribute("num");											
													for (int i = 0; i < aList.size(); i++) {
														if(aList.get(i).equals(num)){															
												%>
												<option value="<%=aList.get(i)%>" selected = "selected"><%=aList.get(i)%></option>

												<%
													}else{
												%>
												<option value="<%=aList.get(i)%>"><%=aList.get(i)%></option>
												<%
													}}
												%>
										</select>

									</span>
									</label>
								</div>
								<div class="col-xs-2">
								<label class="block"> <span
										class="block input-icon input-icon-right"> <select
											name="year" class="col-xs-10">
												<%
												String year = (String) request.getAttribute("year");
												int y=2018; 
												if (year!=null){
													y=Integer.parseInt(year);
												}
													for (int i = 2000; i <= 2050; i++) {
													if(i==y){
														%> 
														<option value="<%=i%>" selected = "selected"><%=i%></option>
														<% 
													}
													else{
												%>
												<option value="<%=i%>"><%=i%></option>

												<%
													}}
												%>
										</select>

									</span>
									</label>
								
								</div>
								<div class="col-xs-4">
									<button type="submit"
										class="width-35 pull-right btn btn-sm btn-primary"
										id="login-btn" name="order" value=1>

										<span class="bigger-110">查询</span>
									</button>
								</div>
								<div class="col-xs-2 center">
									<button class="btn btn-sm btn-info" name="order" value=2>
										<i class="ace-icon fa fa-arrow-down bigger-120"></i>
									</button>
									<button class="btn btn-sm btn-info" name="order" value=3>
										<i class="ace-icon fa fa-arrow-up  bigger-120"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
					<div class="space-6"></div>

					<div class="row">
						<div class="col-xs-12">


							<div>
								<table id="dynamic-table"
									class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<%
												int f = (int) request.getAttribute("f");
												if (f == 2 || f == 3) {
											%>
											<th class="center">排名</th>
											<%
												}
											%>


											<th class="center">学号</th>
											<th class="center">姓名</th>
											<th class="center">课程名称</th>
											<th class="center">学年</th>
											<th class="center">成绩</th>


										</tr>
									</thead>
									<tbody>
										<%
											ArrayList<StudentBean> slist = (ArrayList<StudentBean>) request.getAttribute("slist");

											for (int i = 0; i < slist.size(); i++) {
										%>
										<tr>
											<%
												if (f == 2) {
											%><td class="center"><%=slist.size() - i%></td>
											<%
												} else if (f == 3) {
											%>
											<td class="center"><%=i + 1%></td>
											<%
												}
											%>
											<td class="center"><%=slist.get(i).getNum()%></td>
											<td class="center"><%=slist.get(i).getName()%></td>
											<td class="center"><%=slist.get(i).getCoursename()%></td>
											<td class="center"><%=slist.get(i).getYear()%></td>
											<%if(slist.get(i).getGpa()==0) {%>
												<td class="center">待录入</td>
											<% }else{
											%>
											<td class="center"><%=slist.get(i).getGpa() %></td>
											<%
												}
											%>


											<%
												}
											%>
										
									</tbody>



								</table>
								<h2>${flag }</h2>
							</div>
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>

					
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<div class="footer">
			<div class="footer-inner">
				<div class="footer-content">
					<span class="bigger-120"> <span class="blue bolder">高校</span>
						选课管理系统 &copy; Shao&nbsp;Z.R.
					</span> &nbsp; &nbsp; <span class="action-buttons"> <a href="#">
							<i class="ace-icon fa fa-weixin light-green bigger-150"></i>
						</a> <a href="#"> <i class="ace-icon fa fa-qq text-primary bigger-150"></i>
						</a> <a href="#"> <i class="ace-icon fa fa-weibo orange bigger-150"></i>
						</a>
					</span>
				</div>
			</div>
		</div>

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i
				class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="assets/js/jquery-2.1.4.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
			.write("<script src='assets/js/jquery.mobile.custom.min.js'>" +
				"<" + "/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
	<script src="assets/js/jquery-ui.custom.min.js"></script>
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="assets/js/jquery.easypiechart.min.js"></script>
	<script src="assets/js/jquery.sparkline.index.min.js"></script>
	<script src="assets/js/jquery.flot.min.js"></script>
	<script src="assets/js/jquery.flot.pie.min.js"></script>
	<script src="assets/js/jquery.flot.resize.min.js"></script>

	<!-- ace scripts -->
	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>



</body>

</html>