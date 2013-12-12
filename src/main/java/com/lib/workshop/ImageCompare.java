package com.lib.workshop;

public interface ImageCompare extends Comparable<ImageCompare> {
	public float getImageCoverage();

	public float getFontCoverage();

	public float relevance();
	
	public int tolerance();

	public FontImage getFontImage();

	public double getMedian();

}
