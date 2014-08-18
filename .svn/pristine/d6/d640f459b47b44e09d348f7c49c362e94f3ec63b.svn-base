define(function(require) {

	window.org = window.org || {};
	org.activemq = org.activemq || {};

	org.activemq.AmqAdapter =  {

		init: function(options) {
		},

	/**
	 *  Implement this method to make an AJAX call to the AjaxServlet. An
	 *  options object will accompany this class and will contain the properties
	 *  that describe the details of the AJAX call. The options object will
	 *  have the following properties:
	 *
	 *  - method:  'get' or 'post'
	 *  - data:    query data to accompany the post or get.
	 *  - success: A callback function that is invoked upon successful
	 *             completion of the AJAX call. The parameter is:
	 *             - data: The result of the AJAX call. In the case of XML
	 *                     data should resolve to a Document element.
	 *  - error:   A callback when some type of error occurs. The callback
	 *             function's parameters should be:
	 *             - xhr:    The XmlHttpRequest object.
	 *             - status: A text string of the status.
	 *             - ex:     The exception that caused the error.
	 *  - headers: An object containing additional headers for the ajax request.
	 */
	 ajax: function(uri, options) {
	 	request = {
	 		url: uri,
	 		data: options.data,
	 		success: options.success || function(){},
	 		error: options.error || function(){}
	 	}
	 	var headers = {};
	 	if( options.headers ) {
	 		headers = options.headers;
	 	}
	 	
	 	if (options.method == 'post') {
	 		request.type = 'POST';
			/* Force "Connection: close" for Mozilla browsers to work around
			 * a bug where XMLHttpReqeuest sends an incorrect Content-length
			 * header. See Mozilla Bugzilla #246651.
			 */
			 headers[ 'Connection' ] = 'close';
			} else {
				request.type = 'GET';
				request.dataType = 'xml';
			}
			
			if( headers ) {
				request.beforeSend = function(xhr) {
					for( h in headers ) {
						xhr.setRequestHeader( h, headers[ h ] );
					}
				}
			}
			
			jQuery.ajax( request );
		},

		log: function(message, exception) {
			return window.log;
		}
	};

});