package primitives;

/**
 * a class with builder pattern
 */
public class Material
{
    /**
     * fields
     */

    //Diffuse
    public Double3 kD=Double3.ZERO;
    //Specular
    public Double3 kS=Double3.ZERO;
    //transparency - שקיפות
    public Double3 kT=Double3.ZERO;
    //השתקפות -reflection
    public Double3 kR=Double3.ZERO;
    int nShininess=0;

    /**
     *
     * @param kT
     * @return material
     * setter to "kt" in builder pattern
     */
    public Material setKt(Double3 kT)
    {
        this.kT = kT;
        return this;
    }

    /**
     *
     * @param kR
     * @return material
     * setter to "kt" in builder pattern
     */
    public Material setKr(Double3 kR)
    {
        this.kR = kR;
        return this;

    }

    /**
     *
     * @param kD
     * @return material
     * setter to "kd" in builder pattern
     */
    public Material setKd(Double3 kD)
    {
        this.kD = kD;
        return this;
    }

    /**
     *
     * @param kS
     * @return material
     * setter to "ks" in builder pattern
     */
    public Material setKs(Double3 kS)
    {
        this.kS = kS;
        return this;
    }

    /**
     *
     * @param kD
     * @return material
     * setter to "kd" in builder pattern for the second constructor in Double3
     */
    public Material setKd(Double kD)
    {
        this.kD=new Double3(kD);
        return this;
    }

    /**
     *
     * @param kS
     * @return material
     * setter to "ks" in builder pattern for the second constructor in Double3
     */
    public Material setKs(Double kS)
    {
        this.kS=new Double3(kS);
        return this;
    }

    /**
     *
     * @param nShininess
     * @return material
     * setter to "nshininess" in builder pattern.
     */
    public Material setnShininess(int nShininess)
    {
        this.nShininess = nShininess;
        return this;
    }

    /**
     *
     * @return int
     * getter for "nshininess"
     */
    public int getnShininess()
    {
        return nShininess;
    }
}
