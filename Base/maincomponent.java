/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;

/**
 *
 * @author siddharth
 */
public class maincomponent {
    
    public static final int height = 400;
    public static final int width = 800;
    public static final String title = "PHSYICS ENGINE";
    private boolean isRunning;
    private static final double frame_cap = 5000.0;
    private Game game;
    public maincomponent()
    {
        System.out.println(RenderUtil.GetOpenGLVersion());
        RenderUtil.initGraphics();
        game = new Game();
        isRunning = false;
    }
    public void start()
    {
        if(isRunning)
            return;
        
        run();
    }

    public void stop()
    {
        if(!isRunning)
            return;
        
        isRunning = false;                   
    }
    
    private void run()
    {
        int frames = 0;
        long frameCounter = 0;
        boolean render = false;
        isRunning = true;
        final double frameTime = 1.0 / frame_cap;
        long lastTime = Time.getTime();
        double unprocessedTime = 0;
        while(isRunning)
        {
            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;
            unprocessedTime += passedTime / (double)Time.second;
            frameCounter += passedTime;
            while(unprocessedTime > frameTime)
            {
                render = true;
                unprocessedTime -= frameTime;
                //CLOSE REQUEST
                if(Display.isCloseRequested())
                    stop();
                
                Time.setDelta(frameTime);
                
                //TAKING INPUTS
                game.input();
                Input.update();
                game.update();
                
                //PRINT FRAME RATE
                if(frameCounter >= Time.second)
                {
                    Display.setTitle("FRAME RATE : " + frames + " PER SECOND");
                    frames = 0;
                    frameCounter = 0;
                }
            }
            
            if(render)   
            {
                render();
                frames++;
            }//if
            else
            {
                try {
                    Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(maincomponent.class.getName()).log(Level.SEVERE, null, ex);
            }
            }//else
        }
        cleanup();
    }
    private void render()
    {
        RenderUtil.clearScreen();
        game.render();
        window.render();
    } 
    
    private void cleanup()
    {
        window.dispose();
    }   
    
    public static void main(String args[])
    {
        window.creatWindow(height,width,title);
        maincomponent screen = new maincomponent();
        screen.start();
    }
    
}
