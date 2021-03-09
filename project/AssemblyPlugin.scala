import sbt.Keys._
import sbt.{Def, _}
import sbtassembly.AssemblyPlugin.autoImport.{assembly, assemblyMergeStrategy}
import sbtassembly.{MergeStrategy, PathList}

object AssemblyPlugin extends AutoPlugin {
  override def requires: Plugins = sbtassembly.AssemblyPlugin

  override def projectSettings: Seq[Def.Setting[_]] =
    super.projectSettings ++ Seq(
      retrieveManaged := true,
      assemblyMergeStrategy in assembly := {
        case r if r.startsWith("reference.conf") => MergeStrategy.concat
        case PathList("META-INF", m) if m.equalsIgnoreCase("MANIFEST.MF") =>
          MergeStrategy.discard
        case x => MergeStrategy.first
      }
    )
}
