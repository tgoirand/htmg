package com.lib.workshop;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

public class DefaultImageCompare implements ImageCompare {
	FontImage fontImage;
	SubImage subImage;
	int tolerance = 5;
	Map<Integer, Float> coverageFont = null;
	Map<Integer, Float> coverageImage = null;

	private static Logger log = Logger.getLogger(DefaultImageCompare.class
			.getName());

	public DefaultImageCompare(FontImage fontImage, SubImage subImage,
			int tolerance) {
		this.fontImage = fontImage;
		this.subImage = subImage;
		this.tolerance = tolerance;
	}

	private void calculateCoverage() {
		double med = getMedian();

		coverageFont = new TreeMap<Integer, Float>();
		coverageImage = new TreeMap<Integer, Float>();

		float fontsize = fontImage.getSize();

		for (int i = 0; i < tolerance; i++) {
			float total = 0;
			float match = 0;

			double minmatch = med - i;
			double maxmatch = med + i;

			for (Pixel p : fontImage) {
				int val = subImage.getPos(p);
				if (fontImage.getPos(p) > val && (fontImage.getPos(p) - val < minmatch) || fontImage.getPos(p) < val && (val - fontImage.getPos(p) < minmatch))
					match++;
				total++;
			}
			coverageFont.put(i, match + 1 / fontsize + 1);
			coverageImage.put(i, match + 1 / total + 1);
		}
	}

	@Override
	public int compareTo(ImageCompare o) {
		return (int) (relevance() - o.relevance());
	}

	private double median = 0;

	@Override
	public double getMedian() {
		/*
		 * if (median == 0) { DescriptiveStatistics stats = new
		 * DescriptiveStatistics(); int end = 0; for (Pixel p : fontImage) {
		 * stats.addValue(subImage.getPos(p)); end = subImage.getPos(p); }
		 * //median = end - stats.getStandardDeviation(); median = ; }
		 */
		return subImage.getPos(fontImage.getCenter());
	}

	@Override
	public float getImageCoverage() {
		if (coverageImage == null)
			calculateCoverage();

		float res = 1;
		for (float val : coverageImage.values()) {
			res = res * val;
		}
		return res;
	}

	public int getLongestContinueConverage() {
		int max = 0;

		for (Pixel p : fontImage) {
			// if (fontImage.getPos(p))
		}
		return 0;
	}

	@Override
	public float getFontCoverage() {
		if (coverageFont == null)
			calculateCoverage();

		float res = 1;
		for (float val : coverageFont.values()) {
			res = res * val;
		}
		return res;
	}

	@Override
	public FontImage getFontImage() {
		return this.fontImage;
	}

	@Override
	public float relevance() {
		return ((getImageCoverage() / subImage.getSize()) + (getFontCoverage() / fontImage
				.getSize()) * 20) ;
	}

	@Override
	public int tolerance() {
		return tolerance;
	}

}
