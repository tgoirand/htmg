package com.lib.workshop;

import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {

	private static final File f = new File("reception.jpg");

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		JFrame frame = new JFrame("for test");
		frame.setBackground(Color.white);
		frame.setSize(200, 200);

//		Container c = frame.setContentPane(contentPane);

		BufferedImage image = ImageIO.read(new File(
				"/Users/al/some-picture.jpg"));
		
		
		
		
		

	}
}
