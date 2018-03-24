maze-solver
===========
A simple CLI based maze solver that provides implementations of **graph** data structure and their 
basic search techniques (breadth-first-search and depth-first-search) to demonstrate and contrast 
their behavior to the same problem i.e. a maze in this case.

Input
-----
A `maze-solver` requires the maze in form of `.maze` file, The format can be described as:

    4 4
    
      *
     **
     
The first line in the file consists of number of `rows` and `columns` in the maze. Subsequent 
number of lines i.e. `rows` consists of the maze itself. ` ` blank space indicates a valid path 
whereas `*` indicates an obstacle.

Usage
-----
Load the maze:
```java
String mazeFilePath = "data/mazes/maze1.maze"
Maze maze = new Maze(mazeFile);
System.out.println("Maze:");
maze.printMaze();
```
```
Maze:
----
--*-
-**-
----
```

The DFS solution to the maze:
```java
List<MazeNode> dfsPath = maze.dfs(3, 3, 2, 2);
maze.setPath(dfsPath);
System.out.println("DFS Path:");
maze.printMaze();
```
```
DFS Path:
oooo
o-*o
G**o
---S
```

The BFS solution to the maze:
```java
List<MazeNode> bfsPath = maze.bfs(3, 3, 2, 2);
maze.setPath(bfsPath);
System.out.println("BFS Path:");
maze.printMaze();
```

```
BFS Path:
----
--*-
G**-
oooS
```

License
-------

    MIT License
    
    Copyright (c) 2017 utsavoza
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.