import java.util.ArrayList;
import javax.swing.ImageIcon;

public class NBody {
	static private In in = null;
	static private int numOfPlanets = -1;
	static private double radius = -1.0;
	static private Planet[] planets = null;
	static private String backgroundFileName = "images/starfield.jpg";
	static private String imgFolder = "images/";

	static public int readnumOfPlanets(String txtPath){
		if(in == null) in = new In(txtPath);
		if(numOfPlanets == -1) numOfPlanets = in.readInt();
		return numOfPlanets;
	}	
	static public double readRadius(String txtPath){
		if(in == null) in = new In(txtPath);
		if(radius == -1.0)
		{
			if(numOfPlanets == -1) readnumOfPlanets(txtPath);
			radius = in.readDouble();
		}
		return radius;
	}
	
	static public Planet[] readPlanets(String txtPath){
		if(in == null) in = new In(txtPath);
		if(numOfPlanets == -1) readnumOfPlanets(txtPath);
		if(radius == -1.0) readRadius(txtPath);
		if(planets == null)
		{
			ArrayList<Planet> planetlist = new ArrayList<Planet>();
			while(!in.isEmpty())
			{
				double xPos = in.readDouble();
				double yPos = in.readDouble();
				double xVel = in.readDouble();
				double yVel = in.readDouble();
				double mass = in.readDouble();
				String imgFileName = in.readString();
				Planet planet = new Planet(xPos, yPos, xVel, yVel, mass, imgFileName);
				planetlist.add(planet);
			}
			planets = planetlist.toArray(new Planet[planetlist.size()]);
		}
		//Planet[] planets;
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		ImageIcon backgroundImage = new ImageIcon(backgroundFileName);
		int backgroundWidth = backgroundImage.getImage().getWidth(null);
		int backgroundHeight = backgroundImage.getImage().getHeight(null);

		StdDraw.setCanvasSize(backgroundWidth, backgroundHeight);
		StdDraw.setXscale(0 - backgroundWidth / 2.0, backgroundWidth / 2.0);
		StdDraw.setYscale(0 - backgroundHeight / 2.0, backgroundHeight / 2.0);
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundFileName);
		for(Planet planet : planets){
			planet.draw(imgFolder + planet.imgFileName, radius, backgroundWidth / 2, backgroundHeight / 2);
		}
		StdDraw.show(10);

		double tempForceX;
		double tempForceY;
		double[] netForceX = new double[numOfPlanets];
		double[] netForceY = new double[numOfPlanets];

		double time = 0;
		while(time < T)
		{		
			for(int loopOfNet = 0; loopOfNet < numOfPlanets; loopOfNet++){
				tempForceX = 0;
				tempForceY = 0;
				for(int loopOfTemp = 0; loopOfTemp < numOfPlanets; loopOfTemp++){
					if(loopOfTemp != loopOfNet){
						tempForceX += planets[loopOfNet].calcForceExertedByX(planets[loopOfTemp]);
						tempForceY += planets[loopOfNet].calcForceExertedByY(planets[loopOfTemp]);
					}
				}
				netForceX[loopOfNet] = tempForceX;
				netForceY[loopOfNet] = tempForceY;
			}

			StdDraw.clear();
			StdDraw.picture(0, 0, backgroundFileName);
			int loopOfPlanets = 0;
			for(Planet planet : planets){
				planet.update(dt, netForceX[loopOfPlanets], netForceY[loopOfPlanets]);
				planet.draw(imgFolder + planet.imgFileName, radius, backgroundWidth / 2, backgroundHeight / 2);
				loopOfPlanets++;
			}
			StdDraw.show(10);
			time = time + dt;
		}
	}
} 