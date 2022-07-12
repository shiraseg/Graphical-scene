package geometries;

import primitives.*;

import java.util.List;

/**
 * an abstract class with builder pattern
 */
public abstract class Geometry extends Intersectable
{
    /**
     * fields
     */
    private Material material= new Material();

    protected Color emission =Color.BLACK;

    /**
     * methods
     */

    /**
     *
     * @return color
     * getter for "emission"
     */
    public Color getEmission()
    {
        return this.emission;
    }

    /**
     *
     * @return material
     * getter for "material"
     */
    public Material getMaterial()
    {
        return material;
    }

    /**
     *
     * @param p
     * @return vector
     * a methode that helps us find normal in point
     */
    abstract public Vector getNormal(Point p);

    /**
     *
     * @param material
     * @return Geometry
     * setter in builder pattern
     */
    public Geometry setMaterial(Material material)
    {
        this.material = material;
        return this;
    }

    /**
     *
     * @param emission
     * @return Geometry
     *  setter in builder pattern
     */
    public Geometry setEmission(Color emission)
    {
        this.emission = emission;
        return this;
    }


}