/*
 * Copyright (C) 2015 William Matrix Peckham
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
package com.matrixpeckham.raytracer.build.figures.ch21;

import com.matrixpeckham.raytracer.cameras.Pinhole;
import com.matrixpeckham.raytracer.geometricobjects.Instance;
import com.matrixpeckham.raytracer.geometricobjects.compound.Grid;
import com.matrixpeckham.raytracer.geometricobjects.compound.TriangleMesh;
import com.matrixpeckham.raytracer.geometricobjects.parametric.ParametricObject;
import com.matrixpeckham.raytracer.geometricobjects.primitives.Plane;
import com.matrixpeckham.raytracer.lights.Ambient;
import com.matrixpeckham.raytracer.lights.PointLight;
import com.matrixpeckham.raytracer.materials.Matte;
import com.matrixpeckham.raytracer.materials.Phong;
import com.matrixpeckham.raytracer.tracers.RayCast;
import com.matrixpeckham.raytracer.util.Normal;
import com.matrixpeckham.raytracer.util.Point3D;
import com.matrixpeckham.raytracer.util.Utility;
import com.matrixpeckham.raytracer.world.BuildWorldFunction;
import com.matrixpeckham.raytracer.world.World;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author William Matrix Peckham
 */
public class BuildFigure04 implements BuildWorldFunction{

    @Override
    public void build(World w) {
        int numSamples = 16;
	
	w.vp.setHres(600);
	w.vp.setVres(350); 
	w.vp.setSamples(numSamples);	
	
	Ambient ambientPtr = new Ambient();
	ambientPtr.scaleRadiance(0.5);
	w.setAmbient(ambientPtr);
		
	w.tracer = new RayCast(w);
	
	Pinhole pinHolePtr = new Pinhole();
	pinHolePtr.setEye(7.7, 6, 15);
	pinHolePtr.setLookat(-1.0, -0.5, 0); 
	pinHolePtr.setViewDistance(650);
	pinHolePtr.computeUVW();
	w.setCamera(pinHolePtr);
	
	PointLight lightPtr1 = new PointLight();
	lightPtr1.setLocation(30, 30, 25); 
	lightPtr1.setLocation(30, 20, -10);  
	lightPtr1.scaleRadiance(2.5);  
	lightPtr1.setShadows(true);
	w.addLight(lightPtr1);
	
	PointLight lightPtr2 = new PointLight();
	lightPtr2.setLocation(10, 20, 40);  
	lightPtr2.scaleRadiance(1.5);  
	lightPtr2.setShadows(true);
	w.addLight(lightPtr2);
	
	// ground plane 
	
	Matte mattePtr1 = new Matte();		
	mattePtr1.setKa(0.35);
	mattePtr1.setKd(0.75);
	mattePtr1.setCd(Utility.WHITE);  
	
	Plane planePr1 = new Plane(new Point3D(0, 0.24, 0), new Normal(0, 1, 0));
	planePr1.setMaterial(mattePtr1);
	w.addObject(planePr1);

				
	double 	x0 					= -5.0;					// minimum x center coordinate
	double 	z0 					= -5.0;					// minimum z center coordinate
	double 	x1 					= 5.0;					// minimum x center coordinate
	double 	z1 					= 5.0;					// minimum z center coordinate
	int 	numXBunnies		= 8;					// number of bunnies in the x direction
	int 	numZBunnies		= 8;  					// number of bunnies in the z direction
	double	xSpacing			= (x1 - x0) / (numXBunnies - 1); // center spacing in x direction
	double	zSpacing			= (z1 - z0) / (numZBunnies - 1); // center spacing in x direction
	
	
	String fileName = "C:\\Users\\Owner\\Documents\\Ground Up raytracer\\Models\\Stanford Bunny\\Bunny16K.ply"; 
	
	TriangleMesh bunnyPtr1 = new TriangleMesh();
        try {
            bunnyPtr1.readSmoothTriangles(new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(BuildFigure04.class.getName()).
                    log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
	bunnyPtr1.setupCells();
	
	Utility.setRandSeed(1000);
	Grid gridPtr = new Grid();
	
	for (int iz = 0; iz < numZBunnies; iz++)
		for (int ix = 0; ix < numXBunnies; ix++) {
			Phong phongPtr = new Phong();	
			phongPtr.setKa(0.25); 
			phongPtr.setKd(0.75);
			phongPtr.setCd(Utility.randDouble(), Utility.randDouble(), Utility.randDouble());
			phongPtr.setKs(0.125);  
			phongPtr.setExp(20.0);
			
			Instance bunnyPtr2 = new Instance(bunnyPtr1);
			bunnyPtr2.setMaterial(phongPtr);
			bunnyPtr2.scale(6.5,6.5,6.5);
			bunnyPtr2.translate(x0 + ix * xSpacing, 0, z0 + iz * zSpacing);
			bunnyPtr2.computeBoundingBox(); // essential for placing each bunny in the grid
			gridPtr.addObject(bunnyPtr2);
		}

	gridPtr.setupCells();
	w.addObject(gridPtr);
    }
    
}