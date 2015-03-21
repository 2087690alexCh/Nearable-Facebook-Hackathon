import java.util.Set;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import org.json.*;

public class Main {

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
    			String src = obj.getString("src");
    			Double latitude = obj.getDouble("lat");
    			Double longitude = obj.getDouble("lng");
    			String urlInstagram = obj.getString("urli");
    			String id = obj.getString("id");
    							  
    			
    			System.out.println(src);
    			System.out.println(latitude);
    			System.out.println(longitude);
    			System.out.println(urlInstagram);
    			System.out.println(id);
    			
    		return  "hi";
    		}
    		});
		
		Spark.post("/twitter", new Route() {
    		@Override
    		public Object handle(Request request, Response response) {
    			String data = request.body();
    			
    			JSONObject obj = new JSONObject(data);
    			String text = obj.getString("text");
    			Double latitude = obj.getDouble("lat");
    			Double longitude = obj.getDouble("lng");
    			String urlInstagram= obj.getString("urlt");
    			String id = obj.getString("id");
    							  
    			
    			System.out.println(text);
    			System.out.println(latitude);
    			System.out.println(longitude);
    			System.out.println(urlInstagram);
    			System.out.println(id);
    			
    		return  "hi";
    		}
    		});
		
//		Spark.post("/testPost", new Route() {
//            @Override
//            public Object handle(Request request, Response response) {
//            	//get data
//                String data = request.queryParams("article-title");
//                System.out.println(data);
// 
//                Article article = new Article(title, summary, content, HelloSpark.articles.size() + 1);
// 
//                HelloSpark.articles.addFirst(article);
// 
//                response.status(201);
//                response.redirect("/");
//                return "";
//            }
//        });

	}

}
