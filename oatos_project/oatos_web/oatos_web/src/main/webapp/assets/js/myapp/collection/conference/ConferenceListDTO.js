 define(function(require, exports, module) {

	/**
	 * 视频会议Collection
	 */
	 window.ConferenceListDTO = Backbone.Collection.extend({
	 	model : ConferenceDTO,
	 	url : "",
	 	localStorage: new Backbone.LocalStorage("conferenceListDTO"), 

	 	fetch: function(){
	 		var fetchConferenceListTask = eval(Wind.compile('async', function() {
	 			var conferencesDTO = $await(resturl.getConferenceListByUserId(cache.userId));
	 			log.debug('[ajax] conference list data:', conferencesDTO);
	 			collection.conferenceList.reset(conferencesDTO.conferenceList);
	 			log.info("[ajax] conference list fetch ok.");
	 		}));
	 		fetchConferenceListTask().start();
	 	}
	 /*end collection*/});

	});
