
public class GeslTeam {
	
	private String id;
	
	private String teamName;
	
	private String week14Points;
	
	private String week15Points;

	
	private Integer week14Wins = 0;
	
	private Integer week14Losses = 0;
	
	private Integer week14Ties = 0;
	
	private Integer week15Wins = 0;
	
	private Integer week15Losses = 0;
	
	private Integer week15Ties = 0;
	
	
	public GeslTeam(String teamName, String id) {
		this.teamName = teamName;
		this.id = id;
	}
	
	public void setWeek14Points(String week14Points) {
		this.week14Points = week14Points;
	}
	
	public void setWeek15Points(String week15Points) {
		this.week15Points = week15Points;
	}
	
	public String getTeamName() {
		return this.teamName;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getWeek14Points() {
		return this.week14Points;
	}
	
	public String getWeek15Points() {
		return this.week15Points;
	}
	
	public String getTotalPoints() {
		
		
		if(this.week15Points == null) {
			return this.week14Points;
		}
		
		else {
			Float week15Pts = new Float(this.week15Points);
			Float week14Pts = new Float(this.week14Points);
			Float totalPoints = week14Pts + week15Pts;
			return  totalPoints.toString();
		}
		

	}
	
	public void addWin(String week) {
		if (week.contains("14")) {
			this.week14Wins +=1;
		}
		
		if (week.contains("15")) {
			this.week15Wins +=1;
		}
		
	}
	
	public void addLoss(String week) {
		if (week.contains("14")) {
			this.week14Losses +=1;
		}
		
		if (week.contains("15")) {
			this.week15Losses +=1;
		}
		
	}
	
	public void addTies(String week) {
		if (week.contains("14")) {
			this.week14Ties +=1;
		}
		
		if (week.contains("15")) {
			this.week15Ties +=1;
		}
		
	}
	
	public String getWeek14Record() {
		if (!(this.week14Ties == 0)) {
			
			return week14Wins.toString() + "-" + week14Losses.toString() + week14Ties.toString();
		}
		
		else {
			return week14Wins.toString() + "-" + week14Losses.toString();
		}
	}

	public String getWeek15Record() {
		if (!(this.week15Ties == 0)) {
			
			return week15Wins.toString() + "-" + week15Losses.toString() + week15Ties.toString();
		}
		
		else {
			return week15Wins.toString() + "-" + week15Losses.toString();
		}
	}
	
	public String getTotalRecord() {
		Integer totalWins = week14Wins + week15Wins;
		Integer totalLosses = week14Losses + week15Losses;
		Integer totalTies = week14Ties + week15Ties;
		
		if (!(totalTies == 0)) {
			
			return totalWins.toString() + "-" + totalLosses.toString() + totalTies.toString();
		}
		
		else {
			return totalWins.toString() + "-" + totalLosses.toString();
		}
	}


}
