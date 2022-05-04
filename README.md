# Tennis Kata
As a starting point, I took a look at the implementation of `TennisGame1` and the tests, and then read up a bit on the scoring rules of tennis. Weird rules, that's for sure. I didn't look through any of the other implementations (not enough time), so my "refactor" (more a redesign, really) is just based on what came to mind looking at that first implementation and understanding the rules.

Basically, the rules screamed "state machine" to me since the actual numerical score doesn't really seem to matter at all. I thought it might be interesting to flex some Java 17 features for that implementation by using records and a sealed interface to create the states of my state machine. Effectively, this gives me what we saw before with Rust's enums (I think the fancy term for them are algebraic data types, specifically sum types).

Of course, beyond this design change, there's a bunch of clean up of obvious mistakes that were included in the starter code. I tried to make changes a step at a time with good commit messages so that you can (hopefully) follow my thinking. 