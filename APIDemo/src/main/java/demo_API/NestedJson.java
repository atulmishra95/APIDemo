package demo_API;

import io.restassured.path.json.JsonPath;
import udemyFiles.Payloads;

public class NestedJson {
	public static void main(String[] args) {
		JsonPath js = new JsonPath(Payloads.coursePrice());
		// Print No of Courses
		int count = js.getInt("courses.size()");
		System.out.println("Courses count :" + count);
		// Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount :" + totalAmount);
		// Print Title of the first course
		String titleFirstCourse = js.get("courses[2].title");
		System.out.println("Title of the first course :" + titleFirstCourse);

		// print all courses and their respective prices
		for (int i = 0; i < count; i++) {
			String courseTitles=js.get("courses[" + i + "].title");
			System.out.println("Course Titles :"+courseTitles);
			System.out.println("Price of courses :"+js.get("courses[" + i + "].price").toString());
		}
	}
}