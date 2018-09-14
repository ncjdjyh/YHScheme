## YHScheme
YHScheme may be a dialect of scheme programing in java, whatever, it`s my first own language though more like a toy.

## Introduce
### 1.expression
Like other dialect of list, YHScheme adopted the prefix expression .

like this	`(+ 1 1)` &emsp; `(/ 9 (* 3 (+ 1 2)))`

### 2.syntax
`Datatype` 

- number
- procedure

`keyword`

- begin
- cond
- def
- if
- lambda
- let

`essential procedure`

- '+'
- '-'
- '*'
- '/'
- '>'
- '<'
- '='
- 'print'

## How To Use
You can see YHScheme.jar file in "/src" catalog, just run it like this `java -jar YHScheme.jar`

Here ara some example for the convenience of using.

	;logic
	(> 1 2)                         ;false           			
	;def
	(def (sum x y))
	(sum 10 10)                     ;20					
	;lambda
	((lambda(x y) (+ x y)) 10 10)	;20
	



See more detail in my chinese blog