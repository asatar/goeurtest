package gogeo.api;

import java.util.Optional;


public interface CityReader {
    /**
     * reads data from the source
     * @param source id that identifies data you requested
     * @return string that represents city data structure in some defined format e.g. json
     */
    Optional<String> readCities(String source);
}
