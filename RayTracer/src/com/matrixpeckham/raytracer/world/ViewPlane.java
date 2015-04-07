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
package com.matrixpeckham.raytracer.world;

/**
 *
 * @author William Matrix Peckham
 */
public class ViewPlane {

    public int hRes;
    public int vRes;
    public float s;
    public float gamma;
    public float invGamma;
    public boolean showOutOfGamut;

    // default Constructor
    public ViewPlane() {
        hRes=400;
        vRes=400;
        s=1;
        gamma=1;
        invGamma=1;
        showOutOfGamut=false;
    }

    // copy ructor
    public ViewPlane(ViewPlane vp) {
        hRes=vp.hRes;
        vRes=vp.vRes;
        s=vp.s;
        gamma=vp.gamma;
        invGamma=vp.invGamma;
        showOutOfGamut=vp.showOutOfGamut;
    }

    // assignment operator
    public ViewPlane setTo(ViewPlane vp) {
        hRes=vp.hRes;
        vRes=vp.vRes;
        s=vp.s;
        gamma=vp.gamma;
        invGamma=vp.invGamma;
        showOutOfGamut=vp.showOutOfGamut;
        return this;
    }

    public void setHres(int h_res){hRes=h_res;}

    public void setVres(int v_res){vRes=v_res;}

    public void setPixelSize(float size){
        s=size;
    }

    public void setGamma(float g){
        gamma=g;
        invGamma=1.0f/gamma;
    }

    public void setGamutDisplay(boolean show){
        showOutOfGamut=show;
    }
}
