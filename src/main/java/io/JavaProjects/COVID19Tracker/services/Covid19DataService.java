package io.JavaProjects.COVID19Tracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.JavaProjects.COVID19Tracker.models.LocationData;

@Service
public class Covid19DataService {

  private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

  private List<LocationData> allData = new ArrayList<>();

  public List<LocationData> getAllData() {
    return allData;
  }

  @PostConstruct
  @Scheduled(cron = "* * 1 * * *")
  public void fetchData() throws IOException, InterruptedException {

    List<LocationData> newData = new ArrayList<>();

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();

    HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

    StringReader csvBodyReader = new StringReader(httpResponse.body());
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

    for (CSVRecord record : records) {
      LocationData locationData1 = new LocationData();
      locationData1.setProvince(record.get("Province/State"));
      locationData1.setCountry(record.get("Country/Region"));

      int latestCases = Integer.parseInt(record.get(record.size() - 1));

      int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
      locationData1.setLatestTotalCases(latestCases);
      locationData1.setDiffCases(latestCases - prevDayCases);
      newData.add(locationData1);
    }
    this.allData = newData;
  }
}
