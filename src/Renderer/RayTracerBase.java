package Renderer;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 * an abstract class.
 */
public abstract class RayTracerBase
{
    /**
     * field
     */
    protected Scene scene;
    protected boolean softShadows =false;
    protected double beamRadius =20d;//אלומת אור
    protected boolean antiA=false;

    /**
     * ctr
     * @param scene the scene
     */
    public RayTracerBase(Scene scene)
    {
        this.scene = scene;
    }


    /**
     * method
     * @param ray rey
     * @return color
     */
    public abstract Color traceRay(Ray ray,int beam);

    public abstract Color traceRay(List<Ray> ray,int beam);

    /**
     * return true if we want soft shadows
     * @param softShadows user decision
     * @return this
     */
    public void setSoftShadows(boolean softShadows) {
        this.softShadows = softShadows;
        // return this;
    }

    /**
     * for the soft shadows
     * @param beamRadius radius of the conus
     */
    public void setBeamRadius(double beamRadius) {
        this.beamRadius = beamRadius;
        //return this;
    }

    /**
     * for anti aliasing
     * @param anti answer
     */
    public void setAntiA(boolean anti)
    {
        this.antiA = anti;
        //return this;
    }
    public boolean isSoftShadows() {
        return softShadows;
    }

    public double getBeamRadius() {
        return beamRadius;
    }

    public boolean isAntiA() { return antiA; }
}
