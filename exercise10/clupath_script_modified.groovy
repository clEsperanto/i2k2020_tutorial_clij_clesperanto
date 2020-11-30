// To make this script run in QuPath, please install CluPath as described here: 
// https://clij.github.io/clupath

// Note: QuPath support is experimental yet. 
//       This script may only work if the result is a binary image.
// Generator version: 0.4.2.0

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

// Crop2D
image2 = image1;








// Copy
image3 = clij2.create(image2.getWidth(), image2.getHeight());
clij2.copySlice(image2, image3, 1);
image2.close();

// Gaussian Blur2D
image4 = clij2.create(image3);
sigma_x = 2.0
sigma_y = 2.0
clij2.gaussianBlur2D(image3, image4, sigma_x, sigma_y);
image3.close();

// Detect Maxima2D Box
image5 = clij2.create(image4);
radiusX = 2.0
radiusY = 2.0
clij2.detectMaxima2DBox(image4, image5, radiusX, radiusY);

;

// pull back result and turn it into a QuPath ROI
imp = clij2.pull(image5);
roi = clij2.pullAsROI(image5);

imagePlane = IJTools.getImagePlane(roi, imp);
roi = IJTools.convertToROI(roi, -request.getX() / downsample, -request.getY() / downsample, downsample, imagePlane);

// add the ROI as annotation
annotation = PathObjects.createAnnotationObject(roi);
addObject(annotation)// Threshold Otsu
image6 = clij2.create(image4);
clij2.thresholdOtsu(image4, image6);
image4.close();

// pull back result and turn it into a QuPath ROI
imp = clij2.pull(image6);
roi = clij2.pullAsROI(image6);

imagePlane = IJTools.getImagePlane(roi, imp);
roi = IJTools.convertToROI(roi, -request.getX() / downsample, -request.getY() / downsample, downsample, imagePlane);

// add the ROI as annotation
annotation = PathObjects.createAnnotationObject(roi);
addObject(annotation)// Binary And
image7 = clij2.create(image5);
clij2.binaryAnd(image5, image6, image7);
image5.close();
image6.close();

// pull back result and turn it into a QuPath ROI
imp = clij2.pull(image7);
roi = clij2.pullAsROI(image7);

imagePlane = IJTools.getImagePlane(roi, imp);
roi = IJTools.convertToROI(roi, -request.getX() / downsample, -request.getY() / downsample, downsample, imagePlane);

// add the ROI as annotation
annotation = PathObjects.createAnnotationObject(roi);
addObject(annotation)// Maximum2D Sphere
image8 = clij2.create(image7);
radius_x = 6.0
radius_y = 6.0
clij2.maximum2DSphere(image7, image8, radius_x, radius_y);
image7.close();
image8.close();

