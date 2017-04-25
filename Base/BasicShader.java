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
public class BasicShader extends Shader
{
    private static final BasicShader instance = new BasicShader();
    
    public static BasicShader getInstance()
    {
        return instance;
    }
    
    public BasicShader()
    {
        super();
        addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
        addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
        compileShader();
        
        addUniform("transform");
        addUniform("color");
    }
    
    @Override
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
    {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();
        
        setUniform("transform", projectedMatrix);
        setUniform("color", material.getColor());
    }
}
