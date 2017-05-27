public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet P) {
		this.xxPos = P.xxPos;
		this.yyPos = P.yyPos;
		this.xxVel = P.xxVel;
		this.yyVel = P.yyVel;
		this.mass = P.mass;
		this.imgFileName = P.imgFileName;
	}
	public double calcDistance(Planet refP){
		double distance;
		distance = Math.sqrt(Math.pow((xxPos - refP.xxPos), 2.0) +
							 Math.pow((yyPos - refP.yyPos), 2.0));
		return distance;
	}

	public double calcForceExertedBy(Planet refP){
		final double gravityConst = 6.67e-11;
		double distance = calcDistance(refP);
		double force = (gravityConst * this.mass * refP.mass) / Math.pow(distance, 2.0);
		return force;
	}

	public double calcForceExertedByX(Planet refP){
		double force = calcForceExertedBy(refP);
		double forceX = force * (refP.xxPos - this.xxPos) / calcDistance(refP);
		return forceX;
	}

	public double calcForceExertedByY(Planet refP){
		double force = calcForceExertedBy(refP);
		double forceY = force * (refP.yyPos - this.yyPos) / calcDistance(refP);
		return forceY;
	}

	public void update(double time, double forceX, double forceY){
		double accelerateX = forceX / this.mass;
		double accelerateY = forceY / this.mass;
		this.xxPos = this.xxPos + this.xxVel * time + 0.5 * accelerateX * Math.pow(time, 2.0);
		this.yyPos = this.yyPos + this.yyVel * time + 0.5 * accelerateY * Math.pow(time, 2.0);
		this.xxVel = this.xxVel + accelerateX * time;
		this.yyVel = this.yyVel + accelerateY * time;
		//this.xxPos = this.xxPos + this.xxVel * time;
		//this.yyPos = this.yyPos + this.yyVel * time;
	}

	public void draw(String imgPath, double radius, int halfPixelX, int halfPixelY){
		StdDraw.picture(halfPixelX * xxPos / radius, halfPixelY * yyPos / radius, imgPath);
	}
}