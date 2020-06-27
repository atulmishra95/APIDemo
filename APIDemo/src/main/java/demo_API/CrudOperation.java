package demo_API;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import udemyFiles.Payloads;
import udemyFiles.ReuseableMethods;

public class CrudOperation {

	@Test
	public void Testing() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = RestAssured.given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(Payloads.AddPlace1()).when()
				.post("maps/api/place/add/json").then().assertThat().statusCode(200)

				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();
		System.out.println(response + "Response of API");
		JsonPath js = new JsonPath(response);

		String placeId = js.getString("place_id");
		System.out.println(placeId);

		String newAddress = "335,MRA HOSTEL";
		// Update place~~~~PUT Method~~~~
		RestAssured.given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payloads.updatePlace(placeId)).when().put("maps/api/place/update/json").then().assertThat().log()
				.all().statusCode(200).body("msg", equalTo("Address successfully updated"));

		// GET Place~~~~GET Method~~~~~
		String getPlaceResponse = RestAssured.given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", placeId).when().get("maps/api/place/get/json").then().assertThat().log().all()
				.statusCode(200).extract().asString();
		JsonPath js1 = ReuseableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println("Actual Address :" + actualAddress);
		Assert.assertEquals(actualAddress, newAddress);

	}
}