package com.lnvortex.core

import org.bitcoins.crypto.StringFactory

sealed abstract class RoundStatus {
  def order: Int
}

object RoundStatus extends StringFactory[RoundStatus] {

  case object Pending extends RoundStatus {
    override val order: Int = 0
  }

  case object AlicesRegistered extends RoundStatus {
    override val order: Int = 1
  }

  case object OutputsRegistered extends RoundStatus {
    override val order: Int = 2
  }

  case object SigningPhase extends RoundStatus {
    override val order: Int = 3
  }

  case object Signed extends RoundStatus {
    override val order: Int = 4
  }

  case object Canceled extends RoundStatus {
    override val order: Int = 5
  }

  val all: Vector[RoundStatus] = Vector(Pending,
                                        AlicesRegistered,
                                        OutputsRegistered,
                                        SigningPhase,
                                        Signed,
                                        Canceled)

  override def fromStringOpt(string: String): Option[RoundStatus] = {
    all.find(_.toString.toLowerCase == string.toLowerCase)
  }

  override def fromString(string: String): RoundStatus = {
    fromStringOpt(string) match {
      case Some(value) => value
      case None =>
        sys.error(s"Could not find a RoundStatus for $string")
    }
  }

  def fromIntOpt(int: Int): Option[RoundStatus] = {
    all.find(_.order == int)
  }

  def fromInt(int: Int): RoundStatus = {
    fromIntOpt(int) match {
      case Some(value) => value
      case None =>
        sys.error(s"Could not find a RoundStatus for order $int")
    }
  }
}
