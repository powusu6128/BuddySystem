# Buddy System

##Introduction
This repository is an implementation of the Buddy memory allocation technique to simulate memory management. Of course, many such techniques exist, but this particular technique offers simple block splitting and coalescing. The project was assigned in an undergraduate Operating Systems course with Dr. G. Baliga.

It should be noted that an implementation of this algorithm exists inside the Linux and FreeBSD kernels.

##Stage 1
In this stage, we have developed our own implementation of the Buddy memory allocation technique in an MVC architecture to allow for the creation of a GUI. We have also created extensive JUnit tests, that of this writing, all pass.

##Stage 2
In this stage, we have worked off of the components built in Stage 1 to build a fully functional GUI using Swing. It simulates allocation and deallocation requests randomly. The project depicts such a simulation graphically by drawing blocks. Green denotes that a block is free, while red denotes a block currently holds a process. A screenshot gives a good idea of how the project works:
![Screenshot of Buddy System Manager](http://elvis.rowan.edu/~smithr1/buddy_screenshot.png)


##Team Members:

* Tyler Andjel
* Ed Carter
* Justin Hyland
* Ryan Smith
