package com.drawHist.Hist;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
  
public class RGBFrame extends JFrame {
	
public static String testMsg(){
	
	return "ok"; //test message
}

	 private BufferedImage myimg;
	
	 private  int h=700;
	 private  int[] Rfreq; //colors
	 private  int[] Gfreq;//colors
	 private  int[] Bfreq;//colors
	 private  int rcolor;
	 private  int gcolor;
	 private  int bcolor;
	 private  int rgb;
	 private  String outputFile;
	 
	 public RGBFrame(){
		 
	 }
	     
    public RGBFrame(BufferedImage newimg, String outputFile) {
        setSize(700,700);
        myimg=newimg;
        setVisible(true);
        this.outputFile = outputFile;
    }
     
    public void paint(Graphics g) {
    	
        Graphics2D g2D=(Graphics2D)g;
        BufferedImage bImg = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
        g2D = bImg.createGraphics();
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, 700, 700);
        
        Rfreq=new int[256];
        Gfreq=new int[256];
        Bfreq=new int[256];
        int width = myimg.getWidth();
        int height = myimg.getHeight();
        for(int i=0;i<width;i++) {
            for(int j=0;j<height;j++) {
                 
                rgb = myimg.getRGB(i,j);//get all pixels
                
                rcolor = (rgb >> 16) & 0xff;//red
                gcolor = (rgb >> 8)  & 0xff;//green
                bcolor = (rgb >> 0)  & 0xff;//blue
                Rfreq[rcolor]++;
                Gfreq[gcolor]++;
                Bfreq[bcolor]++;
            }
        }
        
        Integer sq = width*height;
        Integer mult = sq/307200;
        Integer scale = 20*mult;
        if (scale<=0) { 
        	scale=5;
        }
       int k=1;
       for(int i=0;i<256;i++) {
           
            g2D.setColor(Color.RED);
            g2D.drawLine(20+i+k,h-25,20+i+k,h-(Rfreq[i]/scale)-25);
            g2D.drawLine(20+i+k+1,h-25,20+i+k+1,h-(Rfreq[i]/scale)-25);
            g2D.setColor(Color.GREEN);
            g2D.drawLine(20+i+k,h-25,20+i+k,h-(Gfreq[i]/scale)-25);
            g2D.drawLine(20+i+k+1,h-25,20+i+k+1,h-(Gfreq[i]/scale)-25);
            g2D.setColor(Color.BLUE);
            g2D.drawLine(20+i+k,h-25,20+i+k,h-(Bfreq[i]/scale)-25);
            g2D.drawLine(20+i+k+1,h-25,20+i+k+1,h-(Bfreq[i]/scale)-25);
            
            k++;
         
             
        } 
      
        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(3));
        g2D.drawLine(10,h-10,10,h-600);//axis
        g2D.drawLine(10,h-10,600,h-10);//axis
        g2D.setFont(new Font("Arial",Font.BOLD,15));
        
        g2D.drawString("0-->255",14,h-12);
        g2D.drawString("Histogram",600,100);
       
       
       
        try {
			ImageIO.write(bImg, "png", new File(outputFile));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
        System.exit(0);
    }
     
  

    


}

