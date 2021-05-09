package com.vehiclecheck.stepdefs;

import com.vehiclecheck.contexts.TestContext;
import com.vehiclecheck.dto.CarDTO;
import com.vehiclecheck.enums.Context;
import com.vehiclecheck.pageojects.CarCheckHomePage;
import com.vehiclecheck.pageojects.FreeCarCheckPage;
import com.vehiclecheck.stepdefs.hooks.TestHook;
import com.vehiclecheck.utils.FileReader;
import com.vehiclecheck.utils.PropertyReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class CarDetailCheckSteps {
    private static final Logger log = LoggerFactory.getLogger(CarDetailCheckSteps.class);

    private TestContext testContext;
    private CarCheckHomePage carCheckHomePage;
    private FreeCarCheckPage freeCarCheckPage;
    private TestHook hook;

    public CarDetailCheckSteps(TestContext context, TestHook hook) {
        this.testContext = context;
        this.hook = hook;
        this.carCheckHomePage = testContext.getPageObjectManager().getCarCheckHomePage();
        this.freeCarCheckPage = testContext.getPageObjectManager().getFreeCarCheckPage();
    }

    @Given("there are list of car registration numbers extracted from the input files")
    public void there_are_list_of_car_registration_numbers_extracted_from_the_input_files() throws IOException {
        String filePath = PropertyReader.getInputDataFilePath();
        Pattern carRegistrationPattern = Pattern.compile(PropertyReader.getCarRegistrationPattern());
        List<String> carList = FileReader.readInputDataFromFiles(filePath, carRegistrationPattern);
        testContext.scenarioContext.setContext(Context.VEHICLE_LIST, carList);
    }

    @Given("I am on car check home page")
    public void i_am_on_car_check_home_page() {
        carCheckHomePage.navigate_to_homePage();
    }

    @When("I do free car check for all the registration numbers extracted from the input files")
    public void i_do_free_car_check_for_all_the_registration_numbers_extracted_from_the_input_files() {
        List<String> vehicleList = (List<String>) testContext.scenarioContext.getContext(Context.VEHICLE_LIST);
        Map<String, CarDTO> actualVehicleDetails = new HashMap<>();
        vehicleList.stream()
                .forEach( registration -> {
                    do_free_car_check(registration);
                    actualVehicleDetails.put(registration, getVehicleIdentificationDetails());
                    do_check_another_vehicle();
                });
        log.info("Actual vehicle details:: " + actualVehicleDetails.toString());
        testContext.scenarioContext.setContext(Context.VEHICLE_DETAILS_LIST, actualVehicleDetails);
    }

    @Then("the expected car details should be displayed in the free-car-check page")
    public void the_expected_car_details_should_be_displayed_in_the_free_car_check_page() {
        Map<String, CarDTO> actualVehicleDetails = (Map<String, CarDTO>) testContext.scenarioContext.getContext(Context.VEHICLE_DETAILS_LIST);
        Map<String, CarDTO> expectedVehicleDetails = getExpectedVehicleDetails();

        actualVehicleDetails.entrySet().stream()
                .forEach(e -> {
                   CarDTO actualData = e.getValue();
                   CarDTO expectedData = expectedVehicleDetails.get(e.getKey());
                   assertThat(actualData, samePropertyValuesAs(expectedData));
                });
    }

    private Map<String, CarDTO> getExpectedVehicleDetails() {
        Map<String, CarDTO> expectedVehicleDetils = new HashMap<>();
        try {
            expectedVehicleDetils = FileReader.readExpectedDatafromFile(PropertyReader.getExpectedDataFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Expected vehicle details:: " + expectedVehicleDetils.toString());
        return expectedVehicleDetils;
    }

    private void do_free_car_check(String registration) {
        carCheckHomePage.do_free_car_check(registration);
    }

    private CarDTO getVehicleIdentificationDetails() {
        return freeCarCheckPage.get_vehicle_id_details();
    }

    private void do_check_another_vehicle() {
        freeCarCheckPage.click_btn_check_another_vehicle();
    }
}
