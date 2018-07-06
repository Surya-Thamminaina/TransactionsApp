package model

import org.joda.time.DateTime

/**
  * Created by surya on 6/7/18.
  * Description: Transaction object is a scala case class
  * that holds all the information regarding the transaction record
  */
case class Transaction(
  id: Long,
  transactionType: TransactionType,
  amount: Double,
  timestamp: DateTime
)
