package com.lib.workshop;

import java.awt.image.BufferedImage;

public class PictureManager {
	
	private BufferedImage image;
	private char allowedChars;

	
	public PictureManager(BufferedImage image) {
		this.image = image;
	}
	
	protected int[][] getSample(int x, int y, int w, int h) {
		 int[][] r = new int[w][h];
		 for (int j = 0; j < h; j++) {
			 for (int i = 0; i < w; i++) {
				 r[i][j] = image.getRGB(x + i, y + j);
			 }
		 }
		 return r;
	}
	
	
}
