package primitives;

import geometries.Plane;

import java.util.Objects;

public class Point
{
    /**
     * field
     */

    final Double3 xyz;

    /**
     * a special point that all its values are zero
     */
    public final static Point ZERO = new Point(0d, 0d, 0d);

    /**
     * methods
     */

    /**
     *
     * @param x
     * @param y
     * @param z
     * a constructor that gets 3 values and puts them in respectively in the field xyz
     */

    public Point(double x, double y, double z)
    {
        xyz=new Double3(x,y,z);
    }

    public Point(Point p)
    {
        this.xyz=p.xyz;
    }
    /**
     *
     * @param xyz
     * a constructor that gets double3 and puts it in the field xyz
     */
    public Point(Double3 xyz)
    {
        this.xyz=xyz;
    }

    /**
     *
     * @return douoble
     * getter for x int the "xyz"
     */
    public double getX()
    {
        return xyz.d1;
    }

    /**
     *
     * @return double
     * getter for y in the "xyz"
     */
    public double getY()
    {
        return xyz.d2;
    }

    /**
     *
     * @return double
     * getter for z in the "xyz"
     */
    public double getZ()
    {
        return xyz.d3;
    }

    /**
     *
     * @param o
     * @return true/ false
     * this methode checks if an object is equals to the current point
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(((Point) o).xyz);
    }

    /**
     *
     * @return string
     *  a methode that returns string with all the characteristics of the point
     */
    @Override
    public String toString()
    {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     *
     * @param vector
     * @return point
     * this methode  adds vector to a point.
     */
    public Point add(Vector vector)
    {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     *
     * @param p1
     * @return vector
     * this method creates vector by subtracting between 2 points
     */
    public Vector subtract(Point p1)
    {
        return new Vector(xyz.subtract(p1.xyz));
    }

    /**
     *
     * @param p1
     * @return double
     * this methode calculates the distance between 2 points and raise them squared.
     */
    public double distanceSquared(Point p1)
    {

        double x=this.xyz.subtract(p1.xyz).d1 * this.xyz.subtract(p1.xyz).d1;
        double y=this.xyz.subtract(p1.xyz).d2 * this.xyz.subtract(p1.xyz).d2;
        double z=this.xyz.subtract(p1.xyz).d3 * this.xyz.subtract(p1.xyz).d3;
        return x+y+z;

    }

    /**
     *
     * @param p
     * @return double
     * this methode calculates the distance between 2 points
     */
    public double distance(Point p)
    {
        return Math.sqrt(this.distanceSquared(p));
    }

}