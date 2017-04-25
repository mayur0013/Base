/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author siddharth
 */
public class Camera 
{
    public static final Vector3f yAxis = new Vector3f(0,1,0);
    
    private Vector3f pos;
    private Vector3f forward;
    private Vector3f up;
    
    public Camera()
    {
        this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
    }
    public Camera(Vector3f pos, Vector3f forward, Vector3f up)
    {
        this.pos = pos;
        this.forward = forward;
        this.up = up;
        
        up.normalize();
        forward.normalize();
    }

    public void move(Vector3f dir, float amt)
    {
        pos = pos.add(dir.mul(amt));
    }
    boolean mouseLocked = false;
    Vector2f centerPosition = new Vector2f(window.getWidth()/2 , window.getHeight()/2); 
    
    public void input()
    {
        float moveAmt = (float)(10 * Time.getDelta());
        float rotAmt = (float) (100 * Time.getDelta());
        float sensitivity = 0.5f;
        
        if(Input.getKey(Keyboard.KEY_ESCAPE))
        {
            Input.setCursor(true);
            mouseLocked = false;
        }
        
        if(Input.getMouseDown(0))
        {
            Input.setMousePosition(centerPosition);
            Input.setCursor(false);
            mouseLocked = true;
        }
        
        if(Input.getKey(Keyboard.KEY_W))
            move(getForward(),moveAmt);
        if(Input.getKey(Keyboard.KEY_A))
            move(getLeft(),moveAmt);
        if(Input.getKey(Keyboard.KEY_D))
            move(getRight(),moveAmt);
        if(Input.getKey(Keyboard.KEY_S))
            move(getForward(),-moveAmt);
        
        if(Input.getKey(Keyboard.KEY_UP))
                rotateX(-rotAmt);
        if(Input.getKey(Keyboard.KEY_DOWN))
                rotateX(rotAmt);
        if(Input.getKey(Keyboard.KEY_LEFT))
                rotateY(-rotAmt);
        if(Input.getKey(Keyboard.KEY_RIGHT))
                rotateY(rotAmt);
        
        if(mouseLocked)
        {
            Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);
            
            boolean rotY = deltaPos.getX() != 0;
            boolean rotX = deltaPos.getY() != 0;
            
            if(rotY)
                rotateY(deltaPos.getX() * sensitivity);
            if(rotX)
                rotateX(-deltaPos.getY() * sensitivity);
            
            if(rotY || rotX)
                Input.setMousePosition(new Vector2f(window.getWidth()/2, window.getHeight()/2));
        }
    }
    public void rotateY(float angle)
    {
        Vector3f Haxis = yAxis.cross(forward);
        Haxis.normalize();
        
        forward.rotate(angle, yAxis);
        forward.normalize();
        
        up = forward.cross(Haxis);
        up.normalize();
    }
    
    public void rotateX(float angle)
    {
       Vector3f Haxis = yAxis.cross(forward);
       Haxis.normalize();
       
       forward.rotate(angle, Haxis);
       forward.normalize();
       
       up = forward.cross(Haxis);
       up.normalize();
    }
    
    public Vector3f getLeft()
    {
        Vector3f left = forward.cross(up);        
        left.normalize();        
        return left;                
    }
    
    public Vector3f getRight()
    {
        Vector3f right = up.cross(forward);
        right.normalize();
        return right;
    }
            
    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }
    
    
}
