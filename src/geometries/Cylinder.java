package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

public class Cylinder extends Tube
{
    /**
     * field
     */
    double height;

    /**
     * methods
     */

    /**
     *
     * @param axisRay
     * @param radius
     * @param height
     * constructor to build a cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height)
    {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     *
     * @return string
     * a methode that returns string with all the characteristics of the cylinder.
     */
    @Override
    public String toString()
    {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     *
     * @return double
     * getter for "height"
     */
    public double getHeight()
    {
        return height;
    }


    /**
     *
     * @param point
     * @return vector
     * a methode that helps us find normal in point.
     */
    @Override
    public Vector getNormal(Point point)
    {

        /**  // if the dotProduct function is zero than the point is on the disk and the vector of the ray is the normal
         if(point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()) == 0)
         return axisRay.getDir().normalize();
         // else the point is on the tube, and we use the getNormal function of the tube
         return super.getNormal(point);
         */

        // returns the center point of the second disk
        Point bottomPoint = axisRay.getPoint(height);
        if(point.equals(bottomPoint) ||point.equals(axisRay.getP0()) )
            return axisRay.getDir().normalize();

        // meaning it is on the surface of second disk
        if (point.subtract(bottomPoint).length() < radius)
            return axisRay.getDir().normalize();

        // meaning it is on the surface of first disk
        if (point.subtract(axisRay.getP0()).length() < radius)
            return axisRay.getDir().normalize();

        // else the point is on the tube, and we use the get normal function of the tube.(super)
        return super.getNormal(point);
    }

    /**
     * implementation of {@link Intersectable#findGeoIntersectionsHelper(Ray)}
     * @param ray
     * @return list of geo- points
     */
    public List<GeoPoint> findGeoIntersectionHelper(Ray ray)
    {
        return super.findGeoIntersectionsHelper(ray);
    }

}