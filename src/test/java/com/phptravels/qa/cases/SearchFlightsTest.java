package com.phptravels.qa.cases;

import com.phptravels.qa.base.BaseTest;
import com.phptravels.qa.ui.page.SearchFlightsPage;
import org.testng.annotations.Test;

public class SearchFlightsTest extends BaseTest {

    private SearchFlightsPage searchFlightsPage = new SearchFlightsPage();

    @Test(priority = 1)
    public void verifyPricesAreShowingLowToHighInSearchResults() {
        searchFlightsPage.verifyPricesAreShowingLowToHigh();
    }

    @Test(priority = 2)
    public void verifyStartAndEndLocationInSearchResults() {
        searchFlightsPage.verifyStartLocationInSearchResults();
        searchFlightsPage.verifyEndLocationInSearchResults();
    }

    @Test(priority = 3)
    public void verifyShortestRouteAndBook() {
        searchFlightsPage.verifyShortestRouteAndBook();
    }
}
