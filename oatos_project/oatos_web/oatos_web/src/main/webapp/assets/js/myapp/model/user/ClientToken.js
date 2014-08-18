define(function(require, exports, module) {

	require('jquerycookie');

	var token = $.cookie("ut");




	if (token) {
		var args = token.split('@');
		if (args.length !== 2) { throw 'invalid token'; }
		exports.userId = parseInt(args[0]);
		exports.userToken = args[1];
		exports.token = token;

		if (isNaN(exports.userId)) { throw 'invalid token'; }
	}
});

