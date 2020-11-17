// To make this script run in Icy Bioimaging, please install the clIcy plugin in Icy first. 
// Read more: http://icy.bioimageanalysis.org/plugin/clicy-blocks/

// Generator version: 0.4.0.8

importClass(Packages.icy.sequence.Sequence);
importClass(net.haesleinhuepf.clicy.CLICY);
importClass(Packages.icy.main.Icy);

// Init GPU
clij2 = CLICY.getInstance();

// get current image from Icy
sequence = getSequence();
// push image to GPU
image1 = clij2.pushSequence(sequence);

// Copy
image2 = clij2.create(image1);
clij2.copy(image1, image2);
image1.close();

// pull result back from GPU
output = clij2.pullSequence(image2);

// Show result
Icy.addSequence(output);
// Gaussian Blur2D
image3 = clij2.create(image2);
sigma_x = 16.0
sigma_y = 16.0
clij2.gaussianBlur2D(image2, image3, sigma_x, sigma_y);

;

// pull result back from GPU
output = clij2.pullSequence(image3);

// Show result
Icy.addSequence(output);
// Greater
image4 = clij2.create(image2);
clij2.greater(image2, image3, image4);
image2.close();
image3.close();

// pull result back from GPU
output = clij2.pullSequence(image4);

// Show result
Icy.addSequence(output);
// Connected Components Labeling Box
image5 = clij2.create(image4.getDimensions(), clij2.Float );
clij2.connectedComponentsLabelingBox(image4, image5);
image4.close();

// pull result back from GPU
output = clij2.pullSequence(image5);
image5.close();

// Show result
Icy.addSequence(output);
