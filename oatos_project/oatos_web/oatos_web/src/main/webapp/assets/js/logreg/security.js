(function(window) {
	var Security = window.Security || {};
	window.Security = Security;

	var i = 0, j = 0, sbox = [];

	function setup(key) {
		var k, x;
		var kl = key.length;

		for (i = 0; i < 256; i++) {
			sbox[i] = i >= 128 ? (i % 128 - 128) : i;
		}

		for (i = 0, j = 0, k = 0; i < 256; i++) {
			j = j + sbox[i] + key.charCodeAt(k) & 0xff;
			k = (k + 1) % kl;

			x = sbox[i];
			sbox[i] = sbox[j];
			sbox[j] = x;
		}

		// Set things up to start coding/decoding

		i = 0;
		j = 0;
	}
	function codeDecode(key, plaintext) {
		setup(key);

		var x, r = "";
		var pl = plaintext.length;
		for ( var k = 0; k < pl; k++) {
			i = i + 1 & 0xff;
			j = j + sbox[i] & 0xff;

			x = sbox[i];
			sbox[i] = sbox[j];
			sbox[j] = x;

			r += String.fromCharCode(plaintext.charCodeAt(k)
					^ sbox[sbox[i] + sbox[j] & 0xff] & 0xff);
		}
		return r;

	}
	function randomCharString() {
		var radStr = "0123456789abcdefghijklmnopqrstuvwxyz";
		var length = 32;
		var randBuffer = [];
		for ( var i = 0; i < length; i++) {
			randBuffer[i] = radStr.charAt(~~(Math.floor(Math.random() * 36)));
		}
		return randBuffer.join("");
	}
	function byteStringToHexString(s) {
		var r = "", i;
		for (i = 0; i < s.length; i++) {
			r += (s.charCodeAt(i)).toString(16);
		}
		return r;
	}

	// export method
	Security.codeDecode = codeDecode;
	Security.randomCharString = randomCharString;
	Security.byteStringToHexString = byteStringToHexString;
})(window);