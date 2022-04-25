package csc2a.ui;

import csc2a.designpatterns.RenderGameObjectVisitor;
import csc2a.util.KMBuffer;
import javafx.scene.canvas.Canvas;
/**
 * 
 * Canvas used to render all of your GameObjects using the Visitor
 * This is the Client in the Visitor Design Pattern
 * @author  <YOUR DETAILS HERE>
 *
 */
public class GameCanvas extends Canvas{
	
	//Attributes
	RenderGameObjectVisitor visitor = new RenderGameObjectVisitor();
	/* TODO: Store all of your GameObjects (Using GameObjectContainers) here */
	
	/**
	 * Default Constructor
	 */
	public GameCanvas() {
		/*
		 * Construct your Game Canvas as you see fit
		 */
	}
	
	/* TODO: Set your GameObjects and redrawCanvas() */
	
	/**
	 * Method used to redraw the canvas whenever called
	 */
	public void redrawCanvas(){
		requestFocus();
		/* TODO: Get GraphicsContext */
		/* TODO: Set Visitor's GraphicsContext */
		/* TODO: Iterate through ALL GameObjects (Using GameObjectContainers) */
			/* TODO: Get EACH GameObject to accept() the Visitor */
	}	
}
