package gogeo;

import gogeo.impl.JsonCityToCsvConverter;

import java.util.List;

import static org.junit.Assert.*;


public class CityInformationProcessorTest {

    private static final String expected = "376217,Berlin,location,52.52437,13.41053"+System.lineSeparator() +
            "448103,Berlingo,location,45.50298,10.04366"+System.lineSeparator();

    @org.junit.Test
    public void testProcessCities() throws Exception {
        MockResultWriter writer = new MockResultWriter();
        CityInformationProcessor processor = new CityInformationProcessor(new MockReader(),new JsonCityToCsvConverter(), writer);
        processor.processCities(new String[]{"a"});
        List<String> lines = writer.getLines();
        assertEquals("number or lines should be 1",1,lines.size());
        assertEquals(lines.get(0),expected);
    }
}