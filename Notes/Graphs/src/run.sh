#!/bin/bash

# Supply a file to run in $1
javac ca/pluszero/graphs/*.java
java ca.pluszero.graphs/$1 < $2
