/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;
import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author siddharth
 */
public class Texture {
    
    private int id;
    
    public Texture(int id)
    {
        this.id = id;
    }
    
    public void bind()
    {
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    public int getID()
    {
        return id;
    }
}
