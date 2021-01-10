import pyclesperanto_prototype as cle
import numpy as np
import time

cle.select_device('RTX')
cle.set_wait_for_kernel_finish(True)

# config
num_iterations = 10
num_tests = 10

# initialize GPU
print("Used GPU: " + cle.get_device().name)

# generate data; 50 MB
image = np.random.random([50, 1024, 1024])
print("Image size: " + str(image.shape))

# push image to GPU memory
flip = cle.push_zyx(image)
flop = cle.create_like(flip)

for j in range(0, num_tests):
    start = time.time()

    for i in range(0, num_iterations):
        cle.maximum_sphere(flip, flop, 3, 3, 0)
        cle.minimum_sphere(flop, flip, 3, 3, 0)

    end = time.time()

    print("Flip-flop took " + str(end - start) + "s")


for j in range(0, num_tests):
    start = time.time()

    for i in range(0, num_iterations):
        flop = cle.maximum_sphere(flip, radius_x=3, radius_y=3, radius_z=0)
        flip = cle.minimum_sphere(flop, radius_x=3, radius_y=3, radius_z=0)

    end = time.time()

    print("Re-alloc took " + str(end - start) + "s")




