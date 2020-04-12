package EFL1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import Basicclass.Matches1;
import Basicclass.Teams1;

public class Method1 {
	

    public static final int numTeams = 43;
    public static Teams1[] teams = new Teams1[numTeams];
    public static final int numMatchs = 7600;
    public static Matches1[] matches = new Matches1[numMatchs]; 
    public static double [][]input= new double[288][6];
    public static double[][] output =new double[288][2];
    public static double[][] outputcompressed =new double[288][2];
    public static int[] points = new int[20];
    public static HashMap<Integer,Integer> total=new HashMap<Integer,Integer>();
    
    public static Map<String,Teams1> teamsmap=new HashMap<String,Teams1>();
//    public static int bifen[][]=new int [40][2];
//    public static int success=100;
//    
    
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		readteams();
//		for(int i=0;i<41;i++) {
//		//System.out.println(teams[0]);
//			System.out.println(teams[i].teamNo+" " +teams[i].teamName);
//		}
		
		
		readmatch();
		
//		for(int i=0;i<7508;i++) {
//			//System.out.println(teams[0]);
//				System.out.println(matches[i].getdateyear()+" " +matches[i].getHomeTeamNo()+" " +matches[i].getAwayTeamNo());
//			}
		
		
		for(int k=0;k<100;k++) {
		
	    //HashMap<String,HashMap<String,Integer>> res= calculatewithbp(matches);
	    
		//HashMap<String,HashMap<String,Integer>> res= calculatewithhomeandawaynobp(matches);
		
		HashMap<String,HashMap<String,Integer>> res= calculatewithweight(matches);
		
		for (String key : res.keySet()) {

			HashMap<String,Integer>  pointsmap= res.get(key);

			//System.out.println("Key = " + key[0]+key[1] + ", Value = " + value);

		}
		for(int i=6000;i<7508;i++) {
			if(matches[i].dateyear==2019) {
				//System.out.println(i);
				int countmatches=0;
				
				input(matches[i],countmatches);
				renewpointsandrank(matches[i]);

			}
		}
		
        for(int i=0;i<288;i++) {
        	outputcompressed[i][0]=output[i][0]/6;
        	outputcompressed[i][1]=output[i][1]/6;
        	
        }

		
		
       result(res);
       
		}
//       points[0] += teams[0].points;
//       points[1] += teams[1].points;
//       points[2] += teams[5].points;
//       points[3] += teams[7].points;
//       points[4] += teams[8].points;
//       points[5] += teams[10].points;
//       points[6] += teams[12].points;
//       points[7] += teams[14].points;
//       points[8] += teams[20].points;
//       points[9] += teams[21].points;
//       points[10] += teams[22].points;
//       points[11] += teams[23].points;
//       points[12] += teams[25].points;
//       points[13] += teams[26].points;
//       points[14] += teams[30].points;
//       points[15] += teams[31].points;
//       points[16] += teams[35].points;
//       points[17] += teams[36].points;
//       points[18] += teams[38].points;
//       points[19] += teams[40].points;
       
       
       
       
       
//       if(bifen[0][0]<bifen[0][1])success++;
//       if(bifen[1][0]<bifen[1][1])success++;
//       if(bifen[2][0]>bifen[2][1])success++;
//       if(bifen[3][0]<bifen[3][1])success++;
//       if(bifen[4][0]==bifen[4][1])success++;
//       if(bifen[5][0]>bifen[5][1])success++;
//       
//       if(bifen[6][0]<bifen[6][1])success++;
//       if(bifen[7][0]==bifen[7][1])success++;
//       if(bifen[8][0]==bifen[8][1])success++;
//       if(bifen[9][0]<bifen[9][1])success++;
//       
//       if(bifen[10][0]>bifen[10][1])success++;
//       if(bifen[11][0]>bifen[11][1])success++;
//       if(bifen[12][0]>bifen[12][1])success++;
//       if(bifen[13][0]>bifen[13][1])success++;
//       if(bifen[14][0]<bifen[14][1])success++;
//       if(bifen[15][0]<bifen[15][1])success++;
//       
//       if(bifen[16][0]>bifen[16][1])success++;
//       if(bifen[17][0]==bifen[17][1])success++;
//       if(bifen[18][0]==bifen[18][1])success++;
//       if(bifen[19][0]>bifen[19][1])success++;
//       
//       
//       
//       if(bifen[20][0]>bifen[20][1])success++;
//       if(bifen[21][0]<bifen[21][1])success++;
//       if(bifen[22][0]==bifen[22][1])success++;
//       if(bifen[23][0]>bifen[23][1])success++;
//       if(bifen[24][0]==bifen[24][1])success++;
//       if(bifen[25][0]<bifen[25][1])success++;
//       
//       if(bifen[26][0]==bifen[26][1])success++;
//       if(bifen[27][0]>bifen[27][1])success++;
//       if(bifen[28][0]<bifen[28][1])success++;
//       if(bifen[29][0]==bifen[29][1])success++;
//       
//       if(bifen[30][0]>bifen[30][1])success++;
//       if(bifen[31][0]<bifen[31][1])success++;
//       if(bifen[32][0]<bifen[32][1])success++;
//       if(bifen[33][0]==bifen[33][1])success++;
//       if(bifen[34][0]==bifen[34][1])success++;
//       if(bifen[35][0]>bifen[35][1])success++;
//       
//       if(bifen[36][0]>bifen[36][1])success++;
//       if(bifen[37][0]<bifen[37][1])success++;
//       if(bifen[38][0]<bifen[38][1])success++;
//       if(bifen[39][0]==bifen[39][1])success++;
//		}
//       System.out.println("success"+success);
		
//		Arrays.sort(teams, new Comparator<Team>() {
//			@Override
//			public int compare(Team arg0, Team arg1) {
//				// TODO Auto-generated method stub
//				int i = arg1.getPoints()-arg0.getPoints();
//				if(i == 0) {
//					i = arg1.getGoaldifference()-arg0.getGoaldifference();
//				}
//				if(i == 0) {
//					i = arg1.getTotalgoal()-arg0.getTotalgoal();
//				}
//				return i;
//			}  
//        });  
		for(Teams1 t:teams) {
			
			//System.out.println(t.teamName+" "+t.points);
			if(total.containsKey(t.teamNo)) {
				total.put(t.teamNo, total.get(t.teamNo)+t.getPoints());
			}
			else {
				total.put(t.teamNo, t.points);
			}
		}
       
//		}
//		}
//		 
//		for(Integer in: total.keySet()) {
//			System.out.println(teams[in].teamName +" "+total.get(in)/100);
		
