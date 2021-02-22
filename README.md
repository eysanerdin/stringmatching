# Motivation
Visualizes Naive and Boyer Moore Horspool to help gain insight as to how these algorithms work, and how they compare to each other. 
# Goal
The aim of the String Matching Problem is to find all the occurrences of a word in a text.
# Naive Algorithm 
Uses two nested loops to search through the text. Outer loop iterates through all possible positions and the inner loop iterates through the corresponding characters of the text and the word in the current position whilst comparing the corresponding characters. Inner loop breaks if a mismatch happens. 
# Boyer Moore Horspool Algorithm
To increase the time efficiency, this algorithm skips some positions by using a heuristic table. The heuristic table is filled by preprocessing the word, and it consists of calculated values for all characters in the alphabet of the text.
Characters that are not in the word, has the length of the word as its value in this table and the values of those that are in the word (except the rightmost character of the word) is calculated by the distance between the rightmost position of that character and the rightmost character in the word.
Which corresponds to m-i, where m=length of the word and i=rightmost position of the character.
And this algorithm uses the same approach as naive algorithm, except that the outer loop uses this heuristic table to adjust the new position. 
# The Code
I used Java Swing to get input text and word from the user, and to visualize the chosen algorithm.
Here is how it looks like:
![](https://outklip.com/v/-MUAzbctt_tqMa-Ay7rU)
