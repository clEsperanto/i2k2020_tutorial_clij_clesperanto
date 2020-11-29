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
image1 = net.haesleinhuepf.clijx.assistant.utilities.AssistantUtilities.openImage("C:/structure/code/i2k2020_tutorial_clij_clesperanto/exercise7/CalibZAPWfixed_000154_max.tif");
image1.setC(1);
image1.setZ(1);
image1.setT(46);
image1.setTitle("CalibZAPWfixed_000154_max.tif");
image1.show();
// copy
node = new net.haesleinhuepf.clijx.assistant.AssistantGUIStartingPoint();
node.setSources(image1);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(70, 926);
window.setSize(415, 295);
window.getCanvas().setMagnification(1.0);
image2 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// detectAndLabelMaxima
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clijx.plugins.DetectAndLabelMaxima());
node.setSources(image2);
node.run("");
node.getArgs()[2] = 3.0;
node.getArgs()[3] = 3.0;
node.getArgs()[4] = 0.0;
node.getArgs()[5] = 0.0;
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(989, 63);
window.setSize(415, 295);
window.getCanvas().setMagnification(1.0);
image3 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// normalize
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clijx.plugins.Normalize());
node.setSources(image2);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(570, 931);
window.setSize(415, 295);
window.getCanvas().setMagnification(1.0);
image4 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// extendLabelingViaVoronoi
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.ExtendLabelingViaVoronoi());
node.setSources(image3);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(991, 379);
window.setSize(415, 295);
window.getCanvas().setMagnification(1.0);
image5 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// detectLabelEdges
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.DetectLabelEdges());
node.setSources(image5);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(1468, 336);
window.setSize(415, 295);
window.getCanvas().setMagnification(1.0);
image6 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// maximumImages
node = new net.haesleinhuepf.clijx.assistant.interactive.generic.GenericAssistantGUIPlugin(new net.haesleinhuepf.clij2.plugins.MaximumImages());
node.setSources(image4, image6);
node.run("");
node.refreshDialogFromArguments();
node.setTargetInvalid();
// set window position and size
window = node.getTarget().getWindow();
window.setLocation(1056, 697);
window.setSize(804, 530);
window.getCanvas().setMagnification(2.0);
image7 = node.getTarget();
java.lang.Thread.sleep(500);
IJ.run("In [+]");
IJ.run("Out [-]");

// reset auto-positioning
IJ.wait(500);
net.haesleinhuepf.clijx.assistant.AbstractAssistantGUIPlugin.setAutoPosition(was_auto_position);

