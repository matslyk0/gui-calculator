<h4>What it's Built With</h4>

The EvalEx library is used for evaluating the calculation itself, everything else is written from scratch using built-in libraries.

<h4>Intention Behind It</h4>
The layout and functionality aim to be a cross between a typical desktop calculator and a scientific calculator.<br>

<h4>How the Result is Formatted</h4>
The way the result is formatted is based on how a scientific calculator does it.
<p>Background terminology: a leading digit is in front of the decimal, a trailing digit is behind the decimal.<br>
For example, 34.5 has two leading digits, 3 & 4, and one trailing digit, 5.<br>

When a result is formatted, the method counts how many leading digits it has and the format is picked based on that.<br>
Small results are formatted based on the amount of 0's immediately after the decimal.<br>

1 leading digit is formatted as #.######### (1 leading : 9 trailing)<br>
2 leading digits are formatted as ##.######## (2 leading : 8 trailing)<br>
3 leading digits are formatted as ###.####### (3 leading : 7 trailing)<br>
...<br>
9 leading digits are formatted as #########.# (9 leading : 1 trailing)<br>
10 leading digits are formatted as ########## (10 leading : 0 trailing)<br>

When a number exceeds 10 leading digits, it is converted to standard form and outputted in the 1:9 ratio followed by E 
and a number.

Numbers beginning with 0.0 or 0.00 are formatted as 0.0######## and 0.00####### respectively.<br>
Any number smaller than that (e.g. starting with 0.000) is converted to standard form and outputted in the 1:9 ratio 
followed by E and a number.
</p>