# Vaadin Jetty Add-on

Vaadin Jetty is a Vaadin add-on that allows you to run a Jetty Server for a Vaadin application in Java.

## Creating Vaadin add-ons

Vaadin Jetty Add-on is highly useful when developing Vaadin add-ons:

1. Create a Vaadin project using the `vaadin-archetype-widget` Maven archetype (see <https://vaadin.com/maven#archetypes>).

2. Remove the generated demo module.

3. Optionally, copy the `src` directory, the `assembly` directory, and the `pom.xml` file in the addon module to the root directory and remove the addon directory (this will overwrite the pom in the root directory).

4. Add the vaadin-jetty dependency using `<scope>test</scope>` in your pom.xml

5. If a custom widgetset is required, add the `vaadin-maven-plugin`:
```
<plugin>
    <groupId>com.vaadin</groupId>
    <artifactId>vaadin-maven-plugin</artifactId>
    <version>${vaadin.plugin.version}</version>
    <configuration>
        <extraJvmArgs>-Xmx512M -Xss1024k</extraJvmArgs>
        <webappDirectory>${basedir}/target/classes/VAADIN/widgetsets</webappDirectory>
        <draftCompile>false</draftCompile>
        <compileReport>false</compileReport>
        <style>OBF</style>
        <strict>true</strict>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>update-theme</goal>
                <goal>update-widgetset</goal>
                <goal>compile</goal>
                <!-- Comment out compile-theme goal to use on-the-fly theme compilation -->
                <goal>compile-theme</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

6. Add the `vaadin-themes` dependency in the `pom.xml` file if required (most of the time it is).

7. Implement a UI in the test directory. Use the `Widgetset` annotation if required.

8. Add a standard `main` method to the UI implementation and create and start a new `VaadinJettyServer`:

```
public static void main(String[] args) throws Exception {
    VaadinJettyServer server = new VaadinJettyServer(8080, TestUI.class);
    server.start();
}

```

9. Build the project (mvn clean package).

10. Run the Java application from your IDE.

If you are using Linux or Mac, copy and paste the following in a terminal and continue from step 4:

```
mvn -B archetype:generate -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=vaadin-archetype-widget -DarchetypeVersion=7.6.8 -DgroupId=com.example -Dpackage=com.example -DartifactId=example -DreleaseVersion=1.0-SNAPSHOT
cd example
rm -rf example-demo/
cp -r example-addon/src .
cp -r example-addon/assembly .
cp example-addon/pom.xml .
rm -rf example-addon/
```

## License

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.