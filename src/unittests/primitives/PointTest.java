package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void add() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(p1.add(new Vector(-1, -2, -3)),new Point(0, 0, 0),"ERROR: Point + Vector does not work correctly");
    }

    @Test
    void subtract() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(new Vector(1, 1, 1),new Point(2, 3, 4).subtract(p1),"ERROR: Point - Point does not work correctly");
    }

    @Test
    void distanceSquared()
    {
        Point point1=new Point(2,1,1);
        Point point2=new Point(0,1,1);
        assertEquals(4,point1.distanceSquared(point2),"ERROR: wrong distance squared.");
    }

    @Test
    void distance()
    {
        Point point1=new Point(1,1,1);
        Point point2=new Point(0,1,1);
        assertEquals(1,point1.distance(point2),"ERROR: wrong distance between points.");

    }
}