        DESIGN AND ASSUMPTIONS
*************************************
Digraph class: a weighted direct graph to represent the relationship between the routes.
              It is a generics class, so it would be able to any types of data to represent Vertex.
Vertex class: For this particular problem, each vertex representing a town.
              It stores all of incoming and outgoing edges on each vertex.
Edge class: represents the relationship between the each vertex and distance between the vertices.

I used depth-first search to find solutions for problems like max stops, exact stops etc.
I used dijkstra algorithm to find the shortest path between two points. I made a slight modification
so that it would work for case like origin and destination are the same.


HOW TO PROVIDE INPUT
*************************************
- Program takes input from text files
- init.txt: This input file includes all of route to initialize the graph
            Assume data are in "AB5" -> ('A','B',5) format, which represent origin, destination, and distance.

- input1.txt: This input file provides input to find the distance with provided the routes.
            Assume data are in "ABC" -> ('A','B','C') format; each Character represents a stop.

- input2.txt: This input file provides input to the find number of paths between two points with max stops constrain.
            Assume data are in "CC5" -> ('C','C',5) format, which represent origin, destination, and max number of stops.

- input3.txt: This input file provides input to find number of paths between two points with exact number of stops.
            Assume data are in "AC4" -> ('A','C',4) format, which represent origin, destination, and max number of stops.

-input4.txt: This input file provides input to find the shorest distance between two points
            Assume data are in "BB" -> ('A','C) format, which represent origin and destination.

 -input5.txt: This input file provides input to find the number of different routes between two points with max distance constrain.
            Assume data are in "CC 30" -> ('C','C', 30) format, which represent origin, destination, and max distance.


HOW TO RUN
*************************************
The program was locally tested on macOS 10.14.2 with java version "1.8.0_111"
- makefile: to execute the source files or to clean the .class files
- run: type "make" on the terminal
- clean: type "make clean" on terminal to clean .class files