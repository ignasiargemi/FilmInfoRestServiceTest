package FilmInfoRestServiceTest;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

import Domain.Film;

public class Test {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		
		// Get plain text ALL FILMS
		System.out.println(service.path("rest").path("films").accept(MediaType.TEXT_PLAIN).get(String.class));
		
		// Get plain text FILMS with 'WAR'
		System.out.println(service.path("rest").path("films").path("film/war").accept(MediaType.TEXT_PLAIN).get(String.class));
		
		// Post new film
		Form form = new Form();
		form.add("title","abcdef");
		form.add("year", 1234);
		form.add("director", "John");
		form.add("duration", 134);
		form.add("credits", "creditscreditscredits");
		form.add("review", "reviewreviewreview");
		service.path("rest").path("films").path("addFilm").type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
		
		// Get plain text FILMS with 'abcdef'
		System.out.println(service.path("rest").path("films").path("film/abcdef").accept(MediaType.TEXT_PLAIN).get(String.class));

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/FilmInfoRestService").build();
	}

}
