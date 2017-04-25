<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<ul id="menu" class="nav nav-pills nav-stacked">
	<li>
		<a href="${ctx}/homePage" target="mainFrame" title="主页">
			<span class="glyphicon glyphicon-home icon"></span>
			<span class="text">主页</span>
		</a>
	</li>
	<c:set var="menuList" value="${fns:getMenuList()}" />
	<c:forEach items="${menuList}" var="menu1" varStatus="idxStatus">
		<c:if test="${menu1.parent.id eq '1' && menu1.isShow eq '1'}">
			<li>
				<a data-menu="#menu-${menu1.id}" href="${fn:indexOf(menu1.href, '://') eq -1 ? ctx : ''}${not empty menu1.href ? menu1.href : '/404'}" target="${not empty menu1.target ? menu1.target : 'mainFrame'}" title="${menu1.name}">
					<span class="glyphicon glyphicon-menu-right pull-right"></span>
					<span class="glyphicon glyphicon-${not empty menu1.icon ? menu1.icon : 'folder-open'} icon"></span>
					<span class="text">${menu1.name}</span>
				</a>
				<ul id="menu-${menu1.id}" class="menu1 nav nav-pills nav-stacked">
					<li class="title">${menu1.name}</li>
					<c:forEach items="${menuList}" var="menu2">
						<c:if test="${menu2.parent.id eq menu1.id && menu2.isShow eq '1'}">
							<li>
								<a data-menu="#menu-${menu2.id}" href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}" target="${not empty menu2.target ? menu2.target : 'mainFrame'}" title="${menu2.name}">
									<span class="glyphicon glyphicon-${not empty menu2.icon ? menu2.icon : 'folder-open'} icon"></span>
									${menu2.name}
								</a>
								<ul id="menu-${menu2.id}" class="menu2 nav nav-pills nav-stacked" style="margin:0; padding-right:0;">
									<c:forEach items="${menuList}" var="menu3">
										<c:if test="${menu3.parent.id eq menu2.id && menu3.isShow eq '1'}">
											<li>
												<a href="${fn:indexOf(menu3.href, '://') eq -1 ? ctx : ''}${not empty menu3.href ? menu3.href : '/404'}" target="${not empty menu3.target ? menu3.target : 'mainFrame'}" title="${menu3.name}">
													<span class="glyphicon glyphicon-${not empty menu3.icon ? menu3.icon : 'file'} icon"></span>
													${menu3.name}
												</a>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</li>
		</c:if>
	</c:forEach>
</ul>