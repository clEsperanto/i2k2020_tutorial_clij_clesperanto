// To make this script run in Fiji, please activate 
// the clij and clij2 update sites in your Fiji 
// installation. Read more: https://clij.github.io

// Generator version: 0.4.1.0

// Init GPU
run("CLIJ2 Macro Extensions", "cl_device=");

// Load image from disc 
open("C:/structure/code/i2k2020_tutorial_clij_clesperanto/exercise6/perfect_psf_stack.tif");
image1 = getTitle();
Ext.CLIJ2_push(image1);

// Gaussian Blur3D
sigma_x = 4.0;
sigma_y = 4.0;
sigma_z = 0.0;
Ext.CLIJ2_gaussianBlur3D(image1, image2, sigma_x, sigma_y, sigma_z);
Ext.CLIJ2_release(image1);

Ext.CLIJ2_pull(image2);

// Load image from disc 
open("C:/structure/code/i2k2020_tutorial_clij_clesperanto/exercise6/convolved_stack.tif");
image3 = getTitle();
Ext.CLIJ2_push(image3);

// Deconvolve Richardson Lucy FFT
num_iterations = 91.0;
Ext.CLIJx_deconvolveRichardsonLucyFFT(image3, image2, image4, num_iterations);
Ext.CLIJ2_release(image3);
Ext.CLIJ2_release(image2);

Ext.CLIJ2_pull(image4);

// Greater Constant
constant = 270.0;
Ext.CLIJ2_greaterConstant(image4, image5, constant);
Ext.CLIJ2_release(image4);

Ext.CLIJ2_pull(image5);

// Connected Components Labeling Box
Ext.CLIJ2_connectedComponentsLabelingBox(image5, image6);
Ext.CLIJ2_release(image5);

Ext.CLIJ2_pull(image6);
run("glasbey_on_dark");
Ext.CLIJ2_release(image6);
