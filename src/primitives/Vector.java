package primitives;
import java.util.Objects;

public class Vector extends Point
{
    /**
     * this class has no field of its own
     * methodes
     */


    /**
     *
     * @param x
     * @param y
     * @param z
     * a constructor that gets 3 values and puts them in respectively in the field xyz
     */
    public Vector(double x, double y, double z)
    {
        this(new Double3(x,y,z));
    }

    /**
     *
     * @param xyz
     *  a constructor that gets double3 and puts it in the field xyz
     */
    public Vector(Double3 xyz)
    {
        super(xyz);
        if(xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Cant vector zero");
    }

    /**
     *
     * @return string
     * a methode that returns string with all the characteristics of the vector
     */
    @Override
    public String toString()
    {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * operations
     */

    /**
     *
     * @param scalingFactor
     * @return vector
     * this methode multiplier in scalar the current vector
     */

    public Vector scale(double scalingFactor)
    {
        return new Vector(xyz.scale(scalingFactor));
    }

    /**
     *
     * @param v
     * @return vector
     * this methode makes "cross product" to the given vector @param "v" and the current vector.
     */
    public Vector crossProduct(Vector v)
    {
        double ax = xyz.d1;
        double ay = xyz.d2;
        double az = xyz.d3;
        double bx = v.xyz.d1;
        double by = v.xyz.d2;
        double bz = v.xyz.d3;

        double cx = ay * bz - az * by;
        double  cy = az * bx - ax * bz;
        double cz = ax * by - ay * bx;
        return  new Vector(cx, cy, cz);
    }

    /**
     *
     * @param vector
     * @return double
     * this methode makes "dot product" to the given vector @param "vector" and the current vector.
     */
    public double dotProduct(Vector vector)
    {
        return this.xyz.d1 * vector.xyz.d1 +
                this.xyz.d2 * vector.xyz.d2 +
                this.xyz.d3 * vector.xyz.d3;
    }

    /**
     *
     * @return double
     * this methode returns the length of the vector
     */
    public double length()
    {
        return  Math.sqrt(lengthSquared());
    }

    /**
     *
     * @return double
     * this methode calculates the length of the vector and returns it squared
     */
    public double lengthSquared()
    {
        return xyz.d1 * xyz.d1 +
                xyz.d2 * xyz.d2 +
                xyz.d3 * xyz.d3;
    }

    /**
     *
     * @return vector
     * this methode puts the current vector in its normal form.
     */
    public Vector normalize()
    {
        double len = length();
        if(len == 0)
            throw new ArithmeticException("Divide by zero!");
        return new Vector(xyz.reduce((len)));
    }

    /**
     *
     * @return true/ false
     * a methode that checks if the current vector is the zero vector (all its values are 0)
     */
    public boolean isZeroVector()
    {
        return xyz.equals(Double3.ZERO);
    }

}