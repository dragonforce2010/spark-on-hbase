Installation

Usage
Preliminary
First, add the following import to get the necessary implicits:
import *packageName*.Implicits._

Then, you have to give configuration parameters to connect to HBase. This is done by using the function set(key : scala.Predef.String, value : scala.Predef.String) of the class org.apache.spark.SparkConf with the key "spark.hbase.host" and the value equals to the url of the HBase host you want to connect with.
val sc = new SparkConf().setMaster("local").set("spark.hbase.host", "url")
More ways will be able in the future.

Reading from HBase
First, select which table you want to read from. The function "hbaseTable" extends the org.apache.spark.SparkContext in order to do it.
sc.hbaseTable("tableName")

Second, you may want to select which column familes and qulaifers to read from the table. This is done with the function select(columns: String*) where the column family and the qualifier are separated by colon.
sc.hbaseTable("tableName").select("columnFamily:qualifer")

Finally, use the function load() for getting the RDD collection represents all the records and data you wanted to read from HBase.
sc.hbaseTable("tableName").select("columnFamily:qualifer").load()

Other functions you may use are:
withRowStart(startRow: String) - The row key of the record you want to start reading from.
withRowStop(startStop: String) - The row key of the record you want to stop reading from.
withRowKeys(rowKeys: Seq[String]) - The row keys of the records you want to read from.
And some more...

Writing to HBase
First, create a RDD collection where each element containing a row key and values of a record you want to write to HBase
val recordsToSave = sc.parallielize(0 to 9).map(x => (x, "value1", "value2", "value3"))

Second, select which table you want to write to. The function toHBaseTable(tableName: String) extends a certain type of RDD in order to do it.
recordsToSave.toHBaseTable("tableName")

Third, select which columns you want to write to. Put attention that each column should be a corresponding value in the record you want to write.
recordsToSave.toHBaseTable("tableName").toColumns("cf1:q1", "cf2:q2", "cf3:q3")

Finally, use the function save() in order to write the records to HBase.
recordsToSave.toHBaseTable("tableName").toColumns("cf1:q1", "cf2:q2", "cf3:q3").save()


Deleting from HBase
First, create or use a RDD[String] of row keys of records you want to delete from HBase.
val recordsToDelete = sc.parallelize(1 to 3)

Second, select which table you want to delete from.
recordsToDelete.toHBaseTable("tableName")

Third and last, use the function delete() in order to delete the records from HBase.
recordsToDelete.toHBaseTable("tableName").delete()