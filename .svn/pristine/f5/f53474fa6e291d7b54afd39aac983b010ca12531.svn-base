define(function(require, exports, module) {

	require("wind");

	return {
		serviceUrl: "/flexService",

		ajaxTask: function(option) {
			return new Wind.Async.Task(function(task) {
				jQuery.extend(option, {
					success: function(data) {
						task.complete("success", data);
					},
					error: function(data) {
						task.complete("failure", data);
					}
				});
				$.ajax(option);
			});
		},

		param: function(serviceType, data) {
			return {
				url: this.serviceUrl,
				type: 'POST',
				headers: {
					'serviceType': serviceType,
					'UT': app.model.clientToken.token
				},
				data: JSON.stringify(data),
				dataType: 'json'
			};
		},

		/** 获取用户信息 */
		getUserProfile: function(data) {
			return this.ajaxTask(this.param("/sc/login/getUserProfile", data));
		},

		/** 获取企业信息 */
		getEnterprise: function(data) {
			return this.ajaxTask(this.param("/sc/ent/getEnterprise", data));
		},

		/** 按文件夹取企业网盘子文件夹和文件，取顶层文件夹时，文件夹id传null */
		getEntFolderAndFileByFolderId: function(data) {
			return this.ajaxTask(this.param("/sc/shareDisk/getEntFolderAndFileByFolderId", data));
		},
		/** 按文件夹取个人网盘子文件夹和文件，取顶层文件夹时，文件夹ID传null */
		getPersonalFolderAndFileByFolderId: function(data) {
			return this.ajaxTask(this.param('/sc/disk/getPersonalFolderAndFileByFolderId', data));
		}
	};
});
