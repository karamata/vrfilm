<%@page import="com.edinnova.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/bootstrap/css/bootstrap.min.css") %>">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/font-awesome/css/font-awesome.min.css") %>">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/ionicon/css/ionicons.min.css") %>">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/css/AdminLTE.min.css") %>">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/css/skins/_all-skins.min.css") %>">
    <!-- iCheck -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/plugins/iCheck/flat/blue.css") %>">
    <!-- Morris chart -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.css") %>">
    <!-- Date Picker -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/plugins/datepicker/datepicker3.css") %>">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/plugins/daterangepicker/daterangepicker-bs3.css") %>">
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css") %>">
    
    <!-- jQuery 2.1.4 -->
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/jQuery/jQuery-2.1.4.min.js") %>"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="<%=Configuration.getResourcePath("admin/resources/js/jquery-ui.min.js") %>"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<%=Configuration.getResourcePath("admin/resources/bootstrap/js/bootstrap.min.js") %>"></script>
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/sparkline/jquery.sparkline.min.js") %>"></script>
    <!-- jvectormap -->
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js") %>"></script>
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js") %>"></script>
    <!-- jQuery Knob Chart -->
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/knob/jquery.knob.js") %>"></script>
    <!-- daterangepicker -->
    <script src="<%=Configuration.getResourcePath("admin/resources/js/moment.min.js") %>"></script>
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/daterangepicker/daterangepicker.js") %>"></script>
    <!-- datepicker -->
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/datepicker/bootstrap-datepicker.js") %>"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js") %>"></script>
    <!-- Slimscroll -->
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/slimScroll/jquery.slimscroll.min.js") %>"></script>
    <!-- FastClick -->
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/fastclick/fastclick.min.js") %>"></script>
    
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/datatables/jquery.dataTables.min.js") %>"></script>
    
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/datatables/dataTables.bootstrap.min.js") %>"></script>
    
    <!-- AdminLTE App -->
    <script src="<%=Configuration.getResourcePath("admin/resources/js/app.min.js") %>"></script>
    
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/slimScroll/jquery.slimscroll.min.js") %>"></script>
    
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/fastclick/fastclick.min.js") %>"></script>
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

      <header class="main-header">
        <!-- Logo -->
        <a href="/admin" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>VR</b></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>VR</b>Film</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <li class="dropdown user user-menu">
                <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<%=Configuration.getResourcePath("admin/resources/img/avatar.jpg") %>" class="user-image" alt="Admin Avatar">
                  <span class="hidden-xs"><s:property value="session.get('admin')"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="<%=Configuration.getResourcePath("admin/resources/img/avatar.jpg") %>" class="img-circle" alt="Admin Avatar">
                    <p>
                      <s:property value="session.get('admin')"/> - <s:property value="getText('administrator.title')"/>
                    </p>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                    </div>
                    <div class="pull-right">
                      <a href="#" class="btn btn-default btn-flat"><s:property value="getText('sign.out.button')"/></a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="<%=Configuration.getResourcePath("admin/resources/img/avatar.jpg") %>" class="img-circle" alt="Admin Avatar">
            </div>
            <div class="pull-left info">
              <p><s:property value="session.get('admin')"/></p>
              <a href="#"><i class="fa fa-circle text-success"></i> <s:property value="getText('online.title')"/></a>
            </div>
          </div>
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header"><s:property value="getText('menu.navigator.title')"/></li>
            <li class="active">
              <a href="/admin/category">
                <i class="fa fa-th"></i> <span><s:property value="getText('film.category.title')"/></span>
              </a>
            </li>
            <li>
              <a href="/admin/video">
	            <i class="fa fa-video-camera"></i> <span><s:property value="getText('video.title')"/></span>
	          </a>
            </li>
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Quản trị
            <small>Bảng điều khiển</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Quản trị</a></li>
            <li class="active">Bảng điều khiển</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <tiles:insertAttribute name="body"/>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2016 <a href="http://almsaeedstudio.com">EdInnova team</a>.</strong> All rights reserved.
      </footer>
    </div><!-- ./wrapper -->
  </body>
</html>