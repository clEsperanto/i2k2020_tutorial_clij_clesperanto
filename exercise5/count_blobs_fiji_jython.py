# To make this script run in Fiji, please activate 
# the clij and clij2 update sites in your Fiji 
# installation. Read more: https://clij.github.io

# Generator version: 0.4.0.8

from ij import IJ
from ij import WindowManager
from net.haesleinhuepf.clij2 import CLIJ2

# Init GPU
clij2 = CLIJ2.getInstance()

# Load image from disc 
imp = IJ.openImage("C:/Users/rober/AppData/Local/Temp/temp1605606091856.tif")
# Push temp1605606091856.tif to GPU memory
image1 = clij2.push(imp);

# Copy
image2 = clij2.create(image1)
clij2.copy(image1, image2)
image1.close()

result = clij2.pull(image2)
result.setDisplayRange(16.0, 248.0)
result.show()

# Gaussian Blur2D
image3 = clij2.create(image2)
sigma_x = 16.0
sigma_y = 16.0
clij2.gaussianBlur2D(image2, image3, sigma_x, sigma_y)

result = clij2.pull(image3)
result.setDisplayRange(75.0, 153.0)
result.show()

# Greater
image4 = clij2.create(image2)
clij2.greater(image2, image3, image4)
image2.close()
image3.close()

result = clij2.pull(image4)
result.setDisplayRange(0.0, 1.0)
result.show()

# Connected Components Labeling Box
image5 = clij2.create(image4.getDimensions(), clij2.Float )
clij2.connectedComponentsLabelingBox(image4, image5)
image4.close()

result = clij2.pull(image5)
result.setDisplayRange(0.0, 67.0)
IJ.run(result, "glasbey_on_dark", "")
result.show()
image5.close()

