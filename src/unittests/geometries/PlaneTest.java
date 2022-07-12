package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest
{


    @Test
    public void testConstructor()
    {
        // ============ Equivalence Partitions Tests ==============
        try
        {
            new Plane(new Point(1,1,1),new Point(0,1,1),new Point(1,2,1));
        }
        catch(IllegalArgumentException e)
        {
            fail("failed constructing the plane");
        }
        //TC01: same points
        assertThrows( IllegalArgumentException.class, () -> new Plane(new Point(1,1,1),new Point(1,1,1),new Point(1,2,1)),
                "Constructed a plane with tow equal points");

        //TC01: same ray
        assertThrows( IllegalArgumentException.class, () -> new Plane(new Point(0,0,1),new Point(0,0,7),new Point(0,2,3)),
                "Constructed a plane with tow points on the same ray");
    }

    @Test
    void testGetNormal()
    {
        Point p1=new Point(1,1,1);
        Point p2=new Point(1,1,0);
        Point p3=new Point(1,0,1);
        Plane plane=new Plane (p1,p2,p3);
        assertEquals(plane.getNormal(),new Vector(-1,0,0),"ERROR:wrong value!");

        assertEquals(1,plane.getNormal().length(),"ERROR: the length isn't 1");

    }
    @Test
    public void FindIntersectionsRay() {
        Plane pl = new Plane(new Point(0, 0, 1), new Vector(1, 1, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        assertEquals(List.of(new Point(1, 0, 0)),
                pl.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0))),
                "Bad plane intersection");

        // TC02: Ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Must not be plane intersection");

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(0, 1, -1))),
                "Must not be plane intersection");

        // TC12: Ray in plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, .5), new Vector(0, 1, -1))),
                "Must not be plane intersection");


        // TC13: Orthogonal ray into plane
        assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1))),
                "Bad plane intersection");

        // TC14: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC15: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC16: Orthogonal ray from plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC17: Ray from plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 0))),
                "Must not be plane intersection");

        // TC18: Ray from plane's Q point
        assertNull(pl.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 0))),
                "Must not be plane intersection");
    }
}
