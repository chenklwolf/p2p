<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- html <head>标签部分  -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>蓝源Eloan-P2P平台(系统管理平台)</title>
	<#include "../common/header.ftl"/>
	<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
	<script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
	<script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$.extend($.fn.twbsPagination.defaults, {
				first : "首页",
				prev : "上一页",
				next : "下一页",
				last : "最后一页"
			});

			$('#pagination').twbsPagination({
				totalPages : ${pageResult.totalPage}||1,
				startPage : ${pageResult.currentPage},
				visiblePages : 5,
				onPageClick : function(event, page) {
					$("#currentPage").val(page);
					$("#searchForm").submit();
				}
			});
			
			$("[id^=pg_]").click(function(){
				var pid=$(this).attr("id").substring(3);
				$("#parentId").val(pid);
				$("#currentPage").val(1);
				$("#searchForm").submit();
			});
			
			var pid="pg_"+$("#parentId").val();
			$("#systemDictionary_group_detail li a[id="+pid+"]").parent().addClass("active"); //添加样式
			
			$("#addSystemDictionaryItemBtn").click(function(){
				var pid=$("#parentId").val();
				if(pid){
					$("input[name=parentId]").val(pid);
					$("#systemDictionaryItemModal").modal("show");
				}else{
					$.messager.alert("请先选择一个字典分类!");
				}
			});
		
			$("#editForm").validate({
				rules : {
					title:"required",
					sequence:{
						required:true,
						number:true
					}
				},
				messages: {
					title:"名称不能为空",
					sequence:{
						required:"序号不能为空",
						number : "只能是数字",
					}
				}
			});
			
			$("#saveBtn").click(function(){
				//使用jquery-validate方法,首先调用validate方法添加验证规则,
				//如果要手动提交表单,可以先使用$(form).valid()方法执行验证,这个方法返回true或者false
				if($("#editForm").valid()){
					$("#editForm").submit();
				}
			});
			
			$(".edit_Btn").click(function(){
				var json = $(this).data("json");
				$("input[name=id]").val(json.id);
				$("input[name=title]").val(json.title);
				$("input[name=sequence]").val(json.sequence);
				$("input[name=tvalue]").val(json.tvalue);
				$("#introId").html(json.intro);
				$("input[name=parentId]").val(json.parentId);
				$("#systemDictionaryItemModal").modal("show");
			});
			
		});
		</script>
</head>
<body>
	<div class="container">
		<#include "../common/top.ftl"/>
		<div class="row">
			<div class="col-sm-3">
				<#assign currentMenu="systemDictionaryItem" />
				<#include "../common/menu.ftl" />
			</div>
			<div class="col-sm-9">
				<div class="page-header">
					<h3>数据字典明细管理</h3>
				</div>
				<div class="col-sm-12">
					<!-- 提交分页的表单 -->
					<form id="searchForm" class="form-inline" method="post" action="/systemDictionaryItem_list.do">
						<input type="hidden" id="currentPage" name="currentPage" value=""/>
						<input type="hidden" id="parentId" name="parentId" value="${(qo.parentId)!''}" />
						<div class="form-group">
						    <label>关键字</label>
						    <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!''}">
						</div>
						<div class="form-group">
							<button id="query" class="btn btn-success"><i class="icon-search"></i> 查询</button>
							<a href="javascript:void(-1);" class="btn btn-success" id="addSystemDictionaryItemBtn">添加数据字典明细</a>
						</div>
					</form>
					<div class="row"  style="margin-top:20px;">
						<div class="col-sm-3">
							<ul id="menu" class="list-group">
								<li class="list-group-item">
									<a href="#" data-toggle="collapse" data-target="#systemDictionary_group_detail"><span>数据字典分组</span></a>
									<ul class="in" id="systemDictionary_group_detail">
										<#list systemDictionaryGroups as vo>
										   <li><a id="pg_${vo.id}" data-dataid="${vo.id}" href="javascript:;"><span>${vo.title}</span></a></li>
										</#list>
									</ul>
								</li>
							</ul>
						</div>
						<div class="col-sm-9">
							<table class="table">
								<thead>
									<tr>
										<th>名称</th>
										<th>值</th>
										<th>序列</th>
										<th>说明</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<#list pageResult.result as vo>
									<tr>
										<td>${vo.title}</td>
										<td>${vo.tvalue}</td>
										<td>${vo.sequence}</td>
										<td width="150px;">${vo.intro}</td>
										<td>
											<a href="javascript:void(-1);" class="edit_Btn" data-json='${vo.jsonString}'>修改</a> &nbsp; 
											<a href="javascript:void(-1);" class="deleteClass" data-dataId="${vo.id}">删除</a>
										</td>
									</tr>
								</#list>
								</tbody>
							</table>
							
							<div style="text-align: center;">
								<ul id="pagination" class="pagination"></ul>
							</div>
							
							
							<div id="systemDictionaryItemModal" class="modal" tabindex="-1" role="dialog">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							        <h4 class="modal-title">编辑/增加</h4>
							      </div>
							      <div class="modal-body">
							       	  <form id="editForm" class="form-horizontal" method="post" action="systemDictionaryItem_update.do" style="margin: -3px 118px">
										    <input id="systemDictionaryId" type="hidden" name="id" value="" />
									    	<input type="hidden" name="parentId" value="" />
										   	<div class="form-group">
											    <label class="col-sm-3 control-label">名称</label>
											    <div class="col-sm-6">
											    	<input type="text" class="form-control" name="title" placeholder="字典值名称">
											    </div>
											</div>
											<div class="form-group">
											    <label class="col-sm-3 control-label">顺序</label>
											    <div class="col-sm-6">
											    	<input type="text" class="form-control" name="sequence" placeholder="字典值显示顺序">
											    </div>
											</div>
											<div class="form-group">
											    <label class="col-sm-3 control-label">字典值</label>
											    <div class="col-sm-6">
											    	<input type="text" class="form-control" name="tvalue" placeholder="字典值">
											    </div>
											</div>
											<div class="form-group">
											    <label class="col-sm-3 control-label">介绍</label>
											    <div class="col-sm-6">
											    	<textarea id="introId" rows="4" cols="24"  style="margin-top: 5px;" name="intro"></textarea>
											    </div>
											</div>
									   </form>
								  </div>
							      <div class="modal-footer">
							      	<a href="javascript:void(0);" class="btn btn-success" id="saveBtn" aria-hidden="true">保存</a>
								    <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
							      </div>
							    </div>
							  </div>
							</div>
				
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>