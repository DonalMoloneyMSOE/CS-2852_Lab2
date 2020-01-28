# CS-2852_Lab2

Overview

Prior to smartphones and tablets, parents resorted to flattened wood pulp and sticks of colored wax to occupy their kids’ attention. One popular App for paper and crayons was called “Connect the Dots” or “Dot to Dot.” The “screen” consisted of a collection of numbered dots. The user was required to draw lines between consecutive dots. If the user worked the App correctly, a piece of art emerged. In this assignment you will enhance your solution from Lab 1 to create a generator for dot-to-dot games.
Resources

    balloon.dot
    dove.dot
    skull.dot

Assignment

Your program must:

    Incorporate feedback from your instructor from lab 1
    Indicate the total number of dots in the selected file
    Allow the user to enter a desired number of dots
    Use the algorithm described below to remove the least critical dots from the picture
    Display the picture produced by removing all but the user-specified number of dots
    Save a picture to a .dot file

Note: If the user loads a picture with 2,000 dots, then enters 100 for the desired number of dots, and subsequently enters 1,000 for the desired number of dots, your program must show a picture 1,000 dots, not 100.

Here are a few additional sample .dot files that your program must be able to load successfully: balloon.dot, dove.dot, and skull.dot.
Details
Calculating the Critical Value of a Dot

The critical value of a dot depends on the relative proximity of it and its immediate neighbors. Consider three dots, labeled 1, 2, and 3, on a straight line. If dot 2 is removed, the connect the dots version of the line from 1 to 3 will look exactly the same. Therefore, we can conclude that the dot is not critical. If dot 2 is significantly far from the line connecting dot 1 and 3, then error would be introduced if dot 2 is eliminated. The following figure illustrates this:
Critical Value Illustration
Figure 1: Critical Value Illustration

The critical value of dot 2 is calculated as the sum of the distances from dot 2 to dot 1 and from dot 2 to dot 3 minus the distance from dot 1 to dot 3, i.e., cv2=d12+d23−d13

where cvy
is the critical value for dot y and dxz

are the distances illustrated below.
Critical Value Calculation
Figure 2: Critical Value Calculation

Note that in the case of a straight line between dots 1 and 3, cv2=0

Your program should assume that the first and the last dots are connected so they should be treated as neighbors when calculating their critical values.
Software Design

You must implement double calculateCriticalValue(Dot previous, Dot next) in your Dot class which returns the critical value for the dot based on the neighboring dots that are passed to the method. In addition, you must remove all constructors from the Picture class and implement the following constructors:

    Picture(List<Dot> emptyList) — Constructor that uses the list emptyList passed to it to store the dots for this picture.
    Picture(Picture original, List<Dot> emptyList) — Constructor that copies the dots from original into emptyList and uses it to store the dots for this picture.

These constructors provide flexibility in our Picture class because we can now specify the specific implementation of the List used (e.g., ArrayList, LinkedList, or even our own List implementation) by passing the appropriate list to the constructor. In fact, the Picture class is no longer dependent on any concrete list implementation.

You must also implement the following methods in the Picture class:

    void save(Path path) — saves the picture to .dot path that is compatible with the format described in lab 1.
    void removeDots(int numberDesired) — removes all but the numberDesired most critical dots.
        If the picture does not have more than numberDesired dots, the method returns without changing the picture.
        If numberDesired < 3, an IllegalArgumentException is thrown.

Your implementation of this method must be consistent with this flow chart:
Dot Removal Algorithm Flowchart
Figure 3: Dot Removal Algorithm Flowchart
Just For Fun

Ambitious students may wish to:

    Just for Fun suggestions from lab 1.
    Use shades of colors to indicate the order in which to connect the dots.
    Overlay the reduced-dot picture on top of the picture with all the dots in the file. You may wish to display the original picture in a lighter color.
    Use colors to indicate the critical value of each dot.
    Add a third dimension (x, y, z) to each dot, develop an algoritm for calculating the critical value in 3D, and create a 3D viewer to allow viewing the object from different perspectives.

Lab Deliverables

    See your professor’s instructions for details on submission guidelines and due dates.

        Dr. Taylor’s students: See below
        All other students should refer to Blackboard

    If you have any questions, consult your instructor.

Acknowledgment

This laboratory assignment, developed by Dr. Chris Taylor, is motivated by the seventh programming project in chapter 2 of our textbook which is based on the paper: L. J. Latecki and R. Lakamper, “Convexity Rule for Shape Decomposition Based on Discrete Contour Evolution,” Computer Vision and Image Understanding (CVIU) 73(1999), pp. 441-454.
