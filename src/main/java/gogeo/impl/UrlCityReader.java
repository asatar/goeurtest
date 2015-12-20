package gogeo.impl;

import gogeo.api.CityReader;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Reads data from URL by get request
 */
public class UrlCityReader implements CityReader {

    private String urlString = "http://api.goeuro.com/api/v2/position/suggest/en/";

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     *
     * @param url service url
     */
    public UrlCityReader(String url) {
        this.urlString = url;
    }


    public UrlCityReader() {
    }

    /**
     * read city data from url
     * @param source city you want get information about, e.g. Berlin Moscow
     * @return json result from rest service or empty in case of any error
     */
    @Override
    public Optional<String> readCities(String source) {
        Optional<String> result = Optional.empty();
        URL url;
        try {
            String webPath = urlString + source;
            logger.info("about to request data from:"+webPath);
            url = new URL(webPath);
            try (InputStream is = url.openStream()) {
                BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                result = Optional.of(inputStreamReader.lines().collect(Collectors.joining(System.lineSeparator())));
                logger.info("received data");
                if(logger.isDebugEnabled()){
                    logger.debug(result.get());
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
}
