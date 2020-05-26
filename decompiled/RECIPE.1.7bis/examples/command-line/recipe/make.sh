#!/bin/sh
cd ../../../lib/java
. ./setEnv.sh
javac ../../examples/command-line/recipe/PrescriberIntegrationModuleCommandLine.java ..\..\examples\command-line\general\AbstractCommandLine.java -d ../../examples/command-line/recipe/
