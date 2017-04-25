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
public class Game {
    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private Camera camera;
    private Material material;
    //private Texture texture;
    public Game()
    {
        mesh = new Mesh(); ResourceLoader.loadMesh("teddybear.obj"); 
        shader = new PhongShader();
        camera = new Camera();
        transform = new Transform();
        material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(0,1,1));
       // texture = ResourceLoader.loadTexture("test.png");
      /* Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0,0)),
                                      new Vertex(new Vector3f(0,1,0), new Vector2f(1,0)),
                                      new Vertex(new Vector3f(1,-1,0), new Vector2f(1,1)),
                                      new Vertex (new Vector3f(0,-1,1), new Vector2f(0.5f,0))};
        int[] indices = new int[]{3,1,0,
                                  1,3,2,
                                  2,3,0,
                                  1,2,0};
        
        mesh.addVertices(vertices, indices, true);*/
        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-10f, 0f, -10f), new Vector2f(0.0f, 0.0f)),
                                            new Vertex(new Vector3f(-10f, 0f, -30f), new Vector2f(0.0f, 0.0f)),
                                        new Vertex(new Vector3f(30f, 0.0f, -10f), new Vector2f(0.0f, 0.0f)),
                                        new Vertex(new Vector3f(10f, 0f, 30), new Vector2f(0.0f, 0.0f))};
        
        int indices[] = { 0, 1, 2, 2, 1, 3};
        mesh.addVertices(vertices, indices, true);
        Transform.setProjection(70f, window.getWidth(), window.getHeight(), 0.1f, 1000);
        Transform.setCamera(camera);
       // shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
       // shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
       // shader.compileShader();
        RenderUtil.setTexture(true);
      //  shader.addUniform("transform");        
        PhongShader.setAmbientLight(new Vector3f(0.2f,0.2f,0.2f));
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
       // PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0,0), 0.8f), new Attenuation(0,0,1), new Vector3f(-2,0,3f));
       // PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0,0,1), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f));
       // PhongShader.setPointLight(new PointLight[]{pLight1, pLight2});
    }
    
    public void input()
    {
        camera.input();
               
    }
    
    public void render()
    {
        RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
       // shader.bind();
          shader.bind();
          shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(),material);
       // shader.setUniform("transform", transform.getProjectedTransformation());
        mesh.draw();
        //texture.bind();
        
    }
    float temp = 0.0f;
    
    public void update()
    {        
        
        temp += Time.getDelta();
        float sinTemp = (float)Math.sin(temp);
       // transform.setRotation(0,sinTemp*180,0);
       // transform.setTranslation(sinTemp,0,5);
       // transform.setScale(0.1f,0.1f,0.1f);
    }
    
}
