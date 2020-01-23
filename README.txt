Selenium

Selenium is an open-source tool that automates web browsers. It provides a single interface that lets you write test scripts in programming languages like Ruby, Java, NodeJS, PHP, Perl, Python, and C#, among others. POM is a design pattern which is commonly used in Selenium for Automating the Test Cases.

Page Object Model

The Page object is an object-oriented class which acts as an interface for the page of your Application under test. Page class contains web elements and methods to interact with web elements. While Automating the test cases, we create the object of these Page Classes and interact with web elements by calling the methods of these classes.

com.phptravels.qa.base.BasePage.java :

This class contains all the abstractions of selenium’s actions such as sendKeys, click, clear, findElement  etc.

com.phptravels.qa.base.BaseTest :

This class takes care of verifying test pre-requisites such as the choice of the browser, the url to be navigated to, etc.  Also it contains the factory for creating web driver and inject to BasePage. 

com.phptravels.qa.cases.SearchFlightsTest.java :

This class contains all the test cases to be verified. It verifies all business logics over SearchFlightsPage page object.

com.phptravels.qa.ui.map.SearchFlightsMap :
	
This class contains all the required html locators for various elements displayed in UI.

com.phptravels.qa.ui.page.SearchFlightsPage :

This class extends BasePage.java and contains all the business logics related to test cases and do the selenium actions on the UI.

verifyPricesAreShowingLowToHigh() :

This method verifies that the prices are showing in ascending order (lowest to highest prices)
getPrices() method will fetch all the prices against each flight and store into List.
isSortedFromLowToHigh() method will verify if the price list is sorted or not.

verifyStartLocationInSearchResults() :

This method verifies that the city of source displayed against each flight, if the city has more than one airports (NYC : 3 airports)
Ex: Constants.FROM_LOCATION_CITIES will hold the set of applicable airports of source location.

verifyEndLocationInSearchResults :

This method verifies the city of destination displayed against each flight, if in case a city has more than one airports.
Ex: Constants.TO_LOCATION_CITIES will hold the set of applicable airports of destination location.

verifyShortestRouteAndBook :

This method finds the shortest distance among the entire search list and books the ticket for the same flight.

com.phptravels.qa.ui.utilities.Constants :

This class holds all application specific constants. 

com.phptravels.qa.ui.utilities.AppUtil :

This class helps to get the future dates such as two weeks from current date. 

phptravels-selenium\src\test\resources\drivers :

This folder contains all the required drivers to launch respective browsers such as Chrome, Firefox and IE


