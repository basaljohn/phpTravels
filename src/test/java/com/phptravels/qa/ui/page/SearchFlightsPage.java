package com.phptravels.qa.ui.page;

import com.google.common.collect.Ordering;
import com.phptravels.qa.base.BasePage;
import com.phptravels.qa.ui.map.SearchFlightsMap;
import com.phptravels.qa.ui.utilities.AppUtil;
import com.phptravels.qa.ui.utilities.Constants;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SearchFlightsPage extends BasePage {
    private SearchFlightsMap searchFlightsMap = new SearchFlightsMap();

    private void clickFlightsLink() {
        click(searchFlightsMap.flightsLink());
    }

    private void clickBookNowButton(int position) {
        click(searchFlightsMap.bookNowButton(position));
    }

    private void enterEmailField() {
        enterText(searchFlightsMap.emailField(), Constants.EMAIL, true);
    }

    private void enterPasswordField() {
        enterText(searchFlightsMap.passwordField(), Constants.PASSWORD, true);
    }

    private void clickConfirmBooking() {
        click(searchFlightsMap.confirmBookingButton());
    }

    private void enterFromLocationField() {
        click(searchFlightsMap.fromLocationDiv());
        enterText(searchFlightsMap.locationField(), Constants.FROM_LOCATION, true);
        //waitTillVisible(searchFlightsMap.locationAutoCompleteList());
        pressTab();
    }

    private void enterToLocationField() {
        click(searchFlightsMap.toLocationDiv());
        enterText(searchFlightsMap.locationField(), Constants.TO_LOCATION, true);
        waitTillVisible(searchFlightsMap.locationAutoCompleteList());
        pressTab();
    }

    private void enterDepartField() {
        enableField(searchFlightsMap.DEPART_SELECTOR);
        enterText(searchFlightsMap.departField(), AppUtil.getDate(), true);
        pressTab();
    }

    private void enterAdultsField() {
        enableField(searchFlightsMap.ADULTS_SELECTOR);
        enterText(searchFlightsMap.adultsField(), Constants.NO_OF_ADULTS, true);
    }

    private void enterChildrenField() {
        enableField(searchFlightsMap.CHILDREN_SELECTOR);
        enterText(searchFlightsMap.childrenField(), Constants.NO_OF_CHILDREN, true);
    }

    private void clickSearchButton() {
        click(searchFlightsMap.searchButton());
    }

    public void verifyPricesAreShowingLowToHigh() {
        clickFlightsLink();
        enterFromLocationField();
        enterToLocationField();
        enterDepartField();
        enterAdultsField();
        enterChildrenField();
        clickSearchButton();
        waitTillVisible(searchFlightsMap.modifySearchButton());
        assertEquals(isSortedFromLowToHigh(getPrices()), true, "Prices should be lowest to Highest");
        System.out.println("Verified that all prices are displayed from low to high");
    }

    private List<Integer> getPrices() {
        List<Integer> prices = new ArrayList<Integer>();
        List<WebElement> elementList = elements(searchFlightsMap.priceTags());
        for(WebElement element : elementList) {
            prices.add(Integer.parseInt(getText(element).substring(4)));
        }
        return prices;
    }

    public void verifyStartLocationInSearchResults() {
        List<WebElement> elementList = elements(searchFlightsMap.locationInSearchResults());
        List<String> cities = Arrays.asList(Constants.FROM_LOCATION_CITIES);
        for(int i=0;i<elementList.size(); i=i+2) {

            assertEquals(cities.contains(getText(elementList.get(i))), true, "From Location should be "+cities+ "at Row no "+i);
        }
        System.out.println("Verified that All source cities are displayed in the search list are part of configured values");
    }

    public void verifyEndLocationInSearchResults() {
        List<WebElement> elementList = elements(searchFlightsMap.locationInSearchResults());
        List<String> cities = Arrays.asList(Constants.TO_LOCATION_CITIES);
        for(int i=1;i<elementList.size(); i=i+2) {
            assertEquals(cities.contains(getText(elementList.get(i))), true, "To Location should be "+cities+ "at Row no "+i);
        }
        System.out.println("Verified that All destination cities are displayed in the search list are part of configured values");
    }

    public void verifyShortestRouteAndBook() {
        List<WebElement> elementList = elements(searchFlightsMap.distanceInSearch());
        List<Integer> hours = new ArrayList<Integer>();
        List<Integer> minutes = new ArrayList<Integer>();
        for(int i=0;i<elementList.size(); i++) {
            String text = getText(elementList.get(i));
            hours.add(Integer.parseInt(text.substring(14, text.indexOf("h"))));
        }
        int minHours = Collections.min(hours);
        int hoursFrequency = Collections.frequency(hours, minHours);
        String searchText = "Trip Duration "+minHours+"h ";
        if(hoursFrequency > 1) {
            for(int i=0;i<elementList.size(); i++) {
                String text = getText(elementList.get(i));
                if(text.contains(searchText))
                    minutes.add(Integer.parseInt(text.substring(18, text.indexOf("m"))));
            }
            int minMinutes = Collections.min(minutes);
            searchText = "Trip Duration "+minHours+"h "+minMinutes+"m";
        }
        for(int i=0;i<elementList.size(); i++) {
            String text = getText(elementList.get(i));
            if(text.contains(searchText)) {
                System.out.println("Shortest distance found "+text);
                clickBookNowButton(i+1);
                enterEmailField();
                enterPasswordField();
                clickConfirmBooking();
                waitTillVisible(searchFlightsMap.invoiceTable());
                System.out.println("Verified that ticket has been booked");
                break;
            }
        }
    }

    public boolean isSortedFromLowToHigh(List<Integer> list) {
        System.out.println(list);
        return Ordering.natural().isOrdered(list);
    }

    public boolean isSortedFromHighToLow(List<String> list) {
        return Ordering.natural().reverse().isOrdered(list);
    }

}
