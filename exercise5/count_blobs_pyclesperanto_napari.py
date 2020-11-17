# This is an experimentally generated python script. Not all commands are supposed to be executable yet.
# If you want to give it a try, create conda environment named te_oki:
#   `conda create --name te_oki` 
# activate the environment: 
#   `conda activate te_oki` 
# install dependencies: 
#   `pip install pyopencl napari ipython matplotlib numpy pyclesperanto_prototype scikit-image` 
# Also make sure conda is part of the PATH variable.
# 
# If you want to run it from Fiji and you're using a different conda environment, you can configure it in Fijis main menu 
# Plugins > ImageJ on GPU (CLIJx-Assistant) > Options >Conda configuration (Te Oki) 
# Furthermore, activate the scripting language Te Oki in Fijis script editor to run this script.

# Stay tuned and check out http://clesperanto.net to learn more.

# Generator version: 0.4.0.8

import pyclesperanto_prototype as cle
from tifffile import imread

import napari

# Start napari viewer
with napari.gui_qt():
    viewer = napari.Viewer()


    # Load image
    image = imread("C:/Users/rober/AppData/Local/Temp/temp1605606091856.tif")
    
    # Push temp1605606091856.tif to GPU memory
    image1 = cle.push_zyx(image)
        
    # Copy
    image2 = cle.create_like(image1);
    cle.copy(image1, image2)
    # show result
    viewer.add_image(cle.pull_zyx(image2), scale=(1.0, 1.0))
    
        
    # Gaussian Blur2D
    image3 = cle.create_like(image2);
    sigma_x = 16.0;
    sigma_y = 16.0;
    cle.gaussian_blur(image2, image3, sigma_x, sigma_y)
    # show result
    viewer.add_image(cle.pull_zyx(image3), scale=(1.0, 1.0))
    
        
    # Greater
    image4 = cle.create_like(image2);
    cle.greater(image2, image3, image4)
    # show result
    viewer.add_image(cle.pull_zyx(image4), scale=(1.0, 1.0))
    
        
    # Connected Components Labeling Box
    image5 = cle.create_like(image4);
    cle.connected_components_labeling_box(image4, image5)
    # show result
    viewer.add_labels(cle.pull_zyx(image5), scale=(1.0, 1.0))
    
    