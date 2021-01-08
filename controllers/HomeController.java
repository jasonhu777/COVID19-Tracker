package io.JavaProjects.COVID19Tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.JavaProjects.COVID19Tracker.models.LocationData;
import io.JavaProjects.COVID19Tracker.services.Covid19DataService;

@Controller
public class HomeController {

  @Autowired
  Covid19DataService covid19DataService;

  @GetMapping("/")
  public String home(Model model) {
    List<LocationData> allData = covid19DataService.getAllData();
    int totalReportedCases = allData.stream().mapToInt(ele -> ele.getLatestTotalCases()).sum();
    int totalNewCases = allData.stream().mapToInt(ele -> ele.getDiffCases()).sum();
    model.addAttribute("locationData", allData);
    model.addAttribute("totalReportedCases", totalReportedCases);
    model.addAttribute("totalNewCases", totalNewCases);

    return "home";
  }
}
