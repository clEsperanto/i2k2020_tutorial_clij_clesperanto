from ij import IJ;
from net.haesleinhuepf.clij2 import CLIJ2;

IJ.run("Close All");

# load example image
imp = IJ.openImage("http://wsr.imagej.net/images/blobs.gif");
imp.show();

# init GPU
clij2 = CLIJ2.getInstance();

# push image to GPU
input = clij2.push(imp);

# reserve memory for output, same size and type as input
blurred = clij2.create(input);
thresholded = clij2.create(input);
labelmap = clij2.create(input);

# blur, threshold and label the image
clij2.gaussianBlur(input, blurred, 1, 1, 0);
clij2.automaticThreshold(blurred, thresholded, "Otsu");
clij2.connectedComponentsLabeling(thresholded, labelmap);

# show result
clij2.show(labelmap, "label map");
IJ.run("glasbey");

number_of_labels = clij2.maximumOfAllPixels(labelmap); 
print("Number of labels: " + str(number_of_labels));

# clean up
clij2.clear();