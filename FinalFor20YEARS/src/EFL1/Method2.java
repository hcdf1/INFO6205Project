package EFL1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import Basicclass.Matches2;
import Basicclass.Teams2;


public class Method2 {
	
	
	    public static final int numTeams = 40;
	    public static Teams2[] teams = new Teams2[numTeams];
	    public static Teams2[] trainteams = new Teams2[40];
	    public static final int numMatchs = 380;
	    public static Matches2[] matches = new Matches2[numMatchs]; 
	    public static Matches2[] trainmatches = new Matches2[6748]; 
	    public static double [][]input= new double[6748][6];
	    public static double[][] output =new double[6748][2];
	    public static double [][]input1= new double[6748][6];
	    public static double[][] output1 =new double[6748][2];
	    public static int success = 0;
	    public static int[][] points = new int[20][2];
	   
	    
	    
	    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 for(int i = 0;i<20;i++){  
	            points[i][1] = 0;
	            points[i][0] = i;
	        }  
		for(int j = 0;j<100;j++) {
		Method2 epl = new Method2();
		epl.trainread();
		epl.trainmatch();
		epl.read();


		
		epl.readmatch();

        print();

        double output2[][]=new double[360][2];
       
        double max1=0;
        double min1=10;
        double max2=0;
        double min2=10;
        for(int i=0;i<360;i++) {
        	if(output1[i][0]>max1)max1=output1[i][0];
        	if(output1[i][0]<min1)min1=output1[i][0];
        	
        	if(output1[i][1]>max2)max2=output1[i][1];
        	if(output1[i][1]<min2)min2=output1[i][1];
        	
        }
        for(int i=0;i<360;i++) {
        	output2[i][0]=(output1[i][0]-min1)/(max1-min1);
        	output2[i][1]=(output1[i][1]-min2)/(max2-min2);
        	
        }
        

        predictmatch();
        

	      for(int i = 0;i<20;i++){  
	            points[i][1] += teams[i].points;
	            points[i][0] = i;
	        }  

		}
		
		Arrays.sort(points, new Comparator<int[]>() {
			public int compare(int[] a, int[] b){
				if(a[1]==b[1]){
					return b[1] - a[1];
				}else {
					return b[1] - a[1];
				}
			}
		});

	         
		
		 for(int i = 0;i<20;i++){  
	           System.out.println(teams[points[i][0]].teamName + "  " + points[i][1]/100);
	        }  
	}

	
	public static void read() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cheny\\eclipse-workspace\\FinalFor20YEARS\\teams2.csv"));
		
			String line = null;
			int n =0;
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");
				
				String last = item[item.length-1];
				teams[n] = new Teams2();
				
				teams[n].teamNo = Integer.parseInt(item[0]);
				teams[n].teamName = item[1];
				
				n++;
				
			}
		} catch (Exception e) {
			e.printStackTrace();

	}
	}
	public static void trainread() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cheny\\eclipse-workspace\\FinalFor20YEARS\\teams2.csv"));
			
			String line = null;
			int n =0;
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");
				
				String last = item[item.length-1];
				trainteams[n] = new Teams2();
				
				trainteams[n].teamNo = Integer.parseInt(item[0]);
				trainteams[n].teamName = item[1];
				
				n++;
				
			}
		} catch (Exception e) {
			e.printStackTrace();

	}
	}
	
	public static void predictmatch() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cheny\\eclipse-workspace\\FinalFor20YEARS\\matchesneededpredict2.csv"));
			
			String line = null;
			
			int n =288;
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");
				matches[n] = new Matches2();
				
				matches[n].round = Integer.parseInt(item[0]);
				matches[n].homeTeamNo = Integer.parseInt(item[1]);
				String home = teams[matches[n].homeTeamNo].getTeamName();
				int a = matches[n].homeTeamNo;
				matches[n].AwayTeamNo = Integer.parseInt(item[2]);
				String away = teams[matches[n].AwayTeamNo].getTeamName();
				int b = matches[n].AwayTeamNo;
				
				
				 double output3[][]=new double [6748][2];
			        for(int i=0;i<6748;i++) {
			        	output3[i][0]=output1[i][0]/6;
			        	output3[i][1]=output1[i][1]/6;
			        	
			        }
			
               int c =predict(a,b);
               matches[n].win = c;

               renew1(matches[n]);
				n++;
				
			}
		} catch (Exception e) {
			e.printStackTrace();

	}
	}
	public static void input(Matches2 m,int n) {
		
		input[n][0]=  trainteams[m.homeTeamNo].rank;
		
		input[n][1] = trainteams[m.AwayTeamNo].rank;
		input[n][2] = trainteams[m.homeTeamNo].avggoal;
		input[n][3] = trainteams[m.AwayTeamNo].avgloss;
		output[n][0] = m.getHomegoal();
		output[n][1] = m.getAwaygoal();
		input1[n][0]= trainteams[m.homeTeamNo].rank;
	
		input1[n][1] = trainteams[m.AwayTeamNo].rank;
		input1[n][2] = trainteams[m.homeTeamNo].avggoal;
		input1[n][3] =trainteams[m.homeTeamNo].avgloss;
		input1[n][4]=trainteams[m.AwayTeamNo].avggoal;
		input1[n][5] = trainteams[m.AwayTeamNo].avgloss;
		output1[n][0] = m.getHomegoal();
		output1[n][1] =m.getAwaygoal();
	}
	
