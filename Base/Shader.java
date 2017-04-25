/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.util.HashMap;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

/**
 *
 * @author siddharth
 */
public class Shader {
    private int program;
    private HashMap<String,Integer>  uniforms;
    public Shader()
    {
        program = glCreateProgram();
        uniforms = new HashMap<String, Integer>();
        if(program == 0)
        {
            System.out.println("SHADER CREATER FAILED: COULD NOT FIND LOCATION");
            System.exit(1);
        }
    }
    
    public void bind()
    {
        glUseProgram(program);
    }
    
    public void addUniform(String uniform)
    {
        int uniformLocation = glGetUniformLocation(program, uniform);
       
        if(uniformLocation == 0xFFFFFFFF)
        {
            System.err.println("Error: Could not find uniform" + uniform);
            new Exception().printStackTrace();
            System.exit(1);
        }
        
        uniforms.put(uniform,uniformLocation);
    }
    public void addVertexShader(String text)
    {
        addProgram(text, GL_VERTEX_SHADER);
    }
    
    public void addGeometryShader(String text)
    {
        addProgram(text, GL_GEOMETRY_SHADER);
    }
    
    public void addFragmentShader(String text)
    {
        addProgram(text, GL_FRAGMENT_SHADER);
    }
    
    public void compileShader()
    {
        glLinkProgram(program);
        if(glGetProgram(program, GL_LINK_STATUS) == 0)
        {
            System.err.println(glGetProgramInfoLog(program,1024));
            System.exit(1);
        }
        glValidateProgram(program);
        if(glGetProgram(program, GL_VALIDATE_STATUS) == 0)
        {
            System.err.println(glGetProgramInfoLog(program,1024));
            System.exit(1);
        }
    }
    private void addProgram(String text, int type)
    {
        int shader = glCreateShader(type);
        if(shader == 0)
        {
            System.err.println("SHADER CREATION FAILED: COULD NOT FIND VALID SHADER");
            System.exit(1);
        }
        
        glShaderSource(shader, text);
        glCompileShader(shader);
        
        if(glGetShader(shader, GL_COMPILE_STATUS) == 0)
        {
            System.err.println(glGetShaderInfoLog(shader,1024));
            System.exit(1);
        }
        glAttachShader(program,shader);
    }
    
    public void setUniformi(String uniformName, int value)
    {
        glUniform1i(uniforms.get(uniformName), value);
    }
    
    public void setUniformf(String uniformName, float value)
    {
        glUniform1f(uniforms.get(uniformName), value);
    }
    
    public void setUniform(String uniformName, Vector3f value)
    {
        glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
    }
    
    public void setUniform(String uniformName, Matrix4f value)
    {
        glUniformMatrix4(uniforms.get(uniformName), true, Util.createFlippedBuffer(value));
    }
    
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
    {
        
    }
}
