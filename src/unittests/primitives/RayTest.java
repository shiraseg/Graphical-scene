package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest
{

    @Test
    void findClosestPoint()
    {
        // ============ Equivalence Partitions Tests ==============

        Ray ray = new Ray(new Point(0, 0, 10), new Vector(1, 10, -100));

        List<Point> list = new LinkedList<Point>();
        list.add(new Point(1, 1, -100));
        list.add(new Point(-1, 1, -99));
        list.add(new Point(0, 2, -10));
        list.add(new Point(0.5, 0, -100));

        assertEquals(list.get(2), ray.findClosestPoint(list));

        // =============== Boundary Values Tests ==================

        // TC01: the list is empty.

        List<Point> list2 = null;
        assertNull(ray.findClosestPoint(list2), "try again");

        // TC02: the closest point is the first one.

        List<Point> list3 = new LinkedList<Point>();
        list3.add(new Point(0, 2, -10));
        list3.add(new Point(-1, 1, -99));
        list3.add(new Point(1, 1, -100));
        list3.add(new Point(0.5, 0, -100));

        assertEquals(list3.get(0), ray.findClosestPoint(list3));


        // TC03: the closest pont is the last one.

        List<Point> list4 = new LinkedList<Point>();
        list4.add(new Point(1, 1, -100));
        list4.add(new Point(0.5, 0, -100));
        list4.add(new Point(-1, 1, -99));
        list4.add(new Point(0, 2, -10));

        assertEquals(list4.get(3), ray.findClosestPoint(list4));

    }
}
