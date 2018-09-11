$(function() {
	var shopId = getQueryString('shopId');
	var isEdit = shopId ? true : false;
	var initUrl = '/o2o/shopadmin/getShopInitInfo';
	var registerShopUrl = '/o2o/shopadmin/registerShop';
	var shopInfoUrl = "/o2o/shopadmin/getShopInfoById?shopId=" + shopId;
	var editShopUrl = '/o2o/shopadmin/modifyShop';
	if (!isEdit) {
		getShopInitInfo();
	} else {
		getShopInfo(shopId);
	}
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				var shop = data.shop;
				$('#shopName').val(shop.shopName);
				$('#shopAddr').val(shop.shopAddr);
				$('#shopPhone').val(shop.shopPhone);
				$('#shopDesc').val(shop.shopDesc);
				var shopCategory = '<option data-id="'
					+ shop.shopCategory.shopCategoryId + '"selected>'
					+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
						+ item.areaName + '</option>';
				});
				$('#shopCategory').html(shopCategory);
				//店铺类别不可修改
				$('#shopCategory').attr('disabled', 'disabled');
				$('#area').html(tempAreaHtml);
				$("#area option[data-id='" + shop.area.areaId + "']").attr("selected", "selected");
			}
		});
	}
	function getShopInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '<option>点击选择</option>';
				var tempAreaHtml = '<option>点击选择</option>';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId + '">'
						+ item.shopCategoryName + '</option>';
				});
				$('#shopCategory').html(tempHtml);

				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
						+ item.areaName + '</option>';
				});
				$('#area').html(tempAreaHtml);
			}
		});
	}
	$('#submit').click(function() {
		var shop = {};
		if (isEdit) {
			shop.shopId = shopId;
		}
		shop.shopName = $('#shopName').val();
		shop.shopAddr = $('#shopAddr').val();
		shop.phone = $('#shopPhone').val();
		shop.shopDesc = $('#shopDesc').val();
		shop.shopCategory = {
			shopCategoryId : $('#shopCategory').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		shop.area = {
			areaId : $('#area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		var shopImg = $('#shopImg')[0].files[0];
		var formData = new FormData();
		formData.append('shopImg', shopImg);
		formData.append('shopStr', JSON.stringify(shop));
		var verifyCodeActual = $('#verifyCode').val();
		if (!verifyCodeActual) {
			$.toast('请输入验证码')
			return;

		}
		formData.append('verifyCodeActual', verifyCodeActual);
		$.ajax({
			url : (isEdit ? editShopUrl : registerShopUrl),
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');

				} else {
					$.toast('提交失败！' + data.errMsg);
				}
			/*$('#onchange').click();*/
			}
		})

	});
});