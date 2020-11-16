// Mean Filter comparison CPU versus GPU (CLIJ2)
//  Robert Haase, November 2020

run("Close All");

run("T1 Head (2.4M, 16-bits)");
input = getTitle();

selectWindow(input);
run("Duplicate...", "duplicate range=1-129");

// Apply filter using ImageJ
run("Mean 3D...", "x=3 y=3 z=3");
cpu_result = "CPU result";
rename(cpu_result);
	
// Initialization of the GPU:
run("CLIJ2 Macro Extensions", "cl_device=");
Ext.CLIJ2_clear();

// Push images to GPU
Ext.CLIJ2_push(input);

// apply filter using CLIJ
gpu_result = "GPU result";
Ext.CLIJ2_mean3DBox(input, gpu_result, 3, 3, 3);

// TRY mean3DSPhere!
// Ext.CLIJ2_mean3DSphere(input, gpu_result, 3, 3, 3);

// transfer CPU result to GPU
Ext.CLIJ2_push(cpu_result);

// compare images
absolute_difference = "absolute_difference";
Ext.CLIJ2_absoluteDifference(cpu_result, gpu_result, absolute_difference);
Ext.CLIJ2_pull(absolute_difference);

// measure mean squared error
Ext.CLIJ2_getMeanSquaredError(cpu_result, gpu_result, mse);
print("MSE: " + mse)

// For documentation purposes, we also should report about the used GPU:
Ext.CLIJ2_getGPUProperties(gpu, memory, opencl_version);
print("GPU: " + gpu);
print("Memory in GB: " + (memory / 1024 / 1024 / 1024) );
print("OpenCL version: " + opencl_version);

// At the end of the macro, clean up:
Ext.CLIJ2_clear();
