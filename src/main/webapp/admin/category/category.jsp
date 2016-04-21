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
		                        <td><a class="label label-danger" href="/cat/<s:property value="catId"/>/delete"><s:property value="getText('delete.title')"/></a></td>
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

<script>
      $(function () {
        $(".data-table#cat-table").DataTable({
        	"pagingType": "full",
        	"dom": 'lrt<<"table-info"i><"table-paging-button"p>>'
        });
      });
    </script>