		Arrays.sort(teams, new Comparator<Teams1>() {
		@Override
		public int compare(Teams1 arg0, Teams1 arg1) {
			// TODO Auto-generated method stub
			int i = arg1.getPoints()-arg0.getPoints();
			if(i == 0) {
				i = arg1.getGoaldifference()-arg0.getGoaldifference();
			}
			if(i == 0) {
				i = arg1.getTotalgoal()-arg0.getTotalgoal();
			}
			return i;
		}  
    });  
             for(Teams1 t:teams) {
			
			System.out.println(t.teamName+" "+t.points/100);
			
		}
		
	}
	public static void readteams() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cheny\\eclipse-workspace\\FinalFor20YEARS\\teams1.csv"));
		
			String line = null;
			int n =0;
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");
				
				String last = item[item.length-1];
				teams[n] = new Teams1();
				
				teams[n].teamNo = Integer.parseInt(item[0]);
				
				teams[n].teamName = item[1];
				teamsmap.put(item[1], teams[n]);
				n++;
				
			}
		} catch (Exception e) {
			e.printStackTrace();

	}
	}
	public static void readmatch() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cheny\\eclipse-workspace\\FinalFor20YEARS\\2000-2001.csv"));
		reader.readLine();
		String line = null;
		int n =0;
		while((line=reader.readLine())!=null){
			String item[] = line.split(",");
			String last = item[item.length-1];
			matches[n] = new Matches1();
			//matches[n].round = Integer.parseInt(item[0]);
			//System.out.println(item[1]);
			matches[n].setyear(Integer.parseInt(item[1]));
			matches[n].homeTeam= teamsmap.get(item[2]);
			matches[n].AwayTeam= teamsmap.get(item[3]);
			matches[n].setHomegoal(Integer.parseInt(item[4]));
			
			matches[n].setAwaygoal(Integer.parseInt(item[5]));
			n++;
		}
		
	}
	public static HashMap<String,HashMap<String,Integer>> calculatewithweight(Matches1[] matches ){
		HashMap<String,HashMap<String,Integer>> m=new HashMap<String,HashMap<String,Integer>>();
		for(int i=0;i<7508;i++) {
			
			String n= String.valueOf(matches[i].homeTeam.teamNo)+" "+String.valueOf(matches[i].AwayTeam.teamNo);
			String nreverse= String.valueOf(matches[i].AwayTeam.teamNo)+" "+String.valueOf(matches[i].homeTeam.teamNo);
			
			String s=matches[i].getHomegoal()+" "+matches[i].getAwaygoal();
			String sreverse=matches[i].getAwaygoal()+" "+matches[i].getHomegoal();
			
			int year=matches[i].getdateyear();
			int weight=1;
			if(year<2010) {
				weight=2;
			}
			else if(year>=2010&&year<2015) {
				weight=4;
			}
			else {
				weight=6;
			}
			if(!m.containsKey(n)) {
				
			    HashMap<String,Integer> map=new HashMap<String,Integer>();
				map.put(s, weight);
				m.put(n, map);
				
			}
			else {
				HashMap<String,Integer>  map=m.get(n);
				
				if(map.containsKey(s)) {
					map.put(s,map.get(s)+weight);
					m.put(n, map);
				}
				else {
					map.put(s, weight);
					m.put(n, map);
				}
				
				
			}
            if(!m.containsKey(nreverse)) {
				
			    HashMap<String,Integer> map=new HashMap<String,Integer>();
				map.put(sreverse, weight/2);
				m.put(nreverse, map);
				
			}
			else {
				HashMap<String,Integer>  map=m.get(nreverse);
				
				if(map.containsKey(sreverse)) {
					map.put(sreverse,map.get(sreverse)+weight/2);
					m.put(nreverse, map);
				}
				else {
					map.put(sreverse, weight/2);
					m.put(nreverse, map);
				}
				
				
			}
			
		}
//		for (String key : m.keySet()) {
//
//			HashMap<String,Integer>  pointsmap= m.get(key);
//
//			if(pointsmap.containsKey("1 0")) {
//				pointsmap.put("1 0", pointsmap.get("1 0")+1);
//			}
//			else {
//				pointsmap.put("1 0", 1);
//			}
//			
//			if(pointsmap.containsKey("0 0")) {
//				pointsmap.put("0 0", pointsmap.get("0 0")+1);
//			}
//			else {
//				pointsmap.put("0 0", 1);
//			}
//			if(pointsmap.containsKey("0 1")) {
//				pointsmap.put("0 1", pointsmap.get("0 1")+1);
//			}
//			else {
//				pointsmap.put("0 1", 1);
//			}
//
//		}
		return m;
		
	}
	public static HashMap<String,HashMap<String,Integer>> calculatewithhomeandawaynobp(Matches1[] matches ){
		HashMap<String,HashMap<String,Integer>> m=new HashMap<String,HashMap<String,Integer>>();
		for(int i=0;i<7508;i++) {
			
			String n= String.valueOf(matches[i].homeTeam.teamNo)+" "+String.valueOf(matches[i].AwayTeam.teamNo);
			String nreverse= String.valueOf(matches[i].AwayTeam.teamNo)+" "+String.valueOf(matches[i].homeTeam.teamNo);
			
			String s=matches[i].getHomegoal()+" "+matches[i].getAwaygoal();
			String sreverse=matches[i].getAwaygoal()+" "+matches[i].getHomegoal();
			
			int year=matches[i].getdateyear();
			int weight=1;
			if(year<2010) {
				weight=2;
			}
			else if(year>=2010&&year<2015) {
				weight=4;
			}
			else {
				weight=6;
			}
			if(!m.containsKey(n)) {
				
			    HashMap<String,Integer> map=new HashMap<String,Integer>();
				map.put(s, weight);
				m.put(n, map);
				
			}
			else {
				HashMap<String,Integer>  map=m.get(n);
				
				if(map.containsKey(s)) {
					map.put(s,map.get(s)+weight);
					m.put(n, map);
				}
				else {
					map.put(s, weight);
					m.put(n, map);
				}
				
				
			}
            
		}
		for (String key : m.keySet()) {

			HashMap<String,Integer>  pointsmap= m.get(key);

			if(pointsmap.containsKey("1 0")) {
				pointsmap.put("1 0", pointsmap.get("1 0")+1);
			}
			else {
				pointsmap.put("1 0", 1);
			}
			
			if(pointsmap.containsKey("0 0")) {
				pointsmap.put("0 0", pointsmap.get("0 0")+1);
			}
			else {
				pointsmap.put("0 0", 1);
			}
			if(pointsmap.containsKey("0 1")) {
				pointsmap.put("0 1", pointsmap.get("0 1")+1);
			}
			else {
				pointsmap.put("0 1", 1);
			}

		}
		return m;
		
	}
	public static HashMap<String,HashMap<String,Integer>> calculatewithbp(Matches1[] matches ){
		HashMap<String,HashMap<String,Integer>> m=new HashMap<String,HashMap<String,Integer>>();
		for(int i=0;i<7508;i++) {
			
			String n= String.valueOf(matches[i].homeTeam.teamNo)+" "+String.valueOf(matches[i].AwayTeam.teamNo);
			String nreverse= String.valueOf(matches[i].AwayTeam.teamNo)+" "+String.valueOf(matches[i].homeTeam.teamNo);
			
			String s=matches[i].getHomegoal()+" "+matches[i].getAwaygoal();
			String sreverse=matches[i].getAwaygoal()+" "+matches[i].getHomegoal();
			
			int year=matches[i].getdateyear();
			int weight=1;
			if(year<2010) {
				weight=2;
			}
			else if(year>=2010&&year<2015) {
				weight=4;
			}
			else {
				weight=6;
			}
//			if(!m.containsKey(n)) {
//				
//			    HashMap<String,Integer> map=new HashMap<String,Integer>();
//				map.put(s, weight);
//				m.put(n, map);
//				
//			}
//			else {
//				HashMap<String,Integer>  map=m.get(n);
//				
//				if(map.containsKey(s)) {
//					map.put(s,map.get(s)+weight);
//					m.put(n, map);
//				}
//				else {
//					map.put(s, weight);
//					m.put(n, map);
//				}
//				
//				
//			}
            
		}
		
		return m;
		
	}
	public static ArrayList<Integer>  predict1(int a,int b,double[][] input,double[][] ideal) {
	    ArrayList<Integer> res=new ArrayList<Integer> ();
		BasicNetwork  nw=new BasicNetwork();
		nw.addLayer(new BasicLayer(null,true,6));
		nw.addLayer(new BasicLayer(new ActivationSigmoid(),true,2));
		nw.addLayer(new BasicLayer(new ActivationSigmoid(),false,2));
		nw.getStructure().finalizeStructure();
		nw.reset();
		MLDataSet ts=new BasicMLDataSet(input,ideal);
	
		MLTrain t=new ResilientPropagation (nw,ts);
		
		int epoch=1;
		do {
			t.iteration();
			//System.out.println(""+epoch+""+t.getError());
			epoch++;
		}while(t.getError()>0.2);
		double aa=teams[a].getRank();
		double bb=teams[b].getRank();
		MLData o1=nw.compute(new BasicMLData (new double[] {a,b,aa,b,teams[a].Goaldifference,teams[b].getGoaldifference()}));
		
		int homegoa=(int)(o1.getData()[0]*6);
		int awaygoa=(int)(o1.getData()[1]*6);
	
		
		if(homegoa>awaygoa) {
			res.add(1);
		}
		else if(homegoa==awaygoa) {
			res.add(0);
		}
		else {
			res.add(-1);
		}
		
		res.add(homegoa);
		res.add(awaygoa);
		return res;
	}
	
	public static void renewpointsandrank(Matches1 m) {
		
		
		if(m.homegoal>m.awaygoal) {
			if(teams[m.homeTeam.teamNo].points == teams[m.AwayTeam.teamNo].points) {
			 teams[m.homeTeam.teamNo].rank +=5;
			 teams[m.AwayTeam.teamNo].rank -=5;
			}else if(teams[m.homeTeam.teamNo].points < teams[m.AwayTeam.teamNo].points) {
				 teams[m.homeTeam.teamNo].rank += 7;
				 teams[m.AwayTeam.teamNo].rank -= 5;
			}else {
				 teams[m.homeTeam.teamNo].rank += 5;
				 teams[m.AwayTeam.teamNo].rank -= 7;
			}
			 teams[m.homeTeam.teamNo].points +=3;
			
		}else if(m.homegoal<m.awaygoal){
			if(teams[m.homeTeam.teamNo].points == teams[m.AwayTeam.teamNo].points) {
			 teams[m.homeTeam.teamNo].rank -=7;
			 //teams[m.homeTeamNo].points +=1;
			 teams[m.AwayTeam.teamNo].rank +=7;
			}else if(teams[m.homeTeam.teamNo].points < teams[m.AwayTeam.teamNo].points) {
				teams[m.homeTeam.teamNo].rank -= 4;
				 teams[m.AwayTeam.teamNo].rank += 7;
			}else {
				 teams[m.homeTeam.teamNo].rank -= 7;
				 teams[m.AwayTeam.teamNo].rank += 4;
			}
			 teams[m.AwayTeam.teamNo].points +=3;
		}else {
			if(teams[m.homeTeam.teamNo].points == teams[m.homeTeam.teamNo].points) {
			 teams[m.homeTeam.teamNo].rank -=1;
			 teams[m.AwayTeam.teamNo].rank +=1;
			}else if(teams[m.homeTeam.teamNo].points < teams[m.AwayTeam.teamNo].points) {
				teams[m.homeTeam.teamNo].rank +=0;
				 teams[m.AwayTeam.teamNo].rank -=0;
			}else {
				teams[m.homeTeam.teamNo].rank -=3;
				 teams[m.AwayTeam.teamNo].rank +=2;
			}
			teams[m.AwayTeam.teamNo].points +=1;
			teams[m.homeTeam.teamNo].points +=1;
		}
		 teams[m.homeTeam.teamNo].Goaldifference = teams[m.homeTeam.teamNo].Goaldifference+(m.homegoal-m.awaygoal);
		 teams[m.AwayTeam.teamNo].Goaldifference = teams[m.AwayTeam.teamNo].Goaldifference+(m.awaygoal-m.homegoal);
		}
	
	public static void input(Matches1 m,int n) {
			
			
			input[n][0]= teams[m.homeTeam.teamNo].points;
			//System.out.print(m.getHomeTeamNo());
			input[n][1] =teams[m.AwayTeam.teamNo].points;
			input[n][2] =teams[m.homeTeam.teamNo].rank;
			input[n][3] = teams[m.AwayTeam.teamNo].rank;
			input[n][4]=teams[m.homeTeam.teamNo].Goaldifference;
			input[n][5] = teams[m.AwayTeam.teamNo].Goaldifference;
			output[n][0] = m.getHomegoal();
			output[n][1] =m.getAwaygoal();
		}
	
	public static void result(HashMap<String,HashMap<String,Integer>> res) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cheny\\eclipse-workspace\\FinalFor20YEARS\\matchesneedpredict.csv"));
			//reader.readLine();
			String line = null;
            int cou=0;
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");
				String last = item[item.length-1];
				
				String n=item[1]+" "+item[2];
				//System.out.println(n);
				System.out.println(teams[Integer.parseInt(item[1])].teamName+ " :"+ teams[Integer.parseInt(item[2])].teamName);
				if(res.containsKey(n)){
					HashMap<String,Integer> pointsmap=res.get(n);
					
					if(!pointsmap.isEmpty()) {
						
						Collection values = pointsmap.values();    
						int totalmat=0;
						int totalpo=0;
					    for (Object  frequency: values) {
					        totalmat+=(int)frequency;
					    }
					    int r1 = new Random().nextInt(totalmat);
					    String currentpoint="";
					    
					    for(String s: pointsmap.keySet()) {
					    	if((r1-pointsmap.get(s))<=0) {
					    		currentpoint=s;
					    		break;
					    	}
					    	r1-=pointsmap.get(s);
					    }
					    System.out.println(currentpoint);
					    String[] strarray=currentpoint.split(" ");
					  
					       int 	a = Integer.parseInt(strarray[0]);
					       int 	b = Integer.parseInt(strarray[1]);
					    
					       
					       
					       
//					       bifen[cou][0]=a;
//					       bifen[cou][1]=b;
					       cou++;
					    		   
					    		   
						System.out.println(a+" "+b);
						if(a>b) {
							teams[Integer.parseInt(item[1])].points+=3;
		
						}
						else if (a==b){
							teams[Integer.parseInt(item[1])].points+=1;
							teams[Integer.parseInt(item[2])].points+=1;
						}
						else {
							teams[Integer.parseInt(item[2])].points+=3;
						}
					
					  }
				 }
				else {
					
					ArrayList<Integer > l=predict1(Integer.parseInt(item[1]),Integer.parseInt(item[2]),input,outputcompressed);
					  int 	a = l.get(1);
				      int 	b = l.get(2);
				      
//				      
//				      bifen[cou][0]=a;
//				       bifen[cou][1]=b;
				       cou++;
				      
				      
				      System.out.println(a+" "+b);
						if(a>b) {
							teams[Integer.parseInt(item[1])].points+=3;
		
						}
						else if (a==b){
							teams[Integer.parseInt(item[1])].points+=1;
							teams[Integer.parseInt(item[2])].points+=1;
						}
						else {
							teams[Integer.parseInt(item[2])].points+=3;
						}
				}
				
				
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();

	     }
		
	}
	
}
