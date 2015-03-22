import java.util.Set;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import org.json.*;

public class Main {
	
	//Instagram API Data
	public static String srcI;
	public static Double latitudeI;
	public static Double longitudeI;
	public static String urlI;
	public static String idI;
	
	//Twitter API Data
	
	
	public static Double latitudeT;
	public static Double longitudeT;
	public static String urlT;
	public static String textT;
	public static String idT;
	
	public static void main(String[] args) {
		Spark.get("/hello", new Route() {
    		@Override
    		public Object handle(Request request, Response response) {
    		return "Hello World!";
    		}
    		});
		
		Spark.post("/instagram", new Route() {
    		@Override
    		public Object handle(Request request, Response response) {
    			String data = request.body();
    			
    			JSONObject obj = new JSONObject(data);
    			srcI = obj.getString("src");
    			latitudeI = obj.getDouble("lat");
    			longitudeI= obj.getDouble("lng");
    			urlI = obj.getString("urli");
    			idI = obj.getString("id");
    							  
    			
    		return  "OK";
    		}
    		});
		
		Spark.post("/twitter", new Route() {
    		@Override
    		public Object handle(Request request, Response response) {
    			String data = request.body();
    			
    			JSONObject obj = new JSONObject(data);
    			textT = obj.getString("text");
    			latitudeT = obj.getDouble("lat");
    			longitudeT = obj.getDouble("lng");
    			urlT = obj.getString("urlt");
    			idT = obj.getString("id");
    				
    			
    		return  "OK";
    		}
    		});

	}

}
