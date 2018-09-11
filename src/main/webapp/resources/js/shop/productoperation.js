$(function() {
	//从url里面获取productId参数的值
	var productId = getQueryString('productId');
	//通过productId获取商品信息的url
	var infoUrl = '/o2o/shopadmin/getproductbyid?productId=' + productId;
	//获取当前店铺设定的商品类别列表的url
	var categoryUrl = '/o2o/shopadmin/getProductCategoryList';
	//更新商品信息的url
	var productPostUrl = '/o2o/shopadmin/modifyproduct';
	var isEdit = false;
	if (productId) {
		//若有productId则为编辑操作
		getInfo(productId);
		isEdit = true;
	} else {
		getCategory();
		productPostUrl = '/o2o/shopadmin/addProduct';
	}
	//获取需要编辑的商品的商品信息，并赋值给表单
	function getInfo(id) {
		$
			.getJSON(
				infoUrl,
				function(data) {
					if (data.success) {
						//从返回的JSON中获取到product对象的信息，并赋值给表单
						var product = data.product;
						$('#product-name').val(product.productName);
						$('#product-desc').val(product.productDesc);
						$('#priority').val(product.priority);
						$('#normal-price').val(product.normalPrice);
						$('#promotion-price').val(
							product.promotionPrice);
						//获取原本的商品类别以及该店铺的所有商品类别列表
						var optionHtml = '';
						var optionArr = data.productCategoryList;
						var optionSelected = product.productCategory.productCategoryId;
						//生成前端的Html商品类别列表，并默认选择编辑前的商品类别  
						optionArr
							.map(function(item, index) {
								var isSelect = optionSelected === item.productCategoryId ? 'selected'
									: '';
								optionHtml += '<option data-value="'
									+ item.productCategoryId
									+ '"'
									+ isSelect
									+ '>'
									+ item.productCategoryName
									+ '</option>';
							});
						$('#category').html(optionHtml);
					}
				});
	}
	//为商品添加操作提供该店铺下的的所有商品类别列表
	function getCategory() {
		$.getJSON(categoryUrl, function(data) {
			if (data.success) {
				var productCategoryList = data.data;
				var optionHtml = '';
				productCategoryList.map(function(item, index) {
					optionHtml += '<option data-value="'
						+ item.productCategoryId + '">'
						+ item.productCategoryName + '</option>';
				});
				$('#category').html(optionHtml);
			}
		});
	}
	//针对商品详情图控件组，若该空间组的最后一个元素发生变化：即上传了图片
	//且控件数未达到六个，则生成新的一个文件上传控件
	$('.detail-img-div').on('change', '.detail-img:last-child', function() {
		if ($('.detail-img').length < 6) {
			$('#detail-img').append('<input type="file" class="detail-img">');
		}
	});
	//提交按钮的响应事件，分别对商品添加和编辑做不同的响应
	$('#submit').click(
		function() {
			//创建 商品json对象，并从表单里面获取对应的属性值
			var product = {};
			product.productName = $('#product-name').val();
			product.productDesc = $('#product-desc').val();
			product.priority = $('#priority').val();
			product.normalPrice = $('#normal-price').val();
			product.promotionPrice = $('#promotion-price').val();
			//获取选定的商品类别值
			product.productCategory = {
				productCategoryId : $('#category').find('option').not(
					function() {
						return !this.selected;
					}).data('value')
			};
			product.productId = productId;
			//获取缩略图文件流
			var thumbnail = $('#small-img')[0].files[0];
			/*console.log(thumbnail);*/
			var formData = new FormData();
			formData.append('thumbnail', thumbnail);
			$('.detail-img').map(
				function(index, item) {
					if ($('.detail-img')[index].files.length > 0) {
						formData.append('productImg' + index,
							$('.detail-img')[index].files[0]);
					}
				});
			formData.append('productStr', JSON.stringify(product));
			var verifyCodeActual = $('#j_captcha').val();
			if (!verifyCodeActual) {
				$.toast('请输入验证码！');
				return;
			}
			formData.append("verifyCodeActual", verifyCodeActual);
			$.ajax({
				url : productPostUrl,
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					if (data.success) {
						$.toast('提交成功！');
						$('#captcha_img').click();
					} else {
						$.toast('提交失败！');
						$('#captcha_img').click();
					}
				}
			});
		});

});