public static void input2(Matches2 m,int n) {
		
		input[n][0]= trainteams[m.homeTeamNo].rank;
		
		input[n][1] = trainteams[m.getAwayTeamNo()].getPoints();
		input[n][2] = trainteams[m.homeTeamNo].rank;
		input[n][3] = trainteams[m.AwayTeamNo].rank;
		output[n][0] = m.getHomegoal();
		output[n][1] = m.getAwaygoal();
		input1[n][0]= trainteams[m.homeTeamNo].points;
		
		input1[n][1] =trainteams[m.AwayTeamNo].points;
		input1[n][2] =trainteams[m.homeTeamNo].rank;
		input1[n][3] = trainteams[m.AwayTeamNo].rank;
		input1[n][4]=trainteams[m.homeTeamNo].Goaldifference;
		input1[n][5] = trainteams[m.AwayTeamNo].Goaldifference;
		output1[n][0] = m.getHomegoal();
		output1[n][1] =m.getAwaygoal();
	}
	
	
	
	public static void print() throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter("C:\\Users\\cheny\\eclipse-workspace\\FinalFor20YEARS\\print.csv"));
		StringBuilder sb = new StringBuilder();
		for(int n = 0;n<360;n++) {
		for (double element : input1[n]) {
		sb.append(element);
		sb.append(",");
		}
		sb.append("\n");
		}
		for(int n = 0;n<360;n++) {
			for (double element : output1[n]) {
			sb.append(element);
			sb.append(",");
			}
			sb.append("\n");
			}
 
		br.write(sb.toString());
		br.close();
		
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
			
			epoch++;
		}while(t.getError()>0.2);
		
		
		double a1 = trainteams[a].getAvggoal();
		double a2 = trainteams[a].getAvgloss();
		double aa=trainteams[a].getRank();
		double bb=trainteams[b].getRank();
		double b1 = trainteams[b].getAvggoal();
		double b2 = trainteams[b].getAvgloss();
		MLData o1=nw.compute(new BasicMLData (new double[] {a2,b2,aa,b,a1,b1}));
		
	
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
	public static int predict(int a, int b) {
		Teams2 aTeam = teams[a];
		int apoints =aTeam.points; 
		Teams2 bTeam = teams[b];
		int bpoints = bTeam.points;
		if(a == 18 ) {
			apoints += 30;
		}
		if(b == 18 ) {
			bpoints += 30;
		}
		if(a == 11) {
			apoints += 10;
		}
		if(b == 11) {
			bpoints +=10;
		}
		if(a == 0) {
			apoints += 10;
		}
		if(b == 0) {
			bpoints +=10;
		}
		
		
		
		double x1 = 0.448 + 0.0053*(apoints-bpoints);
		double x2 = 0.2452+ 0.0039*(bpoints-apoints);
		double x3 = x1+x2;
		int y = 0;
		double random;
		random = Math.random();
		if(x1-random>0) {
			y=1;
		}else if(x3-random>0) {
			y=-1;
		}else {
			y=0;
		}
		
		return y;
	}
	
	public static void readmatch() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cheny\\eclipse-workspace\\FinalFor20YEARS\\matches.csv"));
			
			String line = null;
			int n =0;
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");
				
				String last = item[item.length-1];
				matches[n] = new Matches2();
				matches[n].round = Integer.parseInt(item[0]);
				matches[n].homeTeamNo = Integer.parseInt(item[1]);

				matches[n].AwayTeamNo = Integer.parseInt(item[2]);

				
				matches[n].homegoal = Integer.parseInt(item[3]);
				
				matches[n].awaygoal = Integer.parseInt(item[4]);

				input(matches[n],n);
				
				if(matches[n].homegoal>matches[n].awaygoal) {
					matches[n].win = 1;
			
				}else if(matches[n].homegoal == matches[n].awaygoal) {
					matches[n].win = 0;
					 
				}else {  
					matches[n].win = -1;
					
				}
			    renew1(matches[n]);
				
			
				n++;
				
			}
		} catch (Exception e) {
			e.printStackTrace();

	}
	}
	
	public static void trainmatch() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cheny\\eclipse-workspace\\FinalFor20YEARS\\train 2018-2019.csv"));
			
			String line = null;
			int n =0;
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");
				
				String last = item[item.length-1];

				trainmatches[n] = new Matches2();
				trainmatches[n].round = Integer.parseInt(item[0]);
				trainmatches[n].homeTeamNo = Integer.parseInt(item[1]);

				trainmatches[n].AwayTeamNo = Integer.parseInt(item[2]);
	
				trainmatches[n].homegoal = Integer.parseInt(item[3]);
				
				trainmatches[n].awaygoal = Integer.parseInt(item[4]);

				input(trainmatches[n],n);
				
				if(trainmatches[n].homegoal>trainmatches[n].awaygoal) {
					trainmatches[n].win = 1;
			
				}else if(trainmatches[n].homegoal == trainmatches[n].awaygoal) {
					trainmatches[n].win = 0;
					 
				}else {  
					trainmatches[n].win = -1;
					
				}
			    renew2(trainmatches[n]);
				
			
				n++;
				
			}
		} catch (Exception e) {
			e.printStackTrace();

	}
	}
	
	public static void renew(Matches2 m) {
		if(m.win>0) {
			 teams[m.homeTeamNo].rank +=5;
			 teams[m.AwayTeamNo].rank -=5;
		}else if(m.win < 0 ){
			 teams[m.homeTeamNo].rank -=7;
			 teams[m.AwayTeamNo].rank +=7;
		}else {
			 teams[m.homeTeamNo].rank -=1;
			 teams[m.AwayTeamNo].rank +=1;
		}
	}
	
	public static void renew1(Matches2 m) {
		teams[m.homeTeamNo].Goaldifference += (m.homegoal-m.awaygoal);
		teams[m.homeTeamNo].totalgoal += m.homegoal;
		teams[m.homeTeamNo].totalloss += m.awaygoal;
		teams[m.homeTeamNo].avggoal = (teams[m.homegoal].avggoal+m.homegoal)/m.round;
		teams[m.homeTeamNo].avgloss = 
		teams[m.AwayTeamNo].Goaldifference += (m.awaygoal-m.homegoal);
		teams[m.AwayTeamNo].totalgoal += m.awaygoal;
		teams[m.AwayTeamNo].totalloss += m.homegoal;
		
		if(m.win>0) {
			if(teams[m.homeTeamNo].points == teams[m.AwayTeamNo].points) {
			 teams[m.homeTeamNo].rank +=5;
			 teams[m.AwayTeamNo].rank -=5;
			}else if(teams[m.homeTeamNo].points < teams[m.AwayTeamNo].points) {
				 teams[m.homeTeamNo].rank += 7;
				 teams[m.AwayTeamNo].rank -= 5;
			}else {
				 teams[m.homeTeamNo].rank += 5;
				 teams[m.AwayTeamNo].rank -= 7;
			}
			 teams[m.homeTeamNo].points +=3;
		}else if(m.win < 0 ){
			if(teams[m.homeTeamNo].points == teams[m.AwayTeamNo].points) {
			 teams[m.homeTeamNo].rank -=7;
			 //teams[m.homeTeamNo].points +=1;
			 teams[m.AwayTeamNo].rank +=7;
			}else if(teams[m.homeTeamNo].points < teams[m.AwayTeamNo].points) {
				teams[m.homeTeamNo].rank -= 4;
				 teams[m.AwayTeamNo].rank += 7;
			}else {
				 teams[m.homeTeamNo].rank -= 7;
				 teams[m.AwayTeamNo].rank += 4;
			}
			 teams[m.AwayTeamNo].points +=3;
		}else {
			if(teams[m.homeTeamNo].points == teams[m.AwayTeamNo].points) {
			 teams[m.homeTeamNo].rank -=1;
			 teams[m.AwayTeamNo].rank +=1;
			}else if(teams[m.homeTeamNo].points < teams[m.AwayTeamNo].points) {
				teams[m.homeTeamNo].rank +=0;
				 teams[m.AwayTeamNo].rank -=0;
			}else {
				teams[m.homeTeamNo].rank -=3;
				 teams[m.AwayTeamNo].rank +=2;
			}
			 teams[m.homeTeamNo].points +=1;
			 teams[m.AwayTeamNo].points +=1;
		}
	}
	
	public static void renew2(Matches2 m) {
		trainteams[m.homeTeamNo].Goaldifference += (m.homegoal-m.awaygoal);
		trainteams[m.homeTeamNo].totalgoal += m.homegoal;
		trainteams[m.homeTeamNo].totalloss += m.awaygoal;
		trainteams[m.homeTeamNo].played += 1;
		trainteams[m.homeTeamNo].avggoal = (trainteams[m.homeTeamNo].avggoal+m.homegoal)/trainteams[m.homeTeamNo].played;
		trainteams[m.homeTeamNo].avgloss = (trainteams[m.homeTeamNo].avgloss+m.awaygoal)/trainteams[m.homeTeamNo].played;
		trainteams[m.AwayTeamNo].Goaldifference += (m.awaygoal-m.homegoal);
		trainteams[m.AwayTeamNo].totalgoal += m.awaygoal;
		trainteams[m.AwayTeamNo].totalloss += m.homegoal;
		trainteams[m.AwayTeamNo].played += 1;
		trainteams[m.AwayTeamNo].avggoal = (trainteams[m.AwayTeamNo].avggoal+m.homegoal)/trainteams[m.AwayTeamNo].played;
		trainteams[m.AwayTeamNo].avgloss = (trainteams[m.AwayTeamNo].avgloss+m.awaygoal)/trainteams[m.AwayTeamNo].played;
		
		if(m.win>0) {
			if(trainteams[m.homeTeamNo].points == trainteams[m.AwayTeamNo].points) {
			 trainteams[m.homeTeamNo].rank +=5;
			 trainteams[m.AwayTeamNo].rank -=5;
			}else if(trainteams[m.homeTeamNo].points < trainteams[m.AwayTeamNo].points) {
				 trainteams[m.homeTeamNo].rank += 7;
				 trainteams[m.AwayTeamNo].rank -= 5;
			}else {
				 trainteams[m.homeTeamNo].rank += 5;
				 trainteams[m.AwayTeamNo].rank -= 7;
			}
			 trainteams[m.homeTeamNo].points +=3;
		}else if(m.win < 0 ){
			if(trainteams[m.homeTeamNo].points == trainteams[m.AwayTeamNo].points) {
			 trainteams[m.homeTeamNo].rank -=7;
			 //trainteams[m.homeTeamNo].points +=1;
			 trainteams[m.AwayTeamNo].rank +=7;
			}else if(trainteams[m.homeTeamNo].points < trainteams[m.AwayTeamNo].points) {
				trainteams[m.homeTeamNo].rank -= 4;
				 trainteams[m.AwayTeamNo].rank += 7;
			}else {
				 trainteams[m.homeTeamNo].rank -= 7;
				 trainteams[m.AwayTeamNo].rank += 4;
			}
			 trainteams[m.AwayTeamNo].points +=3;
		}else {
			if(trainteams[m.homeTeamNo].points == trainteams[m.AwayTeamNo].points) {
			 trainteams[m.homeTeamNo].rank -=1;
			 trainteams[m.AwayTeamNo].rank +=1;
			}else if(trainteams[m.homeTeamNo].points < trainteams[m.AwayTeamNo].points) {
				trainteams[m.homeTeamNo].rank +=0;
				 trainteams[m.AwayTeamNo].rank -=0;
			}else {
				trainteams[m.homeTeamNo].rank -=3;
				 trainteams[m.AwayTeamNo].rank +=2;
			}
			 trainteams[m.homeTeamNo].points +=1;
			 trainteams[m.AwayTeamNo].points +=1;
		}
	
	
	}
	
	public static void BP(double [][]  data , double [][] target){
	
	BpDeep bp = new BpDeep(new int[]{6,10,2}, 0.15, 0.8);
	
	for(int n=0;n<5000;n++)
		for(int i=0;i<data.length;i++)
			bp.train(data[i], target[i]);
	
	for(int j=0;j<data.length;j++){
		double[] result = bp.computeOut(data[j]);
		System.out.println(Arrays.toString(data[j])+":"+Arrays.toString(result));
	}

	double[] x = new double[]{3,1};
	double[] result = bp.computeOut(x);
	System.out.println(Arrays.toString(x)+":"+Arrays.toString(result));
}
	
	public static void input1(Matches2 m,int n) {
		
		input[n][0]= teams[m.getHomeTeamNo()].getPoints();
		//System.out.print(m.getHomeTeamNo());
		input[n][1] = teams[m.getAwayTeamNo()].getPoints();
		input[n][2] = teams[m.homeTeamNo].rank;
		input[n][3] = teams[m.AwayTeamNo].rank;
		output[n][0] = m.getHomegoal();
		output[n][1] = m.getAwaygoal();
	}
	


}
