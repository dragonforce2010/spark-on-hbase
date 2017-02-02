package main.org.x.spark.hbase

/**
  * Created by Tomer.
  */
object HBaseUtils {

  // transform columns into an array of (column family, qualifier)
  def toColumnFamilyAndQualifier(columns: Seq[String]) = {
    columns.map(s => {
      val splitArray = s.split(":")
      val columnFamily = splitArray(0)
      val qualifier = splitArray(1)
      (columnFamily, qualifier)
    }).toArray
  }
}
