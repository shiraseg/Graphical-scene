package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.*;
import java.util.List;

import static primitives.Util.alignZero;

public class Sphere extends Geometry
{

    /**
     * fields
     */

    final Point center;
    final double radius;

    /**
     * methods
     */

    /**
     *
     * @param center
     * @param radius
     * a constructor.
     */

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }


    /**
     *  implementation of {@link Geometry#getNormal(Point)}
     * @param point external Point
     * @return normal to plane
     */
    @Override
    public Vector getNormal(Point point)
    {
        Vector normal = point.subtract(center);
        return normal.normalize();
    }


    /**
     * implementation of {@link Intersectable#findGeoIntersectionsHelper(Ray)}
     * @param ray
     * @return list of geo- points
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        //if P0 is the center of the sphere, return the radius to there
        if (P0.equals(center))
        {
            //center+(radius*v)
            return List.of(new GeoPoint(this, center.add(v.scale(radius))));
        }

        //u=center-p0
        Vector U = center.subtract(P0);

        //tm=v*u
        double tm = alignZero(v.dotProduct(U));

        //d is the distance from the center to the ray
        //d=squrt(|u|^2-tm^2)
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        //if d is larger are equals radius, then there are no intersections
        if (d >= radius)
        {
            return null;
        }

        //th is the last part of the triangle between radius and d, calculate according to pythagoras
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        //t1=tm-th
        double t1 = alignZero(tm - th);
        //t2=tm+th
        double t2 = alignZero(tm + th);

        //if they are both positive then there are 2 intersections
        if (t1 > 0 && t2 > 0)
        {
            //p1=p0+t1*v
            Point P1 = ray.getPoint(t1);
            //p2=p0+t2*v
            Point P2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
        }


        //otherwise if only one is positive so there is one intersection
        if (t1 > 0)
        {
            //p1=p0+t1*v
            Point P1 = ray.getPoint(t1);
            return List.of(new GeoPoint(this, P1));
        }

        if (t2 > 0)
        {
            //p2=p0+t2*v
            Point P2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, P2));
        }

        //if they are both negative then there are no intersections
        return null;
    }


    /**
     *
     * @return string
     * a methode that returns string with all the characteristics of the sphere
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}