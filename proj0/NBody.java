public class NBody{
	public static double readRadius(String file){
		In in = new In(file);
		in.readInt();
		double Radius = in.readDouble();
		return Radius;	
	}
	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int N = in.readInt();
		in.readDouble();
		int i = 0;
		Planet[] p = new Planet[N];
		while(i<N){
			double xp = in.readDouble();
			double yp = in.readDouble();
			double xv = in.readDouble();
			double yv = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();
			p[i] = new Planet(xp, yp, xv, yv, mass, name);
			i++;
		}
		return p;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double universe_r = NBody.readRadius(filename);
		Planet[] p = NBody.readPlanets(filename);

		String background = "images/starfield.jpg";
		StdDraw.setScale(-universe_r,universe_r);
		StdDraw.clear();
		StdDraw.picture(0,0,background);
		StdDraw.show();

		int i = 0;
		while(i<p.length){
			p[i].draw();
			i++;
		} 

		StdDraw.enableDoubleBuffering();
		double time = 0;
		while(time < T){
			double[] xForces = new double[p.length];
			double[] yForces = new double[p.length];
			i = 0;
			while(i<p.length){
				xForces[i] = p[i].calcNetForceExertedByX(p);
				yForces[i] = p[i].calcNetForceExertedByY(p);
				i++;
			}
			i = 0;
			while(i<p.length){
				p[i].update(dt,xForces[i],yForces[i]);
				i++;
			}
			StdDraw.picture(0,0,background);
			i = 0;
			while(i<p.length){
				p[i].draw();
				i++;
			} 
			StdDraw.show();
			StdDraw.pause(10);

			time += dt;
		}
		StdOut.printf("%d\n", p.length);
		StdOut.printf("%.2e\n", universe_r);
		for (i = 0; i < p.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  p[i].xxPos, p[i].yyPos, p[i].xxVel,
                  p[i].yyVel, p[i].mass, p[i].imgFileName);   
}
	}

}