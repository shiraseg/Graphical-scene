package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;
import java.util.Objects;


/**
 * an abstract class
 */
public abstract class Intersectable
{

    /**
     *  An internal Assistance Department-PDS
     *  that contains  geometry and points
     *  its purpose is to help us finds also points and also geometries in the intersectables.
     */
    public static class GeoPoint
    {

        public Geometry geometry;
        public Point point;

        /**
         * constructor.
         */

        public GeoPoint(Geometry geometry, Point point)
        {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         *
         * @param o
         * @return true/false
         * this methode checks if an object is equals to the current geo-point.
         *
         */
        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        /**
         *
         * @return string
         * a methode that returns string with all the characteristics of the geo- point.
         */

        @Override
        public String toString()
        {
            return "GeoPoint:" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '.';
        }
    }

    /**
     *
     * @param ray
     * @return list of points
     * a methode that helps us find points that intersect with the ray.
     */

    public List<Point> findIntersections(Ray ray)
    {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();

    }

    /**
     *
     * @param ray
     * @return list of geo points
     * an abstract methode that helps us find geo- points that intersect with the ray.
     */

    abstract protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    public List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return this.findGeoIntersectionsHelper(ray);
    }

}
