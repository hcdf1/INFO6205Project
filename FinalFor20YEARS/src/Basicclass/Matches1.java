package Basicclass;

public class Matches1 {
	public int round;
	public int dateyear;
	
	public Teams1 homeTeam;
	public Teams1 AwayTeam;
	public int homegoal;
	public int awaygoal;
	public int win;
	
    
    public Matches1()
    {
    	
    }
    public int getdateyear() {
		return dateyear;
	}


	public void setyear(int dateyear) {
		this.dateyear = dateyear;
	}

	public int getRound() {
		return round;
	}


	public void setRound(int round) {
		this.round = round;
	}


	public String getHomeTeamNo() {
		return homeTeam.teamName;
	}


	public void setHomeTeamNo(Teams1 homeTeam) {
		this.homeTeam = homeTeam;
	}


	public String getAwayTeamNo() {
		return AwayTeam.teamName;
	}


	public void setAwayTeamNo(Teams1 awayTeamNo) {
		AwayTeam = awayTeamNo;
	}


	public int getHomegoal() {
		return homegoal;
	}


	public void setHomegoal(int homegoal) {
		this.homegoal = homegoal;
	}


	public int getAwaygoal() {
		return awaygoal;
	}


	public void setAwaygoal(int awaygoal) {
		this.awaygoal = awaygoal;
	}


	public int getWin() {
		return win;
	}


	public void setWin(int win) {
		this.win = win;
	}
    
	

}
