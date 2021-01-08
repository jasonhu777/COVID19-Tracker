package io.JavaProjects.COVID19Tracker.models;

public class LocationData {
  private String province;
  private String country;
  private int latestTotalCases;
  private int diffCases;

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getLatestTotalCases() {
    return latestTotalCases;
  }

  public void setLatestTotalCases(int latestTotalCases) {
    this.latestTotalCases = latestTotalCases;
  }

  public int getDiffCases() {
    return diffCases;
  }

  public void setDiffCases(int diffCases) {
    this.diffCases = diffCases;
  }

  @Override
  public String toString() {
    return "LocationData{" + "province='" + province + '\'' + ", country='" + country + '\'' + ", latestTotalCases="
        + latestTotalCases + '}';
  }
}
