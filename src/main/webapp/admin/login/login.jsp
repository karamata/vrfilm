<%@page import="com.edinnova.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Log in</title>
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
    <!-- iCheck -->
    <link rel="stylesheet" href="<%=Configuration.getResourcePath("admin/resources/plugins/iCheck/flat/blue.css") %>">

	<script src="<%=Configuration.getResourcePath("admin/resources/plugins/jQuery/jQuery-2.1.4.min.js") %>"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<%=Configuration.getResourcePath("admin/resources/bootstrap/js/bootstrap.min.js") %>"></script>
    <!-- iCheck -->
    <script src="<%=Configuration.getResourcePath("admin/resources/plugins/iCheck/icheck.min.js") %>"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </head>
  <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="/"><b>VR</b> Film</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg"><s:property value="getText('signin.to.admin.page')"/></p>
        <form action="/login" method="post">
          <div class="form-group has-feedback">
            <input type="text" name="username" id="username" class="form-control" placeholder="<s:property value="getText('username')"/>">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" name="password" id="password" class="form-control" placeholder="<s:property value="getText('password')"/>">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat"><s:property value="getText('signin.button')"/></button>
            </div><!-- /.col -->
          </div>
        </form>
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
  </body>
</html>