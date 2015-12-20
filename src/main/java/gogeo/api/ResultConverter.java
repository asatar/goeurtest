package gogeo.api;

import java.util.Optional;

public interface ResultConverter {

    /**
     * get source and converts to something
     * @param source
     * @return
     */
    Optional<String> convert(Optional<String> source);
}
