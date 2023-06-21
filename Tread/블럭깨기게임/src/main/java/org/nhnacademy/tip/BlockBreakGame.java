package org.nhnacademy.tip;

import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import java.awt.*;

public class BlockBreakGame extends JFrame{
    final BoundedWorld world;
    JTextField scoreField;
    JTextField countField;
    JTextField blockField;
    HpBlock bar;
    
    public BlockBreakGame(int width, int height){
        super();
        setSize(width,height);
        setLayout(null);
        getContentPane().setBackground(Color.darkGray);
        world = new BoundedWorld(width -100, height -200);
        world.setLocation(50, 100);
        BoundedBall ball1 = new BoundedBall(new Point(50,50), 10, Color.black);
        ball1.setMotion(new Vector(8,40));
        MovableBlock mvBox = new MovableBlock(new Point(world.getWidth()/2 - 100/2, world.getHeight()/2), 100, 10, -180);
        MovableBlock mvBox2 = new MovableBlock(new Point(world.getWidth()/2 - 100/2, world.getHeight()/3),100 ,10 , 0);
        MovableBlock mvBox3 = new MovableBlock(new Point(world.getWidth()/2 - 100/2, (world.getHeight()/2)+(world.getHeight()/4)),100 ,10 , -180);
        world.add(mvBox3);
        world.add(mvBox);
        world.add(mvBox2);
        world.add(ball1);
        initGame();
        add(world);
        setKeyListener();
        bar = new Bar(new Point(world.getWidth()/2 - 100/2, 100),80 ,10);
        world.add(bar);
        world.addBlockList(bar);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel blockLabel = new JLabel();
        blockLabel.setForeground(Color.yellow);
        blockLabel.setBounds(world.getX(), world.getY() - 50, 90, 40);
        blockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        blockLabel.setText("Block : ");
        blockLabel.setBackground(Color.yellow);
        add(blockLabel);

        blockField = new JTextField();
        blockField.setBackground(Color.BLACK);
        blockField.setBounds(blockLabel.getX() + blockLabel.getWidth() + 10, blockLabel.getY() , 90, 40);
        blockField.setEnabled(false);
        add(blockField);

        JLabel countLabel = new JLabel();
        countLabel.setForeground(Color.yellow);
        countLabel.setBounds(blockField.getX()+blockField.getWidth(), blockField.getY() , 90, 40);
        countLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        countLabel.setText("BoundedCount : ");
        countLabel.setBackground(Color.yellow);
        add(countLabel);

        countField = new JTextField();
        countField.setBackground(Color.BLACK);
        countField.setBounds(countLabel.getX() + countLabel.getWidth() + 10, countLabel.getY() , 90, 40);
        ((Bounded) ball1).addCollisionEventListener(event -> {
            if (world.getList().contains(event.getDestination())) {
                world.addCount();
                countField.setText(String.valueOf(world.boundCount()));
                blockField.setText(String.valueOf(world.getBlock()));
            }
        });
        countField.setEnabled(false);
        add(countField);
        
        JLabel scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.yellow);
        scoreLabel.setBounds(countField.getX()+countField.getWidth(), countField.getY() , 90, 40);
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        scoreLabel.setText("Score : ");
        scoreLabel.setBackground(Color.yellow);
        add(scoreLabel);

        scoreField = new JTextField();
        scoreField.setBackground(Color.BLACK);
        scoreField.setBounds(scoreLabel.getX() + scoreLabel.getWidth() + 10, scoreLabel.getY() , 90, 40);
        ((Bounded) ball1).addCollisionEventListener(event -> {
            if (world.getList().contains(event.getDestination())) {
                scoreField.setText(String.valueOf(world.getScore()));
            }
        });
        scoreField.setEnabled(false);
        add(scoreField);

        ((Bounded) ball1).addCollisionEventListener(event -> {
            if(event.getDestination() instanceof Bar){
                if (((Bar)event.getDestination()).getName()=="bar" && world.getList().contains(event.getDestination())) {
                    if(ball1.getLocation().getX() < bar.getLocation().getX() - ball1.getWidth()+ bar.getWidth()/5){
                        ball1.getMotion().add(new Vector(1,ball1.getMotion().getAngle()*1.2));
                    }
                    if(ball1.getLocation().getX() > bar.getLocation().getX() - ball1.getWidth() + bar.getWidth()/5
                    && ball1.getLocation().getX() < bar.getLocation().getX() - ball1.getWidth() + bar.getWidth()/5*2){
                        ball1.getMotion().add(new Vector(1,ball1.getMotion().getAngle()*1.2));
                    }
                    if(ball1.getLocation().getX() > bar.getLocation().getX() - ball1.getWidth() + bar.getWidth()/5*3
                    && ball1.getLocation().getX() < bar.getLocation().getX() - ball1.getWidth() + bar.getWidth()/5*4){
                        ball1.getMotion().add(new Vector(1,ball1.getMotion().getAngle()*0.8));
                    }
                    if(ball1.getLocation().getX() > bar.getLocation().getX() - ball1.getWidth() + bar.getWidth()/5*4
                    && ball1.getLocation().getX() < bar.getLocation().getX() - ball1.getWidth() + bar.getWidth()){
                        ball1.getMotion().add(new Vector(1,ball1.getMotion().getAngle()*0.8));
                    }
                }
            }
            
        });
        ((Bounded) ball1).addCollisionEventListener(event -> {
            if(event.getDestination() instanceof SpeedUpBlock){
                ball1.getMotion().add(new Vector(((SpeedUpBlock)event.getDestination()).getSpeed(),0));
            }
        });




    }

    public void run(long seconds) {
        world.run(seconds);
    }

    public void close() {
        Thread.currentThread().interrupt();
    }
    public void initGame(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 12 ; j++){
                HpBlock hpblockd;
                switch(new Random().nextInt(4)){
                    case 0: hpblockd = new HpBlock(new Point(50*j+j+25,(world.getHeight()-i*21-50)),50,20,Color.blue, 1); world.add(hpblockd); world.addBlockList(hpblockd); break;
                    case 1: hpblockd = new Hp2upBlock(new Point(50*j+j+25,(world.getHeight()-i*21-50)),50,20);world.add(hpblockd);world.addBlockList(hpblockd); break;
                    case 2: hpblockd = new UnbreakBlock(new Point(50*j+j+25,(world.getHeight()-i*21-50)),50,20); world.add(hpblockd); world.addBlockList(hpblockd);break;
                    case 3: hpblockd = new SpeedUpBlock(new Point(50*j+j+25,(world.getHeight()-i*21-50)),50,20); world.add(hpblockd); world.addBlockList(hpblockd);break;
                }
            }
        }
    }

    public void setKeyListener() {
        this.addKeyListener( new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { 
                if( e.getKeyCode() == KeyEvent.VK_LEFT ) {
                    bar.movebar(-10);
                }
                else if( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                    bar.movebar(10);
                }
            }
        });
    }
}


