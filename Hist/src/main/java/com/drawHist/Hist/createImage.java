package com.drawHist.Hist;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class createImage {

	public static void main(String[] args) throws IOException, ParseException {
		
		// create Options object
		Options options = new Options();
		options.addOption("i", true, "input file");
		options.addOption("o", true, "output file");
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);
		
		String inputFile = cmd.getOptionValue("i");
		String outputFile = cmd.getOptionValue("o");
		
	
		BufferedImage img = ImageIO.read(new File(inputFile));
		RGBFrame hist = new RGBFrame(img,outputFile);
		
		

	}

}
