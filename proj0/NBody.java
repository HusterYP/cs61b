public class NBody {
    public static double readRadius(String fileName) {
        if (fileName.isEmpty()) {
            return 0;
        }
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        Planet[] planets = {};
        if (fileName.isEmpty()) {
            return planets;
        }
        In in = new In(fileName);
        int n = in.readInt();
        planets = new Planet[n];
        in.readDouble();
        int index = 0;
        while (index < n) {
            Planet p = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            planets[index] = p;
            index++;
        }
        return planets;
    }

    private static String starfield = "images/starfield.jpg";

    public static void main(String[] args) {
        if (args.length < 3) {
            return;
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
//        double T = 157788000.0;
//        double dt = 25000.0;
//        String filename = "./data/planets.txt";
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time < T) {
            int count = planets.length;
            double[] xForces = new double[count];
            double[] yForces = new double[count];
            for (int i = 0; i < count; i++) {
                Planet p = planets[i];
                xForces[i] = p.calcNetForceExertedByX(planets);
                yForces[i] = p.calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < count; i++) {
                double forceX = xForces[i];
                double forceY = yForces[i];
                Planet p = planets[i];
                p.update(dt, forceX, forceY);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, starfield);

            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
