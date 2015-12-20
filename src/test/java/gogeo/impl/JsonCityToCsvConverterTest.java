package gogeo.impl;

import gogeo.MockReader;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;


public class JsonCityToCsvConverterTest {
    private static final String expected = "376217,Berlin,location,52.52437,13.41053"+System.lineSeparator() +
            "448103,Berlingo,location,45.50298,10.04366"+System.lineSeparator();
    @Test
    public void convert(){
        JsonCityToCsvConverter converter = new JsonCityToCsvConverter();
        Optional<String> result = converter.convert(Optional.of(MockReader.JSON));
        assertTrue("result should be present",result.isPresent());
        assertEquals(expected,result.get());
    }

}