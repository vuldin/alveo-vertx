alveo-vertx
===========

> **PLEASE NOTE** This project is currently under development and being implemented, and as such all info here should be considered plans only! At this stage, much of what is described in this README is not functional. Eventually the project's build status (along with implemented features) will be available here.

The goal for this project is to allow a person to create and view text, links, images and other content that is relevant to the media being played at that time. As video or audio is being played, the user will see the metadata being 'played back', so the information obtained from the media is inaugmented with extra relevant information. There are several features of this project (and along with these features come some assumptions/limitations which are also explained below):

**Media server**
This project does not focus on serving media itself. Instead it will search the local network for a UPnP media server at the initial startup and begin generating metadata on the media found. UPnP functionality is provided by [Cling](http://4thline.org/projects/cling/). Initial metadata collection is done with the help of the [OMDB Project](http://omdbapi.com/).

**Viewing content**
Media and metadata viewing is done in a browser and relies heavily on an awesome Mozilla project named [PopcornJS](http://popcornjs.org/). PopcornJS handles the association between media and other information on a webpage. Most testing has been done in Firefox, but Chrome and webkit-based browsers should work as well. Please create an issue if you notice this is not the case.

Only HTML5-compatible media is targeted for this project.

Prerequisites
-------------

**Java**
JDK 1.7.0+ is required, and there are a few options here. Two are [OpenJDK](http://download.java.net/openjdk/jdk7/) and [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).

**Vert.x**
Install instructions for vertx can be found [here](http://vertx.io/install.html).

**Gradle** (optional)
Gradle is used to build this project. You can either install locally or use the included gradlew executable in the root directory). Info on installing gradle is located [here](http://www.gradle.org/downloads).

Installation
------------

Download the code:

    # git clone https://github.com/joshuapurcell/alveo-vertx.git

Build the source (replace **gradle** with **gradlew** if you don't have this app installed locally):

    # gradle build

Run the server:

    # gradle run


