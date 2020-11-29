// To make this script run in Fiji, please activate 
// the clij and clij2 update sites in your Fiji 
// installation. Read more: https://clij.github.io

// This script has been modified to process a timelapse
// frame by frame.

// Init GPU
run("CLIJ2 Macro Extensions", "cl_device=");

// Load image from disc 
open("C:/structure/code/i2k2020_tutorial_clij_clesperanto/exercise7/CalibZAPWfixed_000154_max.tif");
image1 = getTitle();

// determine stack size
getDimensions(width, height, channels, slices, frames);

// create a stack to save the result
result_stack = "result_stack";
Ext.CLIJ2_create3D(result_stack, width, height, frames, 32);

for (frame = 0; frame < frames; frame += 1) {
	Stack.setFrame(frame + 1);
	Ext.CLIJ2_pushCurrentSlice(image1);
	
	// Copy
	Ext.CLIJ2_copy(image1, image2);
	Ext.CLIJ2_release(image1);
	
	// Detect And Label Maxima
	sigma_x = 3.0;
	sigma_y = 3.0;
	sigma_z = 0.0;
	invert_ = 0.0;
	Ext.CLIJx_detectAndLabelMaxima(image2, image3, sigma_x, sigma_y, sigma_z, invert_);
	
	// Normalize
	Ext.CLIJx_normalize(image2, image4);
	Ext.CLIJ2_release(image2);
	
	// Extend Labeling Via Voronoi
	Ext.CLIJ2_extendLabelingViaVoronoi(image3, image5);
	Ext.CLIJ2_release(image3);
	
	// Detect Label Edges
	Ext.CLIJ2_detectLabelEdges(image5, image6);
	Ext.CLIJ2_release(image5);
	
	// Maximum Images
	Ext.CLIJ2_maximumImages(image4, image6, image7);
	Ext.CLIJ2_release(image4);
	Ext.CLIJ2_release(image6);

	// save the result slice in the result stack
	Ext.CLIJ2_copySlice(image7, result_stack, frame);
	Ext.CLIJ2_release(image7);
}
// show the result
Ext.CLIJ2_pull(result_stack);