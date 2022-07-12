package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.isZero;

/**
 * a class with builder pattern
 */
public class PointLight extends Light implements LightSource
{
    /**
     * fields
     */
    private static final Random RND = new Random();
    private Point position;
    private Double kC=1.0;
    private Double kL=0.0;
    private Double kQ=0.0;

    /**
     * methods
     */

    /**
     *
     * @param intensity
     * @param p0
     * a constructor
     */

    public PointLight(Color intensity, Point p0)
    {
        super(intensity);
        this.position=p0;
    }


    /**
     * implementation of {@link LightSource#getIntensity(Point)}
     * @param p
     * @return color
     * a methode that gets the intensity in a point
     */
    @Override
    public Color getIntensity(Point p)
    {
        double distance= p.distance(this.position);
        double disquer=p.distanceSquared(this.position);
        return super.getIntensity().scale(1/(kC+kL*distance+kQ*disquer));
    }

    /**
     * implementation of {@link LightSource#getL(Point)}
     * @param p
     * @return vector
     * a methode that returns the normalized direction.
     */
    @Override
    public Vector getL(Point p)
    {
        return p.subtract(position).normalize();
    }


    /**
     * implementation of {@link LightSource#getDistance(Point)}
     * @param point
     * @return infinity
     * a methode that returns the distance between the light source and a point.
     */
    @Override
    public double getDistance(Point point)
    {
        return position.distance(point);
    }

    @Override
    public List<Vector> getBeamL(Point p, double radius, int amount) {
        if (p.equals(position)) {
            return null;
        }
        LinkedList<Vector> beam = new LinkedList<>();

        //from pointlight position to p point
        Vector v = this.getL(p);
        beam.add(v);
        if (amount <= 1) {
            return beam;
        }

        double distance = this.position.distance(p);

        if (isZero(distance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point lightHead = new Point(v.getX(),v.getY(),v.getZ());
        Vector normX;

        // if v._head == (0,0,w) then normX.head ==(-w,0,0)
        // otherwise normX.head == (-y,x,0)
        if (isZero(lightHead.getX()) && isZero(lightHead.getY())) {
            normX = new Vector(lightHead.getZ() * -1, 0, 0).normalize();
        } else {
            normX = new Vector(lightHead.getY() * -1, lightHead.getX(), 0).normalize();
        }

        Vector normY = v.crossProduct(normX).normalize();
        double cosTheta;
        double sinTheta;

        double d;
        double x;
        double y;

        for (int counter = 0; counter < amount; counter++) {
            Point newPoint = new Point(this.position);
            // randomly coose cosTheta and sinTheta in the range (-1,1)
            cosTheta = 2 * RND.nextDouble() - 1;
            sinTheta = Math.sqrt(1d - cosTheta * cosTheta);

            //d ranged between -radius and  +radius
            d = radius * (2 * RND.nextDouble() - 1);
            //d ranged between -radius and  +radius
            if (isZero(d)) { //Thanks to Michael Shachor
                counter--;
                continue;
            }
            x = d * cosTheta;
            y = d * sinTheta;

            if (!isZero(x)) {
                newPoint = newPoint.add(normX.scale(x));
            }
            if (!isZero(y)) {
                newPoint = newPoint.add(normY.scale(y));
            }
            beam.add(p.subtract(newPoint).normalize());
        }
        return beam;

    }

    /**
     *
     * @param kC
     * @return point light
     * setter to "kc" in builder pattern
     */
    public PointLight setkC(Double kC)
    {
        this.kC = kC;
        return this;
    }

    /**
     *
     * @param kL
     * @return point light
     * setter to "kl" in builder pattern
     */
    public PointLight setkL(Double kL)
    {
        this.kL = kL;
        return  this;
    }

    /**
     *
     * @param kQ
     * @return point light
     * setter to "kq" in builder pattern
     */
    public PointLight setkQ(Double kQ)
    {
        this.kQ = kQ;
        return  this;
    }
}
