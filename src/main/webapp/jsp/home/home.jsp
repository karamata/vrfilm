<%@page import="com.edinnova.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="main">
  	<div class="wrap">
  	
  	<s:iterator value="listCat">
  		<div class="content">
  			<div class="content_top">
	    		<div class="heading">
	    		<h3 onclick="$('#category-<s:property value="catId"/>').toggle('slow');"><s:property value="catName"/></h3>
	    		</div>
	    	</div>
	    	<div class="section group" id="category-<s:property value="catId"/>">
	    		<s:iterator begin="0" end="listMovie.size()-1" var="iRun" step="2">
	    			<s:if test="listMovie.size() == 1">
	    				<div>
	    				<div class="grid_1_of_2 images_1_of_2">
							 <a href="/watch-movie?id=<s:property value="listMovie.get(#iRun).getMovieId()"/>"><img src="<s:property value="listMovie.get(#iRun).getImgUrl()"/>" alt="" /></a>
							 <h2><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun).getMovieId()"/>"><s:property value="listMovie.get(#iRun).getMovieName()"/></a></h2>
							<div class="price-details">
						       		<div class="add-cart">								
										<h4><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun).getMovieId()"/>"><s:property value="getText('view.movie')"/></a></h4>
								     </div>
								 <div class="clear"></div>
							</div>					 
						</div>
						</div>
	    			</s:if>
	    			<s:elseif test="listMovie.size() == 2">
	    				<div>
	    				<div class="grid_1_of_2 images_1_of_2">
							 <a href="/watch-movie?id=<s:property value="listMovie.get(#iRun).getMovieId()"/>"><img src="<s:property value="listMovie.get(#iRun).getImgUrl()"/>" alt="" /></a>
							 <h2><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun).getMovieId()"/>"><s:property value="listMovie.get(#iRun).getMovieName()"/></a></h2>
							<div class="price-details">
						       		<div class="add-cart">								
										<h4><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun).getMovieId()"/>"><s:property value="getText('view.movie')"/></a></h4>
								     </div>
								 <div class="clear"></div>
							</div>					 
						</div>
						<div class="grid_1_of_2 images_1_of_2">
							 <a href="/watch-movie?id=<s:property value="listMovie.get(#iRun+1).getMovieId()"/>"><img src="<s:property value="listMovie.get(#iRun+1).getImgUrl()"/>" alt="" /></a>
							 <h2><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun+1).getMovieId()"/>"><s:property value="listMovie.get(#iRun+1).getMovieName()"/></a></h2>
							<div class="price-details">
						       		<div class="add-cart">								
										<h4><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun+1).getMovieId()"/>"><s:property value="getText('view.movie')"/></a></h4>
								     </div>
								 <div class="clear"></div>
							</div>					 
						</div>
						</div>
	    			</s:elseif>
	    			<s:else>
	    				<div>
	    				<div class="grid_1_of_2 images_1_of_2">
							 <a href="/watch-movie?id=<s:property value="listMovie.get(#iRun).getMovieId()"/>"><img src="<s:property value="listMovie.get(#iRun).getImgUrl()"/>" alt="" /></a>
							 <h2><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun).getMovieId()"/>"><s:property value="listMovie.get(#iRun).getMovieName()"/></a></h2>
							<div class="price-details">
						       		<div class="add-cart">								
										<h4><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun).getMovieId()"/>"><s:property value="getText('view.movie')"/></a></h4>
								     </div>
								 <div class="clear"></div>
							</div>					 
						</div>
						<s:if test="#iRun == listMovie.size() - 1">
						
						</s:if>
						<s:else>
							<div class="grid_1_of_2 images_1_of_2">
								 <a href="/watch-movie?id=<s:property value="listMovie.get(#iRun+1).getMovieId()"/>"><img src="<s:property value="listMovie.get(#iRun+1).getImgUrl()"/>" alt="" /></a>
								 <h2><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun+1).getMovieId()"/>"><s:property value="listMovie.get(#iRun+1).getMovieName()"/></a></h2>
								<div class="price-details">
							       		<div class="add-cart">								
											<h4><a href="/watch-movie?id=<s:property value="listMovie.get(#iRun+1).getMovieId()"/>"><s:property value="getText('view.movie')"/></a></h4>
									     </div>
									 <div class="clear"></div>
								</div>					 
							</div>
						</s:else>
						</div>
	    			</s:else>
	    		</s:iterator>
	    	</div>
  		</div>
  	</s:iterator>
  </div>
</div>