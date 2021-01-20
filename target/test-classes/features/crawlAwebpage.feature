#--------------------------------------------#
#--Assessment Test Using Cucumber-Java[BDD]--#
#   Author: Ronald Nickson Pasteen Baskar    #
#     email-id: roni.nickson@gmail.com       #
#     Date of creation: 18th-Jan-2021        #
#             Version: 1.0                   #
#--------------------------------------------#
#           Keywords Summary:                #
#--------------------------------------------#
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)

Feature: Scan & Validate a given website for no console errors, response code & broken links by crawl over all the web pages of the site 
  I want to validate the below scenario on the site

  @SanityTest
  Scenario Outline: Sanity check for the listed webpages 
    Given I want to open "<browser>" browser
    And I want to scan the website 
    When I enter the url-"<url>"
    When I maximize the browser
    Then the "<browser>" browser should be maximized
    And I verify the page title as "<page_title>"
    And I wait for 5 seconds to load the page
    When I close the "<browser>" browser
    Then the current browser should be closed

    Examples:
    	|browser|								url																 |													page_title																								  |
    	|chrome |https://www.kraken.com														 |Bitcoin & Cryptocurrency Exchange \| Bitcoin Trading Platform \| Kraken								|
    	|firefox|https://www.kraken.com/prices										 |Cryptocurrency Prices \| Live Crypto Quotes \| Kraken																  |
			|chrome |https://www.kraken.com/en-us/features/security/pgp|Kraken \| Buy, Sell and Margin Trade Bitcoin (BTC) and Ethereum (ETH) - PGP Public Key|
			
	@SmokeTest
  Scenario Outline: Validation for no console errors on page loads
    Given I want to open "<browser>" browser
    And I want to scan the website
    When I enter the url-"<url>"
    Then I should be redirected to the corressponding webpage
    And I validate the response code from the page url "<url>" is <resp_code>
    When I maximize the browser
    Then the "<browser>" browser should be maximized
    And I verify the page title as "<page_title>"
    And I wait for 10 seconds to load the page
    Then I validate for no console errors for the url-"<url>"
    When I close the "<browser>" browser
    Then the current browser should be closed

    Examples:
    	|browser|								url																 |resp_code|													page_title																								  |
    	|chrome |https://www.kraken.com														 |200      |Bitcoin & Cryptocurrency Exchange \| Bitcoin Trading Platform \| Kraken								|
    	|chrome |https://www.kraken.com/prices										 |200      |Cryptocurrency Prices \| Live Crypto Quotes \| Kraken																  |
			|chrome |https://www.kraken.com/doesntexist								 |404      |€29,775.10 - Kraken - 404 - Page Not Found																					  |
			|chrome |https://www.kraken.com/en-us/features/security/pgp|200      |Kraken \| Buy, Sell and Margin Trade Bitcoin (BTC) and Ethereum (ETH) - PGP Public Key|
		
	@Regression
  Scenario Outline: Scan for all links on the page go to another live (non 4xx) page
    Given I want to open "<browser>" browser
    And I want to scan the website
    When I enter the url-"<url>"
    Then I should be redirected to the corressponding webpage
    When I maximize the browser
    Then the "<browser>" browser should be maximized
    And I verify the page title as "<page_title>"
    And I wait for 10 seconds to load the page
    Then I validate all the links on the page-"<url>" 
    And I validate all the images on the page-"<url>" 
    When I close the "<browser>" browser
    Then the current browser should be closed

    Examples:
    	|browser|								url																 |													page_title																								  |
    	|chrome |https://www.kraken.com														 |Bitcoin & Cryptocurrency Exchange \| Bitcoin Trading Platform \| Kraken								|
    	#|chrome |https://linka.permanenttsb.ie/vpn/index.html														 |Citrix Access Gateway								|
 