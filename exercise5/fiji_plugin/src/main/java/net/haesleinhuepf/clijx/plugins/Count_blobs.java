package net.haesleinhuepf.clijx.plugins;


import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clij.macro.CLIJMacroPlugin;
import net.haesleinhuepf.clij.macro.CLIJOpenCLProcessor;
import net.haesleinhuepf.clij.macro.documentation.OffersDocumentation;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clijx.CLIJx;
import net.haesleinhuepf.clijx.utilities.AbstractCLIJxPlugin;
import org.scijava.plugin.Plugin;
/**
 *
 * Author: Robert Haase
 *         Tue Nov 17 10:48:10 CET 2020
 */

@Plugin(type = CLIJMacroPlugin.class, name = "CLIJx_count_blobs")
public class Count_blobs extends AbstractCLIJxPlugin implements CLIJMacroPlugin, CLIJOpenCLProcessor, OffersDocumentation {

    @Override
    public String getParameterHelpText() {
        return "Image image1, ByRef Image image5, Number sigma_x_5, Number sigma_y_6";
    }

    @Override
    public Object[] getDefaultValues() {
        return new Object[]{null, null, new Float(16.0), new Float(16.0)};
    }

    @Override
    public boolean executeCL() {
        return count_blobs(getCLIJx(), (ClearCLBuffer) args[0], (ClearCLBuffer) args[1], asFloat(args[2]), asFloat(args[3]));
    }

    public static boolean count_blobs(CLIJx clijx, ClearCLBuffer image1, ClearCLBuffer image5, Float sigma_x_5, Float sigma_y_6) {

        // 
        
        // CLIJ2_copy
        ClearCLBuffer image2 = clijx.create(new long[]{image1.getWidth(), image1.getHeight()}, image1.getNativeType());
        clijx.copy(image1, image2);
        
        
        
        // CLIJ2_gaussianBlur2D
        ClearCLBuffer image3 = clijx.create(new long[]{image2.getWidth(), image2.getHeight()}, image2.getNativeType());
        clijx.gaussianBlur2D(image2, image3, sigma_x_5, sigma_y_6);
        
        
        
        // CLIJ2_greater
        ClearCLBuffer image4 = clijx.create(new long[]{image2.getWidth(), image2.getHeight()}, image2.getNativeType());
        clijx.greater(image2, image3, image4);
        image2.close();
        image3.close();
        
        
        
        // CLIJ2_connectedComponentsLabelingBox
        clijx.connectedComponentsLabelingBox(image4, image5);
        image4.close();
        
        
        

        return true;
    }

    @Override
    public String getDescription() {
        return "Counts blobs in grey value images";
    }

    @Override
    public String getAvailableForDimensions() {
        return "2D, 3D";
    }
}

