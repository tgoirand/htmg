package com.lib.workshop;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class VirtualImage implements Iterable<Pixel> {

	protected int[][] image; // todo change everything to an array

	// protected VirtualImage(int[][] image) {
	// this.image = image;
	// }

	int tolerance = 10;

	int tone = Color.BLACK.getRGB();

	Color background;

	public VirtualImage(Color background) {
		this.background = background;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public int getTolerance() {
		return tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	public int getTone() {
		return tone;
	}

	public void setTone(int tone) {
		this.tone = tone;
	}

	public void incrementTone() {
		tone++;
	}

	public int getPos(Pixel p) {
		return image[p.getX()][p.getY()];
	}

	public int getSize() {
		return image.length * image[0].length;
	}

	public Iterator<Pixel> iterator() {

		ArrayList<Pixel> res = new ArrayList<Pixel>();

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				res.add(new Pixel(i, j));
			}
		}

		return res.iterator();

	}

	public Pixel getCenter() {
		int cx = 0;
		int cy = 0;

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				if (image[i][j] != Color.white.getRGB()) {
					if ((i - (image.length / 2)) < (cx - (image.length / 2)))
						cx = i;
					if ((j - (image.length / 2)) < (cy - (image.length / 2)))
						cx = i;
				}
			}
		}

		return new Pixel(cx, cy);
	}

	protected int[] flat() {
		int[] flat = new int[image.length * image.length];
		int incr = 0;
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				flat[incr++] = image[i][j];
			}
		}
		return flat;
	}

	public int surface() {
		int surface = 0;
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				if (image[i][j] != Color.white.getRGB())
					surface++;
			}
		}
		return surface;
	}

}
