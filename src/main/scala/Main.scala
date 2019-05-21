import scala.collection.mutable
import scala.io.Source
import scala.util.matching.Regex

object Main {

  val importRegex: Regex = "\\s*import\\s*(\\w+)\\s*".r
  val valueRegex: Regex = "\\s*(\\w+)\\s*=\\s*(\\w+)\\s*".r
  val booleanRegex: Regex = "\\s*(\\w+)\\s*=\\s*(true|false)\\s*".r
  val numberRegex: Regex = """\s*(\w+)\s*=\s*-?(0|[1-9]\d*)(\.\d+)?\s*""".r
  val stringRegex: Regex = """\s*(\w+)\s*=\s*(".*")\s*""".r

  def main(args: Array[String]): Unit = {
    val fileName = args(0)
    println(fileName)
    getValues(fileName, mutable.HashMap(fileName -> "initial file")).foreach(pair => println(s"${pair._1} = ${pair._2}"))
  }

  private def read(fileName: String): Iterator[String] =
    Source.fromResource(fileName).getLines()

  def getValues(fileName: String, files: mutable.HashMap[String, String]): mutable.HashMap[String, Any] = {
    val result: mutable.HashMap[String, Any] = mutable.HashMap.empty[String, Any]
    read(s"$fileName.vars").foreach({
      case booleanRegex(key, value) => result += (key -> value.toBoolean)
      case numberRegex(key, integer, null) => result += (key -> integer.toInt)
      case numberRegex(key, integer, fractional) => result += (key -> (integer + fractional).toDouble)
      case stringRegex(key, value) => result += (key -> value)
      case valueRegex(key, value) =>
        result.get(value) match {
          case Some(res) => result += (key -> res)
          case None => println(s"Cannot resolve symbol $value in $fileName" )
        }
      case importRegex(file) =>
        if(files.contains(file)){
          println(s"Cyclical dependency in $fileName. $file was imported in ${files(file)}")
        }
        else {
          files += (file -> fileName)
          result ++= getValues(file, files)
        }
      case other => println(s"Cannot resolve line '$other' in $fileName")
    })
    result
  }
}
