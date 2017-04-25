/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import static org.lwjgl.opengl.GL20.glUniform3f;

/**
 *
 * @author siddharth
 */
public class PhongShader extends Shader 
{
    private static final int MAX_POINT_LIGHTS = 4;
    private static final PhongShader instance = new PhongShader();
    
    public static PhongShader getInstance()
    {
        return instance;
    }
    
    private  static Vector3f ambientLight = new Vector3f(0.1f,0.1f,0.1f);
    private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0), new Vector3f(0,0,0));
    private static PointLight[] pointLights = new PointLight[] {};
    public PhongShader ()
    {
        super();
        addVertexShader(ResourceLoader.loadShader("phongVertex.vs"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment.fs"));
        compileShader();
        
        addUniform("transform");
        addUniform("baseColor");
        addUniform("ambientLight");
        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");
        addUniform("transformProjected");
        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
        
       /* for(int i = 0; i < MAX_POINT_LIGHTS; i++)
        {
            addUniform("pointLights[" + i + "].base.color");
            addUniform("pointLights[" + i + "].base.intensity");
            addUniform("pointLights[" + i + "].atten.constant");
            addUniform("pointLights[" + i + "].atten.linear");
            addUniform("pointLights[" + i + "].atten.exponent");
            addUniform("pointLights[" + i + "].position");
        }*/

    }
    
    @Override
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
    {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();
        
        setUniform("transformProjected", projectedMatrix);
        setUniform("transform", worldMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight",ambientLight);
       
        setUniform("directionalLight", directionalLight);
       /* for(int i=0;i < pointLights.length; i++)
            setUniform("pointLights[" + i + "]", pointLights[i]);*/
        setUniformf("specularIntensity", material.getSpecularIntensity());
        setUniformf("specularPower", material.getSpecularPower());
        setUniform("eyePos", Transform.getCamera().getPos());
    }

    public static void setPointLight(PointLight[] pointLights)
    {
        if(pointLights.length > MAX_POINT_LIGHTS)
        {           
            System.err.println("Error: TOO MANY POINT LIGHTS");
            new Exception().printStackTrace();
            System.exit(1);
        }
        
        PhongShader.pointLights = pointLights;
    }
    public  static Vector3f getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight) {
       PhongShader.ambientLight = ambientLight;
    }
    
    public static void setDirectionalLight(DirectionalLight directionalLight) {
       PhongShader.directionalLight = directionalLight;
    }
    
    public void setUniform(String uniformName, BaseLight baseLight)
    {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
        
    }
    
    public void setUniform(String uniformName, DirectionalLight directionalLight)
    {
        setUniform(uniformName + ".base", directionalLight.getBase());
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }
    
    public void setUniform(String uniformName, PointLight pointLight)
    {
        setUniform(uniformName + ".base",pointLight.getBaseLight());
        setUniformf(uniformName + ".atten.constant", pointLight.getAtten().getConstant());
        setUniformf(uniformName + ".atten.linear", pointLight.getAtten().getLinear());
        setUniformf(uniformName + ".atten.constant", pointLight.getAtten().getExponent());
        setUniform(uniformName + ".position", pointLight.getPosition());
    }
}
