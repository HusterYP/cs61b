public class Planet {
    // 重力常量
    private static double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(p.xxPos - this.xxPos, 2) + Math.pow(p.yyPos - this.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        if (distance <= 0) {
            return 0;
        }
        return G * this.mass * p.mass / (Math.pow(distance, 2));
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        if (distance <= 0) {
            return 0;
        }
        return (p.xxPos - this.xxPos) * force / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        if (distance <= 0) {
            return 0;
        }
        return (p.yyPos - this.yyPos) * force / distance;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double forceX = 0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            }
            forceX += calcForceExertedByX(p);
        }
        return forceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double forceY = 0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            }
            forceY += calcForceExertedByY(p);
        }
        return forceY;
    }

    public void update(double dt, double forceX, double forceY) {
        if (this.mass <= 0) {
            return;
        }
        double ax = forceX / this.mass;
        double ay = forceY / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
        StdDraw.show();
    }
}
