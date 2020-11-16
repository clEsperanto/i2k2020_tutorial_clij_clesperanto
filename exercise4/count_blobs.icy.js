importClass(net.haesleinhuepf.clicy.CLICY);
importClass(Packages.icy.main.Icy);
clij2 = CLICY.getInstance();

// CLIJ2_GaussianBlur2DBlock
sequence1 = getSequence();
input = clij2.pushSequence(sequence1);
blurred = clij2.create(input);
clij2.gaussianBlur2D(input, blurred, 1, 1);

// CLIJ2_ThresholdOtsuBlock
binary_mask = clij2.create(input);
clij2.thresholdOtsu(blurred, binary_mask);

// CLIJ2_ConnectedComponentsLabelingBoxBlock
labelmap = clij2.create(input);
clij2.connectedComponentsLabelingBox(binary_mask, labelmap);
sequence2 = clij2.pullSequence(labelmap);
Icy.addSequence(sequence2);

number_of_labels = clij2.maximumOfAllPixels(labelmap);
print(number_of_labels);


//Clean up memory
clij2.clear();