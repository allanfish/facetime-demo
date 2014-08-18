define(function(require, exports, module) {

	/**
	 * 用户DTO
	 */
	window.UserDTO = Backbone.Model.extend({
		urlRoot: "",

		defaults: {},

		validation: {


			registerMailAccount: [{
					required: true,
					msg: '请填写您常用邮箱'
				}, {
					pattern: 'email',
					msg: '邮箱格式不正确，请重新填写'
				}
			],

			signature: [{
					required: true,
					msg: '不能为空'
				}, {
					maxLength: 100,
					msg: '填写1-54个字'
				}
			],

			//city: "深圳",
			realName: [{
					required: true,
					msg: '请填写真实姓名'
				}, {
					minLength: 2,
					maxLength: 10,
					msg: '填写2-10个字'
				}
			],

			//"major": "计算机",
			phone: [{
					minLength: 4,
					maxLength: 14,
					msg: '填写4-14个数字'
				}
			],

			hobby: [{
					maxLength: 30,
					msg: '最多填写30个字'
				}
			]
		}
	});

});