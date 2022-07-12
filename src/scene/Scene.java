package scene;

import Lighting.AmbientLight;
import Lighting.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * a class with a builder pattern
 */
public class Scene
{
    /**
     * fields
     */
    private final String name;
    private final Color background;
    private AmbientLight ambientLight;

    public Geometries geometries;
    private List<LightSource> lights=new LinkedList<>();

    /**
     * methods
     */

    /**
     *
     * @param builder
     * a constructor with a builder pattern
     */
    private Scene(SceneBuilder builder)
    {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
        lights =  builder.lights;
    }

    /**
     *
     * @return ambient light
     * getter to "ambientLight"
     */
    public AmbientLight getAmbientLight()
    {
        return ambientLight;
    }

    /**
     *
     * @return list of light sources
     * getter to "lights"
     */
    public List<LightSource> getLights()
    {
        return lights;
    }

    /**
     *
     * @return string
     * getter to "name"
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return color
     * getter to "background"
     */
    public Color getBackground()
    {
        return background;
    }

    /**
     *
     * @param ambientLight
     * @return scene
     * setter for "ambientLight"
     */
    public Scene setAmbientLight(AmbientLight ambientLight)
    {
        this.ambientLight=ambientLight;
        return this;
    }


    /**
     * using the builder pattern
     */
    public static class SceneBuilder
    {
        /**
         * fields
         */
        private final String name;
        public List<LightSource> lights = new LinkedList<>();
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        /**
         *
         * @param name
         * setter of "name"
         */
        public SceneBuilder(String name)
        {
            this.name = name;
        }

        /**
         *
         * @param lights
         * @return scene builder
         * setter of "lights"
         */
        public SceneBuilder setLights(List<LightSource> lights)
        {
            this.lights = lights;
            return this;
        }

        /**
         *
         * @param background
         * @return scene builder
         * setter of "background"
         */
        public SceneBuilder setBackground(Color background)
        {
            this.background = background;
            return this;
        }

        /**
         *
         * @param ambientLight
         * @return scene builder
         * setter of "ambientLight"
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight)
        {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         *
         * @param geometries
         * @return scene builder
         * setter of geometries
         */
        public SceneBuilder setGeometries(Geometries geometries)
        {
            this.geometries = geometries;
            return this;
        }

        /**
         *
         * @return scene
         * this methode is "building" the scene
         */
        public Scene build()
        {
            Scene scene = new Scene(this);

            return scene;
        }

    }
}