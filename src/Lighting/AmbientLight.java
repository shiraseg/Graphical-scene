package Lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light
{

    /**
     *
     * @param ia
     * @param ka
     * a constructor
     */
    public AmbientLight(Color ia, Double3 ka)
    {
        super(ia.scale(ka));    //the constructor of the father class is working.
    }

    /**
     * default constructor for the background to be black
     */

    public AmbientLight()
    {
        super(Color.BLACK);
    }
}
