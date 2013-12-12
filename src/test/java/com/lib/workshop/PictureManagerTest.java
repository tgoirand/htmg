package com.lib.workshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.media.jai.codec.PNGEncodeParam;
import com.sun.media.jai.codecimpl.PNGCodec;
import com.sun.media.jai.codecimpl.PNGImageEncoder;

public class PictureManagerTest {

	private static final File f = new File(
			"/Users/goirandt/Pictures/jaws07.jpg");

	@Test
	public void testIncrementTone() throws IOException {
		BufferedImage image = ImageIO.read(f);
		int fontsize = 12;
		char[] c = { 'T', 'H', 'O', 'M', 'A', 'S', '.', 'G', 'O', 'I', 'R',
				'A', 'N', 'D', 't', 'h', 'o', 'm', 'a', 's', '.', 'g', 'o',
				'i', 'r', 'a', 'n', 'd', 'l', '@', 'L', 'B', '&' };

		Collection<FontImage> fi = new ArrayList<FontImage>();

		Font font = new Font("Verdana", Font.PLAIN, fontsize);

		for (int i = 0; i < c.length; i++) {
			fi.add(new FontImage(font, (char) c[i]));
		}

		BufferedImage bi = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_INT_RGB);
		Color dominantColor = Util.getDominant(image, 10);

