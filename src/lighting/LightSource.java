package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * an interface
 */
public interface LightSource
{
    public  Color getIntensity(Point p);

    public  Vector getL(Point p);

    double getDistance(Point point);

    /**
     * return vectors with wanted radius in ratio with distance of light source from point
     */
    public List<Vector> getBeamL(Point p, double radius,int amount);

}
