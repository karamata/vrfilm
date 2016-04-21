<%@page import="com.edinnova.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><tiles:insertAttribute name="title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Stream Videos Iphone web template, Andriod web template, Smartphone web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<link href="<%=Configuration.getResourcePath("jsp/resources/css/style.css") %>" rel="stylesheet" type="text/css" media="all"/>
<link href="<%=Configuration.getResourcePath("jsp/resources/css/slider.css") %>" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="<%=Configuration.getResourcePath("jsp/resources/js/jquery-1.9.0.min.js") %>"></script> 
<script type="text/javascript" src="<%=Configuration.getResourcePath("jsp/resources/js/jquery.nivo.slider.js") %>"></script>
<script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider();
    });
    </script>
</head>
<body>
<div class="header">
	  	</div>
  	  		<div class="wrap">
				<div class="header_top">
					<div class="logo">
						<a href="/index"><img src="/jsp/resources/images/logo.png" alt="" /></a>
					</div>
						<div class="header_top_right">
					     	 <a href="#" class="right_bt" id="activator"><img src="/jsp/resources/images/search_icon.png" alt="" /></a>
					     	    <div class="box" id="box">
					        	   <div class="box_content">        					                         
					                <div class="box_content_center">
					                   <div class="form_content">
						                 <div class="search_box">
								     		<form>
								     			<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}"><input type="submit" value="">
								     		</form>
								     	</div>
						                   <a class="boxclose" id="boxclose"><img src="/jsp/resources/images/button.png" alt="" /></a>
					                   </div>                
					                   <div class="clear"></div>
					                </div> 
					           </div>
					        </div>
						 <div class="clear"></div>
						  <script type="text/javascript">
								var $ = jQuery.noConflict();
									$(function() {
										$('#activator').click(function(){
												$('#box').animate({'top':'65px'},500);
										});
										$('#boxclose').click(function(){
												$('#box').animate({'top':'-400px'},500);
										});
										$('#activator_share').click(function(){
												$('#box_share').animate({'top':'65px'},500);
										});
										$('#boxclose_share').click(function(){
												$('#box_share').animate({'top':'-400px'},500);
										});
								
									});
									$(document).ready(function(){
									
									//Hide (Collapse) the toggle containers on load
									$(".toggle_container").hide(); 
									
									//Switch the "Open" and "Close" state per click then slide up/down (depending on open/close state)
									$(".trigger").click(function(){
										$(this).toggleClass("active").next().slideToggle("slow");
										return false; //Prevent the browser jump to the link anchor
									});
									
									});
								</script>
					</div>
						  <script type="text/javascript">
									function DropDown(el) {
										this.dd = el;
										this.initEvents();
									}
									DropDown.prototype = {
										initEvents : function() {
											var obj = this;
						
											obj.dd.on('click', function(event){
												$(this).toggleClass('active');
												event.stopPropagation();
											});	
										}
									}
						
									$(function() {
						
										var dd = new DropDown( $('#dd') );
						
										$(document).click(function() {
											// all dropdowns
											$('.wrapper-dropdown-3').removeClass('active');
										});
						
									});
					    </script>
			 <div class="clear"></div>
  		</div>     
				<div class="header_bottom">
					<div class="header_bottom_left">				
						<div class="categories">
								<div class="menu_container">
									 <p class="menu_head"><s:property value="getText('film.category.title')"/><span class="plusminus">+</span></p>
										<div class="menu_body">
										   <div class="list">
											  <ul>
					                             <li><a href="/index"><s:property value="getText('get.all')"/></a></li>
					                             <s:iterator value="listCatMenu">
											      	<li><a href="/index?catId=<s:property value="catId"/>"><s:property value="catName"/></a></li>
											      </s:iterator>
					                         </ul>
									  </div>	
									</div>								
								</div>
								<script type="text/javascript">
									$(document).ready(function(){
										$(".menu_body").hide();
										//toggle the componenet with class menu_body
										$(".menu_head").click(function(){
											$(this).next(".menu_body").slideToggle(600); 
											var plusmin;
											plusmin = $(this).children(".plusminus").text();
											
											if( plusmin == '+')
											$(this).children(".plusminus").text('-');
											else
											$(this).children(".plusminus").text('+');
										});
									});
								</script>
						  </div>					
		  	         </div>
						    <div class="header_bottom_right">					 
						 	    <!------ Slider ------------>
								  <div class="slider">
							      	<div class="slider-wrapper theme-default">
							            <div id="slider" class="nivoSlider">
							                <img src="/jsp/resources/images/1.jpg" data-thumb="images/1.jpg" alt="" />
							                <img src="/jsp/resources/images/2.jpg" data-thumb="images/2.jpg" alt="" />
							                <img src="/jsp/resources/images/3.jpg" data-thumb="images/3.jpg" alt="" />
							                <img src="/jsp/resources/images/4.jpg" data-thumb="images/4.jpg" alt="" />
							                 <img src="/jsp/resources/images/5.jpg" data-thumb="images/5.jpg" alt="" />
							            </div>
							        </div>
						   		</div>
						<!------End Slider ------------>
			         </div>
			     <div class="clear"></div>
			</div>
   		</div>
   </div>
   <!------------End Header ------------>
  
  <tiles:insertAttribute name="body"/>
  
   <div class="footer">
   	  <div class="wrap">	
	     <div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<h4>Contact</h4>
					<ul>
						<li><span>0975700689(a Phú)</span></li>
					</ul>
				</div>
			</div>
			 <div class="copy_right">
				<p>EdInnova © All rights Reseverd</p>
		   </div>			
        </div>
    </div>
</body>
</html>
