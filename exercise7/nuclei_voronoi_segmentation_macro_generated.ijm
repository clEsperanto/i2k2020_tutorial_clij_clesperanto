// To make this script run in Fiji, please activate 
// the clij and clij2 update sites in your Fiji 
// installation. Read more: https://clij.github.io

// Generator version: 0.4.1.0

// Init GPU
run("CLIJ2 Macro Extensions", "cl_device=");

// Load image from disc 
open("C:/structure/code/i2k2020_tutorial_clij_clesperanto/exercise7/CalibZAPWfixed_000154_max.tif");
image1 = getTitle();
Ext.CLIJ2_push(image1);

// Copy
Ext.CLIJ2_copy(image1, image2);
Ext.CLIJ2_release(image1);

Ext.CLIJ2_pull(image2);

// Detect And Label Maxima
sigma_x = 3.0;
sigma_y = 3.0;
sigma_z = 0.0;
invert = 0.0;
Ext.CLIJx_detectAndLabelMaxima(image2, image3, sigma_x, sigma_y, sigma_z, invert);

Ext.CLIJ2_pull(image3);
run("glasbey_on_dark");

// Normalize
Ext.CLIJx_normalize(image2, image4);
Ext.CLIJ2_release(image2);

Ext.CLIJ2_pull(image4);

// Extend Labeling Via Voronoi
Ext.CLIJ2_extendLabelingViaVoronoi(image3, image5);
Ext.CLIJ2_release(image3);

Ext.CLIJ2_pull(image5);
run("glasbey_on_dark");

// Detect Label Edges
Ext.CLIJ2_detectLabelEdges(image5, image6);
Ext.CLIJ2_release(image5);

Ext.CLIJ2_pull(image6);
run("glasbey_on_dark");

// Maximum Images
Ext.CLIJ2_maximumImages(image4, image6, image7);
Ext.CLIJ2_release(image4);
Ext.CLIJ2_release(image6);

Ext.CLIJ2_pull(image7);
Ext.CLIJ2_release(image7);
