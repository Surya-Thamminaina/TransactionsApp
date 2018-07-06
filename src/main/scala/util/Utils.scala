package util

import model.Constants.TRANSACTION_TIMESTAMP_FORMAT
import model.TransactionType
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

import scala.io.Source

/**
  * Created by surya on 6/7/18.
  * Description: This file holds all the helper and utility methods that can help to smoother
  * things required by this project
  */
object Utils {

  val formatter: DateTimeFormatter = DateTimeFormat.forPattern(TRANSACTION_TIMESTAMP_FORMAT)

  /**
    * This function reads the resource file, prepares lines
    * @param fileName name of the resource file to read
    * @return List[String] - each String in the list represents a row in the file
    */
  def getTransactionLinesFromFile(fileName: String = "transaction_details.txt"): List[String] = Source.fromResource(fileName).getLines.toList

  /***
    * Helper function to parse the given string to required DateTime object
    * @param stringDate date in dd/MM/YYYY format
    * @return a DateTime Object
    */
  def getTimeStampFromString(stringDate: String): DateTime = formatter.parseDateTime(stringDate)

  /**
    *Helper function to parse the DateTime object to String date in dd/MM/YYYY format
    * @param timestamp a DateTime Object
    * @return a dd/MM/YYYY format String
    */
  def getDateFromTimestamp(timestamp: DateTime): String = formatter.print(timestamp)

  /**
    * Helper function to make the output pretty
    * @param result The response object
    * @return Formatted string as required by the output
    */
  def prettyOutput(result: Map[TransactionType, Map[DateTime, Double]]): String = {
    result.map{case (transactionType, dateWiseSum) => {
      val dateNSumString = dateWiseSum.map{case (date, totalTransactionValue) => s"${getDateFromTimestamp(date)} : $totalTransactionValue"}.mkString("\n")

      s"$transactionType:\n $dateNSumString\n"

    }}.mkString
  }
}
