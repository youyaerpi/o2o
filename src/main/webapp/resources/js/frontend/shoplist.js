$(function() {
	var loading = false;
	//分页允许的最大条数，超过次数则禁止访问后台
	var maxItems = 999;
	//一条返回的最大数
	var pageSize = 3;
	//获取店铺列表的url
	var listUrl = '/o2o/frontend/listshops';
	//获取店铺类别列表以及区域列表的url
	var searchDivUrl = '/o2o/frontend/listshopspageinfo';
	//页码
	var pageNum = 1;
	//从地址栏里面获取parent shop category id
	var parentId = getQueryString('parentId');
	var areaId = '';
	var shopCategoryId = '';
	var shopName = '';

	function getSearchDivData() {
		//如果传入了parentId,则取处此一级类别下面的所有二级类别
		var url = searchDivUrl + '?' + 'parentId=' + parentId;
		$
			.getJSON(
				url,
				function(data) {
					if (data.success) {
						var shopCategoryList = data.shopCategoryList;
						var html = '';
						html += '<a href="#" class="button" data-category-id=""> 全部类别  </a>';
						shopCategoryList
							.map(function(item, index) {
								html += '<a href="#" class="button" data-category-id='
									+ item.shopCategoryId
									+ '>'
									+ item.shopCategoryName
									+ '</a>';
							});
						$('#shoplist-search-div').html(html);
						var selectOptions = '<option value="">全部地区</option>';
						var areaList = data.areaList;
						areaList.map(function(item, index) {
							selectOptions += '<option value="'
								+ item.areaId + '">'
								+ item.areaName + '</option>';
						});
						$('#area-search').html(selectOptions);
					}
				});
	}
	getSearchDivData();
	/**
	 * 获取分页展示的店铺列表信息
	 */
	function addItems(pageSize, pageIndex) {
		// 生成新条目的HTML
		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
			+ pageSize + '&parentId=' + parentId + '&areaId=' + areaId
			+ '&shopCategoryId=' + shopCategoryId + '&shopName=' + shopName;
		loading = true;
		$.getJSON(url, function(data) {
			if (data.success) {
				maxItems = data.count;
				var html = '';
				data.shopList.map(function(item, index) {
					var lastEditDate = new Date(item.lastEditTime).getFullYear() + "-" +
					new Date(item.lastEditTime).getMonth() + "-"
					+ new Date(item.lastEditTime).getDay()
					html += '' + '<div class="card" data-shop-id="'
						+ item.shopId + '">' + '<div class="card-header">'
						+ item.shopName + '</div>'
						+ '<div class="card-content">'
						+ '<div class="list-block media-list">' + '<ul>'
						+ '<li class="item-content">'
						+ '<div class="item-media">' + '<img src="'
						+ item.shopImg + '" width="44">' + '</div>'
						+ '<div class="item-inner">'
						+ '<div class="item-subtitle">' + item.shopDesc
						+ '</div>' + '</div>' + '</li>' + '</ul>'
						+ '</div>' + '</div>' + '<div class="card-footer">'
						+ '<p class="color-gray">'
						+ lastEditDate
						+ '更新</p>' + '<span>点击查看</span>' + '</div>'
						+ '</div>';
				});
				$('.list-div').append(html);
				var total = $('.list-div .card').length;
				if (total >= maxItems) {
					// 加载完毕，则注销无限加载事件，以防不必要的加载
					$.detachInfiniteScroll($('.infinite-scroll'));
					// 删除加载提示符
					$('.infinite-scroll-preloader').remove();
				}
				pageNum += 1;
				loading = false;
				$.refreshScroller();
			}
		});
	}
	// 预先加载20条
	addItems(pageSize, pageNum);

	$(document).on('infinite', '.infinite-scroll-bottom', function() {
		if (loading)
			return;
		addItems(pageSize, pageNum);
	});

	$('.shop-list').on('click', '.card', function(e) {
		var shopId = e.currentTarget.dataset.shopId;
		window.location.href = '/o2o/frontend/shopdetail?shopId=' + shopId;
	});

	$('#shoplist-search-div').on(
		'click',
		'.button',
		function(e) {
			if (parentId) { // 如果传递过来的是一个父类下的子类
				shopCategoryId = e.target.dataset.categoryId;
				if ($(e.target).hasClass('button-fill')) {
					$(e.target).removeClass('button-fill');
					shopCategoryId = '';
				} else {
					$(e.target).addClass('button-fill').siblings()
						.removeClass('button-fill');
				}
				$('.list-div').empty();
				pageNum = 1;
				addItems(pageSize, pageNum);
			} else { // 如果传递过来的父类为空，则按照父类查询
				parentId = e.target.dataset.categoryId;
				if ($(e.target).hasClass('button-fill')) {
					$(e.target).removeClass('button-fill');
					parentId = '';
				} else {
					$(e.target).addClass('button-fill').siblings()
						.removeClass('button-fill');
				}
				$('.list-div').empty();
				pageNum = 1;
				addItems(pageSize, pageNum);
				parentId = '';
			}

		});

	$('#search').on('input', function(e) {
		shopName = e.target.value;
		$('.list-div').empty();
		pageNum = 1;
		addItems(pageSize, pageNum);
	});

	$('#area-search').on('change', function() {
		areaId = $('#area-search').val();
		$('.list-div').empty();
		pageNum = 1;
		addItems(pageSize, pageNum);
	});

	$('#me').click(function() {
		$.openPanel('#panel-left-demo');
	});

	$.init();
});