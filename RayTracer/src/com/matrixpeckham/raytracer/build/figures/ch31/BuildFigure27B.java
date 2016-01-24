/*
 * Copyright (C) 2016 William Matrix Peckham
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.matrixpeckham.raytracer.build.figures.ch31;

import com.matrixpeckham.raytracer.cameras.Pinhole;
import com.matrixpeckham.raytracer.geometricobjects.primitives.Sphere;
import com.matrixpeckham.raytracer.lights.PointLight;
import com.matrixpeckham.raytracer.materials.SV_Matte;
import com.matrixpeckham.raytracer.textures.procedural.CubicNoise;
import com.matrixpeckham.raytracer.textures.procedural.FBmTexture;
import com.matrixpeckham.raytracer.tracers.RayCast;
import com.matrixpeckham.raytracer.util.Point3D;
import com.matrixpeckham.raytracer.util.RGBColor;
import com.matrixpeckham.raytracer.world.BuildWorldFunction;
import com.matrixpeckham.raytracer.world.World;

/**
 *
 * @author William Matrix Peckham
 */
public class BuildFigure27B implements BuildWorldFunction {

    @Override
    public void build(World w) {
// 	Copyright (C) Kevin Suffern 2000-2008.
//	This C++ code is for non-commercial purposes only.
//	This C++ code is licensed under the GNU General Public License Version 2.
//	See the file COPYING.txt for the full license.


// This builds the scene for Figure 31.27(b)

// This is now rendered with a gray w.background.

 												

	int numSamples = 16;
	
	w.vp.setHres(600);    
	w.vp.setVres(600);
	w.vp.setSamples(numSamples);
	
	w.backgroundColor = new RGBColor(0.5);
	w.tracer = new RayCast(w);
	
	Pinhole pinholePtr = new Pinhole();
	
	pinholePtr.setEye(0, 0, 100);
	pinholePtr.setLookat(new Point3D(0.0));
	pinholePtr.setViewDistance(9500.0); 	
	pinholePtr.computeUVW();     
	w.setCamera(pinholePtr); 
	
	
	PointLight lightPtr = new PointLight();
	lightPtr.setLocation(10, 10, 20);		
	lightPtr.scaleRadiance(3.5);     
	w.addLight(lightPtr);
	
	
	// noise:
	
	CubicNoise noisePtr = new CubicNoise();
	noisePtr.setNumOctaves(6); 
	noisePtr.setGain(0.5);
	noisePtr.setLacunarity(2.0);
	
	// texture:		
	
	FBmTexture texturePtr = new FBmTexture(noisePtr);			
	texturePtr.setColor(0.5, 0.75, 1.0);   // light blue
	texturePtr.setMinValue(0.0);  
	texturePtr.setMaxValue(1.0);

	// material:
	
	SV_Matte svMattePtr = new SV_Matte();		
	svMattePtr.setKa(0.25);
	svMattePtr.setKd(0.85);
	svMattePtr.setCd(texturePtr);
	
	// the sphere:
	
	Sphere spherePtr = new Sphere(new Point3D(0.0), 3.0); 
	spherePtr.setMaterial(svMattePtr);
	w.addObject(spherePtr);
}





}
