package com.lib.workshop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class FontImage extends VirtualImage {
//	private boolean[][] data;
	private char c;

	public FontImage(Font f, char c) {
		super(Color.white);
		image = getMatrice(f, c);
	}

	public FontImage(Font f, char c, Color background) {
		super(background);
		image = getMatrice(f, c);
//		super(image);
//		this.data = new int[size][size];
	}

	
	private int[][] getMatrice(Font f, char c) {

		int size = f.getSize();

		BufferedImage bi = new BufferedImage(size, size,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(getBackground());
		g.setFont(f);

		FontRenderContext frc = g.getFontRenderContext();

		TextLayout tl = new TextLayout(c + "", f, frc);
		tl.draw(g, 0, size);
		
		int[][] mat = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mat[i][j] = bi.getRGB(j, i);
			}
		}
		
		return mat;
	}

	/*
	public FontImage(int size, char c) {
		this.c = c;
		//this.data = new boolean[size][size];
	}

	public Color getPos(int x, int y) {
		return data[x][y];
	}

	public boolean getPos(Pixel p) {
		return data[p.getX()][p.getY()];
	}

	public void setValue(int x, int y, boolean val) {
		data[x][y] = val;
	}

	public int getSize() {
		ArrayList<Pixel> res = new ArrayList<Pixel>();

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[i][j])
					res.add(new Pixel(i, j));
			}
		}

		return res.size();
	}

	public Iterator<Pixel> iterator() {

		ArrayList<Pixel> res = new ArrayList<Pixel>();

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[i][j])
					res.add(new Pixel(i, j));
			}
		}

		return res.iterator();

	}

	public Pixel getCenter() {
		int cx = 0;
		int cy = 0;

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[i][j]) {
					if ((i - (data.length / 2)) < (cx - (data.length / 2)))
						cx = i;
					if ((j - (data.length / 2)) < (cy - (data.length / 2)))
						cx = i;
				}
			}
		}

		return new Pixel(cx, cy);
	}

	public int surface() {
		int surface = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[i][j])
					surface++;
			}
		}
		return surface;
	}*/

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				sb.append(new Color(image[i][j]).equals(getBackground()) ? c : '.');
			}
			sb.append('\n');
		}

		return sb.toString();
	}

	public char getChar() {
		return c;
	}
}
