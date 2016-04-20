<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<style>
.table-info{
	float: left;
}
.table-paging-button{
	float: right;
}
.table-paging-button .pagination {
	margin-bottom: 0 !important;
	margin-top: 0 !important;
}
</style>
<div class="row">
  <!-- Form Element sizes -->
  	<div class="col-md-6">
  		<div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title"><s:property value="getText('create.new.category')"/></h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form role="form" method="post">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="exampleInputEmail1"><s:property value="getText('category.name')"/></label>
                      <input type="text" class="form-control" id="categoryName" name="categoryName" placeholder="<s:property value="getText('category.name')"/>">
                    </div>
                  </div><!-- /.box-body -->

                  <div class="box-footer">
                    <input type="submit" value="<s:property value="getText('add.category')"/>" name="submitType" class="btn btn-primary">
                    <s:if test="submitType.equals(getText('add.category')) && errMsg != null && !errMsg.isEmpty()">
                    	<label style="color: red;"><s:property value="errMsg"/></label>
                    </s:if>
                  </div>
                </form>
              </div><!-- /.box -->    
  	</div>
  	<div class="col-md-6">
	    <div class="box">
                <div class="box-header">
                  <h3 class="box-title"><s:property value="getText('list.category.video')"/></h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table id="cat-table" class="table data-table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th><s:property value="getText('category.name')"/></th>
                        <th width="10%"></th>
                      </tr>
                    </thead>
                    <tbody>
                    	<s:iterator value="listCat">
                      		<tr>
		                        <td><s:property value="catName"/></td>
		                        <td><a class="label label-danger" href="/admin/cat/<s:property value="catId"/>/delete"><s:property value="getText('delete.title')"/></a></td>
		                      </tr> 
                      </s:iterator>
                    </tbody>
                    <tfoot>
						<tr>
                        	<th><s:property value="getText('category.name')"/></th>
                        	<th></th>
                      </tr>
                    </tfoot>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
  	</div>
</div><!-- /.box -->
<div class="row">
	<div class="col-md-12">
      <!-- general form elements -->
      <div class="box box-primary">
        <div class="box-header with-border">
          <h3 class="box-title"><s:property value="getText('create.new.video')"/></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <form action="/quantri/upload" method="post" enctype="application/x-www-form-urlencoded">
          <div class="box-body">
            <div class="form-group">
              <label for="movieName"><s:property value="getText('film.name')"/></label>
              <input type="text" class="form-control" id="movieName" placeholder="<s:property value="getText('film.name')"/>">
            </div>
            <div class="form-group">
              	<label><s:property value="getText('film.category.title')"/></label>
	             <select id="catId" name="catId" class="form-control select2">
	             	<s:iterator value="listCat">
	             		<option value="<s:property value="catId"/>"><s:property value="catName"/></option>
	             	</s:iterator>
	             </select>
            </div>
            <div class="form-group">
              <label for="description"><s:property value="getText('description.title')"/></label>
              <input type="text" class="form-control" id="description" name="description" placeholder="<s:property value="getText('description.title')"/>">
            </div>
            <div class="form-group">
              <h3><s:property value="getText('detail.title')"/></h3>
              <textarea id="detail" class="textarea" name="detail" placeholder="<s:property value="getText('detail.title')"/>" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
            </div>
            <div class="form-group">
              <label for="imageFeature"><s:property value="getText('image.feature.title')"/></label>(.jpg, .bmp, .png, .gif)
              <input type="file" accept="audio/*,video/*,image/*" name="imageFeature" id="imageFeature">
            </div>
            <div class="form-group">
              <label for="movieFile"><s:property value="getText('movie.file.title')"/></label>(.mpeg, .mpg, .avi, .mov, .mp4, .wmv)
              <input type="file"  accept="audio/*,video/*,image/*"  name="movieFile" id="movieFile">
            </div>
          </div><!-- /.box-body -->

          <div class="box-footer">
            <input type="submit" value="<s:property value="getText('add.video')"/>" class="btn btn-primary">
            <s:if test="submitType.equals(getText('movie.file.title')) && errMsg != null && !errMsg.isEmpty()">
             	<label style="color: red;"><s:property value="errMsg"/></label>
             </s:if>
          </div>
        </form>
      </div><!-- /.box -->

    </div><!--/.col (right) -->
</div>
<div class="row">
	<div class="col-md-12">
		<div class="box box-warning">
			<div class="box-header">
                  <h3 class="box-title"><s:property value="getText('list.category.video')"/></h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table id="movie-table" class="table data-table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th><s:property value="getText('film.name')"/></th>
                        <th><s:property value="getText('description.title')"/></th>
                        <th><s:property value="getText('category.name')"/></th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                    	<s:iterator value="listMovie">
	                      <tr>
	                        <td><s:property value="movieName"/></td>
	                        <td><s:property value="description"/></td>
	                        <td><s:property value="category.catName"/></td>
	                        <td><a class="label label-danger" href="/admin/movie/<s:property value="catId"/>/delete"><s:property value="getText('delete.title')"/></a></td>
	                      </tr>
                      </s:iterator>
                    </tbody>
                    <tfoot>
                      <tr>
                        <th><s:property value="getText('film.name')"/></th>
                        <th><s:property value="getText('description.title')"/></th>
                        <th><s:property value="getText('category.name')"/></th>
                        <th></th>
                      </tr>
                    </tfoot>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
		</div>
	</div>
<script>
      $(function () {
        $(".data-table#cat-table").DataTable({
        	"pagingType": "full",
        	"dom": 'lrt<<"table-info"i><"table-paging-button"p>>'
        });
        $(".data-table#movie-table").DataTable({
        	"pagingType": 'full',
        	"dom": 'lrt<<"table-info"i><"table-paging-button"p>>'
        });
        $(".textarea").wysihtml5();
      });
    </script>