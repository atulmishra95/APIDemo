package udemyFiles;

import io.restassured.path.json.JsonPath;

public class ReuseableMethods {
public static JsonPath rawToJson(String response)
{
	JsonPath js1 =new JsonPath(response);
	return js1;
}
}