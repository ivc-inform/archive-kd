package com.simplesys.play

import com.simplesys.isc.dataBinging.DataSourceDyn
import com.simplesys.isc.system.global.{PortalMode, _}
import com.simplesys.json._
import com.simplesys.common.Strings._

import scala.xml._

object Xml {
    val fixedArrayNames = Seq("fields", "types", "valueMap")

    def xmlToJson(xml: Elem): JsonObject = {

        def nameOf(node: Node) = (if (Option(node.prefix).nonEmpty) node.prefix + ":" else "") + node.label

        def buildJson(node: Node): JsonElement = {
            def buildAttributes(node: Node) = {
                val res = JsonObject()
                node.attributes.foreach {
                    (a: MetaData) =>
                        val value = a.value.text.trim
                        if (value != "")
                            res += (a.key -> value.toJson)
                }
                res
            }
            def directChildren(node: Node): NodeSeq = node.child.filter(_.isInstanceOf[Elem])
            def isLeaf(node: Node) = !node.descendant.find(_.isInstanceOf[Elem]).isDefined

            def isArray(nodes: NodeSeq) = {
                val nodeNames = nodes.map(nameOf(_))
                nodeNames.size != 1 && nodeNames.distinct.size == 1
            }

            val res = JsonObject()
            val attributes = buildAttributes(node)
            res ++= attributes

            //res.log()

            val children = directChildren(node)
            children.foreach {
                child =>
                    val children = directChildren(child)
                    val childrenJson = if (isArray(children) || fixedArrayNames.exists(_ == nameOf(child))) {
                        val res = JsonList()
                        children.foreach {
                            node =>
                                res += buildJson(node)
                            //res.log
                        }
                        res
                    } else {
                        val res = JsonObject()
                        children.foreach {
                            node =>
                                res += nameOf(node) -> buildJson(node)
                            //res.log()
                        }
                        res
                    }
                    res += (nameOf(child) -> childrenJson)
                //res.log()
            }

            if (isLeaf(node) && node.text.trim != "") node.text.toJson else res
        }

        JsonObject(nameOf(xml) -> buildJson(xml))
    }

    def getJS(xml: Elem, componentName: String, prettyString: Boolean): String = {
        val json = Xml.xmlToJson(xml)

        ModeNames = PortalMode

        json.headOption match {
            case Some(item) if item._1 == "DataSource" =>
                item._2 match {
                    case item: JsonObject =>
                        if (prettyString)
                            (new DataSourceDyn(item, true)).toPrettyString
                        else
                            (new DataSourceDyn(item, true)).toString()
                    case x =>
                        throw new RuntimeException(s"Bad branch $x on component: $componentName")
                }

            case Some(item) if item._1 == "Object" =>
                strEmpty
                /*item._2 match {
                    case item: JsonObject =>
                        if (prettyString)
                            item.toPrettyString
                        else
                            item.toString()

                    case x =>
                        throw new RuntimeException(s"Bad branch $x on component: $componentName")
                }*/
            case x =>
                throw new RuntimeException(s"Bad branch $x on component: $componentName")
        }
    }
}
