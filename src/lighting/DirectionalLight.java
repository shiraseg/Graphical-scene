package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import java.util.List;

public class DirectionalLight extends Light implements LightSource
{

    /**
     * field
     */
    private Vector direction;

    /**
     *
     * @param intensity
     * @param dir
     * a constructor
     */
    public DirectionalLight(Color intensity, Vector dir)
    {
        super(intensity);
        this.direction=dir.normalize();
    }

    /**
     * implementation of {@link LightSource#getIntensity(Point)}
     * @param p
     * @return color
     * a methode that gets the intensity in a point
     */
    @Override
    public Color getIntensity(Point p)
    {
        return super.getIntensity();
    }

    /**
     * implementation of {@link LightSource#getL(Point)}
     * @param p
     * @return vector
     * a methode that returns the normalized direction.
     */
    @Override
    public Vector getL(Point p)
    {
        return this.direction.normalize();
    }

    /**
     * implementation of {@link LightSource#getDistance(Point)}
     * @param point
     * @return infinity
     * because directional light is very far away, then the distance is infinity.
     */
    @Override
    public double getDistance(Point point)
    {
        return Double.POSITIVE_INFINITY;
    }


    @Override
    public List<Vector> getBeamL(Point dummyPoint3D, double dummyRadius, int dummyInt) {
        return List.of(new Vector(new Double3(direction.getX(),direction.getY(),direction.getZ())));
    }

}
