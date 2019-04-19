package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.GetCountryPopulationRequest;
import io.spring.guides.gs_producing_web_service.GetCountryPopulationResponse;
import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;
import io.spring.guides.gs_producing_web_service.NewCountryRequest;
import io.spring.guides.gs_producing_web_service.NewCountryResponse;

@Endpoint
public class CountryEndpoint {

	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryPopulationRequest")
	@ResponsePayload
	public GetCountryPopulationResponse getCountryPopulation(@RequestPayload GetCountryPopulationRequest request) {
		GetCountryPopulationResponse response = new GetCountryPopulationResponse();
		Country c = countryRepository.findCountry(request.getName());
		if (c != null) {
			response.setPopulation(c.getPopulation());
		}

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "newCountryRequest")
	@ResponsePayload
	public NewCountryResponse saveCountry(@RequestPayload NewCountryRequest request) {
		NewCountryResponse response = new NewCountryResponse();
		response.setCountry(countryRepository.saveCountry(request.getCountry()));

		return response;
	}
}
