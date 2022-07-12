package unittests.Renderer;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Renderer.Camera;
import primitives.*;
import geometries.*;

import java.util.LinkedList;
import java.util.List;



public class IntegrationTest
{

    private int countIntersections(Camera cam, Intersectable geo, int nX,int nY)
    {
        int count = 0;

        List<Point> allIntersections = null;

        /**
         * Going over all the intersection points and adding them in the list+counting them.
         */

        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));
                if (intersections != null)
                {
                    if (allIntersections == null)
                    {
                        allIntersections = new LinkedList<>();
                    }
                    allIntersections.addAll(intersections);
                }

                if(intersections!=null)
                    count+=intersections.size();
            }
        }

        return count;
    }


    /**
     * Integration tests of Camera Ray with Ray-Sphere intersections.
     * all the test were taken from the presentation of the pre-curse.
     */


    /**Sphere integration*/

    @Test
    public void cameraRaySphereIntegration() {
        Camera cam1 = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setVPSize(3,3).setVPDistance(1);
        Camera cam2 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setVPSize(3,3).setVPDistance(1);

        // TC01: The sphere is small and far from the VP => 2 points
        assertEquals(2,countIntersections(cam1,new Sphere(new Point(0, 0, -3),1),3,3),"Wrong amount of intersections");

        // TC02:The sphere is big and close => 18 points
        assertEquals(18,countIntersections(cam2, new Sphere(new Point(0, 0, -2.5),2.5),3,3),"Wrong amount of intersections");

        // TC03: The sphere is medium => 10 points
        assertEquals(10,countIntersections(cam2, new Sphere( new Point(0, 0, -2),2), 3,3),"Wrong amount of intersections");

        // TC04: The camera is inside the Sphere => 9 points
        assertEquals(9,countIntersections(cam2, new Sphere( new Point(0, 0, -1),4), 3,3),"Wrong amount of intersections");

        // TC05: The sphere is behind the camera => 0 points
        assertEquals(0,countIntersections(cam1, new Sphere( new Point(0, 0, 1),0.5), 3,3),"Wrong amount of intersections");
    }

    /**Plane integration*/

    @Test
    public void cameraRayPlaneIntegration()
    {
        Camera cam = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setVPSize(3,3).setVPDistance(1);

        // TC01: The plane is against camera => 9 points
        assertEquals(9,
                countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1)),3,3 ),
                "Wrong amount of intersections");

        // TC02: The Plane is with small angle => 9 points
        assertEquals(9,
                countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2)), 3,3),
                "Wrong amount of intersections");

        // TC03: The plane is parallel to lower rays => 6 points
        assertEquals(6,
                countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 3,3),
                "Wrong amount of intersections");

        Camera cam2=new Camera(new Point(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0))
                .setVPDistance(1).setVPSize(3,3);

        // TC04: The plane is behind the camera => 0 points
        assertEquals(0,
                countIntersections(cam2, new Plane(new Point(0, -5, 0), new Vector(-1, -9, 13)), 3,3),
                "Wrong amount of intersections");
    }

    /**Triangle integrations*/

    @Test
    public void cameraRayTriangleIntegration() {
        Camera cam = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setVPSize(3,3).setVPDistance(1);

        // TC01: The triangle is small => 1 point
        assertEquals(1,
                countIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2)), 3,3),
                "Wrong amount of intersections");

        // TC02:  The triangle is medium triangle => 2 points
        assertEquals(2,
                countIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)), 3,3),
                "Wrong amount of intersections");
    }

}
