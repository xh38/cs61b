public class Planet{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double G = 6.67e-11;

	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dis = Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos) + (this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));
		return dis;
	} 

	public double calcForceExertedBy(Planet p){
		double force = G * this.mass * p.mass / calcDistance(p) / calcDistance(p);
		return force;
	}

}