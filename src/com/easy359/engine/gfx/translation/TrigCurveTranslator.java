package com.easy359.engine.gfx.translation;

import java.util.ArrayList;
import java.util.List;

import com.easy359.engine.gfx.DisplayImage;

public class TrigCurveTranslator implements DisplayImageTranslator {

    private CurveType type;
    private double period;
    private double length;
    private double angleCos;
    private double angleSin;
    private double x;
    private double curTime;

    public TrigCurveTranslator(CurveType type, double period, double length, double angle) {
	if (type == null)
	    type = CurveType.COS;
	else
	    this.type = type;
	this.period = period;
	this.length = length;
	angleCos = Math.cos(angle);
	angleSin = Math.sin(angle);
	x = 0;
	curTime = 0;
    }

    @Override
    public void translate(DisplayImage img, double delta) {
	List<DisplayImage> imgs = new ArrayList<DisplayImage>(1);
	imgs.add(img);
	translate(imgs, delta);
    }

    @Override
    public void translate(List<DisplayImage> imgs, double delta) {
	curTime = (curTime + delta) % period;
	double percentComplete = (curTime / period);
	double newX = percentComplete * (2 * Math.PI);
	double dp = 0;
	switch (type) {
	case SIN:
	    dp = (-Math.cos(newX) * length) + (Math.cos(x) * length); // (-) - (-) = (-) + (+)
	    break;
	case COS:
	    dp = (Math.sin(newX) * length) - (Math.sin(x) * length);
	    break;
	}
	x = newX;
	double dx = angleCos * dp;
	double dy = angleSin * dp;
	for (DisplayImage img : imgs) {
	    img.setX(img.getX() + dx);
	    img.setY(img.getY() + dy);
	}
    }

    public void reset() {
	curTime = 0;
    }

    public enum CurveType {
	SIN, COS;
    }
}
