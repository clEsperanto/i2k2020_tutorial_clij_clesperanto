// get test data
run("Blobs (25K)");
input = getTitle();

// Initialize GPU
run("CLIJ2 Macro Extensions", "cl_device=");
Ext.CLIJ2_clear();

// push data to GPU
Ext.CLIJ2_push(input);

// process the image
Ext.CLIJ2_gaussianBlur2D(input, blurred, 1, 1);
Ext.CLIJ2_thresholdOtsu(blurred, binary_mask);
Ext.CLIJ2_connectedComponentsLabelingBox(binary_mask, labelmap);
Ext.CLIJ2_pull(labelmap);
run("glasbey on dark");

Ext.CLIJ2_getMaximumOfAllPixels(labelmap, number_of_labels);
print("Number of labels: " + number_of_labels);

// At the end of the macro, clean up:
Ext.CLIJ2_clear();



