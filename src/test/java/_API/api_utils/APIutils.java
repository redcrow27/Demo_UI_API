package _API.api_utils;

import _UI.step_definition.ScenarioContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


public class APIutils {
    ScenarioContext context;

    public APIutils(ScenarioContext testContext){
        context = testContext;
    }

    public static String printPrettyJSONObject(String jsonString){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonString);
        return gson.toJson(jsonElement);
    }


}
