package Renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Camera producing rays through a view plane
 */
public class Camera
{
    /**
     * fields
     */

    // camera eye
    private  Point p0;

    // vector pointing upwards : Y axis
    private  Vector vUp;

    // vector pointing towards the scene
    private  Vector vTo;

    // vector pointing towards the right : X axis
    private  Vector vRight;

    // cameras distance from ViewPlane
    private double distance;

    // ViewPlane width
    private double width;

    // ViewPlane height
    private double height;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    /**
     *
     * @param p0
     * @param vTo
     * @param vUp
     * a constructor
     */
    public Camera(Point p0, Vector vTo, Vector vUp)
    {
        if(!isZero(vUp.dotProduct(vTo)))
        {
            throw  new IllegalArgumentException("vTo and vUp should be orthogonal");
        }

        this.p0 = p0;

        //normalizing the positional vectors
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();

        this.vRight = this.vTo.crossProduct(this.vUp);

    }

    /**
     *
     * @return point
     * getter to "p0"
     */
    public Point getP0()
    {
        return p0;
    }

    /**
     *
     * @return vector
     * getter to "vUp"
     */
    public Vector getvUp()
    {
        return vUp;
    }

    /**
     *
     * @return vector
     * getter to "vTo"
     */
    public Vector getvTo()
    {
        return vTo;
    }

    /**
     *
     * @return vector
     * getter to "vRight"
     */
    public Vector getvRight()
    {
        return vRight;
    }

    /**
     *
     * @param p0
     * setter for "p0"
     */
    public void setP0(Point p0)
    {
        this.p0 = p0;
    }

    /**
     *
     * @param vUp
     * setter for "vUp"
     */
    public void setvUp(Vector vUp)
    {
        this.vUp = vUp;
    }

    /**
     *
     * @param vTo
     * setter for "vTo"
     */
    public void setvTo(Vector vTo)
    {
        this.vTo = vTo;
    }

    /**
     *
     * @param vRight
     * setter for "vRight"
     */
    public void setvRight(Vector vRight)
    {
        this.vRight = vRight;
    }

    /**
     * set distance between the camera and it's view plane
     * @param distance the  distance for the view plane
     * @return instance of Camera for chaining
     */
    public Camera setVPDistance(double distance)
    {
        this.distance = distance;
        return this;
    }

    /**
     * setting View Plane size
     * @param width     "physical" width
     * @param height    "physical" height
     * @return instance of Camera for chaining
     */
    public Camera setVPSize(double width, double height)
    {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     *
     * @param imageWriter
     * @return camera
     * setter of "imageWriter" in builder pattern
     */
    public Camera setImageWriter(ImageWriter imageWriter)
    {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     *
     * @param rayTracer
     * @return camera
     * setter of "rayTracer"
     */
    public Camera setRayTracer(RayTracerBase rayTracer)
    {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Constructing a ray through a pixel
     *
     * @param Nx number of pixels widthwise
     * @param Ny number of pixels heightwise
     * @param j Y value of pixel wanted
     * @param i x value of pixel wanted
     * @return ray form the camera to Pixel[i,j]
     */
    public Ray constructRay(int Nx, int Ny, int j, int i)
    {
        //Pixel center
        Point Pc = p0.add(vTo.scale(distance));

        //Ratio (pixel width & height)
        double Ry =height/ Ny;
        double Rx = width/Nx;

        //delta values for going to Pixel[i,j]  from Pc

        double yI =  -(i - (Ny -1)/2d)* Ry;
        double xJ =  (j - (Nx -1)/2d)* Rx;

        if (! isZero(xJ) )
        {
            Pc = Pc.add(vRight.scale(xJ));
        }

        if (! isZero(yI))
        {
            Pc = Pc.add(vUp.scale(yI));
        }

        return new Ray(p0, Pc.subtract(p0));
    }

    /**
     * a methode that creates an image from the code
     */
    public void writeToImage()
    {
        if(imageWriter == null)
            throw new MissingResourceException("missing image writer", "Camera", "in writeTorImage");
        imageWriter.writeToImage();
    }

    /**
     *
     * @return  camera
     * a methode that rendering the image
     */
    public Camera renderImage()
    {
        try
        {
            if (imageWriter == null)
            {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }

            if (rayTracer == null)
            {
                throw new MissingResourceException("missing resource", RayTracerBasic.class.getName(), "");
            }

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nY; i++)
            {
                for (int j = 0; j < nX; j++)
                {
                    //for anti aliasing:
                    Color pixelColor;
                    if(rayTracer.isAntiA()){
                        List<Ray> rays = constructRays(nX, nY, j, i);
                        pixelColor = rayTracer.traceRay(rays,200);
                    }
                    else{
                        Ray ray = constructRay(nX, nY, j, i);
                        pixelColor = rayTracer.traceRay(ray,200);
                    }

                    imageWriter.writePixel(j, i, pixelColor);
                }
            }
        }

        catch (MissingResourceException e)
        {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }

        return this;
    }

    /**
     *
     * @param interval
     * @param color
     * a methode that prints a grid
     */
    public void printGrid(int interval, Color color)
    {
        if(imageWriter == null)
            throw new MissingResourceException("missing image writer", "Camera", "in print Grid");

        for (int j = 0; j< imageWriter.getNx(); j++)
            for (int i = 0; i< imageWriter.getNy(); i++)
                if(i%interval==0 || j%interval==0)
                    imageWriter.writePixel(j, i, color);
    }


    /**
     *
     * @param Nx
     * @param Ny
     * @param j
     * @param i
     * @return
     * improving the jagged edges - mini project 1
     */
    public List<Ray> constructRays(int Nx, int Ny, int j, int i)
    {
        //Image center
        Point Pc = p0.add(vTo.scale(distance));

        //Ratio (pixel width & height)
        double Ry =height/ Ny;
        double Rx = width/Nx;

        //delta values for going to Pixel[i,j] from Pc
        double yI =  -(i - (Ny -1)/2)* Ry;
        double xJ =  (j - (Nx -1)/2)* Rx;

        if (! isZero(xJ) )
        {
            Pc = Pc.add(vRight.scale(xJ));
        }

        if (! isZero(yI))
        {
            Pc = Pc.add(vUp.scale(yI));
        }
        List<Ray> rays=new ArrayList<>();

        /**
         * puts the pixel center in the first place on the list
         */
        rays.add(new Ray(p0,Pc.subtract(p0)));

        /**
         * creating Ry*Rx rays for each pixel.
         */
        Point newPoint=new Point(Pc.getX()-Rx/2,Pc.getY()+Rx/2,Pc.getZ());
        for (double t = newPoint.getY(); t >newPoint.getY()-Ry; t-=0.01)
        {
            for (double k = newPoint.getX(); k < newPoint.getX()+Rx; k+=0.01)
            {
                rays.add(new Ray(p0,new Point(k,t,Pc.getZ()).subtract(p0)));
            }
        }

        return rays;
    }

    public Camera setsSoftShadows(boolean isSoft)
    {
        rayTracer.setSoftShadows(isSoft);
        return this;
    }

    public Camera setsBeamRadius(double r)
    {
        rayTracer.setBeamRadius(r);
        return this;
    }

    public Camera setsAntiA(boolean a)
    {
        rayTracer.setAntiA(a);
        return this;
    }

}
