/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;
/**
 *
 * @author siddharth
 */
public class RenderUtil {
    
    public static void clearScreen()
    {
        //TODO : Stencil Buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    
    public static void setTexture(boolean enabled)
    {
        if(enabled)
            glEnable(GL_TEXTURE_2D);
        else
            glDisable(GL_TEXTURE_2D);
    }
    public static void initGraphics()
    {
        glClearColor(0.0f, 0.0f , 0.0f, 0.0f);
        
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        
       //TODO: Depth Clamp
       glEnable(GL_DEPTH_CLAMP);
       glEnable(GL_TEXTURE_2D);
       glEnable(GL_FRAMEBUFFER_SRGB);
       
    }
    
    public static void setClearColor(Vector3f color)
    {
        glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
    }
    
    public static String GetOpenGLVersion()
    {
        return glGetString(GL_VERSION);
    }
    
    public static void unbindTextures()
    {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
