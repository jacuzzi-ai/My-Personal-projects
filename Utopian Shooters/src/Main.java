Main.java

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
   // Create Constructors

   public Pane root;

   public List<GameObject> bullets = new ArrayList<>();
   public List<GameObject> enemies = new ArrayList<>();
   public int score = 0;

   public GameObject player;

   public Parent createContent() {
       root = new Pane();
       root.setPrefSize(500, 500);
       root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null), null, null));

       player = new Player();
       player.setVelocity(new Point2D(1, 0));
       addGameObject(player, 300, 300);

// Start Animation Timer

       AnimationTimer timer = new AnimationTimer() {
           @Override
           public void handle(long now) {
               if(player.isAlive())
                   onUpdate();
               else
               {
                   showScore();
                   this.stop();
               }
           }

       };
       timer.start();

       return root;
   }
  
  
   public void showScore() {
       GameObject g = new GameObject(new Rectangle(root.getHeight(),root.getWidth()));
      
       root.getChildren().add(g.getView());
       Label l = new Label("Game OverYour Score is: "+score);
       l.setAlignment(Pos.CENTER);
       l.setFont((new Font("Arial", 30)));
       l.setTextFill(Color.WHITE);
      
       root.getChildren().add(l);
  
   }
// Add the bullets and enemies

   public void addBullet(GameObject bullet, double x, double y) {
       bullets.add(bullet);
       addGameObject(bullet, x, y);
   }

   public void addEnemy(GameObject enemy, double x, double y) {
       enemies.add(enemy);
       addGameObject(enemy, x, y);
   }

   public void addGameObject(GameObject object, double x, double y) {
       object.getView().setTranslateX(x);
       object.getView().setTranslateY(y);
       root.getChildren().add(object.getView());
   }

// Provide collions for the bullets and enimes

   public void onUpdate() {
       for (GameObject bullet : bullets) {
           for (GameObject enemy : enemies) {
               if (bullet.isColliding(enemy)) {
                   bullet.setAlive(false);
                   enemy.setAlive(false);
                   score += 2;
                   root.getChildren().removeAll(bullet.getView(), enemy.getView());
               }
           }
       }

       for (GameObject enemy : enemies) {
           if (player.isColliding(enemy)) {
               player.setAlive(false);
               root.getChildren().removeAll(player.getView());
           }
       }

       bullets.removeIf(GameObject::isDead);
       enemies.removeIf(GameObject::isDead);

       bullets.forEach(GameObject::update);
       enemies.forEach(GameObject::update);

       player.update();

// Generate the enemies

       if (Math.random() < 0.03) {
           addEnemy(new Enemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
       }
   }

// Inherit the player,bullets, and enemies from the GameObject class

   public class Player extends GameObject {
       Player() {
           super(new Rectangle(40, 20, Color.BLUE));
       }
   }

   public class Enemy extends GameObject {
       Enemy() {
           super(new Circle(15, 15, 15, Color.RED));
       }
   }

   public class Bullet extends GameObject {
       Bullet() {
           super(new Circle(5, 5, 5, Color.BROWN));
       }
   }

// Shoot the bullets
   @Override
   public void start(Stage stage) throws Exception {
       stage.setScene(new Scene(createContent()));
       stage.getScene().setOnKeyPressed(e -> {
           if (e.getCode() == KeyCode.LEFT) {
               player.rotateLeft();
           } else if (e.getCode() == KeyCode.RIGHT) {
               player.rotateRight();
           } else if (e.getCode() == KeyCode.SPACE) {
               Bullet bullet = new Bullet();
               bullet.setVelocity(player.getVelocity().normalize().multiply(5));
               addBullet(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
           }
       });
       stage.show();
   }

   public static void main(String[] args) {
       launch(args);
   }
}

