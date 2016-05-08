package ru.qatools.school.RestAssured;

import org.apache.http.HttpStatus;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by alekspog on 07.05.2016.
 */
public class WeatherTests {

    public static final String PortLouis = "Port-Louis";


    @Test
    @Title("Запрос к api/cities возврашает 200")
    public void ShouldReceiveStatusOkWhenGetCities() throws Exception{
        given().baseUri("http://weather.lanwen.ru")
                .basePath("api").param("limit", 5)
                .get("cities")
                .then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Title("Запрос к api/suggest с названием города возврашает список с искомым городом")
    public void ShouldReturnCityNameInSuggest() throws Exception{
        given().baseUri("http://weather.lanwen.ru")
                .basePath("api").param("query", PortLouis)
                .get("suggest")
                .then().assertThat().body("[0].name", equalTo(PortLouis));
    }

}
