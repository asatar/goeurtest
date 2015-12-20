package gogeo.impl;

import gogeo.api.ResultConverter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Optional;

/**
 * Implementation that reads expects json array and converts it into csv strings. Takes only some of json fields
 */
public class JsonCityToCsvConverter implements ResultConverter {

    private enum JsonCityFields {
        ID("_id"), NAME(), TYPE(), GEO_POSITION(), LATITUDE(), LONGITUDE();

        private String fieldName;

        JsonCityFields(){
            this.fieldName = this.name().toLowerCase();
        }

        JsonCityFields(String str){
          this.fieldName = str;
        }

        public String fieldName(){
            return this.fieldName;
        }
    }

    /**
     * converts json to csv format. Extracts only some of fields
     * @param source
     * @return
     */
    @Override
    public Optional<String> convert(Optional<String> source) {
        StringBuilder resultStr = new StringBuilder();
        Optional<String> result = Optional.empty();
        if(source.isPresent()) {
            JSONArray results = new JSONArray(source.get());
            for (int i = 0; i < results.length(); i++) {
                JSONObject jsonObject = results.getJSONObject(i);
                resultStr.append(jsonObject.getInt(JsonCityFields.ID.fieldName())).append(",");
                resultStr.append(jsonObject.getString(JsonCityFields.NAME.fieldName())).append(",");
                resultStr.append(jsonObject.getString(JsonCityFields.TYPE.fieldName())).append(",");
                JSONObject geo_position = jsonObject.getJSONObject(JsonCityFields.GEO_POSITION.fieldName());
                resultStr.append(geo_position.getDouble(JsonCityFields.LATITUDE.fieldName())).append(",");
                resultStr.append(geo_position.getDouble(JsonCityFields.LONGITUDE.fieldName()));
                resultStr.append(System.lineSeparator());
            }
            result = Optional.of(resultStr.toString());
        }
        return result;
    }
}
