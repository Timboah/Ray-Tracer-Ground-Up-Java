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
package com.matrixpeckham.raytracer.lights;

import com.matrixpeckham.raytracer.util.Utility;
import com.matrixpeckham.raytracer.util.RGBColor;
import com.matrixpeckham.raytracer.util.Ray;
import com.matrixpeckham.raytracer.util.ShadeRec;
import com.matrixpeckham.raytracer.util.Vector3D;

/**
 *
 * @author William Matrix Peckham
 */
public abstract class Light {
    protected boolean shadows = true;

    public Light(){}
    public Light(Light ls){
        this.shadows=ls.shadows;
    }
    public Light setTo(Light l){
        this.shadows=l.shadows;
        return this;
    }
    public abstract Light clone();
    public abstract Vector3D getDirection(ShadeRec sr);
    public RGBColor L(ShadeRec sr){
        return Utility.BLACK;
    }
    
    public double G(ShadeRec sr){
        return 1;
    }
    
    public double pdf(ShadeRec sr){
        return 1;
    }
    public void setShadows(boolean b) {
        shadows=b;
    }
    public boolean castsShadows() {
        return shadows;
    }

    public abstract boolean inShadow(Ray shadowRay, ShadeRec sr);
}
