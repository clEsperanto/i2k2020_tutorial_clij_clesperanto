#  
import net.clesperanto.javaprototype.Snake as cle
from ij import IJ

maximum_distance = 72.0

# Load image from disc 
imp = IJ.openImage("C:/structure/data/blobs.tif")
image1 = cle.push(imp);
image2 = cle.create(image1)
image3 = cle.create(image2.getDimensions(), cle.Float )
image4 = cle.create(image3)

# image processing
cle.threshold_otsu(image1, image2)
cle.connected_components_labeling_box(image2, image3)
cle.draw_mesh_between_proximal_labels(image3, image4, maximum_distance)

# show result
cle.pull(image4).show();

# cleanup
cle.clear()




