/*  CLIc example source file - Copyright 2020 Stéphane Rigaud, Robert Haase,
*   Institut Pasteur Paris, Max Planck Institute for Molecular Cell Biology and Genetics Dresden
*
*   CLIc is part of the clEsperanto project http://clesperanto.net 
*
*/

// CLIc include
#include "CLE.h"

// I/O include
#include "tiffreader.h"
#include "tiffwriter.h"

#include <iostream>

// Set CLIc Namespace
using namespace cle;

/**
 * Main function
 */
int main(int argc, char **argv)
{
    if (argc < 2)
    {
        std::cerr << "Usage: argv[0] filename [filename]" << std::endl;
        return EXIT_FAILURE;
    }
    
    /*
     * Manage input and output of the executable.
     */ 
    std::string inputFilename = argv[1];
    std::string outputFilename = "./output.tif";
    if (argc > 2)
    {
        outputFilename = argv[2];
    }
    
    /*
     * Read image from filename.
     */ 
    unsigned int width, height, depth;
    TiffReader reader(inputFilename.c_str());
    float* array = reader.read(&width, &height, &depth);
    Image<float> image(array, width, height, depth, "float");

    // initialise gpu
    cle::GPU gpu;
    cle::CLE cle(gpu);

    // push image to gpu and create gaussian output
    cle::Buffer input = cle.Push(image);
    cle::Buffer outputGaussian = cle.Create(image);
    // apply difference of gaussian
    cle.DifferenceOfGaussian2D(input, outputGaussian, 4, 4, 10, 10);

    // create detecte maxima output and detect maxima
    cle::Buffer outputMaxima = cle.Create(image);
    cle.DetectMaximaBox(outputGaussian, outputMaxima);

    Image<float> output = cle.Pull<float>(outputMaxima);

    // create 1 dimension buffer for sumofallpixel output
    unsigned int dimensions[3] = {1, 1, 1};
    cle::Buffer countMaximum = cle.Create<int>(dimensions, "int");
    // sum all pixels
    cle.SumOfAllPixels(outputMaxima, countMaximum);

    // pull final output and read the data
    Image<int> count = cle.Pull<int>(countMaximum);
    std::cout << "we have " << count.GetData()[0] << std::endl;


    /*
     * Write image to filename.
     */ 
    TiffWriter writer(outputFilename.c_str());
    writer.write(output.GetData(), width, height, depth);

    /*
     * That's all folks!
     */ 
    return EXIT_SUCCESS;
}
