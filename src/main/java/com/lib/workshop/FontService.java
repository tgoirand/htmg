package com.lib.workshop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;

public class FontService {

	/*
	public static FontImage getMatrice(Font f, char c) {

		int size = f.getSize();
		FontImage res = new FontImage(size, c);

		BufferedImage bi = new BufferedImage(size, size,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(Color.WHITE);
		g.setFont(f);

		FontRenderContext frc = g.getFontRenderContext();

		TextLayout tl = new TextLayout(c + "", f, frc);
		tl.draw(g, 0, size);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				res.setValue(j, i, bi.getRGB(i, j) != -16777216);
				// res[j][i] = bi.getRGB(i, j) != -16777216;
			}
		}

		return res;
	}*/

	public static SubImage getImagePart(BufferedImage image, int x, int y,
			int size) {
		BufferedImage sub = image.getSubimage(x, y, size, size);
		
		SubImage si = new SubImage(sub);

		return si;
	}

}
