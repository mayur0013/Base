/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author siddharth
 */
public class PointLight
{
    private BaseLight baseLight;
    private Attenuation atten;
    private Vector3f position;

    public PointLight(BaseLight baseLight, Attenuation atten, Vector3f position) {
        this.baseLight = baseLight;
        this.atten = atten;
        this.position = position;
    }

    public BaseLight getBaseLight() {
        return baseLight;
    }

    public void setBaseLight(BaseLight baseLight) {
        this.baseLight = baseLight;
    }

    public Attenuation getAtten() {
        return atten;
    }

    public void setAtten(Attenuation atten) {
        this.atten = atten;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }
    
    
}
