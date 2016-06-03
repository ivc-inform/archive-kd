import com.simplesys.System.{JSAny, JSDictionaryAny}
import com.simplesys.macros.PropsToDictionary

import scala.scalajs.js;

final class $anon extends PropsToDictionary[com.simplesys.SmartClient.Control.props.MenuProps] {
    def getDictionary(clazz: com.simplesys.SmartClient.Control.props.MenuProps): JSDictionaryAny = {
        val res = js.Dictionary.empty[JSAny];
        clazz.data.foreach(((item) => res.update("data", scala.scalajs.runtime.genTraversableOnce2jsArray(item.map(((x) => x))))));
        clazz.init.foreach(((item) => res.update("init", item)));
        clazz.identifier.foreach(((item) => res.update("identifier", item)));
        clazz.addPropertiesOnCreate.foreach(((item) => res.update("addPropertiesOnCreate", item)));
        res
    }
};
