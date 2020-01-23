package com.phptravels.qa.ui.map;

import org.openqa.selenium.By;

public class SearchFlightsMap {

    public String DEPART_SELECTOR = "#FlightsDateStart";
    public String ADULTS_SELECTOR = "input[name=fadults]";
    public String CHILDREN_SELECTOR = "input[name=fchildren]";

    public By flightsLink() {
        return By.cssSelector("a.flights");
    }

    public By fromLocationDiv() {
        return By.cssSelector("#s2id_location_from");
    }

    public By locationField() {
        return By.cssSelector("#select2-drop input");
    }

    public By locationAutoCompleteList() {
        return By.cssSelector("ul.select2-results li.select2-highlighted");
    }

    public By toLocationDiv() {
        return By.cssSelector("#s2id_location_to");
    }

    public By departField() {
        return By.cssSelector(DEPART_SELECTOR);
    }

    public By adultsField() {
        return By.cssSelector(ADULTS_SELECTOR);
    }

    public By childrenField() {
        return By.cssSelector(CHILDREN_SELECTOR);
    }

    public By searchButton() {
        return By.cssSelector("form[name=flightmanualSearch] button[type=submit]");
    }

    public By modifySearchButton() {
        return By.cssSelector("button.btn-change-search");
    }

    public By priceTags() {
        return By.cssSelector("p.theme-search-results-item-price-tag");
    }

    public By locationInSearchResults() {
        return By.cssSelector("p.theme-search-results-item-flight-section-meta-city");
    }

    public By distanceInSearch() {
        return By.cssSelector("div.theme-search-results-item-flight-section-path-fly-time");
    }

    public By bookNowButton(int position) {
        return By.cssSelector("li:nth-child("+position+") button[class*=theme-search-results-item-price-btn]");
    }

    public By emailField() {
        return By.cssSelector("#username");
    }

    public By passwordField() {
        return By.cssSelector("#password");
    }

    public By confirmBookingButton() {
        return By.cssSelector("#confirmBooking");
    }

    public By invoiceTable() {
        return By.cssSelector("#invoiceTable");
    }
}
