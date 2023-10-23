import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;
import java.text.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{   
    ScoreBoard scoreBoard;
    float combo = 0;
    int shotDone = 0;
    int shotMiss = 0;
    Boards accBoard;
    Boards comboBoard;
    
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    private void recalculateAcc(){
        if(shotMiss<=0){
            accBoard.setMessage("acc: "+ 100);
            return;
        }
        
        int hit = shotDone - shotMiss;
        double accuracy = (double) hit/shotDone * 100;
        accBoard.setMessage("acc: "+ df.format(accuracy));
    }
    
    public void incShotDone(){
        this.shotDone++;
        recalculateAcc();
    }
    
    public void incShotMiss(){
        this.shotMiss++;
        recalculateAcc();
    }
    
    public MyWorld()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 500, 1);
        SpawnPlaer();
        this.scoreBoard = new ScoreBoard();
        this.addObject(scoreBoard, 100, 30);
        this.setPaintOrder(Characters.class, Boards.class, Props.class, Evironments.class);
        accBoard = new Boards();
        this.addObject(accBoard, 100, 60);
        comboBoard = new Boards();
        this.addObject(comboBoard, 80, 120);
    }
    
    private void spawnRandomObject(){
        Random rnd = new Random();
        Evironments env = new Evironments();
        this.addObject(env, rnd.nextInt(this.getWidth() - 30 ), 0);
    }
    
    private void SpawnPlaer(){
        Random rnd = new Random();
        Player p1 = new Player();
        p1.setRotation(270);
        this.addObject(p1, rnd.nextInt(this.getWidth()-30),this.getHeight()-30);
    }
    
    private void SpawnEnemies(){
        Random rnd = new Random();
        for(int i = 0 ;i<rnd.nextInt(5); i++){
            if(i%2==0){
                Kutu kutu = new Kutu();
                this.addObject(kutu, rnd.nextInt(this.getWidth() -30), this.getHeight()-30);
            }
            Enemies en = new Enemies();
            this.addObject(en, rnd.nextInt(this.getWidth() - 30), 5);
        }
    }
    
    public void act(){
        spawnRandomObject();
        List<Enemies> enemies = this.getObjects(Enemies.class);
        if(enemies.size()==0){
            SpawnEnemies();
        }
    }
}
