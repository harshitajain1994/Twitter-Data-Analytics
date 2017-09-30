
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
//import com.teamdev.jxbrowser.chromium.BrowserFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class a {
	 public static final int MIN_ZOOM = 0;
	    public static final int MAX_ZOOM = 21;

	    /**
	     * In map.html file default zoom value is set to 4.
	     */
	    private static int zoomValue = 4;

	    public static void main(String[] args) {
	        final Browser browser = new Browser();
	        BrowserView browserView = new BrowserView(browser);

	        JButton zoomInButton = new JButton("Zoom In");
	        zoomInButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (zoomValue < MAX_ZOOM) {
	                    browser.executeJavaScript("map.setZoom(" + ++zoomValue + ")");
	                }
	            }
	        });

	        JButton zoomOutButton = new JButton("Zoom Out");
	        zoomOutButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (zoomValue > MIN_ZOOM) {
	                    browser.executeJavaScript("map.setZoom(" + --zoomValue + ")");
	                }
	            }
	        });

	        JButton setMarkerButton = new JButton("Set Marker");
	        setMarkerButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	            	double []latitude ={51.5072,41.0136,39.7392,22.7000,22.5667,77.0167,52.5167,47.0000,8.0000};
	            	double []longitude ={0.1275,28.9550,104.9903,75.9000,88.3667,38.8833,13.3833,2.0000,10.0000};
	            	String []countryName={"LONDON: 9"," ISTANBUL TURKEY: 1", "DENVER COLORADO:2", "INDORE INDIA: 2","KOLKATA INDIA: 6","UNITED STATES: 8","GERMANY : 5","FRANCE : 1","NIGERIA:1"};
	            	for(int i=0;i<latitude.length;i++){
	            		 browser.executeJavaScript("var myLatlng = new google.maps.LatLng("+latitude[i]+","+longitude[i]+");\n" +
	 	                        "var marker = new google.maps.Marker({\n" +
	 	                        "    position: myLatlng,\n" +
	 	                        "    map: map,\n" +
	 	                        "    title: '"+countryName[i]+"'\n" +
	 	                        "});");// this code will get latlang valuse,set marker and brower object contains it.
	            		
	            		 
	            		 
	            		 browser.executeJavaScript("var infowindow = new google.maps.InfoWindow({\n"+
	 	                        "content: State Legislature, Gujarat State" +
	            				 "});");

	 	            		
	            	}
	            	
//	                browser.executeJavaScript("var myLatlng = new google.maps.LatLng(48.4431727,23.0488126);\n" +
//	                        "var marker = new google.maps.Marker({\n" +
//	                        "    position: myLatlng,\n" +
//	                        "    map: map,\n" +
//	                        "    title: 'Hello World!'\n" +
//	                        "});");// this code will get latlang valuse,set marker and brower object contains it.
//	            	
	            }
	        });

	        JPanel toolBar = new JPanel();
	        toolBar.add(zoomInButton);
	        toolBar.add(zoomOutButton);
	        toolBar.add(setMarkerButton);

	        JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        frame.add(browserView, BorderLayout.CENTER);
	        frame.add(toolBar, BorderLayout.SOUTH);
	        frame.setSize(900, 500);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);

	        // Provide the correct full path tow the map.html file, e.g. D:\\map.html
	        browser.loadURL("D:\\map.html");// to get map in that div whose id is map canvas.
	    }
}
