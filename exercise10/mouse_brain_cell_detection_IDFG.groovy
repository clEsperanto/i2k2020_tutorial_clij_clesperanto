// This script contains a CLIJx-assistant workflow. You can load this workflow by 
// executing this script in Fijis script editor after choosing the Groovy language.
// 
//                   This script is not meant to be edited.
// 
// For image analysis automation, generate an ImageJ Groovy script for example.
// Read more: https://clij.github.io/assistant/save_and_load

// Generator version: 0.4.2.0



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
image1 = net.haesleinhuepf.clijx.assistant.utilities.AssistantUtilities.openImage("C:/structure/data/mouse_brain/P1_H_C3H_M004_17.czi - P1_H_C3H_M004_17.czi #1.tif");
image1.setC(1);
image1.setZ(1);
image1.setT(1);
image1.setTitle("P1_H_C3H_M004_17.czi - P1_H_C3H_M004_17.czi #1.tif");
image1.show();
// crop2D
node = new net.haesleinhuepf.clijx.assistant.interactive.handcrafted.Crop2D();
node.setSources(image1);
node.run("");
node.getArgs()[2] = 12256;
node.getArgs()[3] = 5440;
node.getArgs()[4] = 640;
node.getArgs()[5] = 512;
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(793, 169);
window.setSize(346, 338);
window.getCanvas().setMagnification(0.5);
image2 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// copy
node = new net.haesleinhuepf.clijx.assistant.interactive.handcrafted.ExtractChannel();
node.setSources(image2);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(853, 585);
window.setSize(346, 316);
window.getCanvas().setMagnification(0.5);
image3 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// gaussianBlur2D
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.GaussianBlur2D());
node.setSources(image3);
node.run("");
node.getArgs()[2] = 2.0;
node.getArgs()[3] = 2.0;
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(858, 1024);
window.setSize(346, 316);
window.getCanvas().setMagnification(0.5);
image4 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// detectMaxima2DBox
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.DetectMaxima2DBox());
node.setSources(image4);
node.run("");
node.getArgs()[2] = 2.0;
node.getArgs()[3] = 2.0;
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(1572, 460);
window.setSize(346, 316);
window.getCanvas().setMagnification(0.5);
image5 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// thresholdOtsu
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.ThresholdOtsu());
node.setSources(image4);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(1054, 1172);
window.setSize(666, 572);
window.getCanvas().setMagnification(1.0);
image6 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// binaryAnd
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.BinaryAnd());
node.setSources(image5, image6);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(1359, 998);
window.setSize(346, 316);
window.getCanvas().setMagnification(0.5);
image7 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// maximum2DSphere
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.Maximum2DSphere());
node.setSources(image7);
node.run("");
node.getArgs()[2] = 6.0;
node.getArgs()[3] = 6.0;
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(1765, 819);
window.setSize(506, 444);
window.getCanvas().setMagnification(0.75);
image8 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// reset auto-positioning
IJ.wait(500);
net.haesleinhuepf.clijx.assistant.AbstractAssistantGUIPlugin.setAutoPosition(was_auto_position);

