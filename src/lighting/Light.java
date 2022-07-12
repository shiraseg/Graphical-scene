package lighting;


import primitives.Color;

/**
 * an abstract class
 */
abstract class Light
{
    /**
     * field
     */
    private Color intensity;

    /**
     * methods
     */

    /**
     *
     * @param intensity
     * a constructor
     */
    protected Light(Color intensity)
    {
        this.intensity = intensity;
    }

    /**
     *
     * @return color
     * getter for "intensity"
     */
    public Color getIntensity()
    {
        return intensity;
    }

}
