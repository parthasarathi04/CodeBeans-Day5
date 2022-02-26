# CodeBeans-Day5
Day 5 of 5 day Coding Challenge &amp;lt;C{o}deBeans/> 1.0 by CoffeeBeans Consulting

# Crime Master
Inspector Robostop is very angry. Last night, a bank was robbed and Crime Master Gogo escaped.
As quickly as possible, all roads leading out of the city were blocked, making it impossible for Crime Master Gogo to escape.
The inspector then asked everybody in the city to watch out for Crime Master Gogo, but the only messages he got were "We don't see him."

Robostop is determined to discover exactly how the he escaped.
He asks you to write a program which analyzes all the inspector's information to find out where Crime Master Gogo was at any given time.

The city in which the bank was robbed has a rectangular shape.
All roads leaving the city were blocked for a certain period of time t,
during which several observations of the form "Crime Master Gogo isn't in the rectangle Ri at time ti" were reported.
Assuming that Crime Master Gogo can move at most one unit per time step, try to find the exact position of Crime Master Gogo at each time step.

# Input
The input file describes several robberies. The first line of each description consists of three numbers W, H, and t (1<=W, H , <=100), where W is the width, H the height of the city, and t is the length of time during which the city is locked.
The next line contains a single integer n (0 <=1 <=100), where n is the number of messages the inspector received. The next n lines each consist of five integers ti, Li, Ti, Ri, Bi, where t, is the time at which the observation has been made (1 <= ti<=t), and Li, Ti, Ri, Bi, are the left, top, right, and bottom, respectively, of the rectangular area which has been observed. The point (1,1) is the upper-left-hand corner, and (W, H) is the lower-right-hand corner of the city. The messages mean that Crime Master Gogo was not in the given rectangle at time ti.
The input is terminated by a test case starting with W = H = t = 0. This case should not be processed.

# Output
For each robbery, output the line "Robbery #k:", where k is the number of the robbery. Then, there are three possibilities:
If it is impossible thatCrime Master Gogo is still in the city, output "Crime Master Gogo has escaped."
In all other cases, assume that the He is still in the city. Output one line of the form "Time step T: Crime Master Gogo has been at x, y." for each time step in which the exact location can be deduced, and x and y are the column and row, respectively, of Crime Master Gogo in time step 1. Output these lines ordered by time T.
If nothing can be deduced, output the line "Nothing known." and hope that the inspector does not get even angrier.
Print a blank line after each processed case.

# Requirements
Java 8+

# Run
```
javac CrimeMaster.java
java CrimeMaster
```
* then as mentioned in the input section

# Example 

```
4 4 5
4
1 1 1 4 3
1 1 1 3 4
4 1 1 3 4
4 4 2 4 4
Robbery #1:
Time step 1: Crime Master Gogo has been at 4,4.
Time step 2: Crime Master Gogo has been at 4,3.
Time step 3: Crime Master Gogo has been at 4,2.
Time step 4: Crime Master Gogo has been at 4,1.

10 10 3
1
2 1 1 10 10
Robbery #2:
Crime Master Gogo has escaped.

0 0 0
```
