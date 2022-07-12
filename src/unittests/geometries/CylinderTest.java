package unittests.geometries;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void getNormal()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Cylinder cy = new Cylinder(
                new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)),
                2,
                3);
        // test for point on first disk
        assertEquals(new Vector(0,0, 1), cy.getNormal(new Point(0, 1, 1)));

        assertEquals(new Vector(0,0, 1), cy.getNormal(new Point(0, 1, 4)));

        assertEquals(new Vector(0,1, 0), cy.getNormal(new Point(0, 2, 3)));
    }

    @Test
    void FindIntersections() {
    }
}