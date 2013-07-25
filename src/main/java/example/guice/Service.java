package example.guice;

import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class Service {

	public static final String SERVICE_STRING = "SERVICE_STRING";

	public Service() {}

	public String get() {
		return SERVICE_STRING;
	}

}
