package levels_and_states;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

import simpleGraphicsExample.InputHandler;

public class TestLevel extends GameState{

	InputHandler input;

	private int x1 = 200;
	private int y1 = 400;

	private int x2 = 100;
	private int y2 = 200;

	final double P1SPEED = 2.5;
	final double P2SPEED = 3;

	double p1HSpeed, p1VSpeed, p2HSpeed, p2VSpeed;

	int p1up = 0;
	int p1down = 0;
	int p1right = 0;
	int p1left = 0;
	int p2up = 0;
	int p2down = 0;
	int p2right = 0;
	int p2left = 0;

	public TestLevel(GameStateManager gsm){
		super(gsm);
	}


	public void init() {
	}


	public void tick() {
	}


	public void draw(Graphics g) {


		//Direction Calculations
		System.out.println("tick");
		p1HSpeed = (-p1left + p1right) * P1SPEED;
		p1VSpeed = (p1down + -p1up) * P1SPEED;
		p2HSpeed = (-p2left + p2right) * P2SPEED;
		p2VSpeed = (p2down + -p2up) * P2SPEED;

		x1 += p1HSpeed;
		y1 += p1VSpeed;
		x2 += p2HSpeed;
		y2 += p2VSpeed;

		g.setColor(Color.RED);
		g.fillRect(this.x2, this.y2, 20, 4);
		g.setColor(Color.BLUE);
		g.fillRect(this.x1, this.y1, 80, 30);
	}


	public void keyPressed(int k) {
		System.out.println("pressed");
		p1up = 0;
		p1down = 0;
		p1right = 0;
		p1left = 0;
		p2up = 0;
		p2down = 0;
		p2right = 0;
		p2left = 0;

		//Player 1 input
		if (k == KeyEvent.VK_UP){
			p1up = 1;
		}
		if (k == KeyEvent.VK_DOWN){
			p1down = 1;
		}
		if (input.isKeyDown(KeyEvent.VK_RIGHT)){
			p1right = 1;
		}
		if (k == KeyEvent.VK_LEFT){
			p1left = 1;
		}

		//Player 2 input
		if (k == KeyEvent.VK_W){
			p2up = 1;
		}
		if (k == KeyEvent.VK_S){
			p2down = 1;
		}
		if (k == KeyEvent.VK_D){
			p2right = 1;
		}
		if (k == KeyEvent.VK_A){
			p2left = 1;
		}

	}


	public void keyReleased(int k) {

	}

}
