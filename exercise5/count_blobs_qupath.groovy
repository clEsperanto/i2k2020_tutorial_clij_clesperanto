// To make this script run in QuPath, please install CluPath as described here: 
// https://clij.github.io/clupath

// Note: QuPath support is experimental yet. 
//       This script may only work if the result is a binary image.
// Generator version: 0.4.0.8

import qupath.lib.regions.*
import qupath.imagej.tools.IJTools
import qupath.imagej.gui.IJExtension
import net.haesleinhuepf.clupath.CLUPATH

// Init GPU
clij2 = CLUPATH.getInstance();
// Read image and convert it to ImageJ
def server = getCurrentServer();
def parent = getSelectedObject();
double downsample = 2.0
def request = parent == null ? RegionRequest.createInstance(server, downsample) : RegionRequest.createInstance(server.getPath(), downsample, parent.getROI());
def pathImage = IJTools.convertToImagePlus(server, request);
def imp = pathImage.getImage();

// push input image to GPU memory
image1 = clij2.push(imp);

// Copy
image2 = clij2.create(image1);
clij2.copy(image1, image2);
image1.close();

// Gaussian Blur2D
image3 = clij2.create(image2);
sigma_x = 16.0
sigma_y = 16.0
clij2.gaussianBlur2D(image2, image3, sigma_x, sigma_y);

;

// Greater
image4 = clij2.create(image2);
clij2.greater(image2, image3, image4);
image2.close();
image3.close();

// pull back result and turn it into a QuPath ROI
imp = clij2.pull(image4);
roi = clij2.pullAsROI(image4);

imagePlane = IJTools.getImagePlane(roi, imp);
roi = IJTools.convertToROI(roi, -request.getX() / downsample, -request.getY() / downsample, downsample, imagePlane);

// add the ROI as annotation
def annotation = PathObjects.createAnnotationObject(roi);
addObject(annotation)// Connected Components Labeling Box
image5 = clij2.create(image4.getDimensions(), clij2.Float );
clij2.connectedComponentsLabelingBox(image4, image5);
image4.close();
image5.close();

