package Lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends  PointLight
{
    /**
     * field
     */
    private Vector direction;

    /**
     * methods
     */

    /**
     *
     * @param intensity
     * @param p0
     * @param dir
     * a constructor
     */
    public SpotLight(Color intensity,Point p0, Vector dir)
    {
        super(intensity,p0);
        this.direction=dir.normalize();
    }

    /**
     * implementation of {@link LightSource#getIntensity(Point)}
     * @param p
     * @return color
     * a methode that gets the intensity in a point
     */
    public Color getIntensity(Point p)
    {
        double max= Math.max(0,direction.dotProduct(getL(p)));
        return super.getIntensity(p).scale(max);
    }
}
