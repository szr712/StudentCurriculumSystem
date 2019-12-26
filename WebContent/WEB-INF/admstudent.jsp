<%@page import="studentCurriculum.StudentBean"%>
<%@page import="studentCurriculum.TeacherBean"%>
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
					<a class="btn btn-success" href="AdmTeacher"> <i class="ace-icon fa fa-user"></i>
					</a> <a class="btn btn-info" href="AdmStudent"> <i class="ace-icon fa fa-table"></i>
					</a> <a class="btn btn-warning" href="AdmPlace"> <i class="ace-icon fa fa-map-marker"></i>
					</a>


				</div>

				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span>

					<span class="btn btn-warning"></span>


				</div>
			</div>
			<!-- /.sidebar-shortcuts -->

			<ul class="nav nav-list">

				<li class=""><a href="AdmTeacher"> <i class="menu-icon fa fa-user"></i> <span class="menu-text">
							维护教师信息 </span> <b class="arrow"></b>

				<li class="active"><a href="AdmStudent"> <i class="menu-icon fa fa-table"></i> <span class="menu-text">
							维护学生信息 </span>
					</a> <b class="arrow"></b>
				<li class=""><a href="AdmCourse"> <i class="menu-icon fa fa-map-marker"></i> <span class="menu-text">
							维护课程 </span>
					</a> <b class="arrow"></b>
				<li class=""><a href="AdmCPassword"> <i class="menu-icon fa fa-cog"></i> <span class="menu-text">
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
						<h1>维护学生信息<small>${flag }</small></h1>
					</div>
					<!-- /.page-header -->


                    <div class="row">
						
						<table id="dynamic-table"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">学号</th>
									<th class="center">姓名</th>
									<th class="center col-xs-1">性别</th>
									<th class="center">年龄</th>
									<th class="center">班级</th>
									<th class="center">生源所在地</th>
									<th class="center">已修学分总数</th>
									<th class="center detail-col">
									<th class="center detail-col">
										<button class="btn btn-xs btn-warning pull-right" href="#row"
											role="button" data-toggle="modal">
											<i class="ace-icon fa fa-plus "></i>
										</button>
									</th>
								</tr>
							</thead>
							<tbody>
								<%
									ArrayList<StudentBean> sList = (ArrayList<StudentBean>) request.getAttribute("slist");
									for (int i = 0; i < sList.size(); i++) {
								%>
								<tr>

									<td class="center"><%=sList.get(i).getNum()%></td>
									<td class="center"><%=sList.get(i).getName()%></td>
									<td class="center"><%=sList.get(i).getSex()%></td>
									<td class="center"><%=sList.get(i).getAge()%></td>
									<td class="center"><%=sList.get(i).getClassname()%></td>
									<td class="center"><%=sList.get(i).getPlace()%></td>
									<td class="center"><%=sList.get(i).getCredit()%></td>

									<td>


										<div>

											<button class="btn btn-xs btn-info" href="#row<%=i%>"
												role="button" data-toggle="modal" value="<%=i%>">
												<i class="ace-icon fa fa-pencil bigger-120"></i>
											</button>

										</div>
									</td>
									<td>
										<div>
											<form class="no-padding" action="AdmSDelete" method="post"
												accept-charset="UTF-8">

												<button class="btn btn-xs btn-danger" name="delete"
													value="<%=i%>">
													<i class="ace-icon fa fa-trash-o bigger-120"></i>
												</button>
											</form>
										</div>




									</td>
								</tr>
								<%
									}
								%>


							</tbody>


						</table>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
        
		<div id="row" class="modal fade" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header no-padding">
						<div class="table-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">
								<span class="white">&times;</span>
							</button>
							新增学生信息
						</div>
					</div>

					<div class="modal-body no-padding">
						<tr class="detail-row">
							<form class="form-horizontal" role="form" action="AdmSAdd"
								method="POST" accept-charset="UTF-8">
								<div class="space-6"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										学号： </label>

									<div class="col-sm-9">
										<input type="text" placeholder=学号 " class="col-xs-8"
											required="required" name="num"
											  />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										姓名： </label>

									<div class="col-sm-9">
										<input type="text" placeholder="姓名" class="col-xs-8"
											required="required" name="name"
											 />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										性别： </label>

									<div class="col-sm-9">
										
										<input type="radio" name="sex" value="男" checked="true" />男 <input
											type="radio" name="sex" value="女" />女
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										年龄： </label>

									<div class="col-sm-9">
										<input type="text" placeholder="年龄" class="col-xs-8"
											required="required" name="age"
											/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										班级： </label>

									<div class="col-sm-9">
										<select name="class" class="col-xs-8">
											<%
												ArrayList<String> aList = (ArrayList<String>) request.getAttribute("alist");
												for (int j = 0; j < aList.size(); j++) {
											%>
											<option value="<%=aList.get(j)%>" ><%=aList.get(j)%></option>
											<%
												}
											%>

										</select>
									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										生源所在地： </label>

									<div class="col-sm-9">
										<input type="text" placeholder="生源所在地" class="col-xs-8"
											required="required" name="place"
											 />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="submit" name="change"
											>
											<i class="ace-icon fa fa-check bigger-110"></i> 完成
										</button>

										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i> 重置
										</button>
									</div>
								</div>
							</form>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<%
			for (int i = 0; i < sList.size(); i++) {
		%>
		<div id="row<%=i%>" class="modal fade" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header no-padding">
						<div class="table-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">
								<span class="white">&times;</span>
							</button>
							编辑学生信息
						</div>
					</div>

					<div class="modal-body no-padding">
						<tr class="detail-row">
							<form class="form-horizontal" role="form" action="StuSChange"
								method="POST" accept-charset="UTF-8">
								<div class="space-6"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										学号： </label>

									<div class="col-sm-9">
										<input type="text" placeholder=学号 " class="col-xs-8"
											required="required" name="num"
											value="<%=sList.get(i).getNum()%>" readonly="readonly" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										姓名： </label>

									<div class="col-sm-9">
										<input type="text" placeholder="姓名" class="col-xs-8"
											required="required" name="name"
											value="<%=sList.get(i).getName()%>" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										性别： </label>

									<div class="col-sm-9">
										<%
											if (sList.get(i).getSex().equals("男")) {
										%>
										<input type="radio" name="sex" value="男" checked="true" />男 <input
											type="radio" name="sex" value="女" />女
										<%
											} else {
										%>
										<input type="radio" name="sex" value="男" />男 <input
											type="radio" name="sex" value="女" checked="true" />女
										<%
											}
										%>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										年龄： </label>

									<div class="col-sm-9">
										<input type="text" placeholder="年龄" class="col-xs-8"
											required="required" name="age"
											value="<%=sList.get(i).getAge()%>" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										班级： </label>

									<div class="col-sm-9">
										<select name="class" class="col-xs-8">
											<%
												aList = (ArrayList<String>) request.getAttribute("alist");
													for (int j = 0; j < aList.size(); j++) {
											%>
											<%
												String num = aList.get(j).substring(aList.get(j).indexOf("（") + 1, aList.get(j).length() - 1);
														if (num.equals(sList.get(i).getClassnum())) {
											%><option value="<%=aList.get(j)%>" selected="selected"><%=aList.get(j)%></option>

											<%
												} else {
											%>
											<option value="<%=aList.get(j)%>"><%=aList.get(j)%></option>

											<%
												}
													}
											%>

										</select>
									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">
										生源所在地： </label>

									<div class="col-sm-9">
										<input type="text" placeholder="电话" class="col-xs-8"
											required="required" name="place"
											value="<%=sList.get(i).getPlace()%>" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="submit" name="change"
											value="<%=i%>">
											<i class="ace-icon fa fa-check bigger-110"></i> 完成
										</button>

										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i> 重置
										</button>
									</div>
								</div>
							</form>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<%
			}
		%>

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