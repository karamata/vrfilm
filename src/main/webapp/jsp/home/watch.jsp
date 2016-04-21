<%@page import="com.edinnova.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="main">
  	<div class="wrap">
  	
  	<div class="content_top">
    		<div class="back-links">
    		<p><a href="index.html"><s:property value="getText('home.title')"/></a> &gt;&gt;&gt;&gt; <a href="javascript:void(0);" class="active"><s:property value="movie.category.catName"/></a></p>
    	    </div>
    		<div class="clear"></div>
    	</div>
   	 	<div class="section group">
				<div class="cont-desc span_1_of_2">
				  <div class="product-details">				
					<div class="grid images_3_of_2">
						<img src="<s:property value="movie.imgUrl"/>" alt="" />
				  </div>
				<div class="desc span_3_of_2">
					<h2><s:property value="movie.movieName"/></h2>
					<p><s:property value="movie.description"/></p>					
				<div class="share-desc">
					<div class="share">
					</div>
					<div class="button"><span><a href="javascript:void(0);"><s:property value="getText('view.movie')"/></a></span></div>					
					<div class="clear"></div>
				</div>
			</div>
			<div class="clear"></div>
		  </div>
		<div class="product_desc">	
			 <h2><s:property value="getText('detail.title')"/> :</h2>
			   <p><s:property escapeHtml="false" value="movie.detail"/></p>
	   </div>
   </div>
				<div class="rightsidebar span_3_of_1 sidebar">
					<h2><s:property value="getText('movie.relate.title')"/></h2>
						<s:iterator value="movie.listRelateMovie">
							<div class="special_movies">
					 	   <div class="movie_poster">
					 		  <a href="/watch-movie?id=<s:property value="movieId"/>"><img src="<s:property value="imgUrl"/>" alt="" /></a>
					 		 </div>
					 		   <div class="movie_desc">
							    <h3><a href="/watch-movie?id=<s:property value="movieId"/>"><s:property value="movieName"/></a></h3>
								     <span><a href="/watch-movie?id=<s:property value="movieId"/>"><s:property value="getText('view.movie')"/></a></span>
								 </div>
								<div class="clear"></div>
					 		</div>
						</s:iterator>
 					   </div>
 		 		 </div>
  	
  </div>
</div>