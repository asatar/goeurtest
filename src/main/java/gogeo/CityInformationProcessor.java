package gogeo;

import gogeo.api.CityReader;
import gogeo.api.ResultConverter;
import gogeo.api.ResultWriter;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Reads data about cities transforms and writes to destination
 */
public class CityInformationProcessor {
    private final CityReader reader;
    private final ResultConverter converter;
    private final ResultWriter writer;

    private Logger logger = Logger.getLogger(this.getClass());

    public CityInformationProcessor(CityReader reader, ResultConverter converter, ResultWriter writer) {
        this.reader = reader;
        this.converter = converter;
        this.writer = writer;
    }

    /**
     * Reads specified cities from reader, transforms and writes to writer
     * @param cities array of cities you want to get information about
     */
    public void processCities(String[] cities) {
        //well it is not wise to use parallel for possibly long running tasks like network connection, but.....
        List<Optional<String>> citiesJson = Arrays.stream(cities).parallel().map(reader::readCities).collect(toList());
        List<Optional<String>> csvStrings = citiesJson.stream().map(converter::convert).collect(toList());
        if(logger.isDebugEnabled()) {
            dumpToLog(csvStrings);
        }
        List<String> lines = csvStrings.stream().filter(Optional::isPresent).map(Optional::get).collect(toList());
        writer.writeLines(lines);
    }

    private void dumpToLog(List<Optional<String>> csvStrings) {
        logger.debug("Start dumping csv to console:");
        csvStrings.forEach(s -> {
            if (s.isPresent()) {
                logger.debug(s.get());
            }
        });
        logger.debug("Stop dumping csv to console");
    }
}
