package Basicclass;

public class Teams2 {

	
	
	public String teamName;
	public int teamNo;
	public int rank;
	public int points;
	public int Goaldifference;
	public int totalgoal;
	public int totalloss;
	public double avggoal;
	public double avgloss;
	public int played;
	
	
	
	public int getPlayed() {
		return played;
	}
	public void setPlayed(int played) {
		this.played = played;
	}
	public int getGoaldifference() {
		return Goaldifference;
	}
	public void setGoaldifference(int goaldifference) {
		Goaldifference = goaldifference;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
    
    public double getAvggoal() {
		return avggoal;
	}
	public void setAvggoal(double avggoal) {
		this.avggoal = avggoal;
	}
	public double getAvgloss() {
		return avgloss;
	}
	public void setAvgloss(double avgloss) {
		this.avgloss = avgloss;
	}
	public int getTotalgoal() {
		return totalgoal;
	}
	public void setTotalgoal(int totalgoal) {
		this.totalgoal = totalgoal;
	}
	
	public int getTotalloss() {
		return totalloss;
	}
	public void setTotalloss(int totalloss) {
		this.totalloss = totalloss;
	}
	public Teams2()
    {
    	this.rank = 100;
    	this.Goaldifference =0;
    	this.totalgoal = 0;
    	this.totalloss = 0;
    	this.avggoal = 0;
    	this.avgloss = 0;
        this.played = 0;
    	
    }
    
    
	
	
}
