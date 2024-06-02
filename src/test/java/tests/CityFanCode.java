package tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;
import utils.ApiUtils;
import utils.ExtentManager;

public class CityFanCode extends BaseTest {

	@Test
	public void validateTodosCompletion() {
		ExtentTest test = extent.createTest("vVlidate-Todos-Completion",
				"Validate that all users of City FanCode have more than half of their todos completed");
		ExtentManager.setTest(test);

		// Get users and todos
		Response usersResponse = ApiUtils.getUsers();
		Response todosResponse = ApiUtils.getTodos();

		Assert.assertEquals(usersResponse.getStatusCode(), 200);
		Assert.assertEquals(todosResponse.getStatusCode(), 200);

		// Filtering FanCode city users
		JSONArray users = new JSONArray(usersResponse.asString());
		JSONArray todos = new JSONArray(todosResponse.asString());

		for (int i = 0; i < users.length(); i++) {
			JSONObject user = users.getJSONObject(i);
			JSONObject address = user.getJSONObject("address");
			JSONObject geo = address.getJSONObject("geo");
			double lat = geo.getDouble("lat");
			double lng = geo.getDouble("lng");

			if (lat > -40 && lat < 5 && lng > 5 && lng < 100) {
				int userId = user.getInt("id");
				int completedCount = 0;
				int totalCount = 0;

				for (int j = 0; j < todos.length(); j++) {
					JSONObject todo = todos.getJSONObject(j);
					if (todo.getInt("userId") == userId) {
						totalCount++;
						if (todo.getBoolean("completed")) {
							completedCount++;
						}
					}
				}

				double completionPercentage = (double) completedCount / totalCount * 100;
				test.info("User " + userId + " completion percentage: " + completionPercentage);
				Assert.assertTrue(completionPercentage > 50, "User " + userId + " has less than 50% tasks completed.");
			}
		}
	}


}
