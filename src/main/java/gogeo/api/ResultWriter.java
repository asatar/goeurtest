package gogeo.api;

import java.util.List;

public interface ResultWriter {

    /**
     * writes lines somewhere
     * @param lines
     */
    void writeLines(List<String> lines);
}
