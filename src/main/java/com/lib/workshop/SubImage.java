package com.lib.workshop;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SubImage extends VirtualImage implements Comparable<FontImage> {

	public SubImage(BufferedImage img, Color background) {
		super(background);
		super.image = getMatrice(img);
	}

	public SubImage(BufferedImage img) {
		super(Color.white);
		super.image = getMatrice(img);
	}


	public int[][] getMatrice(BufferedImage image) {

		int[][] mat = new int[image.getWidth()][image.getWidth()];

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				mat[i][j] = image.getRGB(j, i);
			}
		}

		return mat;
	}

	public int getMin() {
		int min = 0;

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				if (image[i][j] < min)
					min = image[i][j];
			}
		}
		return min;
	}

	public int getMax() {
		int min = 0;

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				if (image[i][j] > min)
					min = image[i][j];
			}
		}
		return min;
	}

	public int coverage() {
		int coverage = 0;

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				// System.out.println((tone) + ":" + image[i][j]);
				if (((image[i][j] > (tone - tolerance)) && (image[i][j] < (tone + tolerance)))) {
					coverage++;
				}
			}
		}

		return coverage;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				// sb.append(image[i][j] + "");
				sb.append(((image[i][j] > (tone - tolerance)) && (image[i][j] < (tone + tolerance))) ? 'M'
						: '.');
			}
			sb.append('\n');
		}

		return sb.toString();
	}

	@Override
	public int compareTo(FontImage o) {
		int[] t1 = this.flat();
		int[] fontimage = o.flat();

		int occurences = 0;
		int overlay = 0;

		for (int i = 0; i < t1.length; i++) {
			if (fontimage.length < i && (fontimage[i] > (t1[i] - tolerance)
					&& fontimage[i] < (t1[i] + tolerance))) {
				occurences++;
			} else if (o.getTone() > (t1[i] - tolerance)
					&& o.getTone() < (t1[i] + tolerance)) {
				overlay++;
			}
		}

		o.surface();

		return 0;
	}

}
