define(function(require, exports, module) {

	require('jquerycookie');

	var token = '101287@d3de82dc697c3c403aed1277453d1049f18b976e9px5wb5h6vicu8ao38wgv7v961367551912318'; // $.cookie("ut");
	if (token) {
		var args = token.split('@');
		if (args.length !== 2) { throw 'invalid token'; }
		exports.userId = parseInt(args[0]);
		exports.userToken = args[1];
		exports.token = token;

		if (isNaN(exports.userId)) { throw 'invalid token'; }
	}
});
