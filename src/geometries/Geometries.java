package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable
{
    /**
     * field
     */

    /**
     * a list of points that intersect with the ray.
     */
    protected List<Intersectable> intersectablesList;

    /**
     * methods
     */

    /**
     * a default constructor.
     */
    public Geometries()
    {
        intersectablesList = new LinkedList<>();
    }

    /**
     *
     * @param intersectables
     * a constructor that receives intersectables
     */
    public Geometries(Intersectable... intersectables)
    {
        intersectablesList = new LinkedList<>();
        Collections.addAll(intersectablesList, intersectables);

    }

    /**
     *
     * @param intersectables
     * methode that adds intersectables to the list.
     */
    public void add(Intersectable...intersectables)
    {
        Collections.addAll(intersectablesList,intersectables);
    }


    /**
     *
     * @param ray
     * @return
     * methode that helps to find points and geometries that intersect with the ray.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : intersectablesList)
        {
            var geoIntersections = geometry.findGeoIntersections(ray);
            if (geoIntersections != null)
            {
                if (intersections == null)
                {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(geoIntersections);
            }
        }
        return intersections;
    }
}

