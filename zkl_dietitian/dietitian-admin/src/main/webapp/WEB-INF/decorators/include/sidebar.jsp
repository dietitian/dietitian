<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	try {
		ace.settings.check('sidebar', 'fixed')
	} catch (e) {
	}
</script>
<div class="sidebar-shortcuts" id="sidebar-shortcuts">
	<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
		<span class="btn btn-success"></span> <span class="btn btn-info"></span>
		<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
	</div>
</div>
<!-- #sidebar-shortcuts -->

<ul class="nav nav-list">
	<li class="active open"><a href="${pageContext.request.contextPath}/"> <i class="icon-home"></i> <span
			class="menu-text"> 主页 </span>
	</a></li>
	
	<li><a href="#" class="dropdown-toggle"> <i
			class="icon-lock icon-large"></i> <span class="menu-text">安全管理
		</span> <b class="arrow icon-angle-down"></b>
	</a>
		<ul class="submenu">
			<li><a href="${pageContext.request.contextPath}/user/list?time=1"> <i class="icon-double-angle-right"></i>
					用户管理
			</a></li>
			
			<li><a href="${pageContext.request.contextPath}/role/list?time=1"> <i class="icon-double-angle-right"></i>
					角色管理
			</a></li>
			<li><a href="${pageContext.request.contextPath}/module/list?time=1"> <i class="icon-double-angle-right"></i>
					模块管理
			</a></li>
			<li><a href="${pageContext.request.contextPath}/function/list?time=1"> <i class="icon-double-angle-right"></i>
					功能管理
			</a></li>
			<li><a href="${pageContext.request.contextPath}/privilege/list?time=1"> <i class="icon-double-angle-right"></i>
					资源管理
			</a></li>
			
			
		</ul></li>
	
	<li><a href="#" class="dropdown-toggle"> <i
			class="icon-desktop"></i> <span class="menu-text"> 系统管理
		</span> <b class="arrow icon-angle-down"></b>
	</a>
		<ul class="submenu">
			<li><a href="${pageContext.request.contextPath}/codeMain/list"> <i class="icon-double-angle-right"></i>
					代码表管理
			</a></li>
			<li><a href="${pageContext.request.contextPath}/menu/list"> <i class="icon-double-angle-right"></i>
					菜单管理
			</a></li>
			
		</ul></li>
	

		<li><a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
			<span class="menu-text">访问日志</span> <b
			class="arrow icon-angle-down"></b>
	</a>
		<ul class="submenu">
			<li><a href="${pageContext.request.contextPath}/accesslog/list?time=1"> <i class="icon-double-angle-right"></i>
					日志查看
			</a></li>
			<li><a href="${pageContext.request.contextPath}/accesslog/focusmap"> <i class="icon-double-angle-right"></i>
					统计分析
			</a></li>
		</ul></li>
		<li><a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
			<span class="menu-text">营养数据</span> <b
			class="arrow icon-angle-down"></b>
	</a>
		<ul class="submenu">
			<li><a href="${pageContext.request.contextPath}/nutrition/listp?time=1"> <i class="icon-double-angle-right"></i>
					食品营养表
			</a></li>
			<li><a href="*"> <i class="icon-double-angle-right"></i>
					身高体重标准
			</a></li>
			
		</ul></li>
		<li><a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
			<span class="menu-text">测试目录</span> <b
			class="arrow icon-angle-down"></b>
	</a>
		<ul class="submenu">
			<li><a href="${pageContext.request.contextPath}/upload/text"> <i class="icon-double-angle-right"></i>
					上传测试
			</a></li>
			
			
		</ul></li>
</ul>

<!-- /.nav-list -->

<div class="sidebar-collapse" id="sidebar-collapse">
	<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
		data-icon2="icon-double-angle-right"></i>
</div>
<script type="text/javascript">
	try {
		ace.settings.check('sidebar', 'collapsed')
	} catch (e) {
	}
</script>