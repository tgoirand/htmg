package com.lib.workshop;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Util {

	public static Color getDominant(BufferedImage data, int tolerance) {
		Map<Color, Integer> s = new HashMap<Color, Integer>();

		for (int x = 0; x < data.getWidth(); x++) {
			for (int y = 0; y < data.getHeight(); y++) {
				
				for (int c = -tolerance; y < tolerance; y++) {
					Color color = new Color(data.getRGB(x, y) + c);

					if (s.containsKey(color)) {
						s.put(color, s.get(color) + 1);
					}
				}
				
				if (!s.containsKey(new Color(data.getRGB(x, y)))) {
					s.put(new Color(data.getRGB(x, y)), 0);
				}
				
			}
		}

		List<Integer> val = new ArrayList<Integer>(s.values());
		
		Collections.sort(val);
		
		Integer max = val.get(val.size() - 1);
		
		System.out.println("max is " + max); 
		
		for (Color c : s.keySet()) {
			if (s.get(c) == max)
				return c;
		}
		
		return null;
	}
	
	
	public static Color getDominant(int[][] data, int tolerance) {
		Map<Color, Integer> s = new TreeMap<Color, Integer>();

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				
				for (int c = -tolerance; c < tolerance; c++) {
					Color color = new Color(data[i][j] + c);
					
					if (s.containsKey(color)) {
						s.put(color, s.get(color) + 1);
					}
					
				}
			}
		}

		List<Integer> val = new ArrayList<Integer>(s.values());
		
		Collections.sort(val);
		
		Integer max = val.get(val.size());
		
		System.out.println("max is " + max); 
		
		for (Color c : s.keySet()) {
			if (s.get(c) == max)
				return c;
		}
		
		return null;
	}

}
