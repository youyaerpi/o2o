$(function() {
	//定义访问后台，获取头条列表以及一级类别的列表的url
	var url = '/o2o/frontend/listmainpageinfo';
	//访问后台，获取头条列表以及一级类别的列表
	$.getJSON(url, function(data) {
		if (data.success) {
			//获取后台传递来的头条列表
			var headLineList = data.headLineList;
			var swiperHtml = '';
			//遍历，拼接轮播图
			headLineList.map(function(item, index) {
				swiperHtml += ''
					+ '<div class="swiper-slide img-wrap">'
					+ '<img class="banner-img" src="'
					+ item.lineImg + '" alt="'
					+ item.lineName + '">'
					+ '</div>';
			});
			$('.swiper-wrapper').html(swiperHtml);
			//设置轮换时间为3秒
			$(".swiper-container").swiper({
				autoplay : 3000,
				autoplayDisableOnInteraction : false
			});
			//获取大类列表
			var shopCategoryList = data.shopCategoryList;
			var categoryHtml = '';
			//遍历
			shopCategoryList.map(function(item, index) {
				categoryHtml += ''
					+ '<div class="col-50 shop-classify" data-category='
					+ item.shopCategoryId + '>'
					+ '<div class="word">'
					+ '<p class="shop-title">'
					+ item.shopCategoryName
					+ '</p>'
					+ '<p class="shop-desc">'
					+ item.shopCategoryDesc + '</p>'
					+ '</div>'
					+ '<div class="shop-classify-img-warp">'
					+ '<img class="shop-img" src="'
					+ item.shopCategoryImg + '">'
					+ '</div>'
					+ '</div>';
			});
			$('.row').html(categoryHtml);
		}
	});
//若点击“我的”则显示侧栏
	$('#me').click(function() {
		$.openPanel('#panel-left-demo');
	});

	$('.row').on('click', '.shop-classify', function(e) {
		var shopCategoryId = e.currentTarget.dataset.category;
		var newUrl = '/o2o/frontend/shoplist?parentId=' + shopCategoryId;
		window.location.href = newUrl;
	});

});