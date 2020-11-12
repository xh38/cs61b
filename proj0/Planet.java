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

	public double calcForceExertedByX(Planet p){
		double force = this.calcForceExertedBy(p);
		double force_x;
		force_x = force * (p.xxPos - this.xxPos) / calcDistance(p);
		return force_x;
	}

	public double calcForceExertedByY(Planet p){
		double force = this.calcForceExertedBy(p);
		double force_y;
		force_y = force * (p.yyPos - this.yyPos) / calcDistance(p);
		return force_y;
	}

	public double calcNetForceExertedByX(Planet[] p){
		int N = p.length;
		int i = 0;
		double net_force_x = 0;
		while(i<N){
			if(!this.equals(p[i])){
				net_force_x += this.calcForceExertedByX(p[i]);
			}
			i++;
		}
		return net_force_x;
	}

	public double calcNetForceExertedByY(Planet[] p){
		int N = p.length;
		int i = 0;
		double net_force_y = 0;
		while(i<N){
			if (!this.equals(p[i])){
				net_force_y += this.calcForceExertedByY(p[i]);
			}
			i++;
		}
		return net_force_y;
	}

	public void update(double t,double f_x,double f_y){
		this.xxVel += f_x * t / this.mass;
		this.yyVel += f_y * t / this.mass;
		this.xxPos += this.xxVel * t;
		this.yyPos += this.yyVel * t;
	}
}