package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    @Test
    void scale() {
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows (IllegalArgumentException.class, () -> v1.crossProduct(v3),"crossProduct() for parallel vectors does not throw an exception");

    }

    @Test
    void dotProduct() {

        Vector v1=new Vector(1,1,1);
        Vector v2=new Vector(2,-2,0);
        Vector v3=new Vector(4,5,6);

        // =============== Boundary Values Tests ==================
        assertTrue(isZero(v1.dotProduct(v2)),"ERROR: dotProduct() for orthogonal vectors is not zero");

        // ============ Equivalence Partitions Tests ==============
        assertEquals(-2,v2.dotProduct(v3),"ERROR: dotProduct() wrong value");


    }

    @Test
    void length()
    {
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5),"ERROR: length() wrong value");
    }

    @Test
    void lengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
        assertTrue(isZero(v1.lengthSquared() - 14),"ERROR: lengthSquared() wrong value");
    }

    @Test
    void normalize() {
        Vector v = new Vector(1, 2, 3);
        double lenght = Math.sqrt(14);
        Vector vn = new Vector(1/lenght,2/lenght,3/lenght);
        Vector u = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        assertEquals(vn,u,"ERROR:Problem with normalization function.");

        assertTrue(isZero(u.length() - 1),"ERROR: the normalized vector is not a unit vector");

        // =============== Boundary Values Tests ==================
        assertThrows( IllegalArgumentException.class,
                () -> {v.crossProduct(new Vector(2,4,6));},
                "ERROR: the normalized vector is not parallel to the original one"); // test that the vectors are co-lined

        // =============== Boundary Values Tests ==================
        assertTrue (v.dotProduct(u) >= 0,"ERROR: the normalized vector is opposite to the original one");
    }

    @Test
    void testEquals()
    {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 2, 3);
        assertEquals(v1,v2,"ERROR: the vectors are equal");
    }
}