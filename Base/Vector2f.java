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
public class Vector2f {
    
    private float x,y;
    
    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public float length()
    {
        return (float)Math.sqrt(x*x + y*y);
    }
    public float dot(Vector2f r)
    {
        return x*r.getX() + y*r.getY();
    }
    public Vector2f normalize()
    {
        float length = length();
        x /= length;
        y /= length;
        return this;
    }
    //HARD
    public Vector2f rotate(float angle)
    {
        double rad = (double)Math.toRadians(angle);
        double cos = (double)Math.cos(rad);
        double sin = (double)Math.sin(rad);
        
        return new Vector2f((float)(x*cos - y*sin),(float)(x*sin + y*cos));
    }
    
    public Vector2f add(Vector2f r)
    {
        return new Vector2f(x+r.getX(),y+r.getY());
    }
    
    public Vector2f add(float r)
    {
        return new Vector2f(x+r,y+r);
    }
    
    public Vector2f sub(Vector2f r)
    {
        return new Vector2f(x-r.getX(),y-r.getY());
    }
    
    public Vector2f sub(float r)
    {
        return new Vector2f(x-r,y-r);
    }
    
    public Vector2f mul(Vector2f r)
    {
        return new Vector2f(x*r.getX(),y*r.getY());
    }
    
    public Vector2f mul(float r)
    {
        return new Vector2f(x*r,y*r);
    }
    
    public Vector2f div(Vector2f r)
    {
        return new Vector2f(x/r.getX(),y/r.getY());
    }
    
    public Vector2f div(float r)
    {
        return new Vector2f(x/r,y/r);
    }
    
    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
    
    public float getX()
    {
        return x;
    }
    
    public float getY()
    {
        return x;
    }
    
    public void setX(float x)
    {
        this.x = x;
    }
    
    public void setY(float y)
    {
        this.y = y;
    }
}
