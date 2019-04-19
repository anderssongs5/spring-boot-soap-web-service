package hello;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CountryRepository {
	private static final Map<String, Country> countries = new HashMap<>();

	@PostConstruct
	public void initData() {
		Country spain = new Country();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setCurrency(Currency.EUR);
		spain.setPopulation(46704314);

		countries.put(spain.getName(), spain);

		Country poland = new Country();
		poland.setName("Poland");
		poland.setCapital("Warsaw");
		poland.setCurrency(Currency.PLN);
		poland.setPopulation(38186860);

		countries.put(poland.getName(), poland);

		Country uk = new Country();
		uk.setName("United Kingdom");
		uk.setCapital("London");
		uk.setCurrency(Currency.GBP);
		uk.setPopulation(63705000);

		countries.put(uk.getName(), uk);

		Country col = new Country();
		col.setName("Colombia");
		col.setCapital("Bogota");
		col.setCurrency(Currency.COP);
		col.setPopulation(49000000);

		countries.put(col.getName(), col);
	}

	public Country findCountry(String name) {
		Assert.notNull(name, "The country's name must not be null");
		return countries.get(name);
	}

	public String saveCountry(Country country) {
		Assert.notNull(country, "The country must not be null");
		Assert.notNull(country.getName(), "The country's name must not be null");
		Assert.notNull(country.getCapital(), "The country's capital must not be null");
		Assert.notNull(country.getCurrency(), "The country's currency must not be null");
		Assert.notNull(country.getCapital(), "The country's capital must not be null");
		Assert.isTrue(!(country.getPopulation() <= 0), "The country's population must be greater than 0");

		countries.put(country.getName(), country);

		return country.getName();
	}
}
