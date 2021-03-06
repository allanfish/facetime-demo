package controllers;

import play.data.validation.Required;
import play.mvc.Controller;
import play.data.validation.*;

public class Application extends Controller {

	public static void index() {
		render();
	}

	public static void sayHello(@Required String myName) {
		if (validation.hasErrors()) {
			flash.error("Oops, please enter your name!");
			index();
		}
		render(myName);
	}
}