package antbuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Generator {
  public static void outputFile(String[] prt_data) throws IOException {
    FileOutputStream build_xml = new FileOutputStream("build.xml");

    String config = getConfig(prt_data[0], prt_data[1]);

    for (int i = 0; i < config.length(); i++) {
      build_xml.write(config.charAt(i));
    }

    build_xml.close();
  }

  public static void runInit() throws IOException {
    String[] args = new String[]{"ant", "init"};
    ProcessBuilder proc = new ProcessBuilder();
    proc.command(args);
    proc.directory(new File(System.getProperty("user.dir")));
    Process ant_proc = proc.start();

    BufferedReader reader = new BufferedReader(
      new InputStreamReader(ant_proc.getInputStream())
    );

    StringBuilder output = new StringBuilder();
    String line = null;
    while ( (line = reader.readLine()) != null) {
       output.append(line);
       output.append(System.getProperty("line.separator"));
    }
    String result = output.toString();
    System.out.println(result);

    System.out.println("Intialize Ant Project...\t[OK]");
  }

  private static String getConfig(String prt_name, String prt_desc) {
    return String.format("""
<project basedir="." default="dist" name="%1$s">
  <description>%2$s</description>
  <property location="src" name="src"></property>
  <property location="out" name="out"></property>
  <property location="dist" name="dist"></property>
  <target name="init">
    <tstamp></tstamp>
    <mkdir dir="${out}"></mkdir>
    <mkdir dir="${src}/%3$s"></mkdir>
    <touch file="${src}/%3$s/Main.java"/>
  </target>
  <target depends="init" description="compile the source" name="compile">
    <javac destdir="${out}" srcdir="${src}"></javac>
  </target>
  <target depends="compile" name="dist">
    <mkdir dir="${dist}/lib"></mkdir>
    <jar destfile="${dist}/lib/%1$s-${DSTAMP}.jar">
      <fileset dir="${out}"></fileset>
      <!-- <zipfileset includes="*/**.class" src="lib/main/some.jar"/> -->
      <manifest>
        <attribute name="Main-Class" value="%3$s.Main"></attribute>
      </manifest>
    </jar>
  </target>
  <target description="clean up" name="clean">
    <delete dir="${out}"></delete>
    <delete dir="${dist}"></delete>
  </target>
</project>
        """, prt_name, prt_desc, prt_name.toLowerCase());
  }
}
