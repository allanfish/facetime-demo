package com.facetime.demo.gwt.jquery.client;

import static com.xedge.jquery.client.JQuery.ready;
import static com.xedge.jquery.client.JQuery.select;

import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.CommandHandler;
import com.xedge.jquery.client.handlers.EventHandler;

public class FirstViewImpl implements FirstView {

	public FirstViewImpl() {
		ready(new CommandHandler() {

			@Override
			public void execute() {
				select("div.msg-panel input[type=button]").click(new EventHandler() {

					@Override
					public void eventComplete(JQEvent event, JQuery currentJQuery) {
						JQuery msgLbl = select("div.msg-panel label");
						if (msgLbl.html().equals("Click Me")) {
							msgLbl.fadeOut(600);
						} else {
							msgLbl.html("Click Me");
						}
					}
				});
			}
		});
	}
}
