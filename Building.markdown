    /**  ____    __    ____  ____  ____/___      ____  __  __  ____
     *  (  _ \  /__\  (_   )(_  _)( ___) __)    (  _ \(  )(  )(  _ \
     *   )   / /(__)\  / /_  _)(_  )__)\__ \     )___/ )(__)(  ) _ <
     *  (_)\_)(__)(__)(____)(____)(____)___/    (__)  (______)(____/
     *                      
     *  Copyright (c) Razvan Cojocaru, 2007+, Creative Commons Attribution 3.0
     */

Developing & Building
---------------------

These projects are setup as eclipse projects and also have ant build and sbt build files. 
The sbt and eclipse are actively maintained...


Here's how to build it:

1. Setup ant and scala
2. Make a workspace directory ${w}
3. checkout the following projects

   cd ${w}
   git clone git@github.com:razie/razbase.git

Note: if you don't have a github ssh key setup, use the anonymous checkout:

    git clone http://github.com/razie/razbase.git
            
4. sbt build

   for ff in "razxml razbase "
   do
      cd $ff
      sbt update publish-local
   done

5. ant build
-  edit ${w}/razbase/razie.properties and set the w property to the workspace

-  build all and create the dist jar file:

   cd ${w}/razbase
   ant clean-all build-all dist

7. .Eclipse setup

   * install the scala 2.8 plugin and the svn plugin
   * download a 2.8 scala distribution someplace, i.e. bin/scala - will need the complier.jar
   * download a 2.8-compatible scalatest distribution someplace - will need the library 
   
   It's important to note that the eclipse projects depend on the sbt jar files! 
   Do an sbt build first...
   
7.1. create the projects
   Create a project for each of the above: razbase, razxml, 20widgets, razweb, scripster, gremlins

7.2. fix library dependencies

   Create two User Libraries (Window/Preferences/Java/Build Path/User Libraries):
   * scalatest - containing the scalatest-0.9.5.jar file or whichever is latest. Make sure you have the version that's compiled for scala 2.8
   * scalacompiler - with scala-compiler.jar (from the scala 2.8 installation) 


Good luck!

