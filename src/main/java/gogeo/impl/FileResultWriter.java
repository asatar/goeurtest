package gogeo.impl;

import gogeo.api.ResultWriter;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Implementation of writer that writes result to file
 */
public class FileResultWriter implements ResultWriter {

    private Logger logger = Logger.getLogger(this.getClass());

    private String filePath = "cities.csv";

    /**
     *
     * @param filePath relative path to a file from
     */
    public FileResultWriter(String filePath) {
        this.filePath = filePath;
    }

    public FileResultWriter() {
    }

    /**
     * Writes data to a file No new line character will be inserted in between
     * @param lines string lines that will be written to file
     */
    @Override
    public void writeLines(List<String> lines) {

        logger.info("about to write results to file");
        Path path = Paths.get(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : lines) {
                writer.write(line);
            }
            logger.info("results were written to file:"+path.toAbsolutePath().toString());
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
