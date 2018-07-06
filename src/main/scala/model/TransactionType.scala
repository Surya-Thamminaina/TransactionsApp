package model

/**
  * Created by surya on 6/7/18.
  * Description: TransactionType is a trait and will have sub types of it
  * where each type is a constant and represents a type of the transaction.
  */
trait TransactionType

case object DEPOSIT extends TransactionType

case object WITHDRAWAL extends TransactionType

case object REPAYMENT extends TransactionType

case object TRANSFER extends TransactionType

object TransactionType {
  def fromString(transactionType: String): TransactionType = {
    transactionType match {
      case "DEPOSIT" => DEPOSIT
      case "WITHDRAWAL" => WITHDRAWAL
      case "REPAYMENT" => REPAYMENT
      case "TRANSFER" => TRANSFER
    }
  }
}