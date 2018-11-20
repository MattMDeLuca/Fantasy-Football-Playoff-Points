import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class PlayoffPointsUiController {


	    @FXML
	    private AnchorPane anchorPane;

	    @FXML
	    private TableColumn<GeslTeam, String> totalPoints;

	    @FXML
	    private TableView<GeslTeam> tableView;
	    
	    @FXML
	    private TableColumn<GeslTeam, String> team;

	    @FXML
	    private TableColumn<GeslTeam, String> week14Points;

	    @FXML
	    private TableColumn<GeslTeam, String> week15Points;
	    
	    @FXML
	    private TableColumn<GeslTeam, String> week14Record;

	    @FXML
	    private TableColumn<GeslTeam, String> week15Record;
	    
	    @FXML
	    private TableColumn<GeslTeam, String> totalRecord;
	    
	    @FXML
	    private TextField refreshedTextfield;
	    
	    private String leagueId = "";
	    
	    private String liveScoringURL = "http://www73.myfantasyleague.com/2018/export?TYPE=liveScoring&L=" + leagueId + "&APIKEY=&W=";
	    
	    
	    
	    ObservableList<GeslTeam> playoffTeams;
	    
	    int test;
	    
	    void updateScoresForWeek15() {
	    		Timer timer = new Timer();
	    		TimerTask checkScores = new TimerTask() {
	    			@Override
	    			public void run() {

						try {
							test +=1;
							liveScoringURL = liveScoringURL + "11";
							URL liveScoringUrl = new URL(liveScoringURL);
							URLConnection urlc = liveScoringUrl.openConnection();
		    					InputStream xmlFile = urlc.getInputStream();
		    					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    					Document doc = dBuilder.parse(xmlFile);
		    					
		    					NodeList nList = doc.getDocumentElement().getElementsByTagName("franchise");
		    					
		    					for (int i = 0; i < nList.getLength(); i++) {
		    						Node node = nList.item(i);
		    						NamedNodeMap attributes = node.getAttributes();
		    						String id = attributes.getNamedItem("id").getTextContent();
		    						for (GeslTeam g: playoffTeams) {
		    							if (g.getId().contains(id)) {
		    								g.setWeek15Points(attributes.getNamedItem("score").getTextContent());
		    							}
		    							
		    						}
		    					}
		    					
		    					for (GeslTeam g1: playoffTeams) {
		    						Float week15Score1 = new Float(g1.getWeek15Points());
		    						for (GeslTeam g2: playoffTeams) {
		    							if (g1.getId().contains(g2.getId())) {continue;}
		    							Float week15Score2 = new Float(g2.getWeek15Points());
		    							if(week15Score1 > week15Score2) {g1.addWin("15");}
		    							if(week15Score1 < week15Score2) {g1.addLoss("15");}
		    							if(week15Score1 == week15Score2) {g1.addTies("15");}
		    						}
		    					}

		    					LocalDateTime time = LocalDateTime.now();
		    					
		    					refreshedTextfield.setText("Last updated: " + time.toLocalDate() + " at " + time.toLocalTime().truncatedTo(ChronoUnit.MINUTES).minusHours(12));
		    					
		    					
						} catch (IOException | ParserConfigurationException | SAXException e) {

							e.printStackTrace();
						}
						
	    			}
	    	};
	    		timer.schedule(checkScores, 0, 60000);
	    		
	    }

	    @FXML
	    void initialize() throws IOException, ParserConfigurationException, SAXException {
	    	liveScoringURL = liveScoringURL + "11";
			URL liveScoringUrl = new URL(liveScoringURL);
			URLConnection urlc = liveScoringUrl.openConnection();
			InputStream xmlFile = urlc.getInputStream();
	    		
			playoffTeams = FXCollections.observableArrayList();
			
			GeslTeam matt = new GeslTeam("Matt D's Team", "0002");
			playoffTeams.add(matt);
			GeslTeam ben = new GeslTeam("Ben's Team", "0005");
			playoffTeams.add(ben);
			GeslTeam nick = new GeslTeam("Nick's Team", "0004");
			playoffTeams.add(nick);
			GeslTeam tyler = new GeslTeam("Tyler's Team", "0008");
			playoffTeams.add(tyler);
			GeslTeam conor = new GeslTeam("Conor's Team", "0010");
			playoffTeams.add(conor);
			GeslTeam chris = new GeslTeam("Chris's Team", "0011");
			playoffTeams.add(chris);
	
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			NodeList nList = doc.getDocumentElement().getElementsByTagName("franchise");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				NamedNodeMap attributes = node.getAttributes();
				String id = attributes.getNamedItem("id").getTextContent();
				for (GeslTeam g: playoffTeams) {
					if (g.getId().contains(id)) {
						g.setWeek14Points(attributes.getNamedItem("score").getTextContent());
					}
					
				}
			}
			
			for (GeslTeam g1: playoffTeams) {
				Float week14Score1 = new Float(g1.getWeek14Points());
				for (GeslTeam g2: playoffTeams) {
					if (g1.getId().contains(g2.getId())) {continue;}
					Float week14Score2 = new Float(g2.getWeek14Points());
					if(week14Score1 > week14Score2) {g1.addWin("14");}
					if(week14Score1 < week14Score2) {g1.addLoss("14");}
					if(week14Score1 == week14Score2) {g1.addTies("14");}
				}
			}
	     
			team.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTeamName()));
			week14Points.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWeek14Points()));
			week14Record.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWeek14Record()));
			week15Points.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWeek15Points()));
			week15Record.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWeek15Record()));
			totalRecord.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTotalRecord()));
			totalPoints.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTotalPoints()));
			totalRecord.setSortType(TableColumn.SortType.DESCENDING);
			tableView.setItems(playoffTeams);
			
			this.updateScoresForWeek15();
			
	    }
}

	

