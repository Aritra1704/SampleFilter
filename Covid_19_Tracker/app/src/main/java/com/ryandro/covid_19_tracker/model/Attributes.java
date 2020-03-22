
package com.ryandro.covid_19_tracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("OBJECTID")
    @Expose
    private Integer oBJECTID;
    @SerializedName("Province_State")
    @Expose
    private String provinceState;
    @SerializedName("Country_Region")
    @Expose
    private String countryRegion;
    @SerializedName("Last_Update")
    @Expose
    private long lastUpdate;
    @SerializedName("Lat")
    @Expose
    private Double lat;
    @SerializedName("Long_")
    @Expose
    private Double _long;
    @SerializedName("Confirmed")
    @Expose
    private Integer confirmed;
    @SerializedName("Recovered")
    @Expose
    private Integer recovered;
    @SerializedName("Deaths")
    @Expose
    private Integer deaths;

    public Integer getOBJECTID() {
        return oBJECTID;
    }

    public void setOBJECTID(Integer oBJECTID) {
        this.oBJECTID = oBJECTID;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }



    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLong() {
        return _long;
    }

    public void setLong(Double _long) {
        this._long = _long;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
