package com.example.rect;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import android.graphics.Canvas;
import android.graphics.Paint;
public class Cub {
	
	private final static int edge = 4; // amount of vertex
	private float[] x = new float [2*edge];
	private float[] y = new float [2*edge];
	private float a = 100; //width of cub
	private float x0 = 200; //initial coordinates
	private float y0 = 200;
	private int angle = 0;
	
	public Cub(){

		for (int i=0; i<edge;i++){
			x[i]=0;
			y[i]=0;
		}
		
	}
	
	private double rad(int angle){
		double rad = PI/180*angle;
		return rad;
	}
	
	public void recalculate(){
		angle++;
		if (angle>359) angle = 0;
		for (int i=0;i<edge;i++){
			x[i]=(float)(x0+a*cos(rad(angle)+i*2*PI/edge));
			y[i]=(float)(y0+a*0.5*sin(rad(angle)+i*2*PI/edge));
			x[i+edge]=(float)(x0+a*cos(rad(angle)+i*2*PI/edge));
			y[i+edge]=(float)(y0+a+a*0.5*sin(rad(angle)+i*2*PI/edge));
			
		}
	}

	
	public void setX0(float x0) {
		this.x0 = x0;
	}

	public void setY0(float y0) {
		this.y0 = y0;
	}

	public void draw(Canvas canvas, Paint p){
		//draw first plan		
		for(int i=0;i<edge-1;i++){
			canvas.drawLine(x[i],y[i],x[i+1],y[i+1],p);
		}
		canvas.drawLine(x[edge-1],y[edge-1],x[0],y[0],p);
		
		//draw second plan		
		for(int i=edge;i<2*edge-1;i++){
			canvas.drawLine(x[i],y[i],x[i+1],y[i+1],p);
		}
		canvas.drawLine(x[2*edge-1],y[2*edge-1],x[edge],y[edge],p);
		
		//draw edges
		for(int i=0;i<edge;i++){
			canvas.drawLine(x[i],y[i],x[i+edge],y[i+edge],p);
		}
		
	}
	
}