		Graphics2D g = bi.createGraphics();
		g.setBackground(dominantColor);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());

		// for (int j = 0; j < ((image.getHeight() / fontsize) ); j++) {
		// for (int i = 0; i < ((image.getWidth() / fontsize)); i++) {

		for (int j = 0; j < 1; j++) {
			for (int i = 0; i < 1; i++) {
				SubImage subImage = FontService.getImagePart(image, i
						* fontsize, j * fontsize, fontsize);
				subImage.setTone(subImage.getMin());
				for (int tone = subImage.getMin(); tone < subImage.getMax(); tone++) {
					subImage.incrementTone();
					if (subImage.coverage() > 0)
						System.out.println(subImage);
				}

			}
		}

	}

	@Test
	public void testIteratePicture() throws IOException {
		BufferedImage image = ImageIO.read(f);
		int fontsize = 60;
		char[] c = { 'T', 'H', 'O', 'M', 'A', 'S', '.', 'G', 'O', 'I', 'R',
				'A', 'N', 'D', 't', 'h', 'o', 'm', 'a', 's', '.', 'g', 'o',
				'i', 'r', 'a', 'n', 'd', 'l', '@', 'L', 'B', '&' };
		// char[] c = { '@', 'L', 'B', 'i' };

		Collection<FontImage> fi = new ArrayList<FontImage>();

		Font font = new Font("Verdana", Font.PLAIN, fontsize);

		for (int i = 0; i < c.length; i++) {
			fi.add(new FontImage(font, (char) c[i]));
		}

		/*
		 * for (int i = 10; i < 128; i++) { fi.add(FontService.getMatrice(font,
		 * (char) i)); }
		 */

		BufferedImage bi = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_INT_RGB);
		Color dominantColor = Util.getDominant(image, 10);

		// BufferedImage bi = image;
		Graphics2D g = bi.createGraphics();
		g.setBackground(dominantColor);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());

		// for (int j = 0; j < ((image.getHeight() / fontsize) ); j++) {
		// for (int i = 0; i < ((image.getWidth() / fontsize)); i++) {

		for (int j = 0; j < 1; j++) {
			for (int i = 0; i < 1; i++) {
				SubImage subImage = FontService.getImagePart(image, i
						* fontsize, j * fontsize, fontsize);

				Set<ImageCompare> ts = new TreeSet<ImageCompare>();
				for (FontImage fontImage : fi) {
					ts.add(new DefaultImageCompare(fontImage, subImage, 10));
				}

				ImageCompare imageCompare = ts.iterator().next();
				FontImage fif = imageCompare.getFontImage();

				g.setColor(new Color((int) imageCompare.getMedian()));
				FontRenderContext frc = g.getFontRenderContext();

				TextLayout tl = new TextLayout(fif.getChar() + "", font, frc);
				tl.draw(g, (i * fontsize), (j * fontsize) + fontsize);

			}
		}

		FileOutputStream fos = new FileOutputStream(new File(
				"/Users/goirandt/Pictures/transform.jpg"));
		// PNGImageEncoder encoder = PNGCodec.createImageEncoder("png", fos,
		// PNGEncodeParam.getDefaultEncodeParam());
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		encoder.encode(bi);

		fos.close();

	}

	@Test
	public void testLayersFonts() throws IOException {
		BufferedImage image = ImageIO.read(f);
		char[] c = { 'T', 'H', 'O', 'M', 'A', 'S', '.', 'G', 'O', 'I', 'R',
				'A', 'N', 'D', 't', 'h', 'o', 'm', 'a', 's', '.', 'g', 'o',
				'i', 'r', 'a', 'n', 'd', 'l', '@', 'L', 'B', '&' };
		// char[] c = { '@', 'L', 'B', 'i' };

		Collection<FontImage> fi = new ArrayList<FontImage>();

		// BufferedImage bi = new BufferedImage(image.getWidth(),
		// image.getHeight(), BufferedImage.TYPE_INT_RGB);
		BufferedImage bi = image;

		Color dominantColor = Util.getDominant(image, 1);

		int fontsize = 12;

		Font font = new Font("Verdana", Font.PLAIN, fontsize);

		// for (int i = 10; i < 128; i++) {
		// fi.add(new FontImage(font, (char) i));
		// // fi.add(FontService.getMatrice(font, (char) i));
		// }
		//
		for (int i = 0; i < c.length; i++) {
			System.out.println(c + "," + new FontImage(font, c[i]));
			fi.add(new FontImage(font, c[i]));
			// fi.add(FontService.getMatrice(font, (char) i));
		}

		Graphics2D g = bi.createGraphics();
		// g.setBackground(dominantColor);
		// g.fillRect(0, 0, image.getWidth(), image.getHeight());

		SubImage subImage = FontService.getImagePart(image, 0, 0, fontsize);

		Set<ImageCompare> ts = new TreeSet<ImageCompare>();
		for (FontImage fontImage : fi) {
			ts.add(new DefaultImageCompare(fontImage, subImage, 10));
		}

		ImageCompare imageCompare = ts.iterator().next();
		FontImage fif = imageCompare.getFontImage();

		g.setColor(new Color((int) imageCompare.getMedian()));
		FontRenderContext frc = g.getFontRenderContext();

		TextLayout tl = new TextLayout(fif.getChar() + "", font, frc);
		tl.draw(g, 0, fontsize);

		FileOutputStream fos = new FileOutputStream(new File(
				"/Users/goirandt/Pictures/transform.jpg"));
		// PNGImageEncoder encoder = PNGCodec.createImageEncoder("png", fos,
		// PNGEncodeParam.getDefaultEncodeParam());
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		encoder.encode(bi);

		fos.close();

	}

	@Test
	public void testFonts() throws Exception {
		Font f = new Font("Verdana", Font.PLAIN, 12);

		for (int c = 0; c < 256; c++) {
			FontImage r = new FontImage(f, (char) c);
			System.out.println(r.toString());
		}

		/*
		 * GraphicsEnvironment env = GraphicsEnvironment
		 * .getLocalGraphicsEnvironment(); Font[] allfonts = env.getAllFonts();
		 * 
		 * for (Font f : allfonts) { System.out.println(f.getName()); }
		 */

		//

		/*
		 * BufferedImage image = new BufferedImage(100, 50,
		 * BufferedImage.TYPE_INT_ARGB);
		 */

		BufferedImage bi = new BufferedImage(12, 12, BufferedImage.TYPE_INT_RGB);

		Graphics2D g = bi.createGraphics();
		// g.create(0, 0, 20, 20);
		//
		g.setBackground(Color.cyan);
		g.setColor(Color.WHITE);
		g.setFont(f);

		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);

		// g.setPaint ( new Paint());

		FontRenderContext frc = g.getFontRenderContext();
		TextLayout tl = new TextLayout("A", f, frc);
		tl.draw(g, 0, 12);

		// g.draw(s)
		// Dimension theSize=getSize()

		// GlyphVector msg = f.createGlyphVector(g.getFontRenderContext(),
		// "Hello");
		// g.drawGlyphVector(msg, 0, 0);

		FileOutputStream fos = new FileOutputStream(new File(
				"/tmp/myimage.jpeg"));
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		encoder.encode(bi);

		fos.close();

	}

	@Test
	public void valTest() {
		double a = (double) 1 / (double) 3;
		System.out.println(a);
	}

	// @Test
	public void testGetPart() throws Exception {

		BufferedImage image = ImageIO.read(new File("reception.jpg"));
		image.getData();
		PictureManager pm = new PictureManager(image);

	}

}
