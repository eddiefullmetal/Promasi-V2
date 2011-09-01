ProMaSi
=============================
Promasi is a Project Management Simulator

How to build
=============================
ProMaSi is using [maven](http://www.apache.org/).
In order to build the project the 3rd party libs must be installed first(mostly SWT libraries).

The project org.promasi.client is the only project with dependencies that must be manually installed 
to the local repository using *mvn install:install-file* command.
The following libraries must be installed

*	[Nebula GanttChart Nightly build](http://www.hexapixel.com/files/nightly/org.eclipse.nebula.widgets.ganttchart-2.0-NIGHTLY.jar). Install using the following command:

	mvn install:install-file -Dfile=[path to jar] -DgroupId=org.eclipse.nebula.widgets -DartifactId=ganttchart -Dversion=2.0-NIGHTLY -Dpackaging=jar
	
*	[Nebula Calendar Combo](http://www.hexapixel.com/files/nightly/org.eclipse.nebula.widgets.calendarcombo-1.0-NIGHTLY.jar). Install using the following command:
	
	mvn install:install-file -Dfile=[path to jar] -DgroupId=org.eclipse.nebula.widgets -DartifactId=calendarcombo -Dversion=1.0-NIGHTLY -Dpackaging=jar
	
*	[Nebula CDatetime](https://hudson.eclipse.org/hudson/job/maven-nebula-inc-nightly/lastSuccessfulBuild/artifact/org.eclipse.nebula.widgets.cdatetime/target/org.eclipse.nebula.widgets.cdatetime-0.14.0-SNAPSHOT.jar)

	mvn install:install-file -Dfile=[path to jar] -DgroupId=org.eclipse.nebula.widgets -DartifactId=cdatetime -Dversion=0.14.0-SNAPSHOT -Dpackaging=jar
	
*	[Nebula CWT](https://hudson.eclipse.org/hudson/job/maven-nebula-inc-nightly/lastSuccessfulBuild/artifact/org.eclipse.nebula.cwt/target/org.eclipse.nebula.cwt-0.9.0-SNAPSHOT.jar)

	mvn install:install-file -Dfile=[path to jar] -DgroupId=org.eclipse.nebula.widgets -DartifactId=cwt -Dversion=0.9.0-SNAPSHOT -Dpackaging=jar
	
*	[SWT](http://www.eclipse.org/swt/)

	mvn install:install-file -Dfile=[path to swt.jar] -DgroupId=org.eclipse -DartifactId=swt -Dversion=3.7 -Dpackaging=jar
	
*	SWT GroupLayout: Install the WindowBuilderPro eclipse plugin and then go to the eclipse plugin folder and take the org.eclipse.wb.swt.layout.grouplayout_1.1.0.r37x201107161520

	mvn install:install-file -Dfile=[path to jar] -DgroupId=org.eclipse.swt -DartifactId=grouplayout -Dversion=1.1.0 -Dpackaging=jar
	
*	The GroupLayout has a dependency in the swt baseline. The org.eclipse.wb.swt.widgets.baseline_1.1.0.r37x201107161502.jar must also be installed in the repo

	 mvn install:install-file -Dfile=[path to jar] -DgroupId=org.eclipse.swt -DartifactId=baseline -Dversion=1.1.0 -Dpackaging=jar
	 