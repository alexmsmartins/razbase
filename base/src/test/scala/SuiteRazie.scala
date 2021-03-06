/**  ____    __    ____  ____  ____,,___     ____  __  __  ____
 *  (  _ \  /__\  (_   )(_  _)( ___)/ __)   (  _ \(  )(  )(  _ \           Read
 *   )   / /(__)\  / /_  _)(_  )__) \__ \    )___/ )(__)(  ) _ <     README.txt
 *  (_)\_)(__)(__)(____)(____)(____)(___/   (__)  (______)(____/    LICENSE.txt
 */
import org.scalatest.junit._
import org.scalatest.SuperSuite

/** main test suite */
class SuiteRazieIWishItWorked extends SuperSuite (
  List (
    new razie.xp.test.TestXpString,
    new razie.xp.test.TestXpScalaXml
  )
)

/** TODO this is sooooooooooooo messed up... */
class SuiteRazie () extends junit.framework.TestSuite(classOf[XNadaRazie]) {
  
  // this is where you list the tests...
   addTestSuite(classOf[com.razie.pub.base.test.AttrAccessTest])
   addTestSuite(classOf[com.razie.pub.base.test.ScalaAttrAccessTest])
   addTestSuite(classOf[razie.test.MTest])
   addTestSuite(classOf[razie.test.SMTest])
   addTestSuite(classOf[razie.test.PerfTest])
   addTestSuite(classOf[razie.xp.test.TestXpString])
   addTestSuite(classOf[razie.xp.test.TestXpScalaXml])
   addTestSuite(classOf[razie.xp.test.XpBeanTest])
   
   addTestSuite(classOf[razie.g.GRefTest])
   
   def test1() = 
     // don't touch this line
     addTest(new junit.framework.TestSuite(classOf[razie.xp.test.TestXpString]))
     
}

// this is here to convince eclipse to run as/junit...
class XNadaRazie extends junit.framework.TestCase {
 def testNada : Unit =  {}
}
