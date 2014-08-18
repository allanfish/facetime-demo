define(function(require, exports, module) {

	/**
	 * 消息DTO
	 */
	 window.MessageDTO = Backbone.Model.extend({
	 	urlRoot : "/message",

	 	defaults : {
	 		'chatId': undefined,
	 		'messageId': undefined,
	 		'messageType': undefined,
	 		'offlineMessage': undefined,
	 		'sender': undefined,
	 		'receiver': undefined,
	 		'sendDate': undefined,
	 		'messageBody': undefined,
	 		'groupId': undefined,
	 		'fromUser': undefined,
	 		'status': undefined,
	 		'showed': undefined,
	 		'confirmed': undefined,
	 		'displaying': undefined
	 	}, 

	 	isChatMessage: function(){

	 	}

	 /**end model*/});

	});
