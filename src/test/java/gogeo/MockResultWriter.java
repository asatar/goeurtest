package gogeo;

import gogeo.api.ResultWriter;

import java.util.List;


public class MockResultWriter implements ResultWriter {

    private List<String> lines;

    @Override
    public void writeLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }
}
