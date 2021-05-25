package com.dshliazhko.android.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelWeather {


    @SerializedName("list")
    @Expose
    public List<Lists> list = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Lists> getList() {
        return list;
    }

    public void setList(List<Lists> list) {
        this.list = list;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}

class Lists {

    @SerializedName("main")
    @Expose
    public Mains main;

    @SerializedName("weather")
    @Expose
    public List<Weathers> weather;

    @SerializedName("dt_txt")
    @Expose
    public String dt_txt;


}

class Mains {
    @SerializedName("temp")
    @Expose
    public Double temp;

    public Double getTemp() {

        return temp - (273.15);
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
class Weathers {
    @SerializedName("description")
    @Expose
    public String description;


}