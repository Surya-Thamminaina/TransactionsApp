package application

import model.Constants._
import model.{Transaction, TransactionType}
import org.joda.time.DateTime
import util.Utils._

import scala.io.StdIn
import scala.util.{Success, Try}

/**
  * Created by surya on 6/7/18.
  * Description: TransactionsApp is the entry point of this application.
  */
object TransactionsApp extends App{

  //This function is the entry point to run this application
  def runApplication: Unit = {

    val linesFromFile: List[String] = getTransactionLinesFromFile()

    val validTransactions: List[Transaction] = linesFromFile.map(prepareTransactionData).collect{case Success(transacion) => transacion}

    val transactionPerType = getTransactionsPerType(validTransactions).mapValues(totalTransactionsValuePerDate)

    println(prettyOutput(transactionPerType))
    println(s"The oldest transaction(s) in the file: \n${getOldestTransaction(validTransactions).mkString("\n")}\n ")
    println(s"Number of invalid lines in the file: ${linesFromFile.length - validTransactions.length}")

  }

  /**
    * Pure function that gets the oldest transaction(s) among the given list of transactions
    * @param transactions: list of transaction objects
    * @return list of oldest transactions if there are more than one when there is only transaction which is older than all it will return a list with one transaction in it
    */
  def getOldestTransaction(transactions: List[Transaction]): List[Transaction] = {
    implicit def dateTimeOrdering: Ordering[DateTime] = Ordering.fromLessThan(_ isBefore _)
    val oldestDate = transactions.map(_.timestamp).max
    transactions.filter(_.timestamp == oldestDate)
  }

  /**
    * Pure function that tries to prepare Transaction object from the given string
    * @param line either a transaction data in valid format or an invalid string
    * @return Try[Transaction]
    */
  def prepareTransactionData(line:String): Try[Transaction] = Try {
    val delimSeperatedValues = line.split(TRANSACTION_ROW_DELIMITER)
    lazy val transactionId: Long = delimSeperatedValues(TRANSACTION_ID_ROW_INDEX).toLong
    lazy val transactionType: TransactionType = TransactionType.fromString(delimSeperatedValues(TRANSACTION_TYPE_ROW_INDEX))
    lazy val amount: Double = delimSeperatedValues(TRANSACTION_AMOUNT_ROW_INDEX).toDouble
    lazy val timestamp: DateTime = getTimeStampFromString(delimSeperatedValues(TRANSACTION_DATETIME_ROW_INDEX))

    Transaction(transactionId, transactionType, amount, timestamp)
  }


  /**
    * Pure function that groups the given transactions by the TransactionType
    * @param transactions list of Transaction objects
    * @return Map[TransactionType, List[Transaction] ]
    */
  def getTransactionsPerType(transactions: List[Transaction]): Map[TransactionType, List[Transaction]] = transactions.groupBy(_.transactionType)

  /**
    * Pure function that sums the transactions amount per day
    * @param transactions list of transactions
    * @return Map[DateTime, Double]
    */
  def totalTransactionsValuePerDate(transactions: List[Transaction]): Map[DateTime, Double] = transactions.groupBy(_.timestamp).mapValues(a => a.map(_.amount).sum)


//  def main(args: Array[String]): Unit = {
//    println(
//      """Welcome to the Transactions Application
//        |Currently the application is using transaction_details.txt file as an input resource file.
//        |Please change the content of transaction_details.txt as needed. However, I've added some test data to that file
//        |Please command RunApp to run the application:
//      """.stripMargin)
//
//    StdIn.readLine() match {
//      case "RunApp" => runApplication
//      case _ => "That's an invalid command. Please command RunApp to run the application:"
//    }
//  }

  runApplication

}
