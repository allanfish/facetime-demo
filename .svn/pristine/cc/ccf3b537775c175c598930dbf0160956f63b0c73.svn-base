(function(window) {
	window.i18n = window.i18n || {};
	i18n.prop = jQuery.i18n.prop;

	var local = util.getLocale();

	var names = [];
	names.push('AuthorizeMessages');
	names.push('MainConstants');

	var name;
	for (name in names) {
		load(names[name]);
	}

	function load(name, path) {
		jQuery.i18n.properties({
			name : name,
			path : path || 'bundle/',
			mode : 'map',
			language : local
		});
	}
})(window)