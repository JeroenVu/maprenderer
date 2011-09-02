/*
 * This file is part of the GeoLatte project.
 *
 *     GeoLatte is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     GeoLatte is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with GeoLatte.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright (C) 2010 - 2011 and Ownership of code is shared by:
 *  Qmino bvba - Esperantolaan 4 - 3001 Heverlee  (http://www.qmino.com)
 *  Geovise bvba - Generaal Eisenhowerlei 9 - 2140 Antwerpen (http://www.geovise.com)
 */

package org.geolatte.maprenderer.sld.symbolizer;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.geolatte.core.Feature;

import java.awt.*;


public class PointSymbolizer extends ShapeSymbolizer {

    private Graphic graphic;
    private double size;


    public void setSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return this.size;
    }

    public void setGraphic(Graphic graphic) {
        this.graphic = graphic;
    }

    public void symbolize(Feature feature) {
        Point pnt = getAnchor(feature);
        Shape markShape = this.graphic.generateMarkShape(pnt.getX(), pnt.getY(), this.size / getGraphics().getScale());
        getGraphics().setColor(getFillColor());
        getGraphics().fill(markShape);
        getGraphics().setColor(getStrokeColor());
        getGraphics().draw(markShape);
    }

    private Point getAnchor(Feature feature) {
        Geometry geom = feature.getGeometry();
        return geom.getCentroid();
    }
}
