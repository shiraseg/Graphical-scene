package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Bonus class
 */
public class  Circle extends Geometry {


    final private Point centre;
    final private double radius;
    final private Vector normal;
    protected Plane plane;


    public Circle(Point centre, double radius, Vector normal) {
        this.centre = centre;
        this.radius = radius;
        this.normal = normal.normalize();
    }



    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> planePoints = plane.findGeoIntersectionsHelper(ray);
        if(planePoints == null)
            return  null;

        planePoints.removeIf( //removes from teh list all the point outside the circles range
                (geoPoint) ->
                {return geoPoint.point.distance(centre) > radius;}
        );

        if(planePoints.isEmpty())
            return  null;

        return List.of(new GeoPoint(this,planePoints.get(0).point));
    }


}
