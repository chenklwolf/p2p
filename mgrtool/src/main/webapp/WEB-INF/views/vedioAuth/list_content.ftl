<#list pageResult.result as vo>
<tr>
	<td>${vo.applier.username}</td>
	<td>${vo.stateDisplay}</td>
	<td>${vo.auditor.username}</td>
	<td>${(vo.remark)!''}</td>
	<td>${(vo.auditTime?string("yyyy-MM-dd"))!""}
</tr>
</#list>

<script>
	$(function(){
		$("#page_container").empty().append($('<ul id="pagination" class="pagination"></ul>'));
		$('#pagination').twbsPagination({
			totalPages : ${pageResult.totalPage}||1,
			startPage : ${pageResult.currentPage},
			visiblePages : 5,
			initiateStartPageClick:false,
			onPageClick : function(event, page) {
				$("#currentPage").val(page);
				$("#searchForm").submit();
			}
		});
	})
</script>
						