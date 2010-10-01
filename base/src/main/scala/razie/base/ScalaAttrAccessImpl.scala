/**  ____    __    ____  ____  ____,,___     ____  __  __  ____
 *  (  _ \  /__\  (_   )(_  _)( ___)/ __)   (  _ \(  )(  )(  _ \           Read
 *   )   / /(__)\  / /_  _)(_  )__) \__ \    )___/ )(__)(  ) _ <     README.txt
 *  (_)\_)(__)(__)(____)(____)(____)(___/   (__)  (______)(____/    LICENSE.txt
 */
package razie.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import razie.base.AttrAccess;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

/** simple base implementation */
abstract class ScalaAttrAccessImpl extends AttrAccess {
//   lazy val _attrs : scala.collection.mutable.HashMap[String, Any] = new scala.collection.mutable.HashMap[String, Any] ()
//   lazy val _types : scala.collection.mutable.HashMap[String, AttrType] = new scala.collection.mutable.HashMap[String, AttrType] ()
   lazy val _order : scala.collection.mutable.ListBuffer[String] = new scala.collection.mutable.ListBuffer[String] ()
    
   def hasAttrType(name:String):Boolean 

        /**
         * build from sequence of parm/value pairs or other stuff
         * 
         * @parm pairs are pais of name/value, i.e. "car", "lexus" OR a Properties, OR another
         *       AttrAccess OR a Map<String,String>. Note the parm names can contain type:
         *       "name:string"
         */
//       def this(pairs:Any*) = { this(); setAttr(pairs) }

       def sgetPopulatedAttr = (razie.RJS apply (this.getPopulatedAttr))

   override def toString() : String = 
      (for (a <- this.sgetPopulatedAttr) 
         yield a + (if (this.hasAttrType(a)) (":"+getAttrType(a)) else "") + "=" + sa(a)).mkString(",")
       
   override def toXml() : String = 
      (for (a <- this.sgetPopulatedAttr)  
         yield "<" + a + ">" + this.getAttr(a) + "</" + a + ">").mkString("")

   override def addToUrl(url:String) : String = {
      val s = (for (a <- sgetPopulatedAttr) 
         yield java.net.URLEncoder.encode(a, "UTF-8") + "=" + java.net.URLEncoder.encode(toStr(getAttr(a)), "UTF-8")).mkString("&")
      
      if (s.length<=0)
         url
      else if (url!= null && url != "" && !url.endsWith("?") && !url.endsWith("&")) 
         url + (if (url.contains("=")) "&" else "?") + s
      else if (url!= null)
         url+s
     else 
        s
   }
   
   override def a(name:String) : AnyRef = getAttr(name)

   override def sa(name:String) : String = {
      val v = this a name
      if (v == null) ""
      else v.toString
   }

   private def toStr (o:Object):String = if (o != null) o.toString() else ""
      
//   def getAttr(name:String) : AnyRef =  _attrs.getOrElse(name, null)
//  def apply (name:String) : Any = _attrs (name)
  
//  def sset (name:String, v:Any) : Any = { _attrs.put (name, v); v }
  
   def getOrElse(name:String, dflt:AnyRef) : AnyRef = if (isPopulated (name)) getAttr(name) else dflt
   def sgetOrElse(name:String, dflt:String) : String = getOrElse (name, dflt).toString

   override def foreach (f : (String, AnyRef) => Unit) : Unit =
      this.sgetPopulatedAttr.foreach (x => f(x, this a x))
      
   override def filter (f : (String, AnyRef) => Boolean) : Iterable[String] = 
      this.sgetPopulatedAttr.filter (x => f(x, this a x))

   override def map [A,B] (f : (String, A) => B) : ScalaAttrAccess = {
      val aa = new AttrAccessImpl()
      this.sgetPopulatedAttr.foreach (x => aa set (x, f(x, (this a x).asInstanceOf[A])))
     aa 
   }

   override def mapValues [A,B] (f : (A) => B) : Seq[B] = {
      val aa = razie.Listi[B]()
      this.sgetPopulatedAttr.foreach (x => aa append (f((this a x).asInstanceOf[A])))
     aa 
   }

   def getPopulatedAttr() : java.lang.Iterable[String] = {
      import scala.collection.JavaConversions._
      this._order;
   }

   def unpopulate (name:String) {
//      this._attrs.remove(name)
      this._order -= name
//      this._types.remove(name)
   }
   

   def tempso (name:String) : Unit = _order append name
   def tempcl () = _order.clear
}