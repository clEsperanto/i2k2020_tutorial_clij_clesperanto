/*
To make this script run in Fiji, please activate 
the clij, clij2 and IJMMD update sites in your Fiji 
installation. Read more: https://clij.github.io

Generator version: 0.4.0.8
*/
// Init GPU
run("CLIJ2 Macro Extensions", "cl_device=");

// Load image from disc 
open("C:/Users/rober/AppData/Local/Temp/temp1605606091856.tif");
image1 = getTitle();
Ext.CLIJ2_push(image1);

/*
## Copy
*/
Ext.CLIJ2_copy(image1, image2);
Ext.CLIJ2_release(image1);

Ext.CLIJ2_pull(image2);

/*
## Gaussian Blur2D
*/
sigma_x = 16.0;
sigma_y = 16.0;
Ext.CLIJ2_gaussianBlur2D(image2, image3, sigma_x, sigma_y);

Ext.CLIJ2_pull(image3);

/*
## Greater
*/
Ext.CLIJ2_greater(image2, image3, image4);
Ext.CLIJ2_release(image2);
Ext.CLIJ2_release(image3);

Ext.CLIJ2_pull(image4);

/*
## Connected Components Labeling Box
*/
Ext.CLIJ2_connectedComponentsLabelingBox(image4, image5);
Ext.CLIJ2_release(image4);

Ext.CLIJ2_pull(image5);
run("glasbey_on_dark");
Ext.CLIJ2_release(image5);
