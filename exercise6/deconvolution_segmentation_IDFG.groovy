// This script contains a CLIJx-assistant workflow. You can load this workflow by 
// executing this script in Fijis script editor after choosing the Groovy language.
// 
//                   This script is not meant to be edited.
// 
// For image analysis automation, generate an ImageJ Groovy script for example.
// Read more: https://clij.github.io/assistant/save_and_load

// Generator version: 0.4.1.0



import ij.IJ;
import net.haesleinhuepf.clijx.CLIJx;

// clean up first
IJ.run("Close All");

// Init GPU
clijx = CLIJx.getInstance();
clijx.clear();

// disable automatic window positioning 
was_auto_position = net.haesleinhuepf.clijx.assistant.AbstractAssistantGUIPlugin.isAutoPosition();
net.haesleinhuepf.clijx.assistant.AbstractAssistantGUIPlugin.setAutoPosition(false);

// Load image from disc 
image1 = net.haesleinhuepf.clijx.assistant.utilities.AssistantUtilities.openImage("C:/structure/code/i2k2020_tutorial_clij_clesperanto/exercise6/perfect_psf_stack.tif");
image1.setC(1);
image1.setZ(1);
image1.setT(1);
image1.setTitle("perfect_psf_stack.tif");
image1.show();
// gaussianBlur3D
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.GaussianBlur3D());
node.setSources(image1);
node.run("");
node.getArgs()[2] = 4.0;
node.getArgs()[3] = 4.0;
node.getArgs()[4] = 0.0;
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(556, 698);
window.setSize(239, 295);
window.getCanvas().setMagnification(12.0);
image2 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// Load image from disc 
image3 = net.haesleinhuepf.clijx.assistant.utilities.AssistantUtilities.openImage("C:/structure/code/i2k2020_tutorial_clij_clesperanto/exercise6/convolved_stack.tif");
image3.setC(1);
image3.setZ(1);
image3.setT(1);
image3.setTitle("convolved_stack.tif");
image3.show();
// deconvolveRichardsonLucyFFT
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clijx.plugins.DeconvolveRichardsonLucyFFT());
node.setSources(image3, image2);
node.run("");
node.getArgs()[3] = 91.0;
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(897, 60);
window.setSize(526, 335);
window.getCanvas().setMagnification(0.5);
image4 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// greaterConstant
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.GreaterConstant());
node.setSources(image4);
node.run("");
node.getArgs()[2] = 270.0;
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(1727, 61);
window.setSize(359, 251);
window.getCanvas().setMagnification(0.3333333333333333);
image5 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// connectedComponentsLabelingBox
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.ConnectedComponentsLabelingBox());
node.setSources(image5);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(1059, 406);
window.setSize(1025, 589);
window.getCanvas().setMagnification(1.0);
image6 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// reset auto-positioning
IJ.wait(500);
net.haesleinhuepf.clijx.assistant.AbstractAssistantGUIPlugin.setAutoPosition(was_auto_position);

