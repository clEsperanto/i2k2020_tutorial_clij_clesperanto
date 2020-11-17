// This script contains a CLIJx-assistant workflow. You can load this workflow by 
// executing this script in Fijis script editor after choosing the Groovy language.
// 
//                   This script is not meant to be edited.
// 
// For image analysis automation, generate an ImageJ Groovy script for example.
// Read more: https://clij.github.io/assistant/save_and_load

// Generator version: 0.4.0.8



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
image1 = net.haesleinhuepf.clijx.assistant.utilities.AssistantUtilities.openImage("C:/Users/rober/AppData/Local/Temp/temp1605606091856.tif");
image1.setC(1);
image1.setZ(1);
image1.setT(1);
image1.setTitle("temp1605606091856.tif");
image1.show();
// copy
node = new net.haesleinhuepf.clijx.assistant.AssistantGUIStartingPoint();
node.setSources(image1);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(307, 341);
window.setSize(282, 314);
window.getCanvas().setMagnification(1.0);
image2 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// gaussianBlur2D
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.GaussianBlur2D());
node.setSources(image2);
node.run("");
node.getArgs()[2] = 16.0;
node.getArgs()[3] = 16.0;
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(788, 142);
window.setSize(282, 314);
window.getCanvas().setMagnification(1.0);
image3 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// greater
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.Greater());
node.setSources(image2, image3);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(752, 550);
window.setSize(282, 314);
window.getCanvas().setMagnification(1.0);
image4 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// connectedComponentsLabelingBox
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.ConnectedComponentsLabelingBox());
node.setSources(image4);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(28, 722);
window.setSize(282, 314);
window.getCanvas().setMagnification(1.0);
image5 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// reset auto-positioning
IJ.wait(500);
net.haesleinhuepf.clijx.assistant.AbstractAssistantGUIPlugin.setAutoPosition(was_auto_position);

