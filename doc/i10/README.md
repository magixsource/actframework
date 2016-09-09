# Issue #10 - Refactor the bytecode scanner/enhancer for varieties of methods used in ActFramework

This directory contains working document about refactoring the method scanner/enhancer and relevant meta data structure so we
can improve the reuse of scanning/enhancing logic across the following parts:
 
* Controller action handlers
* Mailer sending methods
* Job methods 
* Commander methods
 
## Mission Attack Strategy:

1) Evaluate all current meta datastructure
 
* First evaluate the current meta data structure used by all scanner/enhancers
* Check if we can simplify the data structure by delay the inspection to Runtime using reflection (which is not slow 
  because they will only be inspect for one time)
* Extract the common properties across all types of meta data

2) Create class hierarchy for meta data structure for

* Class
* Method
* Parameter

3) Create scanner hierarchy for all types of classes and methods that needs bytecode scanning

4) Create enhancer hierarchy for all types of classes and methods that needs bytecode enhancement

5) Adjust unit test cases accordingly and make sure the refactor work pass all unit tests
