
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;



public class tweet {

	public static void  main(String args[]) throws TwitterException, InterruptedException ,ArrayIndexOutOfBoundsException, IOException,NullPointerException{
	//creation of file to hold all tweet related info
		FileWriter fstream = new FileWriter("C:\\Users\\Tarun\\TwitterAPIWorkspace\\postHHT6.txt",true);
		BufferedWriter out = new BufferedWriter(fstream);
	//creation of file for only tweet posts
		FileWriter fstream1 = new FileWriter("C:\\Users\\Tarun\\TwitterAPIWorkspace\\postTweetPost3.txt",true);
		BufferedWriter out1 = new BufferedWriter(fstream1);
		
	//	textAnalysis ta=new textAnalysis();
		
	    
		
		
		Twitter twitter=new TwitterFactory().getInstance();
		//below in brackets customer_key and customer_key_secret
		
		twitter.setOAuthConsumer("4fuupVsDdRz6MzFZf9lx8DdaF","E1BIXEpE4zTrjIdAwzDjCbeJ6eZg7nmUfHSKUrQTpd9gie1n1I");
		
	//twitter.setOAuthAccessToken(new AccessToken("3431346011-DLkcyXvUWAvxFq1xYdStoddrGdLFCtExckZxfCD","dsNHWjeiVgiwEJE2FNxoZ1jHtGDZAuswY9L5AnEWIeOqY"));
		
		RequestToken requestToken;
		try {
			requestToken = twitter.getOAuthRequestToken();
		
			System.out.println("                               place the below URl  in browser to verify your credentials   \n");
			 System.out.println("Authorization URL: \n\n\n"
					  + requestToken.getAuthorizationURL());
			 
			 AccessToken accessToken = null;

			 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 while (null == accessToken) {
				 System.out.print("Input PIN here:     ");
				  String pin;

					pin = br.readLine();
					accessToken = twitter.getOAuthAccessToken(requestToken, pin);
					}
			 System.out.println("                           the following are your token details   \n");
			 System.out.println("Access Token: " + accessToken.getToken());
			 System.out.println("Access Token Secret: "
			  + accessToken.getTokenSecret()+"\n\n\n");
			 //twitter.updateStatus("hi..  i m updating this using twitterApi health data mining");
			
			
				
				 System.out.println(" Enter the string to which the related posts are to be displayed     ");
				Scanner sc=new Scanner(System.in);
				String a=sc.nextLine();
				Query query = new Query(""); 
				String englishLang=query.getLang();

					
				//setting the keyword in a
				
				query.setQuery(""+a);
			query.setCount(5000);
			
				QueryResult qr = twitter.search(query); 
				
				
				System.out.println("                      wait your tweets are being fetching up !!!!   ");
				System.out.println("                      your tweets are                ");
				
					
				
				
				
				
				// load the driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// establish database connection
				Connection c = DriverManager
						.getConnection("jdbc:oracle:thin:system/1267@localhost:1521:XE");

				// create statement
				Statement st = c.createStatement();
				
				//fire query
				System.out.println("dooone");
				
				
//				ResultSet rs = st.executeQuery("select * from twitter2'");
//
//				while (rs.next()) {
//				if(rs.absolute(1)){
//					int r=st.executeUpdate("delete  from twitter2");
//					  
//				}else {

				  
				 		String language;	

				

				
				
				while(qr.hasNext()){ 
					qr.nextQuery(); 
				List<Status> tweets = qr.getTweets(); 
				

			    
			    
				
				for (Status t: tweets){ 
					language=new String(t.getIsoLanguageCode());

		String tweetId=new String(t.getUser().getScreenName());
		String tweetLang=new String(t.getIsoLanguageCode());
		String tweetGeo=new String(t.getUser().getLocation());
		String tweetPost=new String(t.getText());
			
	Calendar cal=Calendar.getInstance();
				int month=t.getCreatedAt().getMonth();
			String tweetMonth="";
				if(month==0){
				tweetMonth="January";	
				}else if(month==1){
					tweetMonth="Februrary";
				}else if(month==2){
					tweetMonth="March";
				}else if(month==3){
					tweetMonth="April";
				}else if(month==4){
					tweetMonth="May";
				}else if(month==5){
					tweetMonth="June";
				}else if(month==6){
					tweetMonth="July";
				}else if(month==7){
					tweetMonth="August";
				}else if(month==8){
					tweetMonth="September";
				}else if(month==9){
					tweetMonth="October";
				}else if(month==10){
					tweetMonth="November";
				}else if(month==11){
					tweetMonth="December";
				}
				
			//	System.out.println(t.getUser().getRateLimitStatus());
					
					int year=cal.get(Calendar.YEAR);
if(language.equals("en"))
{
				System.out.println(tweetPost.toLowerCase(Locale.getDefault())+"\n");
			//System.out.println(tweetMonth+","+year);
				//	out.write("$@TWEET@$"+t.getText()+"$@ID@$"+t.getId()+"$@LANGUAGE@$"+t.getIsoLanguageCode()+"$@PLACE@$"+t.getPlace()+"$@GEO@$"+t.getUser().getLocation()+"$@TIMESTAMP@$"+t.getCreatedAt()+"\n $@EOTWEET@$");
					//  out.newLine();
					out.write("$@ID@$"+tweetId.toLowerCase(Locale.getDefault())+"$@LANGUAGE@$"+tweetLang.toLowerCase(Locale.getDefault())+"$@GEO@$"+tweetGeo.toLowerCase(Locale.getDefault())+"$@TIMESTAMP@$"+t.getCreatedAt()+"$@TWEET@$"+tweetPost.toLowerCase(Locale.getDefault())+"\n $@EOTWEET@$");
					out.newLine();
					
					out1.write(""+tweetPost.toLowerCase(Locale.getDefault())+"\n");
					out1.newLine();

					st.executeUpdate("insert into twittergeo2 values('"+tweetId.toLowerCase(Locale.getDefault())+"','"+tweetLang.toLowerCase(Locale.getDefault())+"','"+tweetGeo.toLowerCase(Locale.getDefault())+"','"+tweetMonth+"','"+year+"','"+a+"')");
			//st.executeUpdate("insert into twitter3 values('"+tweetPost.toLowerCase(Locale.getDefault())+"')");

						 
}			//st.executeUpdate("insert into twittergeo values('"+t.getUser().getScreenName()+"','"+ t.getIsoLanguageCode()+"','"+ t.getUser().getLocation()+"','"+t.getCreatedAt()+"','"+t.getText()+"')");

						 
					
					 
				}
				out.flush();
				out1.flush();
				break;
} System.out.println("complete");
				
		
				
				try{ Thread.sleep(1000*60*15); 
				}catch(Exception e) {
					
				} finally{
					br.close();
					out.close();
				c.close();
				
				
				}
			//start of file read and write for datasets
				//textAnalysis ta=new textAnalysis();
			//	ta.func1();
			
		}
		 catch (TwitterException e1) {
		
			e1.printStackTrace();
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}
		
		catch (ClassNotFoundException e) {
			System.out.println("Driver is not available " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL Exception " + e.getMessage());
			e.printStackTrace();
		}
	
//	

	}
}

		
		 
			


	 //new tweet().start();// run the Twitter client
		 	    
	

	
	

