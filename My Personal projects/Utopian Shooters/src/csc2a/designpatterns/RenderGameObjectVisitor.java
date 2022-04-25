package csc2a.designpatterns;

import javafx.scene.canvas.GraphicsContext;

/**
 * ConcreteVisitor class
 * Used to visit each GameObject and render them onto the GameCanvas
 * @author  <YOUR DETAILS HERE>
 *
 */
public class RenderGameObjectVisitor implements iRenderVisitor{
	
	//Attributes
	GraphicsContext gc = null;
	
	/**
	 * Mutator for the GraphicsContext from the GameCanvas
	 * Used to set gc so the Visitor can draw things on the Canvas
	 * @param gc the GraphicsContext from the GameCanvas
	 */
	public void setGraphicsContext(GraphicsContext gc) {
		this.gc = gc;
	}
	
	
	/* TODO: render(YourGameObjectA a){} method */
	
	/* TODO: render(YourGameObjectB b){} method */
	
	// ...
	
	/* TODO: render(YourGameObjectC m){} method */
	

}
